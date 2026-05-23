package com.faculty_appraisal.backend.repository.non_teaching;

import com.faculty_appraisal.backend.model.entity.non_teaching.NTWorkflowAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NTWorkflowAssignmentRepository extends JpaRepository<NTWorkflowAssignment, UUID> {

    Optional<NTWorkflowAssignment> findByStaffEmail(String staffEmail);

    Optional<NTWorkflowAssignment> findByDepartment(String department);

    Optional<NTWorkflowAssignment> findByAppraisalRole(String appraisalRole);

    List<NTWorkflowAssignment> findAllByOrderByCreatedAtDesc();
}
