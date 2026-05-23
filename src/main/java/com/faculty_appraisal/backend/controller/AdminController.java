package com.faculty_appraisal.backend.controller;

import com.faculty_appraisal.backend.model.dto.admin.*;
import com.faculty_appraisal.backend.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController extends BaseController {

    private final AdminService adminService;

    // ── Stats ─────────────────────────────────────────────────────────────────

    @GetMapping("/stats")
    public Map<String, Object> getStats(
            @RequestParam(name = "academic_year", required = false) String academicYear
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.getStats(academicYear);
    }

    // ── Env Config ────────────────────────────────────────────────────────────

    @GetMapping("/config")
    public Map<String, String> getConfig() {
        adminService.checkAdminPublic(currentUser());
        return adminService.getConfig();
    }

    @PutMapping("/config")
    public Map<String, Object> updateConfig(@RequestBody Map<String, String> data) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateConfig(data);
    }

    // ── Users ─────────────────────────────────────────────────────────────────

    @GetMapping("/users")
    public List<Map<String, Object>> listUsers(
            @RequestParam(required = false) String school,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String search
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.listUsers(school, role, search);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createUser(@Valid @RequestBody UserCreateRequest req) {
        adminService.checkAdminPublic(currentUser());
        return adminService.createUser(req);
    }

    @PutMapping("/users/{email}")
    public Map<String, Object> updateUser(
            @PathVariable String email,
            @RequestBody UserUpdateRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateUser(email, req);
    }

    @DeleteMapping("/users/{email}")
    public Map<String, String> deleteUser(@PathVariable String email) {
        adminService.checkAdminPublic(currentUser());
        return adminService.deleteUser(email);
    }

    // ── Registrars / ROs ──────────────────────────────────────────────────────

    @GetMapping("/registrars")
    public List<Map<String, Object>> listRegistrars() {
        adminService.checkAdminPublic(currentUser());
        return adminService.listRegistrars();
    }

    @GetMapping("/reporting-officers")
    public List<Map<String, Object>> listReportingOfficers() {
        adminService.checkAdminPublic(currentUser());
        return adminService.listReportingOfficers();
    }

    // ── NT Designations ───────────────────────────────────────────────────────

    @GetMapping("/nt-designations")
    public List<Map<String, Object>> listDesignations() {
        adminService.checkAdminPublic(currentUser());
        return adminService.listDesignations();
    }

    @PostMapping("/nt-designations")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createDesignation(@Valid @RequestBody DesignationCreateRequest req) {
        adminService.checkAdminPublic(currentUser());
        return adminService.createDesignation(req);
    }

    @PutMapping("/nt-designations/{id}")
    public Map<String, Object> updateDesignation(
            @PathVariable UUID id,
            @RequestBody DesignationUpdateRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateDesignation(id, req);
    }

    @DeleteMapping("/nt-designations/{id}")
    public Map<String, String> deleteDesignation(@PathVariable UUID id) {
        adminService.checkAdminPublic(currentUser());
        return adminService.deleteDesignation(id);
    }

    // ── NT Workflow Templates ─────────────────────────────────────────────────

    @GetMapping("/nt-workflow-templates")
    public List<Map<String, Object>> listTemplates() {
        adminService.checkAdminPublic(currentUser());
        return adminService.listTemplates();
    }

    @PostMapping("/nt-workflow-templates")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createTemplate(@Valid @RequestBody TemplateCreateRequest req) {
        adminService.checkAdminPublic(currentUser());
        return adminService.createTemplate(req);
    }

    @PutMapping("/nt-workflow-templates/{id}")
    public Map<String, Object> updateTemplate(
            @PathVariable UUID id,
            @RequestBody TemplateUpdateRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateTemplate(id, req);
    }

    @DeleteMapping("/nt-workflow-templates/{id}")
    public Map<String, String> deleteTemplate(@PathVariable UUID id) {
        adminService.checkAdminPublic(currentUser());
        return adminService.deleteTemplate(id);
    }

    @PutMapping("/nt-workflow-templates/{id}/set-default")
    public Map<String, String> setDefaultTemplate(@PathVariable UUID id) {
        adminService.checkAdminPublic(currentUser());
        return adminService.setDefaultTemplate(id);
    }

    // ── NT Workflow Template Steps ────────────────────────────────────────────

    @PostMapping("/nt-workflow-templates/{templateId}/steps")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> addTemplateStep(
            @PathVariable UUID templateId,
            @Valid @RequestBody StepCreateRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.addTemplateStep(templateId, req);
    }

    @PutMapping("/nt-workflow-templates/{templateId}/steps/{stepNo}")
    public Map<String, String> updateTemplateStep(
            @PathVariable UUID templateId,
            @PathVariable int stepNo,
            @RequestBody StepUpdateRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateTemplateStep(templateId, stepNo, req);
    }

    @DeleteMapping("/nt-workflow-templates/{templateId}/steps/{stepNo}")
    public Map<String, String> removeTemplateStep(
            @PathVariable UUID templateId,
            @PathVariable int stepNo
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.removeTemplateStep(templateId, stepNo);
    }

    @PutMapping("/nt-workflow-templates/{templateId}/reorder")
    public Map<String, String> reorderTemplateSteps(
            @PathVariable UUID templateId,
            @RequestBody ReorderRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.reorderTemplateSteps(templateId, req);
    }

    // ── NT Workflow Assignments ───────────────────────────────────────────────

    @GetMapping("/nt-workflow-assignments")
    public List<Map<String, Object>> listAssignments() {
        adminService.checkAdminPublic(currentUser());
        return adminService.listAssignments();
    }

    @PostMapping("/nt-workflow-assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createAssignment(@Valid @RequestBody AssignmentCreateRequest req) {
        adminService.checkAdminPublic(currentUser());
        return adminService.createAssignment(req);
    }

    @DeleteMapping("/nt-workflow-assignments/{id}")
    public Map<String, String> deleteAssignment(@PathVariable UUID id) {
        adminService.checkAdminPublic(currentUser());
        return adminService.deleteAssignment(id);
    }

    // ── Pending Faculty ───────────────────────────────────────────────────────

    @GetMapping("/pending-faculty")
    public List<Map<String, Object>> getPendingFaculty(
            @RequestParam("academic_year") String academicYear,
            @RequestParam(required = false) String school
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.getPendingFaculty(academicYear, school);
    }

    // ── Submissions ───────────────────────────────────────────────────────────

    @GetMapping("/submissions")
    public List<Map<String, Object>> listSubmissions(
            @RequestParam(required = false) String academic_year,
            @RequestParam(required = false) String school
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.listSubmissions(academic_year, school);
    }

    // ── CSV Exports ───────────────────────────────────────────────────────────

    @GetMapping("/export/submissions")
    public ResponseEntity<byte[]> exportSubmissions(
            @RequestParam(required = false) String academic_year,
            @RequestParam(required = false) String school
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.exportSubmissions(academic_year, school);
    }

    @GetMapping("/export/faculty")
    public ResponseEntity<byte[]> exportFaculty(
            @RequestParam(required = false) String school,
            @RequestParam(required = false) String role
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.exportFaculty(school, role);
    }

    // ── Trends ────────────────────────────────────────────────────────────────

    @GetMapping("/trends")
    public Map<String, Object> getTrends(
            @RequestParam(required = false) String academic_year
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.getTrends(academic_year);
    }

    // ── Appraisal Config ──────────────────────────────────────────────────────

    @GetMapping("/appraisal-config")
    public List<Map<String, Object>> listAppraisalConfigs() {
        adminService.checkAdminPublic(currentUser());
        return adminService.listAppraisalConfigs();
    }

    @PostMapping("/appraisal-config")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createAppraisalConfig(@Valid @RequestBody AppraisalConfigCreateRequest req) {
        adminService.checkAdminPublic(currentUser());
        return adminService.createAppraisalConfig(req);
    }

    @PutMapping("/appraisal-config/{academicYear}")
    public Map<String, Object> updateAppraisalConfig(
            @PathVariable String academicYear,
            @RequestBody AppraisalConfigUpdateRequest req
    ) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateAppraisalConfig(academicYear, req);
    }

    @DeleteMapping("/appraisal-config/{academicYear}")
    public Map<String, String> deleteAppraisalConfig(@PathVariable String academicYear) {
        adminService.checkAdminPublic(currentUser());
        return adminService.deleteAppraisalConfig(academicYear);
    }

    // ── Module Config ─────────────────────────────────────────────────────────

    @GetMapping("/module-config")
    public Map<String, Object> getModuleConfig() {
        adminService.checkAdminPublic(currentUser());
        return adminService.getModuleConfig();
    }

    @PutMapping("/module-config")
    public Map<String, String> updateModuleConfig(@RequestBody ModuleConfigUpdateRequest req) {
        adminService.checkAdminPublic(currentUser());
        return adminService.updateModuleConfig(req);
    }
}
