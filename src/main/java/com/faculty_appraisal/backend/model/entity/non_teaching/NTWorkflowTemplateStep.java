package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nt_workflow_template_steps")
@Data
public class NTWorkflowTemplateStep {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private NTWorkflowTemplate template;

    @Column(name = "step_no", nullable = false)
    private int stepNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designation_id", nullable = false)
    private NTDesignation designation;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
