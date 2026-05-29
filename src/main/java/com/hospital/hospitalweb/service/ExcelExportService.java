package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Patient;
import com.hospital.hospitalweb.repository.PatientRepository;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelExportService {

    private final PatientRepository patientRepository;

    public ExcelExportService(
            PatientRepository patientRepository
    ) {
        this.patientRepository = patientRepository;
    }

    public byte[] exportPatientsToExcel() {

        try {

            List<Patient> patients =
                    patientRepository.findAll();

            XSSFWorkbook workbook =
                    new XSSFWorkbook();

            Sheet sheet =
                    workbook.createSheet("Patients");

            Row header =
                    sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("First Name");
            header.createCell(2).setCellValue("Last Name");
            header.createCell(3).setCellValue("Age");
            header.createCell(4).setCellValue("Gender");
            header.createCell(5).setCellValue("Phone");
            header.createCell(6).setCellValue("Email");
            header.createCell(7).setCellValue("Blood Group");

            int rowNum = 1;

            for (Patient patient : patients) {

                Row row =
                        sheet.createRow(rowNum++);

                row.createCell(0)
                        .setCellValue(patient.getId());

                row.createCell(1)
                        .setCellValue(patient.getFirstName());

                row.createCell(2)
                        .setCellValue(patient.getLastName());

                if (patient.getAge() != null) {

                    row.createCell(3)
                            .setCellValue(patient.getAge());

                } else {

                    row.createCell(3)
                            .setCellValue("");
                }

                row.createCell(4)
                        .setCellValue(patient.getGender());

                row.createCell(5)
                        .setCellValue(patient.getPhone());

                row.createCell(6)
                        .setCellValue(patient.getEmail());

                row.createCell(7)
                        .setCellValue(patient.getBloodGroup());
            }

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            workbook.write(out);

            workbook.close();

            return out.toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to export patients",
                    e
            );
        }
    }
}