package com.faculty_appraisal.backend.model.entity.part_b;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "book_publications")
@Data
public class BookPublication extends BasePartBModel {

    private String title;

    private String book;

    private String issn;

    private String isbn;

    private String publisher;

    private String coauthor;

    @Column(name = "first_author")
    private String firstAuthor;
}