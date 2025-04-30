package com.example.api_medicale.services.consultation;

import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.entities.Consultation;
import com.example.api_medicale.mappers.IConsultationMapper;
import com.example.api_medicale.repositories.IConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService implements IConsultationService{

    private IConsultationMapper mapper;
    private IConsultationRepository repository;

    public ConsulationService(IConsultationRepository repository, IConsultationMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }
    @Override
    public List<ConsultationDto> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public ConsultationDto save(ConsultationDto dto) {
        Consultation c = repository.save(this.toEntity(dto));
        return this.toDto(c);
    }

    @Override
    public ConsultationDto findById(Long id) {
        Consultation c = repository.findById(id).orElseThrow(()-> new RuntimeException("Consultation non trouvée ") );
        return this.toDto(c);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ConsultationDto update(ConsultationDto dto) {
        Consultation existingConsultation = repository.findById(dto.getNumero())
                .orElseThrow(()-> new RuntimeException("Consulation non existante"));

        existingConsultation.setDate(dto.getDate());
        existingConsultation.setNumero(dto.getNumero());

        return this.toDto(existingConsultation);
    }

    private ConsultationDto toDto(Consultation c){
        return mapper.toDto(c);
    }
    private Consultation toEntity(ConsultationDto dto){
        return mapper.toEntity(dto);
    }
}
