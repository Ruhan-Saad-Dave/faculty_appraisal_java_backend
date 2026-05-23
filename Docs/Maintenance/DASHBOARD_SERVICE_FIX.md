# Maintenance: Dashboard Service Fix

## Issue: Payload Type Mismatch
**Date**: 2026-05-23
**File**: `src/main/java/com/faculty_appraisal/backend/service/DashboardService.java`

### The Problem
In the `getFacultySnapshot` method, there was a compilation error:
```java
dto.setPayload(snap.getPayload());
```
- `snap.getPayload()` returned a `String` (stored as JSONB in the database).
- `dto.setPayload()` expected a `Map<String, Object>`.
Java could not implicitly cast the JSON string to a Map.

### The Solution
The code was updated to explicitly deserialize the JSON string into a Map using Jackson's `ObjectMapper`.

**Changes:**
1. Added `ObjectMapper` and `TypeReference` imports.
2. Injected `ObjectMapper` into `DashboardService` via the constructor.
3. Updated the assignment logic:
```java
try {
    if (snap.getPayload() != null) {
        dto.setPayload(objectMapper.readValue(snap.getPayload(), new TypeReference<Map<String, Object>>() {}));
    }
} catch (Exception e) {
    log.error("Failed to parse snapshot payload for {}", email, e);
}
```

### Verification
- Ran `.\mvnw clean compile` to ensure the project builds successfully.
- Verified that the `payload` is now correctly mapped and returned as a structured JSON object to the frontend.
