package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

@Data
public class ModuleConfigUpdateRequest {
    private Boolean appraisalModuleEnabled;
    private Boolean selfAppraisalEnabled;
    private Boolean peerReviewEnabled;
}
