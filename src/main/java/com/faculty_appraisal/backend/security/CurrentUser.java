package com.faculty_appraisal.backend.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class CurrentUser {
    private String id;
    private String email;
    private List<String> roles;
    private String department;
    private String school;

    public boolean hasAuthorityOver(String subordinateId, String subordinateRole,
                                    String subordinateDept, String subordinateSchool) {

        List<String> lowerRoles = roles.stream().map(String::toLowerCase).toList();

        // Admin/HR/SuperAdmin see everything
        if (lowerRoles.stream().anyMatch(r -> List.of("admin","super_admin","hr").contains(r)))
            return true;

        // Self-access
        if (email.equals(subordinateId) || id.equals(subordinateId))
            return true;

        double userWeight = lowerRoles.stream()
                .mapToDouble(RoleUtils::getWeight).max().orElse(0);
        double subWeight = RoleUtils.getWeight(
                subordinateRole != null ? subordinateRole : "faculty");

        if (userWeight > subWeight) {
            if (lowerRoles.contains("vc") || lowerRoles.contains("registrar"))
                return true;

            if (lowerRoles.contains("dean")) {
                if (RoleUtils.ENGINEERING_SCHOOLS.contains(subordinateSchool))
                    return "engineering".equals(school);
                if (RoleUtils.NON_ENGINEERING_SCHOOLS.contains(subordinateSchool))
                    return "non_engineering".equals(school);
                return false; // CISR — only VC/Admin
            }

            if (lowerRoles.stream().anyMatch(r ->
                    List.of("director","section_head","reporting_officer","center_head").contains(r)))
                return Objects.equals(school, subordinateSchool);

            if (lowerRoles.contains("hod"))
                return Objects.equals(school, subordinateSchool)
                        && Objects.equals(department, subordinateDept);
        }

        return false;
    }
}

