package com.faculty_appraisal.backend.repository.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@NoRepositoryBean
public interface BaseAppraisalRepository<T, ID> extends JpaRepository<T, ID> {

    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.facultyEmail = :email AND e.academicYear = :year")
    void deleteByFacultyEmailAndAcademicYear(@Param("email") String email, @Param("year") String year);
}
