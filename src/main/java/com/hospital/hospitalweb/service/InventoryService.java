package com.hospital.hospitalweb.service;

import com.hospital.hospitalweb.model.Inventory;
import com.hospital.hospitalweb.repository.InventoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final MailService mailService;

    public InventoryService(
            InventoryRepository inventoryRepository,
            MailService mailService
    ) {

        this.inventoryRepository =
                inventoryRepository;

        this.mailService =
                mailService;
    }

    public Inventory addMedicine(
            Inventory inventory
    ) {

        Inventory savedMedicine =
                inventoryRepository.save(
                        inventory
                );

        if (savedMedicine.getQuantity() != null
                && savedMedicine.getQuantity() <= 10) {

            mailService.sendLowInventoryAlert(
                    savedMedicine.getMedicineName(),
                    savedMedicine.getQuantity()
            );
        }

        return savedMedicine;
    }

    public List<Inventory> getAllMedicines() {

        return inventoryRepository.findAll();
    }

    public void deleteMedicine(
            Long id
    ) {

        inventoryRepository.deleteById(id);
    }
}