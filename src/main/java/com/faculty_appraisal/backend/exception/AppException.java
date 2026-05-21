package com.faculty_appraisal.backend.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final int statusCode;
    private final String userMessage;

    public AppException(int statusCode, String userMessage) {
        super(userMessage);
        this.statusCode = statusCode;
        this.userMessage = userMessage;
    }
}

