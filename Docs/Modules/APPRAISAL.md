# Appraisal Lifecycle

The core of the application, managing how appraisals are created, stored, and processed.

## Key Components
- **`AppraisalController`**: Endpoints for saving drafts and submitting final appraisals.
- **`AppraisalService`**: The engine behind the appraisal workflow.
- **`AppraisalSnapshot`**: Entity for storing the full JSON payload of an appraisal.

## Workflows

### 1. Saving Drafts (Snapshots)
- As users fill out their appraisal form, the frontend sends periodic updates to be saved as a "snapshot".
- Snapshots are stored as JSONB in the database, allowing for a flexible, schema-less draft state.

### 2. Final Submission & "Shredding"
- When a user submits their appraisal:
    1. The system validates the submission window.
    2. The full JSON payload is "shredded" (normalized) into separate tables:
        - **Part A**: Teaching process, student feedback, activities, etc.
        - **Part B**: Research, publications, patents, etc.
    3. Total scores are calculated and stored in the `Declaration` entity.
    4. Attached files are mapped to `AppraisalDocument`.

### 3. Multi-Level Scoring
- Each Part A/B record can store multiple scores from different levels of the hierarchy:
    - HOD (Head of Department)
    - Center Head
    - Director
    - Dean
    - VC (Vice Chancellor)
- This allows for a granular audit trail of how scores were adjusted during the review process.

## Non-Teaching Workflows
- Non-teaching staff use a separate, template-driven workflow (`NTWorkflowInstance`).
- This involves a sequential step-by-step approval process defined in `NTWorkflowTemplate`.
