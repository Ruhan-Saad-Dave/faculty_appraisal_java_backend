package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.ModuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleConfigRepository extends JpaRepository<ModuleConfig, Integer> {
}