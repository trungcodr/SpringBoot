package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.entity.MedicalForm;
import com.example.Lesson13_.Clinic.Management.dto.MedicalFormRequestDTO;
import com.example.Lesson13_.Clinic.Management.dto.ServiceInFormDTO;
import com.example.Lesson13_.Clinic.Management.entity.MedicalFormServiceDetails;
import com.example.Lesson13_.Clinic.Management.entity.Services;
import com.example.Lesson13_.Clinic.Management.repository.MedicalFormRepository;
import com.example.Lesson13_.Clinic.Management.repository.MedicalFormServiceRepository;
import com.example.Lesson13_.Clinic.Management.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MedicalFormService {
    private final MedicalFormRepository medicalFormRepository;
    private final MedicalFormServiceRepository medicalFormServiceRepository;
    private final ServiceRepository serviceRepository;


    public MedicalFormService(MedicalFormRepository medicalFormRepository, MedicalFormServiceRepository medicalFormServiceRepository, ServiceRepository serviceRepository) {
        this.medicalFormRepository = medicalFormRepository;
        this.medicalFormServiceRepository = medicalFormServiceRepository;
        this.serviceRepository = serviceRepository;
    }

    public MedicalForm createMedicalForm(MedicalFormRequestDTO dto) {
        MedicalForm form = new MedicalForm();
        form.setDoctorName(dto.getDoctorName());
        form.setPatientName(dto.getPatientName());
        form.setGender(dto.getGender());
        form.setDateOfBirth(dto.getDateOfBirth());

        BigDecimal total = BigDecimal.ZERO;

        // Lưu form trước để lấy ID
        form = medicalFormRepository.save(form);

        for (ServiceInFormDTO s : dto.getServices()) {
            Services service = serviceRepository.findById(s.getServiceId())
                    .orElseThrow(() -> new RuntimeException("Service not found: " + s.getServiceId()));

            MedicalFormServiceDetails detail = new MedicalFormServiceDetails();
            detail.setMedicalFormId(form.getId());
            detail.setServiceId(service.getId());
            detail.setServiceName(service.getName());
            detail.setServicePrice(service.getPrice());
            detail.setDosage(s.getDosage());
            detail.setInstruction(s.getInstruction());

            medicalFormServiceRepository.save(detail);


            total = total.add(service.getPrice());
        }

        form.setTotalServicePrice(total);
        return medicalFormRepository.save(form); // cập nhật tổng tiền
    }

    public List<MedicalForm> getAllForms() {
        return medicalFormRepository.findAll();
    }

}
