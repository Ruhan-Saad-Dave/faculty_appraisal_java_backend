# Security & Authorization

The system implements a robust security layer using Spring Security and JWT.

## Authentication (JWT)
- **Stateless**: No server-side session storage.
- **JWT Content**: Tokens include user email, ID, and a list of roles.
- **Lifecycle**: Tokens are generated upon successful login and validated for every subsequent request via `JwtAuthFilter`.

## Authorization (RBAC)
Role-Based Access Control (RBAC) is enforced at multiple levels:

### 1. Endpoint Security
- Defined in `SecurityConfig.java`.
- Some endpoints require specific roles (e.g., `/admin/**` requires `ROLE_ADMIN`).

### 2. Method-Level Security
- Not explicitly used in the current version, but the foundation is set via role weights.

### 3. Hierarchical Authorization Logic
- This is the most complex part of the system, implemented in `CurrentUser.hasAuthorityOver(targetEmail, targetRole, targetDept, targetSchool)`.
- It uses a **Role Weight** system (defined in `RoleUtils`):
    - A reviewer can only see/review a subordinate if their role weight is higher than the subordinate's role weight.
    - Additional checks ensure they belong to the same Department or School (unless they are higher-level roles like Dean or VC).

## Roles and Weights
| Role | Weight | Visibility Scope |
| :--- | :--- | :--- |
| `vc` | 100 | University-wide |
| `dean` | 90 | School Group (Engg/Non-Engg) |
| `director` | 80 | School |
| `hod` | 70 | Department |
| `faculty` | 10 | Personal Only |

## Brute-Force Protection
- `LoginAttemptService` monitors failed login attempts.
- IPs or accounts are flagged if they exceed a configured number of failures within a time window.
