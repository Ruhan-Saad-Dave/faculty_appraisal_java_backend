package com.faculty_appraisal.backend.repository.part_b;

import com.faculty_appraisal.backend.model.entity.part_b.ResearchProposal;
import com.faculty_appraisal.backend.repository.core.BaseAppraisalRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResearchProposalRepository extends BaseAppraisalRepository<ResearchProposal, UUID> {

    List<ResearchProposal> findByFacultyEmailAndAcademicYear(String facultyEmail, String academicYear);

    Optional<ResearchProposal> findByFacultyEmailAndAcademicYearAndRowNo(String facultyEmail, String academicYear, Integer rowNo);

    List<ResearchProposal> findAllByFacultyEmailAndAcademicYearOrderByRowNoAsc(String facultyEmail, String academicYear);
}