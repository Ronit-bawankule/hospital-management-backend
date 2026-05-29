package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Billing;
import com.hospital.hospitalweb.repository.BillingRepository;
import com.hospital.hospitalweb.service.BillingService;
import com.hospital.hospitalweb.service.PdfService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin("*")
public class BillingController {

    private final BillingService billingService;

    private final BillingRepository billingRepository;

    private final PdfService pdfService;

    public BillingController(
            BillingService billingService,
            BillingRepository billingRepository,
            PdfService pdfService
    ) {

        this.billingService = billingService;

        this.billingRepository =
                billingRepository;

        this.pdfService = pdfService;
    }

    @PostMapping
    public Billing addBill(
            @RequestBody Billing billing
    ) {

        return billingService.addBill(
                billing
        );
    }

    @GetMapping
    public List<Billing> getAllBills() {

        return billingService.getAllBills();
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadBillPdf(
            @PathVariable Long id
    ) {

        Billing bill =
                billingRepository.findById(id)
                        .orElseThrow();

        ByteArrayInputStream bis =
                pdfService.generateBillPdf(bill);

        HttpHeaders headers =
                new HttpHeaders();

        headers.add(
                "Content-Disposition",
                "inline; filename=bill.pdf"
        );

        try {

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(
                            MediaType.APPLICATION_PDF
                    )
                    .body(
                            bis.readAllBytes()
                    );

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBill(
            @PathVariable Long id
    ) {

        billingService.deleteBill(id);
    }
}