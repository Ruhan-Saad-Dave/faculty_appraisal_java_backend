package com.faculty_appraisal.backend.controller;

import com.faculty_appraisal.backend.model.dto.non_teaching.*;
import com.faculty_appraisal.backend.model.entity.non_teaching.NonTeachingAppraisal;
import com.faculty_appraisal.backend.security.CurrentUser;
import com.faculty_appraisal.backend.service.NonTeachingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/non-teaching")
@RequiredArgsConstructor
public class NonTeachingController extends BaseController {

    private final NonTeachingService nonTeachingService;

    @GetMapping("/workflow-template")
    public WorkflowTemplateResponse getWorkflowTemplate(
            @RequestParam(required = false) String role
    ) {
        return nonTeachingService.getWorkflowTemplate(role);
    }

    @GetMapping("/workflow/{email}")
    public WorkflowStatusResponse getWorkflowForStaff(
            @PathVariable String email,
            @RequestParam String academicYear
    ) {
        return nonTeachingService.getWorkflowForStaff(email, academicYear, currentUser());
    }

    @GetMapping("/appraisal")
    public ResponseEntity<NonTeachingAppraisal> getMyAppraisal(
            @RequestParam("academic_year") String academicYear
    ) {
        return nonTeachingService.getMyAppraisal(academicYear, currentUser())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(null));  // match Python: returns null (200) when not found
    }

    @PutMapping("/appraisal")
    public NonTeachingAppraisal upsertMyAppraisal(
            @Valid @RequestBody NtAppraisalRequest req
    ) {
        return nonTeachingService.upsertMyAppraisal(req, currentUser());
    }

    @GetMapping("/subordinates")
    public List<NtSubordinateDto> getNtSubordinates(
            @RequestParam("academic_year") String academicYear
    ) {
        return nonTeachingService.getNtSubordinates(academicYear, currentUser());
    }

    @PutMapping("/review/{email}")
    public NonTeachingAppraisal reviewNonTeaching(
            @PathVariable String email,
            @Valid @RequestBody NtReviewRequest req
    ) {
        return nonTeachingService.reviewNonTeaching(email, req, currentUser());
    }
}
