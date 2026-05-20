package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NonTeachingPartAItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NonTeachingPartAItemRepository extends JpaRepository<NonTeachingPartAItem, UUID> {

    List<NonTeachingPartAItem> findByStaffEmail(String staffEmail);

    List<NonTeachingPartAItem> findByAcademicYear(String academicYear);

    List<NonTeachingPartAItem> findByStaffEmailAndAcademicYear(
            String staffEmail,
            String academicYear
    );

    List<NonTeachingPartAItem> findByItemKey(String itemKey);

    List<NonTeachingPartAItem> findByStaffEmailAndAcademicYearAndItemKey(
            String staffEmail,
            String academicYear,
            String itemKey
    );
}