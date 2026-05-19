package com.faculty_appraisal.backend.model.entity.core;

import java.util.Set;

public class AnnouncementAudience {

    public static final Set<String> VALID_ANNOUNCEMENT_AUDIENCES = Set.of(
            "all",
            "faculty",
            "hod",
            "director",
            "dean",
            "registrar",
            "non_teaching_staff",
            "SoCSEA",
            "SoBB",
            "SoCE",
            "SoEMR",
            "SoC",
            "SoMCS",
            "SoD",
            "SoAA",
            "CISR"
    );

    private AnnouncementAudience() {
    }
}
