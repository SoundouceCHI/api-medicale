package com.example.api_medicale.services.medecin;

import com.example.api_medicale.dto.MedecinDto;
import org.springframework.data.domain.Page;


public interface IMedecinService {
    Page<MedecinDto> findAll(String search, int page, int size);
    MedecinDto save(MedecinDto dto);
    MedecinDto findById(Long id);
    void delete(Long id);
    MedecinDto update(MedecinDto dto);
}
