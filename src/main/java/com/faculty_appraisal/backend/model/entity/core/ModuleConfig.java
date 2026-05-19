package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "module_config")
@Data
public class ModuleConfig {

    @Id
    private Integer id = 1;

    @Column(name = "appraisal_module_enabled", nullable = false)
    private Boolean appraisalModuleEnabled = true;

    @Column(name = "self_appraisal_enabled", nullable = false)
    private Boolean selfAppraisalEnabled = true;

    @Column(name = "peer_review_enabled", nullable = false)
    private Boolean peerReviewEnabled = false;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
