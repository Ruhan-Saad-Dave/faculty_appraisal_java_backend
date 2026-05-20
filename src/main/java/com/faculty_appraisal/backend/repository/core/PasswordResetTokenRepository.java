package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {

    Optional<PasswordResetToken> findByTokenHash(String tokenHash);

    List<PasswordResetToken> findByEmail(String email);

    List<PasswordResetToken> findByUsed(Boolean used);

    List<PasswordResetToken> findByExpiresAtBefore(LocalDateTime time);

    Optional<PasswordResetToken> findByTokenHashAndUsedFalse(String tokenHash);

    List<PasswordResetToken> findByEmailAndUsedFalse(String email);

    void deleteByExpiresAtBefore(LocalDateTime now); // cleanup expired tokens
}