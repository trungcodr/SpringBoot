package com.example.Lesson13_.Clinic.Management.controller;

import com.example.Lesson13_.Clinic.Management.dto.MedicineDTO;
import com.example.Lesson13_.Clinic.Management.dto.ServiceDTO;
import com.example.Lesson13_.Clinic.Management.entity.Medicine;
import com.example.Lesson13_.Clinic.Management.entity.Services;
import com.example.Lesson13_.Clinic.Management.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    private final CatalogService catalogService;
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("/services")
    public ResponseEntity<Services> createService(@RequestBody ServiceDTO serviceDTO) {
        return ResponseEntity.ok(catalogService.addService(serviceDTO));
    }

    @GetMapping("/services")
    public ResponseEntity<List<Services>> getAllServices() {
        return ResponseEntity.ok(catalogService.getAllServices());
    }

    @PostMapping("/medicines")
    public ResponseEntity<Medicine> createMedicine(@RequestBody MedicineDTO medicineDTO) {
        return ResponseEntity.ok(catalogService.addMedicine(medicineDTO));
    }

    @GetMapping("/medicines")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(catalogService.getAllMedicines());
    }
}
