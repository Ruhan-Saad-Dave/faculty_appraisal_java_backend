package com.faculty_appraisal.backend.model.entity.non_teaching;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nt_workflow_instance_steps")
@Data
public class NTWorkflowInstanceStep {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instance_id", nullable = false)
    private NTWorkflowInstance instance;

    @Column(name = "step_no", nullable = false)
    private int stepNo;

    @Column(nullable = false)
    private String designation;

    @Column(name = "reviewer_email")
    private String reviewerEmail;

    @Column(nullable = false)
    private String status = "WAITING";

    private BigDecimal score;

    private String remarks;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
