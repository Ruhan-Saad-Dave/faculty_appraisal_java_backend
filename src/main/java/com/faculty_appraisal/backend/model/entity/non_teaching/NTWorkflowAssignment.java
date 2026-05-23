package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nt_workflow_assignments")
@Data
public class NTWorkflowAssignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private NTWorkflowTemplate template;

    @Column(name = "staff_email", unique = true)
    private String staffEmail;

    @Column(name = "appraisal_role")
    private String appraisalRole;

    private String department;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
