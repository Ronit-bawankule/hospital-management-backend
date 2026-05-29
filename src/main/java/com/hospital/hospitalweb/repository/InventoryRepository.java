package com.hospital.hospitalweb.repository;

import com.hospital.hospitalweb.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository
        extends JpaRepository<Inventory, Long> {
}