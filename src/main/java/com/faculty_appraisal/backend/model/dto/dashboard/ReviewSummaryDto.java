package com.faculty_appraisal.backend.model.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewSummaryDto {
    private String reviewerRole;
    private String reviewerEmail;
    private double partAScore;
    private double partBScore;
    private double totalScore;
    private Map<String, Object> sectionScores;
    private String remarks;
    private String status;
    private LocalDateTime reviewedAt;
}
