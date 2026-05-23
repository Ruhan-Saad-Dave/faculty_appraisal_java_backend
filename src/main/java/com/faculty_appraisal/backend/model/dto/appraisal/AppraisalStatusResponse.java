package com.faculty_appraisal.backend.model.dto.appraisal;

import java.util.List;
import java.util.Map;

public record AppraisalStatusResponse(
        Object declaration,
        List<Map<String, Object>> reviews
) {}