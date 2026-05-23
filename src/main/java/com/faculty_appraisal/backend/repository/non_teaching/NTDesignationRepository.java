package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NTDesignation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NTDesignationRepository extends JpaRepository<NTDesignation, UUID> {

    Optional<NTDesignation> findByName(String name);

    List<NTDesignation> findAllByOrderByIsSystemDescNameAsc();
}
