package com.faculty_appraisal.backend.model.entity.part_b;

import com.faculty_appraisal.backend.model.BaseAppraisalModel;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePartBModel extends BaseAppraisalModel {
    // Part B specific common fields (if any)
}
