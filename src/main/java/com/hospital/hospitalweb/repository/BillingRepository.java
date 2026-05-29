package com.hospital.hospitalweb.repository;

import com.hospital.hospitalweb.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository
        extends JpaRepository<Billing, Long> {
}