# Admin Management Module

The Admin module provides high-level administrative tools for managing the entire appraisal system, including users, configurations, workflows, and data exports.

## Key Components
- **`AdminController`**: REST endpoints for administrative tasks, secured with role-based checks.
- **`AdminService`**: The engine for user management, statistics calculation, and system configuration.
- **DTOs (`com.faculty_appraisal.backend.model.dto.admin`)**: A comprehensive set of request objects for CRUD operations on users, templates, and configurations.

## Features

### 1. Dashboard Statistics & Trends
- **Global Stats**: Provides a high-level view of registered users by role and school.
- **Submission Pipeline**: Tracks the status of appraisals (Submitted, Pending Review, etc.) for both faculty and non-teaching staff.
- **Submission Trends**: Monthly visualization of submission counts to track progress against the total registered population.

### 2. User & Access Management
- **User CRUD**: Full Create, Read, Update, and Delete operations for all user accounts.
- **Hierarchy Management**: Administrators can assign Reporting Officers and Registrars to faculty and staff.
- **Security**: The `AdminService` includes a `checkAdminPublic` helper to ensure only users with `ROLE_ADMIN` or `ROLE_SUPER_ADMIN` can access these tools.

### 3. System Configuration
- **Environment Config**: Allows editing of critical `.env` variables (Mail settings, URL configs, Feature toggles) directly from the admin panel. Changes to environment files are persisted to the `.env` file on disk.
- **Appraisal Windows**: Administrators can define and manage appraisal cycles (Academic Year, Start/End dates, and open/closed status).
- **Module Toggles**: High-level switches to enable/disable the entire appraisal module, self-appraisal, or peer reviews.

### 4. Non-Teaching Workflow Administration
This is a complex subsystem for managing the flexible approval chains used by non-teaching staff:
- **NT Designations**: Create and manage roles specific to the non-teaching workflow.
- **Workflow Templates**: Define reusable sequences of approval steps. One template can be set as the "Global Default".
- **Template Steps**: Add, remove, and reorder steps within a template. Each step is linked to a designation.
- **Assignments**: Assign specific templates to individuals, roles, or entire departments.

### 5. Data Export & Reporting
- **Submission Reports**: Filter and export appraisal submission data (including scores and status) to CSV.
- **Faculty Directory**: Export the full list of registered faculty and staff.
- **Pending List**: Quickly identify faculty members who haven't submitted their appraisals for the current cycle.

## Security & Deletion
- **Hard Delete**: Deleting a user account also triggers a cascading deletion of all their associated data (Part A/B entries, snapshots, reviews, and documents) to ensure database integrity and privacy.
- **Role Validation**: All operations validate that roles assigned to users are within the `VALID_ROLES` set defined in the system.
