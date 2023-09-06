package com.example.report.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.report.domain.service.MedicalReportConsultationService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/documents")
public class MedicalReportConsultationController {

  private final MedicalReportConsultationService medicalReportConsultationService;

  public MedicalReportConsultationController(MedicalReportConsultationService medicalReportConsultationService) {
    this.medicalReportConsultationService = medicalReportConsultationService;
  }

  @GetMapping(value = "/maintainDocumentConsultation")
  @PreAuthorize("hasRole('admin')")
  public ResponseEntity<List<Object>> getAllMedicalReports() {
    return ResponseEntity.ok(medicalReportConsultationService.getAllMedicalReports());
  }

  @RolesAllowed("DOCTOR")
  @GetMapping(value = "/maintainDocumentConsultation")
  @PreAuthorize("hasRole('user')")
  public ResponseEntity<List<Object>> getMedicalReportsByFilter() {
    return null;
  }

  @RolesAllowed("DOCTOR")
  @GetMapping(value = "/maintainDocumentConsultation/{id}")
  @PreAuthorize("hasRole('user')")
  public ResponseEntity<Object> findMedicalReport(@PathVariable Long id) {
    return null;
  }

  @RolesAllowed("DOCTOR")
  @GetMapping(value = "/maintainDocumentConsultation/{id}")
  @PreAuthorize("hasRole('user')")
  public ResponseEntity<Object> registerMedicalReport(
      HttpServletRequest request,
      @RequestParam("printCPF") Boolean printCPF, 
      @RequestParam("printAddress") Boolean printAddress
    ) {
    return null;
  }

}
