# System Architecture

The Faculty Appraisal Backend follows a standard 3-tier architecture, optimized for scalability and maintainability.

## 1. Presentation Layer (REST API)
- Built with Spring Web (Spring Boot).
- Exposes RESTful endpoints for the frontend application.
- Located in `com.faculty_appraisal.backend.controller`.

## 2. Business Logic Layer
- Implemented as Spring Services.
- Handles complex workflows like appraisal "shredding", hierarchical visibility, and email notifications.
- Located in `com.faculty_appraisal.backend.service`.

## 3. Data Access Layer
- Uses Spring Data JPA with Hibernate.
- Interacts with a PostgreSQL database.
- Optimized for performance using JSONB fields for flexible data storage where relational structures would be too rigid (e.g., draft snapshots).
- Located in `com.faculty_appraisal.backend.repository`.

## Key Design Patterns
- **Stateless Authentication**: Uses JWT to handle session management without server-side state.
- **DTO (Data Transfer Object)**: Decouples the API contract from the internal database schema.
- **Normalization vs. Denormalization**: 
    - **Drafts**: Stored as JSONB in `AppraisalSnapshot` for flexibility.
    - **Submitted Data**: "Shredded" into normalized tables (Part A, Part B) for granular reporting and multi-level scoring.
- **Role Hierarchy**: Implements a weight-based role system for complex authorization logic.
