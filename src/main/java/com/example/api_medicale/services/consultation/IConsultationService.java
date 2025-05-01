package com.example.api_medicale.services.consultation;

import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.dto.MedicamentPrescritDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConsultationService {
    List<ConsultationDto> findAll();
    ConsultationDto save(ConsultationDto dto);
    ConsultationDto findById(Long id);
    void delete(Long id);
    ConsultationDto update(ConsultationDto dto);
    Page<ConsultationDto> getConsultationsDtoByPatientId(Long patientId, Pageable pageable);
    List<ConsultationDto> getConsultationsDtoByMedecinId(Long medecinId);
    List<MedicamentPrescritDTO> getMedicamentsByConsultation(Long consultationId);
    ConsultationDto addPrescription(Long consultationId, MedicamentPrescritDTO prescription);
    ConsultationDto updatePrescription(Long consultationId, Long medicamentId, MedicamentPrescritDTO prescription);
    void deletePrescription(Long consultationId, Long medicamentId);
}
