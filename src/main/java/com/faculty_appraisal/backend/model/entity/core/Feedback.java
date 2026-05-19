package com.faculty_appraisal.backend.model.entity.core;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 80)
    private String name;

    @Column(length = 254, nullable = false)
    private String email;

    @Column(nullable = false)
    private String category;

    @Column(length = 120, nullable = false)
    private String subject;

    @Column(length = 5000, nullable = false)
    private String message;

    @Column(nullable = false)
    private String status = "new";

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent", length = 512)
    private String userAgent;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
