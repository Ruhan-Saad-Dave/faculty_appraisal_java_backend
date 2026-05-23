# Infrastructure & Utility Services

This document covers cross-cutting concerns like email notifications and error handling.

## 1. Email Service
- **`EmailService.java`**: A centralized service for sending system-generated emails.
- **Use Cases**:
    - Registration confirmation.
    - Password reset links.
    - Submission notifications.
- **Configuration**: SMTP settings are managed in `application.yaml`.

## 2. Global Error Handling
- **`AppException.java`**: A custom runtime exception used throughout the business logic to provide meaningful error messages and HTTP status codes.
- **`GlobalExceptionHandler.java`**: A `@ControllerAdvice` that catches exceptions globally. It ensures that the API always returns a consistent error format:
  ```json
  {
    "status": 403,
    "message": "Not authorized to view this faculty's data",
    "timestamp": "2026-05-23T16:00:00"
  }
  ```

## 3. Storage Service
- The system supports file uploads (e.g., certificates, publications).
- Metadata is stored in `AppraisalDocument`, while the physical files are handled by the storage logic (configurable for local or cloud storage).
