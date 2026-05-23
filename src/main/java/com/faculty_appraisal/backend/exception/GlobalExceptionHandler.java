package com.faculty_appraisal.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── AppException (our own business-logic errors) ──────────────────────────

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(errorBody(ex.getUserMessage(), ex.getUserMessage()));
    }

    // ── @Valid / @Validated bean-validation failures → 422 ───────────────────

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(errorBody(
                        "The request data is invalid. Please check the highlighted fields and try again.",
                        errors
                ));
    }

    // ── Malformed or missing JSON body → 400 ─────────────────────────────────

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleUnreadable(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody(
                        "The request body is malformed or missing.",
                        ex.getMessage() != null ? ex.getMessage() : "Invalid JSON"
                ));
    }

    // ── Missing required @RequestParam → 400 ─────────────────────────────────

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingParam(MissingServletRequestParameterException ex) {
        String msg = "Required parameter '" + ex.getParameterName() + "' is missing";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorBody(msg, msg));
    }

    // ── Catch-all → 500 ──────────────────────────────────────────────────────

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorBody(
                        "An unexpected error occurred. Please try again or contact support.",
                        ex.getMessage() != null ? ex.getMessage() : "Unknown error"
                ));
    }

    // ── Helper ────────────────────────────────────────────────────────────────

    private static Map<String, Object> errorBody(String userMessage, Object detail) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("user_message", userMessage);
        body.put("detail", detail);
        return body;
    }
}
