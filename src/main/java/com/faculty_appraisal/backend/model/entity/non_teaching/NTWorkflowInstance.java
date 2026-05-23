package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "nt_workflow_instances")
@Data
public class NTWorkflowInstance {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "appraisal_id", nullable = false)
    private UUID appraisalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private NTWorkflowTemplate template;

    @Column(name = "staff_email", nullable = false)
    private String staffEmail;

    @Column(name = "academic_year", nullable = false)
    private String academicYear;

    @Column(name = "current_step")
    private Integer currentStep;

    @Column(nullable = false)
    private String status = "PENDING";

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "instance", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("stepNo ASC")
    private List<NTWorkflowInstanceStep> instanceSteps = new ArrayList<>();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
