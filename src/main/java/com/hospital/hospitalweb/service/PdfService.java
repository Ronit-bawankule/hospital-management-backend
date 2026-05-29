package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Billing;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public ByteArrayInputStream generateBillPdf(
            Billing bill
    ) {

        Document document =
                new Document();

        ByteArrayOutputStream out =
                new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(
                    document,
                    out
            );

            document.open();

            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            22
                    );

            Paragraph title =
                    new Paragraph(
                            "Hospital Invoice",
                            titleFont
                    );

            title.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(title);

            document.add(
                    new Paragraph(" ")
            );

            document.add(
                    new Paragraph(
                            "Bill ID: "
                                    + bill.getId()
                    )
            );

            document.add(
                    new Paragraph(
                            "Patient: "
                                    + bill.getPatient()
                                    .getFirstName()
                                    + " "
                                    + bill.getPatient()
                                    .getLastName()
                    )
            );

            document.add(
                    new Paragraph(
                            "Amount: ₹"
                                    + bill.getAmount()
                    )
            );

            document.add(
                    new Paragraph(
                            "Payment Method: "
                                    + bill.getPaymentMethod()
                    )
            );

            document.add(
                    new Paragraph(
                            "Payment Status: "
                                    + bill.getPaymentStatus()
                    )
            );

            document.add(
                    new Paragraph(" ")
            );

            Paragraph footer =
                    new Paragraph(
                            "Thank you for visiting our hospital."
                    );

            footer.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(footer);

            document.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return new ByteArrayInputStream(
                out.toByteArray()
        );
    }
}