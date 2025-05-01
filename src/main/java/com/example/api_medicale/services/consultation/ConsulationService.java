package com.example.api_medicale.services.consultation;

import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.dto.MedicamentPrescritDTO;
import com.example.api_medicale.entities.Consultation;
import com.example.api_medicale.entities.Prescrit;
import com.example.api_medicale.mappers.IConsultationMapper;
import com.example.api_medicale.repositories.IConsultationRepository;
import com.example.api_medicale.repositories.IPrescritRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService implements IConsultationService{

    private IConsultationMapper mapper;
    private IConsultationRepository repository;
    private IPrescritRepository medPrescRepository;

    public ConsulationService(IConsultationRepository repository, IConsultationMapper mapper, IPrescritRepository medPrescRepository){
        this.mapper = mapper;
        this.repository = repository;
        this.medPrescRepository = medPrescRepository;
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
        repository.save(existingConsultation);
        return this.toDto(existingConsultation);
    }

    public List<MedicamentPrescritDTO> getMedicamentsByConsultation(Long consultationId) {
        List<Prescrit> prescriptions = medPrescRepository.findByConsultationNumero(consultationId);

        return prescriptions.stream()
                .map(p -> new MedicamentPrescritDTO(
                        p.getMedicament().getCode(),
                        p.getMedicament().getLibelle(),
                        p.getNbPrises()))
                .toList();
    }
    public Page<ConsultationDto> getConsultationsDtoByPatientId(Long patientId, Pageable pageable) {
        return repository.findByPatientId(patientId, pageable)
                .map(this::toDto);
    }

    public List<ConsultationDto> getConsultationsDtoByMedecinId(Long medecinId) {
        return repository.findByMedecinId(medecinId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private ConsultationDto toDto(Consultation c){
        return mapper.toDto(c);
    }
    private Consultation toEntity(ConsultationDto dto){
        return mapper.toEntity(dto);
    }
}
