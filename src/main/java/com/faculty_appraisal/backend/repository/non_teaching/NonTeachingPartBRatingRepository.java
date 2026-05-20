package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NonTeachingPartBRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NonTeachingPartBRatingRepository extends JpaRepository<NonTeachingPartBRating, UUID> {

    List<NonTeachingPartBRating> findByStaffEmail(String staffEmail);

    List<NonTeachingPartBRating> findByAcademicYear(String academicYear);

    List<NonTeachingPartBRating> findBySectionKey(String sectionKey);

    List<NonTeachingPartBRating> findByStaffEmailAndAcademicYear(
            String staffEmail,
            String academicYear
    );

    List<NonTeachingPartBRating> findByStaffEmailAndAcademicYearAndSectionKey(
            String staffEmail,
            String academicYear,
            String sectionKey
    );

    List<NonTeachingPartBRating> findByParameterNo(Integer parameterNo);

    List<NonTeachingPartBRating> findByStaffEmailAndAcademicYearOrderBySectionKeyAscParameterNoAsc(
            String staffEmail, String academicYear);

    Optional<NonTeachingPartBRating> findByStaffEmailAndAcademicYearAndSectionKeyAndParameterNo(
            String staffEmail, String academicYear, String sectionKey, Integer parameterNo);
}