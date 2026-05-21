package com.faculty_appraisal.backend.security;

import java.util.Map;
import java.util.Set;

public class RoleUtils {

    public static final Set<String> ENGINEERING_SCHOOLS =
            Set.of("SoCSEA", "SoBB", "SoCE", "SoEMR");

    public static final Set<String> NON_ENGINEERING_SCHOOLS =
            Set.of("SoCM", "SoMCS", "SoD", "SoAA");

    private static final Map<String, Double> ROLE_WEIGHTS = Map.ofEntries(
            Map.entry("faculty",            0.0),
            Map.entry("non_teaching_staff", 0.0),
            Map.entry("staff",              0.0),
            Map.entry("hod",                1.0),
            Map.entry("reporting_officer",  1.5),
            Map.entry("section_head",       2.0),
            Map.entry("director",           2.0),
            Map.entry("center_head",        2.5),
            Map.entry("dean",               3.0),
            Map.entry("registrar",          3.5),
            Map.entry("vc",                 4.0),
            Map.entry("admin",              5.0),
            Map.entry("hr",                 5.0),
            Map.entry("super_admin",        6.0)
    );

    public static double getWeight(String role) {
        return ROLE_WEIGHTS.getOrDefault(role.toLowerCase(), 0.0);
    }

    public static String getFormFamily(String school) {
        if (school == null || school.isBlank()) return "standard";
        return switch (school.strip()) {
            case "SoMCS"            -> "media";
            case "SoD", "SoAA"     -> "design";
            default                 -> "standard";
        };
    }
}

