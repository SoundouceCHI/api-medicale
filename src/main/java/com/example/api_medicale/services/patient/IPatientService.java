package com.example.api_medicale.services.patient;

import com.example.api_medicale.dto.PatientDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPatientService {
    List<PatientDto> findAll();
    PatientDto save(PatientDto dto);
    PatientDto findById(Long id);
    void delete(Long id);
    Page<PatientDto> findAll(String search, int page, int size);
}
