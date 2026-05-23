# Dashboard & Reporting

The Dashboard module provides visibility and status tracking for both faculty and reviewers.

## Key Components
- **`DashboardController`**: Endpoints for subordinate lists and faculty snapshots.
- **`DashboardService`**: Implements the complex visibility logic based on role hierarchy.

## Features

### 1. Subordinate Management (`/subordinates`)
- Reviewers (HODs, Deans, etc.) use this endpoint to see the status of appraisals for people they manage.
- **Hierarchical Visibility**:
    - **HODs**: See faculty in their Department.
    - **Directors**: See all faculty in their School.
    - **Deans**: See all faculty in their School Group (Engineering or Non-Engineering).
    - **VC/Admin**: See all faculty in the University.
- Provides a summary of Part A, Part B, and Grand Totals for each subordinate.

### 2. Faculty Detail Snapshot
- Allows reviewers to drill down into a specific faculty member's appraisal.
- Retrieves both the original submitted payload and all reviews conducted so far.

### 3. Review Summaries
- Aggregates scores and remarks from all levels of the review chain (HOD -> VC).
- Decouples the review data (`AppraisalReview`) from the submission data.
