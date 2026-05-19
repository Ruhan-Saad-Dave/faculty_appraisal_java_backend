package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products_developed")
@Data
public class ProductDeveloped extends BasePartBModel {

    private String details;

    private String usage;
}