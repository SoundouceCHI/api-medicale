package com.example.api_medicale.mappers;
import com.example.api_medicale.dto.MedecinDto;
import com.example.api_medicale.entities.Medecin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMedecinMapper {
    MedecinDto toDto(Medecin medecin);
    Medecin toEntity(MedecinDto dto);
}
