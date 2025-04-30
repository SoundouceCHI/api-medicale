package com.example.api_medicale.services.consultation;

import com.example.api_medicale.dto.ConsultationDto;

import java.util.List;

public interface IConsultationService {
    List<ConsultationDto> findAll();
    ConsultationDto save(ConsultationDto dto);
    ConsultationDto findById(Long id);
    void delete(Long id);
    ConsultationDto update(ConsultationDto dto);
}
