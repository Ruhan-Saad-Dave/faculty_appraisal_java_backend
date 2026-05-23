# Upload & Storage Module

The Upload & Storage module provides a centralized service for handling file uploads, supporting multiple storage backends and content-based deduplication.

## Key Components
- **`UploadController`**: REST endpoint for uploading files.
- **`StorageService`**: The engine that handles file processing, hashing, and storage logic.
- **`UploadResponse`**: DTO containing the public URL, file metadata, and deduplication status.

## Features

### 1. Flexible Storage Backends
The system supports three storage modes, configurable via environment variables or `application.yaml`:
- **Google Cloud Storage (GCS)**: The primary backend for production, using Application Default Credentials.
- **Local Storage**: A fallback mode for development or on-premise deployments.
- **Mock Mode**: Used when no bucket is configured, returning placeholder URLs.

### 2. Content-Based Deduplication
- Every uploaded file is hashed using **SHA-256**.
- The hash is used as part of the filename.
- Before writing a new file, the system checks if a file with the same hash and name already exists. If it does, it skips the upload and returns the existing URL, saving storage space.

### 3. Organized Directory Structure
Files are organized logically in the storage bucket/directory:
- **Default Path**: `faculty/{userEmail}/{hash}_{fileName}`
- **Custom Folders**: Support for specifying a custom folder via query parameters.

### 4. Security
- The `UploadController` uses the `currentUser()` context to ensure that files uploaded via the default path are siloed by the user's email address.
- File names are sanitized (spaces replaced with underscores) to prevent path injection or URL issues.

## Configuration
The following properties in `application.yaml` control the storage behavior:
- `GCP_STORAGE_BUCKET`: The name of the GCS bucket.
- `USE_LOCAL_STORAGE`: Boolean flag to enable local filesystem storage.
- `LOCAL_STORAGE_DIR`: Path to the directory where files should be saved locally.

## Usage Example
**Endpoint**: `POST /api/v1/upload`
**Payload**: `MultipartFile` (part name: `file`)
**Optional Param**: `folder` (e.g., `announcements`)

**Response**:
```json
{
  "url": "https://storage.googleapis.com/my-bucket/faculty/user@example.com/a1b2c3d4_my_cv.pdf",
  "publicId": "faculty/user@example.com/a1b2c3d4_my_cv.pdf",
  "name": "my cv.pdf",
  "type": "application/pdf",
  "deduped": false
}
```
