package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.MedicineInPrescriptionDTO;
import com.example.Lesson13_.Clinic.Management.dto.PrescriptionRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Medicine;
import com.example.Lesson13_.Clinic.Management.entity.Prescription;
import com.example.Lesson13_.Clinic.Management.entity.PrescriptionDetails;
import com.example.Lesson13_.Clinic.Management.repository.MedicalFormRepository;
import com.example.Lesson13_.Clinic.Management.repository.MedicineRepository;
import com.example.Lesson13_.Clinic.Management.repository.PrescriptionDetailRepository;
import com.example.Lesson13_.Clinic.Management.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionDetailRepository prescriptionDetailRepository;
    private final MedicineRepository medicineRepository;
    private final MedicalFormRepository medicalFormRepository;
    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionDetailRepository prescriptionDetailRepository, MedicineRepository medicineRepository, MedicalFormRepository medicalFormRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionDetailRepository = prescriptionDetailRepository;
        this.medicineRepository = medicineRepository;
        this.medicalFormRepository = medicalFormRepository;
    }
    public Prescription createPrescription(PrescriptionRequestDTO dto) {
        Long medicalFormId = dto.getMedicalFormId();

        if (!medicalFormRepository.existsById(medicalFormId)) {
            throw new RuntimeException("Phiếu khám không tồn tại với ID: " + medicalFormId);
        }

        Prescription prescription = new Prescription();
        prescription.setMedicalFormId(medicalFormId);
        prescription.setTotalMedicinePrice(BigDecimal.ZERO);

        //Lưu đơn thuốc trước để có ID
        prescription = prescriptionRepository.save(prescription);

        BigDecimal total = BigDecimal.ZERO;

        for (MedicineInPrescriptionDTO m : dto.getMedicines()) {
            Medicine medicine = medicineRepository.findById(m.getMedicineId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy thuốc: " + m.getMedicineId()));

            PrescriptionDetails detail = new PrescriptionDetails();
            detail.setPrescriptionId(prescription.getId());
            detail.setMedicineId(medicine.getId());
            detail.setMedicineName(medicine.getName());
            detail.setMedicinePrice(medicine.getPrice());
            detail.setQuantity(m.getQuantity());
            detail.setDosage(m.getDosage());

            prescriptionDetailRepository.save(detail);

            total = total.add(medicine.getPrice().multiply(BigDecimal.valueOf(m.getQuantity())));
        }

        //Cập nhật lại tổng tiền
        prescription.setTotalMedicinePrice(total);
        return prescriptionRepository.save(prescription);
    }

}
