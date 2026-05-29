package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Billing;
import com.hospital.hospitalweb.repository.BillingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    private final BillingRepository billingRepository;

    public BillingService(
            BillingRepository billingRepository
    ) {
        this.billingRepository = billingRepository;
    }

    public Billing addBill(
            Billing billing
    ) {

        return billingRepository.save(billing);
    }

    public List<Billing> getAllBills() {

        return billingRepository.findAll();
    }

    public void deleteBill(
            Long id
    ) {

        billingRepository.deleteById(id);
    }
}