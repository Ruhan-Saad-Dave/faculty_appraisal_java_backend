# Service Layer Documentation

This document explains the functionality and logic of the service layer in the Faculty Appraisal Java Backend. The service layer contains the core business logic of the application.

---

## 1. AdminService
**Responsibility:** Handles administrative operations and system configuration.

- **`checkAdminPublic(CurrentUser user)`**: Helper to verify if the logged-in user has `admin` or `super_admin` roles.
- **`getStats(String academicYear)`**: Aggregates system-wide statistics including total registered users, submissions by role, and pipeline status (teaching vs. non-teaching) for a specific year.
- **`getConfig()` / `updateConfig(Map<String, String> data)`**: Manages environment variables. `updateConfig` writes changes back to the `.env` file for persistence across restarts.
- **`listUsers(...)` / `createUser(...)` / `updateUser(...)` / `deleteUser(...)`**: CRUD operations for user profiles. `deleteUser` is comprehensive, removing all related appraisal data, documents, and reset tokens across many tables.
- **NT Workflow Management**:
  - `listDesignations` / `createDesignation` / `updateDesignation` / `deleteDesignation`: Manages designations used in Non-Teaching workflows.
  - `listTemplates` / `createTemplate` / `updateTemplate` / `deleteTemplate` / `setDefaultTemplate`: Manages workflow templates.
  - `addTemplateStep` / `updateTemplateStep` / `removeTemplateStep` / `reorderTemplateSteps`: Manages the individual steps within a workflow template.
  - `listAssignments` / `createAssignment` / `deleteAssignment`: Maps staff (by email, role, or department) to specific workflow templates.
- **`getPendingFaculty(...)`**: Identifies faculty members who have not yet submitted their appraisals for a given year.
- **`listSubmissions(...)`**: Lists all submitted declarations for review.
- **`exportSubmissions(...)` / `exportFaculty(...)`**: Generates CSV reports of submission data and user profiles.
- **`getTrends(String academicYear)`**: Calculates cumulative submission trends over months for the dashboard.
- **`getModuleConfig()` / `updateModuleConfig(...)`**: Manages global feature toggles (e.g., enabling/disabling the appraisal module).

---

## 2. AppraisalService
**Responsibility:** Core logic for the teaching faculty appraisal lifecycle.

- **`getSnapshot(email, academicYear)`**: Retrieves the latest saved draft (snapshot) for a faculty member.
- **`upsertSnapshot(email, request)`**: Saves a draft of the appraisal. It stores the JSON payload and replaces any associated documents. It prevents editing if the appraisal is already submitted.
- **`submitAppraisal(email, school, request)`**: 
  - Validates if the appraisal cycle is open.
  - **"Shreds"** the complex frontend JSON form into normalized relational tables (Part A and Part B).
  - Creates or updates a `Declaration` record to track the status.
  - Saves supporting documents.
  - Updates the final snapshot to reflect the submitted state.
- **`getStatus(email, academicYear)`**: Returns the current status of an appraisal and a summary of all reviews (scores and remarks) from HOD, Dean, etc.
- **`shredForm(...)` (Private)**: The logic that maps JSON keys to specific entity fields using reflection and a mapping of aliases. It handles various sections like Lectures, Course Files, Projects, Research Papers, etc.

---

## 3. AuthService
**Responsibility:** Manages user identity, authentication, and security.

- **`login(LoginRequest)`**: Validates credentials using `PasswordEncoder`, checks for email verification, and generates a JWT token via `JwtUtils`. Includes brute-force protection via `LoginAttemptService`.
- **`register(RegisterRequest)`**: Creates a new `FacultyProfile` with `verified=false` and sends a verification email.
- **`verifyEmail(token)`**: Validates a verification JWT and sets the user to `verified=true`.
- **`getProfile(email)` / `updateProfile(email, request)`**: Manages personal profile data.
- **`changePassword(email, request)`**: Updates password after verifying the current one.
- **`forgotPassword(request)`**: Generates a secure reset token, hashes it for the database, and sends a reset link via email.
- **`resetPassword(request)`**: Validates the reset token hash, checks expiration, and updates the user's password.

---

## 4. DashboardService
**Responsibility:** Provides aggregated views for reviewers and administrators.

- **`getSubordinates(...)`**: 
  - Resolves which staff a reviewer is authorized to see based on their role (e.g., HODs see their department, Deans see their school groups).
  - Aggregates the submission status and reviewer scores for all subordinates in a single list (`SubordinateDto`).
  - Integrates status tracking for Non-Teaching staff.
- **`getFacultySnapshot(email, academicYear, currentUser)`**: Retrieves the full appraisal data and review history for a specific faculty member, ensuring the caller has authority over them.

---

## 5. EmailService
**Responsibility:** Sending system notifications.

- **`sendVerificationEmail(toEmail, token)`**: Sends the account activation link.
- **`sendResetEmail(toEmail, resetUrl)`**: Sends the password reset link.
- Uses `JavaMailSender` to deliver HTML-formatted emails.

---

## 6. Non-TeachingService
**Responsibility:** Manages the lifecycle and custom workflows for non-teaching staff.

- **`resolveTemplate(...)` (Private)**: Logic to find the correct workflow template for a staff member, checking specific assignments first (Email -> Dept -> Role) before falling back to the system default.
- **`getWorkflowTemplate(role)`**: Returns the sequence of approval steps defined in the resolved template.
- **`getWorkflowForStaff(email, academicYear)`**: Tracks the progress of a specific staff member's appraisal through the workflow instance steps (PENDING, APPROVED, WAITING).
- **`upsertMyAppraisal(req, currentUser)`**: 
  - Handles saving and submitting the NT appraisal.
  - Automatically routes to "Pending Registrar Review" for staff who report directly to the Registrar.
  - **Instantiates a Workflow Instance** on the first submission, creating specific steps that reviewers must approve.
- **`getNtSubordinates(academicYear, currentUser)`**: Returns a list of NT staff for a reviewer, filtering so that staff only appear if the reviewer's designation matches the *current* pending workflow step.
- **`reviewNonTeaching(email, req, currentUser)`**: 
  - Records reviewer marks and remarks.
  - **Advances the Workflow**: Moves the current step to `APPROVED` and sets the next step to `PENDING`. If no steps remain, the appraisal is marked `COMPLETED`.

---

## 7. RemarksService
**Responsibility:** Handles the multi-stage review and scoring process for teaching faculty.

- **`getDraft(...)` / `saveDraft(...)`**: Allows reviewers (HOD, Director, etc.) to save progress on their reviews before finalizing.
- **`handleReview(role, email, req, currentUser)`**: 
  - **Rejection Logic**: If a reviewer rejects, it sets a specific status (e.g., "HOD Rejected") which locks the appraisal for resubmission.
  - **Scoring**: Saves `AppraisalReview` records and **updates individual scores** in the Part A/B tables (e.g., updating the `hodScore` column on a research paper row).
  - **Status Advancement**: Automatically moves the `Declaration` status to the next stage (e.g., HOD review moves it to "Pending Director Review").
  - **VC Finalization**: The VC review marks the appraisal as "Reviewed", which locks it from further edits.

---

## 8. StorageService
**Responsibility:** Manages file storage and retrieval.

- **`upload(file, folder, userEmail)`**: 
  - Calculates a SHA-256 hash of the file for deduplication.
  - Supports three modes:
    1. **Mock Mode**: Returns a placeholder URL if no storage is configured.
    2. **Local Storage**: Saves files to the local disk (standard for dev).
    3. **GCS**: Uploads files to Google Cloud Storage (standard for production/Cloud Run).
