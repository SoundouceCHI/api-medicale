package com.example.api_medicale.mappers;
import com.example.api_medicale.dto.PatientDto;
import com.example.api_medicale.entities.Patient;
//import org.mapstruct.Mapper;
//
//@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto toDto(Patient patient);
    Patient toEntity(PatientDto dto);
}
