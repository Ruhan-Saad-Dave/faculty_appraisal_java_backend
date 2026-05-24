package com.faculty_appraisal.backend.controller;

import com.faculty_appraisal.backend.model.dto.dashboard.FacultySnapshotDto;
import com.faculty_appraisal.backend.model.dto.dashboard.SubordinateDto;
import com.faculty_appraisal.backend.security.CurrentUser;
import com.faculty_appraisal.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController extends BaseController {

    private final DashboardService dashboardService;

    /**
     * Returns faculty visible to the logged-in reviewer, with their submission
     * and review totals for the given academic year.
     *
     * Query params:
     *   academicYear   (required)
     *   reviewerSchool / reviewerDepartment  — fallback when the JWT lacks school/dept
     *   schools        — comma-separated school filter (admin / vc only)
     */
    @GetMapping("/subordinates")
    public List<SubordinateDto> getSubordinates(
            @RequestParam("academic_year") String academicYear,
            @RequestParam(value = "reviewer_school", required = false) String reviewerSchool,
            @RequestParam(value = "reviewer_department", required = false) String reviewerDepartment,
            @RequestParam(required = false) String schools
    ) {
        CurrentUser user = currentUser();
        return dashboardService.getSubordinates(user, academicYear, reviewerSchool, reviewerDepartment, schools);
    }

    /**
     * Returns a specific faculty member's appraisal snapshot and all reviewer
     * scores. Requires the caller to have authority over that faculty member.
     */
    @GetMapping("/faculty/{email}")
    public FacultySnapshotDto getFacultySnapshot(
            @PathVariable String email,
            @RequestParam("academic_year") String academicYear
    ) {
        return dashboardService.getFacultySnapshot(email, academicYear, currentUser());
    }
}
