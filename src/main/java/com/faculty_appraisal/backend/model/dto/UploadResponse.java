package com.faculty_appraisal.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadResponse {
    private String url;
    private String publicId;
    private String name;
    private String type;
    private Boolean deduped;
}
