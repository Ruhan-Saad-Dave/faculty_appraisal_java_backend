package com.faculty_appraisal.backend.controller;

import com.faculty_appraisal.backend.model.dto.appraisal.AppraisalStatusResponse;
import com.faculty_appraisal.backend.model.dto.appraisal.SnapshotRequest;
import com.faculty_appraisal.backend.model.dto.appraisal.SubmitAppraisalRequest;
import com.faculty_appraisal.backend.security.CurrentUser;
import com.faculty_appraisal.backend.service.AppraisalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/appraisal")
@RequiredArgsConstructor
public class AppraisalController extends BaseController {

    private final AppraisalService appraisalService;

    @GetMapping("/snapshot")
    public ResponseEntity<?> getSnapshot(@RequestParam("academic_year") String academicYear) {
        return ResponseEntity.ok(
                appraisalService.getSnapshot(currentUser().getEmail(), academicYear)
                        .orElse(null)
        );
    }

    @PutMapping("/snapshot")
    public ResponseEntity<Map<String, String>> upsertSnapshot(
            @Valid @RequestBody SnapshotRequest request) {
        return ResponseEntity.ok(
                appraisalService.upsertSnapshot(currentUser().getEmail(), request)
        );
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitAppraisal(
            @RequestBody SubmitAppraisalRequest request) {
        CurrentUser user = currentUser();
        return ResponseEntity.ok(
                appraisalService.submitAppraisal(user.getEmail(), user.getSchool(), request)
        );
    }

    @GetMapping("/status")
    public ResponseEntity<AppraisalStatusResponse> getStatus(
            @RequestParam("academic_year") String academicYear) {
        return ResponseEntity.ok(
                appraisalService.getStatus(currentUser().getEmail(), academicYear)
        );
    }
}

