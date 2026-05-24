package com.faculty_appraisal.backend.repository.part_a;

import com.faculty_appraisal.backend.model.entity.part_a.DepartmentActivity;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentActivityRepository extends BaseAppraisalRepository<DepartmentActivity, UUID> {

    List<DepartmentActivity> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    List<DepartmentActivity> findByRowNo(Integer rowNo);

    Optional<DepartmentActivity> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<DepartmentActivity> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}