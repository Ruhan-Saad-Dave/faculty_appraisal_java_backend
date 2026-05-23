# Authentication & User Management

This module handles the lifecycle of user accounts and secure access to the system.

## Key Components
- **`AuthController`**: REST endpoints for register, login, password reset, and profile updates.
- **`AuthService`**: Contains logic for credential validation, password hashing, and token generation.
- **`FacultyProfileRepository`**: Manages `FacultyProfile` entities (the main user record).

## Features

### 1. User Registration
- Users register with their basic details, school, department, and role.
- Support for email verification (via `EmailService`).

### 2. Secure Login
- Authenticates users against stored hashes.
- **Brute-force protection**: `LoginAttemptService` tracks failed attempts and temporarily blocks IPs or accounts after a threshold.
- Returns a JWT (JSON Web Token) containing user identity and roles.

### 3. Password Management
- **Reset Password**: Uses a token-based system (`PasswordResetToken`).
- **Change Password**: Allows authenticated users to update their credentials.

### 4. Profile Management
- Users can view and update their profile information.
- Authorization checks ensure users can only update their own data.
