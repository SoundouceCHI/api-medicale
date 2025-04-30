package com.example.api_medicale.services.medicament;

import com.example.api_medicale.dto.MedicamentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMedicamentService {
    List<MedicamentDto> findAll();
    MedicamentDto save(MedicamentDto dto);
    MedicamentDto findById(Long id);
    void delete(Long id);
    MedicamentDto update(MedicamentDto dto);
    Page<MedicamentDto> findAll(String search, int page, int size);
}
