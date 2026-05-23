package com.faculty_appraisal.backend.model.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacultySnapshotDto {
    private String id;
    private String facultyEmail;
    private String academicYear;
    private Map<String, Object> payload;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ReviewSummaryDto> reviews;
}
