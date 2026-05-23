package com.faculty_appraisal.backend.model.entity.core;

import com.faculty_appraisal.backend.model.BaseAppraisalModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "appraisal_documents")
@Data
@EqualsAndHashCode(callSuper = true)
public class AppraisalDocument extends BaseAppraisalModel {

    @Column(nullable = false)
    private String section;

    @Column(name = "doc_key")
    private String docKey;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "storage_path")
    private String storagePath;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt = LocalDateTime.now();
}
