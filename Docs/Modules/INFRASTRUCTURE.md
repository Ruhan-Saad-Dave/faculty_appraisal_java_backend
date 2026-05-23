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
    "user_message": "The request data is invalid. Please check the highlighted fields and try again.",
    "detail": ["field: message"]
  }
  ```
- **Error Categories**:
    - **422 Unprocessable Entity**: Validation failures (via `@Valid`).
    - **400 Bad Request**: Malformed JSON or missing required parameters.
    - **AppException**: Business logic errors with custom status codes.
    - **500 Internal Server Error**: Catch-all for unexpected exceptions.

## 3. System Health
- **`HealthController.java`**: Provides a lightweight health check endpoint.
- **`GET /`**: Returns the API status and version. Used for monitoring and load balancer health checks.
  ```json
  {
    "message": "Faculty Appraisal API is running!",
    "version": "2.0.0"
  }
  ```

## 4. Storage Service
- The system supports file uploads (e.g., certificates, publications).
- Metadata is stored in `AppraisalDocument`, while the physical files are handled by the storage logic (configurable for local or cloud storage).
