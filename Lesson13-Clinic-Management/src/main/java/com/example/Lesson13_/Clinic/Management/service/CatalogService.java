package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.MedicineDTO;
import com.example.Lesson13_.Clinic.Management.dto.ServiceDTO;
import com.example.Lesson13_.Clinic.Management.entity.Medicine;
import com.example.Lesson13_.Clinic.Management.entity.Services;
import com.example.Lesson13_.Clinic.Management.repository.MedicineRepository;
import com.example.Lesson13_.Clinic.Management.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private final ServiceRepository serviceRepository;
    private final MedicineRepository medicineRepository;
    public CatalogService(ServiceRepository serviceRepository, MedicineRepository medicineRepository) {
        this.serviceRepository = serviceRepository;
        this.medicineRepository = medicineRepository;
    }

    public Services addService(ServiceDTO serviceDTO) {
        Services services = new Services();
        services.setName(serviceDTO.getName());
        services.setPrice(serviceDTO.getPrice());
        services.setDescription(serviceDTO.getDescription());
        return serviceRepository.save(services);
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Medicine addMedicine(MedicineDTO medicineDTO) {
        Medicine medicine = new Medicine();
        medicine.setName(medicineDTO.getName());
        medicine.setUnit(medicineDTO.getUnit());
        medicine.setPrice(medicineDTO.getPrice());
        medicine.setDescription(medicineDTO.getDescription());
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
}
