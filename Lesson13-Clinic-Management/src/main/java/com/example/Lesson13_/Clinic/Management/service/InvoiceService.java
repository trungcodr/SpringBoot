package com.example.Lesson13_.Clinic.Management.service;

import com.example.Lesson13_.Clinic.Management.dto.InvoiceDTO;
import com.example.Lesson13_.Clinic.Management.dto.InvoiceRequestDTO;
import com.example.Lesson13_.Clinic.Management.entity.Invoice;
import com.example.Lesson13_.Clinic.Management.entity.MedicalForm;
import com.example.Lesson13_.Clinic.Management.entity.Prescription;
import com.example.Lesson13_.Clinic.Management.repository.InvoiceRepository;
import com.example.Lesson13_.Clinic.Management.repository.MedicalFormRepository;
import com.example.Lesson13_.Clinic.Management.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class InvoiceService {
    private final MedicalFormRepository medicalFormRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(MedicalFormRepository medicalFormRepository, PrescriptionRepository prescriptionRepository, InvoiceRepository invoiceRepository) {
        this.medicalFormRepository = medicalFormRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public InvoiceDTO createInvoice(InvoiceRequestDTO request, Long accountantId) {
        // 1. Kiểm tra phiếu khám
        MedicalForm medicalForm = medicalFormRepository.findById(request.getMedicalFormId())
                .orElseThrow(() -> new RuntimeException("Phiếu khám không tồn tại"));

        // 2. Kiểm tra đơn thuốc
        Prescription prescription = prescriptionRepository.findByMedicalFormId(medicalForm.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn thuốc"));

        // 3. Kiểm tra đã thanh toán chưa
        if (invoiceRepository.existsByMedicalFormId(medicalForm.getId())) {
            throw new RuntimeException("Phiếu khám này đã được thanh toán");
        }

        // 4. Tính tổng tiền
        BigDecimal totalAmount = medicalForm.getTotalServicePrice()
                .add(prescription.getTotalMedicinePrice()); //


        // 5. Lưu invoice
        Invoice invoice = new Invoice();
        invoice.setMedicalFormId(medicalForm.getId());
        invoice.setAccountantId(accountantId);
        invoice.setTotalAmount(totalAmount);
        invoice.setPaidAt(LocalDateTime.now());
        invoice.setNote(request.getNote());

        invoice = invoiceRepository.save(invoice);

        // 6. Tự chuyển sang DTO
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setMedicalFormId(invoice.getMedicalFormId());
        dto.setAccountantId(invoice.getAccountantId());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setPaidAt(invoice.getPaidAt());
        dto.setNote(invoice.getNote());

        return dto;
    }
}
