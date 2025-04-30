package com.example.api_medicale.mappers;
import com.example.api_medicale.dto.MedecinDto;
import com.example.api_medicale.entities.Medecin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDto toDto(Medecin patient);
    Medecin toEntity(MedecinDto dto);
}
