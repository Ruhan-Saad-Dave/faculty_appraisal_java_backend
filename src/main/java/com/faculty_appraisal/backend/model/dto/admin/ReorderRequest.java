package com.faculty_appraisal.backend.model.dto.admin;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReorderRequest {
    private List<Map<String, Integer>> steps;
}
