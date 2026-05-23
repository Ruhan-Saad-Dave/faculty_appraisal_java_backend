package com.faculty_appraisal.backend.model.dto.dashboard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubordinateDto {
    private String email;
    private String name;
    private String department;
    private String school;
    private String appraisalRole;
    private String designation;

    // Submission state
    private String status = "pending";
    private LocalDateTime submittedAt;
    private double partATotal;
    private double partBTotal;
    private double grandTotal;

    // HOD review
    private double hodTotal;
    private double hodPartA;
    private double hodPartB;
    private String hodRemarks = "";

    // Center Head review
    private double centerHeadTotal;
    private double centerHeadPartA;
    private double centerHeadPartB;
    private String centerHeadRemarks = "";

    // Director review
    private double directorTotal;
    private double directorPartA;
    private double directorPartB;
    private String directorRemarks = "";

    // Dean review
    private double deanTotal;
    private double deanPartA;
    private double deanPartB;
    private String deanRemarks = "";

    // VC review
    private double vcTotal;
    private double vcPartA;
    private double vcPartB;
    private String vcRemarks = "";
}
