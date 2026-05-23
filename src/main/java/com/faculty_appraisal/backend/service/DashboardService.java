package com.faculty_appraisal.backend.service;

import com.faculty_appraisal.backend.exception.AppException;
import com.faculty_appraisal.backend.model.dto.dashboard.FacultySnapshotDto;
import com.faculty_appraisal.backend.model.dto.dashboard.ReviewSummaryDto;
import com.faculty_appraisal.backend.model.dto.dashboard.SubordinateDto;
import com.faculty_appraisal.backend.model.entity.core.*;
import com.faculty_appraisal.backend.model.entity.non_teaching.NonTeachingAppraisal;
import com.faculty_appraisal.backend.repository.core.*;
import com.faculty_appraisal.backend.repository.non_teaching.NonTeachingAppraisalRepository;
import com.faculty_appraisal.backend.security.CurrentUser;
import com.faculty_appraisal.backend.security.RoleUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardService {

    private static final Set<String> NT_ROLES = Set.of("non_teaching_staff", "reporting_officer", "registrar");

    private final FacultyProfileRepository facultyRepo;
    private final DeclarationRepository declarationRepo;
    private final AppraisalReviewRepository reviewRepo;
    private final AppraisalSnapshotRepository snapshotRepo;
    private final NonTeachingAppraisalRepository ntAppraisalRepo;
    private final ObjectMapper objectMapper;

    // ── GET /subordinates ────────────────────────────────────────────────────

    public List<SubordinateDto> getSubordinates(
            CurrentUser currentUser,
            String academicYear,
            String reviewerSchool,
            String reviewerDepartment,
            String schoolsParam
    ) {
        String effectiveSchool = currentUser.getSchool() != null
                ? currentUser.getSchool() : reviewerSchool;
        String effectiveDept = currentUser.getDepartment() != null
                ? currentUser.getDepartment() : reviewerDepartment;

        List<FacultyProfile> facultyList = resolveFacultyList(
                currentUser, effectiveSchool, effectiveDept, schoolsParam);

        if (facultyList.isEmpty()) return List.of();

        List<String> emails = facultyList.stream()
                .map(FacultyProfile::getEmail)
                .collect(Collectors.toList());

        // Batch-load declarations and reviews
        Map<String, Declaration> declByEmail = declarationRepo
                .findByFacultyEmailInAndAcademicYear(emails, academicYear)
                .stream()
                .collect(Collectors.toMap(Declaration::getFacultyEmail, d -> d));

        Map<String, List<AppraisalReview>> reviewsByEmail = reviewRepo
                .findByFacultyEmailInAndAcademicYear(emails, academicYear)
                .stream()
                .collect(Collectors.groupingBy(AppraisalReview::getFacultyEmail));

        List<SubordinateDto> result = facultyList.stream()
                .map(f -> buildSubordinateDto(f, declByEmail.get(f.getEmail()),
                        reviewsByEmail.getOrDefault(f.getEmail(), List.of())))
                .collect(Collectors.toList());

        // Overlay NT staff statuses
        List<String> ntEmails = result.stream()
                .filter(s -> NT_ROLES.contains(s.getAppraisalRole()))
                .map(SubordinateDto::getEmail)
                .collect(Collectors.toList());

        if (!ntEmails.isEmpty()) {
            Map<String, NonTeachingAppraisal> ntMap = ntAppraisalRepo
                    .findByStaffEmailInAndAcademicYear(ntEmails, academicYear)
                    .stream()
                    .collect(Collectors.toMap(NonTeachingAppraisal::getStaffEmail, a -> a));

            result.forEach(sub -> {
                NonTeachingAppraisal nt = ntMap.get(sub.getEmail());
                if (nt != null) {
                    sub.setStatus(nt.getStatus());
                    sub.setSubmittedAt(nt.getSubmittedAt());
                }
            });
        }

        return result;
    }

    private List<FacultyProfile> resolveFacultyList(
            CurrentUser user, String effectiveSchool, String effectiveDept, String schoolsParam
    ) {
        List<String> roles = user.getRoles().stream()
                .map(String::toLowerCase)
                .toList();

        if (roles.stream().anyMatch(r -> List.of("super_admin", "admin").contains(r))) {
            if (schoolsParam != null && !schoolsParam.isBlank()) {
                List<String> schoolList = Arrays.stream(schoolsParam.split(","))
                        .map(String::trim).collect(Collectors.toList());
                return facultyRepo.findBySchoolIn(schoolList);
            }
            return facultyRepo.findAll();
        }

        if (roles.contains("vc") || roles.contains("registrar")) {
            if (schoolsParam != null && !schoolsParam.isBlank()) {
                List<String> schoolList = Arrays.stream(schoolsParam.split(","))
                        .map(String::trim).collect(Collectors.toList());
                return facultyRepo.findBySchoolIn(schoolList);
            }
            return facultyRepo.findAll();
        }

        if (roles.contains("dean")) {
            if ("engineering".equals(effectiveSchool)
                    || RoleUtils.ENGINEERING_SCHOOLS.contains(effectiveSchool)) {
                return facultyRepo.findBySchoolIn(RoleUtils.ENGINEERING_SCHOOLS);
            }
            if ("non_engineering".equals(effectiveSchool)
                    || RoleUtils.NON_ENGINEERING_SCHOOLS.contains(effectiveSchool)) {
                return facultyRepo.findBySchoolIn(RoleUtils.NON_ENGINEERING_SCHOOLS);
            }
            log.warn("Dean {} has unrecognised school value '{}' — returning empty", user.getEmail(), effectiveSchool);
            return List.of();
        }

        if (roles.contains("center_head")) {
            return facultyRepo.findBySchool("CISR");
        }

        if (roles.stream().anyMatch(r -> List.of("director", "reporting_officer").contains(r))) {
            return facultyRepo.findBySchool(effectiveSchool);
        }

        if (roles.contains("hod")) {
            return facultyRepo.findBySchoolAndDepartment(effectiveSchool, effectiveDept);
        }

        return List.of();
    }

    private SubordinateDto buildSubordinateDto(
            FacultyProfile f, Declaration decl, List<AppraisalReview> reviews
    ) {
        SubordinateDto dto = new SubordinateDto();
        dto.setEmail(f.getEmail());
        dto.setName(f.getFullName());
        dto.setDepartment(f.getDepartment());
        dto.setSchool(f.getSchool());
        dto.setAppraisalRole(f.getAppraisalRole());
        dto.setDesignation(f.getDesignation());

        if (decl != null) {
            dto.setStatus(decl.getStatus());
            dto.setSubmittedAt(decl.getSubmittedAt());
            dto.setPartATotal(decl.getPartATotal() != null ? decl.getPartATotal().doubleValue() : 0);
            dto.setPartBTotal(decl.getPartBTotal() != null ? decl.getPartBTotal().doubleValue() : 0);
            dto.setGrandTotal(decl.getGrandTotal() != null ? decl.getGrandTotal().doubleValue() : 0);
        }

        for (AppraisalReview rev : reviews) {
            double total = rev.getTotalScore() != null ? rev.getTotalScore().doubleValue() : 0;
            double partA = rev.getPartAScore() != null ? rev.getPartAScore().doubleValue() : 0;
            double partB = rev.getPartBScore() != null ? rev.getPartBScore().doubleValue() : 0;
            String remarks = rev.getRemarks() != null ? rev.getRemarks() : "";

            switch (rev.getReviewerRole()) {
                case "hod" -> {
                    dto.setHodTotal(total); dto.setHodPartA(partA);
                    dto.setHodPartB(partB); dto.setHodRemarks(remarks);
                }
                case "center_head" -> {
                    dto.setCenterHeadTotal(total); dto.setCenterHeadPartA(partA);
                    dto.setCenterHeadPartB(partB); dto.setCenterHeadRemarks(remarks);
                }
                case "director" -> {
                    dto.setDirectorTotal(total); dto.setDirectorPartA(partA);
                    dto.setDirectorPartB(partB); dto.setDirectorRemarks(remarks);
                }
                case "dean" -> {
                    dto.setDeanTotal(total); dto.setDeanPartA(partA);
                    dto.setDeanPartB(partB); dto.setDeanRemarks(remarks);
                }
                case "vc" -> {
                    dto.setVcTotal(total); dto.setVcPartA(partA);
                    dto.setVcPartB(partB); dto.setVcRemarks(remarks);
                }
            }
        }

        return dto;
    }

    // ── GET /faculty/{email} ─────────────────────────────────────────────────

    public FacultySnapshotDto getFacultySnapshot(String email, String academicYear, CurrentUser currentUser) {
        FacultyProfile target = facultyRepo.findByEmail(email)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND.value(), "Faculty not found"));

        if (!currentUser.hasAuthorityOver(email, target.getAppraisalRole(),
                target.getDepartment(), target.getSchool())) {
            throw new AppException(HttpStatus.FORBIDDEN.value(),
                    "Not authorized to view this faculty's data");
        }

        List<ReviewSummaryDto> reviews = reviewRepo
                .findByFacultyEmailAndAcademicYear(email, academicYear)
                .stream()
                .map(this::toReviewSummaryDto)
                .collect(Collectors.toList());

        Optional<AppraisalSnapshot> snapshotOpt = snapshotRepo
                .findByFacultyEmailAndAcademicYear(email, academicYear);

        FacultySnapshotDto dto = new FacultySnapshotDto();
        dto.setReviews(reviews);

        if (snapshotOpt.isPresent()) {
            AppraisalSnapshot snap = snapshotOpt.get();
            dto.setId(snap.getId().toString());
            dto.setFacultyEmail(snap.getFacultyEmail());
            dto.setAcademicYear(snap.getAcademicYear());
            try {
                if (snap.getPayload() != null) {
                    dto.setPayload(objectMapper.readValue(snap.getPayload(), new TypeReference<Map<String, Object>>() {}));
                }
            } catch (Exception e) {
                log.error("Failed to parse snapshot payload for {}", email, e);
            }
            dto.setCreatedAt(snap.getCreatedAt());
            dto.setUpdatedAt(snap.getUpdatedAt());
        }

        return dto;
    }

    private ReviewSummaryDto toReviewSummaryDto(AppraisalReview r) {
        ReviewSummaryDto dto = new ReviewSummaryDto();
        dto.setReviewerRole(r.getReviewerRole());
        dto.setReviewerEmail(r.getReviewerEmail());
        dto.setPartAScore(r.getPartAScore() != null ? r.getPartAScore().doubleValue() : 0);
        dto.setPartBScore(r.getPartBScore() != null ? r.getPartBScore().doubleValue() : 0);
        dto.setTotalScore(r.getTotalScore() != null ? r.getTotalScore().doubleValue() : 0);
        dto.setSectionScores(r.getSectionScores() != null ? r.getSectionScores() : Map.of());
        dto.setRemarks(r.getRemarks());
        dto.setStatus(r.getStatus());
        dto.setReviewedAt(r.getReviewedAt());
        return dto;
    }
}
