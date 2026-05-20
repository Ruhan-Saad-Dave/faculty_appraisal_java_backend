package com.faculty_appraisal.backend.repository.core;

import com.faculty_appraisal.backend.model.entity.core.FormSectionDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormSectionDefinitionRepository extends JpaRepository<FormSectionDefinition, String> {

    List<FormSectionDefinition> findByFormFamily(String formFamily);

    List<FormSectionDefinition> findByPart(String part);

    List<FormSectionDefinition> findByFormFamilyAndPart(
            String formFamily,
            String part
    );

    Optional<FormSectionDefinition> findBySectionKey(String sectionKey);

    List<FormSectionDefinition> findByStorageTable(String storageTable);

    List<FormSectionDefinition> findAll(); // used when formFamily is null

    Optional<FormSectionDefinition> findByCodeAndFormFamilyAndSectionKey(
            String code, String formFamily, String sectionKey);
}