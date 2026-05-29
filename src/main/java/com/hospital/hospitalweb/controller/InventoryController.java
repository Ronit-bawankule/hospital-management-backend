package com.hospital.hospitalweb.controller;

import com.hospital.hospitalweb.model.Inventory;
import com.hospital.hospitalweb.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(
            InventoryService inventoryService
    ) {
        this.inventoryService =
                inventoryService;
    }

    @PostMapping
    public Inventory addMedicine(
            @RequestBody Inventory inventory
    ) {

        return inventoryService.addMedicine(
                inventory
        );
    }

    @GetMapping
    public List<Inventory> getAllMedicines() {

        return inventoryService
                .getAllMedicines();
    }

    @DeleteMapping("/{id}")
    public void deleteMedicine(
            @PathVariable Long id
    ) {

        inventoryService.deleteMedicine(id);
    }
}