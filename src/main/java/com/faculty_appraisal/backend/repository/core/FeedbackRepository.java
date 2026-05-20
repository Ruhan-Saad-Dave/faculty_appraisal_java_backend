package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    List<Feedback> findByEmail(String email);

    List<Feedback> findByCategory(String category);

    List<Feedback> findByStatus(String status);

    List<Feedback> findByCategoryAndStatus(
            String category,
            String status
    );

    List<Feedback> findByEmailAndStatus(
            String email,
            String status
    );
}