package com.example.api_medicale.services.consultation;

import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.dto.MedicamentPrescritDTO;
import com.example.api_medicale.entities.Consultation;
import com.example.api_medicale.entities.Medicament;
import com.example.api_medicale.entities.Prescrit;
import com.example.api_medicale.mappers.IConsultationMapper;
import com.example.api_medicale.repositories.IConsultationRepository;
import com.example.api_medicale.repositories.IMedicamentRepository;
import com.example.api_medicale.repositories.IPrescritRepository;
import com.example.api_medicale.tools.PrescritId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsulationService implements IConsultationService{

    private IConsultationMapper mapper;
    private IConsultationRepository repository;
    private IPrescritRepository medPrescRepository;
    private IMedicamentRepository medicamentRepository;

    public ConsulationService(IConsultationRepository repository, IConsultationMapper mapper, IPrescritRepository medPrescRepository, IMedicamentRepository medicamentRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.medPrescRepository = medPrescRepository;
        this.medicamentRepository = medicamentRepository;
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

    @Override
    public List<MedicamentPrescritDTO> getMedicamentsByConsultation(Long consultationId) {
        List<Prescrit> prescriptions = medPrescRepository.findByConsultationNumero(consultationId);

        return prescriptions.stream()
                .map(p -> new MedicamentPrescritDTO(
                        p.getMedicament().getCode(),
                        p.getMedicament().getLibelle(),
                        p.getNbPrises()))
                .toList();
    }

    @Override
    public Page<ConsultationDto> getConsultationsDtoByPatientId(Long patientId, Pageable pageable) {
        return repository.findByPatientId(patientId, pageable)
                .map(this::toDto);
    }

    @Override
    public List<ConsultationDto> getConsultationsDtoByMedecinId(Long medecinId) {
        return repository.findByMedecinId(medecinId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public ConsultationDto addPrescription(Long consultationId, MedicamentPrescritDTO prescription) {
        Consultation consultation = repository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation non trouvée"));

        Prescrit prescrit = new Prescrit();
        Medicament medicament = medicamentRepository.findById(prescription.getMedicamentId())
                .orElseThrow(() -> new RuntimeException("Medicament non trouvé"));

        PrescritId id = new PrescritId(consultation.getNumero(), medicament.getCode());

        prescrit.setId(id);
        prescrit.setConsultation(consultation);
        prescrit.setNbPrises(prescription.getNbPrises());
        prescrit.setMedicament(medicament);

        medPrescRepository.save(prescrit);
        return this.toDto(consultation);
    }

    @Override
    public ConsultationDto updatePrescription(Long consultationId, Long medicamentId, MedicamentPrescritDTO prescription) {
        PrescritId id = new PrescritId(consultationId, medicamentId);
        Prescrit prescrit = medPrescRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription non trouvée"));

        prescrit.setNbPrises(prescription.getNbPrises());
        medPrescRepository.save(prescrit);
        return this.toDto(prescrit.getConsultation());
    }

    @Override
    public void deletePrescription(Long consultationId, Long medicamentId) {
        PrescritId id = new PrescritId(consultationId, medicamentId);

        if (!medPrescRepository.existsById(id)) {
            throw new RuntimeException("Prescription inexistante");
        }

        medPrescRepository.deleteById(id);
    }

    private ConsultationDto toDto(Consultation c){
        return mapper.toDto(c);
    }
    private Consultation toEntity(ConsultationDto dto){
        return mapper.toEntity(dto);
    }
}
