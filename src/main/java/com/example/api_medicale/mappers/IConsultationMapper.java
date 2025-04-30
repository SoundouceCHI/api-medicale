package com.example.api_medicale.mappers;

import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.entities.Consultation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IConsultationMapper {
    ConsultationDto toDto(Consultation consultation);
    Consultation toEntity(ConsultationDto dto);
}
