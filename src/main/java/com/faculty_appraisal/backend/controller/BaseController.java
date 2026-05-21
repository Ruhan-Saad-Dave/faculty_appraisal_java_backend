package com.faculty_appraisal.backend.controller;

import com.faculty_appraisal.backend.security.CurrentUser;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected CurrentUser currentUser() {
        return (CurrentUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
