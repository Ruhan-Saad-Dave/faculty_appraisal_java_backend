package com.faculty_appraisal.backend.model.entity.part_a;

import com.faculty_appraisal.backend.model.BaseAppraisalModel;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePartAModel extends BaseAppraisalModel {
    // Part A specific common fields (if any)
}
