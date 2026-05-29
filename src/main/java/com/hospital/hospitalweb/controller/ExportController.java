package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.service.ExcelExportService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
@CrossOrigin("*")
public class ExportController {

    private final ExcelExportService excelExportService;

    public ExportController(
            ExcelExportService excelExportService
    ) {
        this.excelExportService =
                excelExportService;
    }

    @GetMapping("/test")
    public String test() {

        return "EXPORT WORKING";
    }

    @GetMapping("/patients")
    public ResponseEntity<byte[]> exportPatients() {

        byte[] excelFile =
                excelExportService
                        .exportPatientsToExcel();

        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=patients.xlsx"
                )
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )
                .body(excelFile);
    }
}