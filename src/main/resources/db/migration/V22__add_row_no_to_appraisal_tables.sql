-- V22: Add row_no column to all Part A and Part B appraisal tables.
-- These tables were created by the Python backend which did not have this column.
-- The Java backend's BaseAppraisalModel maps row_no on every entity.

ALTER TABLE teaching_process           ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE course_files               ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE innovative_teaching        ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE projects_guided            ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE qualification_enhancement  ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE student_feedback           ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE department_activities      ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE university_activities      ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE social_contributions       ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE industry_connect           ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE acr_scores                 ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE journal_publications       ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE popular_writings           ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE book_publications          ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE ict_pedagogy               ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE research_guidance          ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE research_projects          ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE external_research_projects ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE ipr_records                ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE patents                    ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE awards                     ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE conferences                ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE research_proposals         ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE products_developed         ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE self_development           ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
ALTER TABLE industrial_training        ADD COLUMN IF NOT EXISTS row_no INTEGER DEFAULT 1;
