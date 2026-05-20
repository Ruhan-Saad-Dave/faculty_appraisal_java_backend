package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.ProductDeveloped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductDevelopedRepository extends JpaRepository<ProductDeveloped, UUID> {

    List<ProductDeveloped> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<ProductDeveloped> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<ProductDeveloped> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}