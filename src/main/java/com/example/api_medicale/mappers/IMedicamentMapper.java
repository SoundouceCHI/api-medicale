package com.example.api_medicale.mappers;

import com.example.api_medicale.dto.MedicamentDto;
import com.example.api_medicale.entities.Medicament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMedicamentMapper {
    MedicamentDto toDto(Medicament medicament);
    Medicament toEntity(MedicamentDto dto);
}
