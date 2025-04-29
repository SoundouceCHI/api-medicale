package com.example.api_medicale.services.patient;

import com.example.api_medicale.dto.PatientDto;
import com.example.api_medicale.entities.Patient;
import com.example.api_medicale.repositories.IPatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService implements IPatientService {
    private final IPatientRepository repository;

    public PatientService(IPatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PatientDto> findAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public PatientDto save(PatientDto dto) {
        Patient entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    @Override
    public PatientDto findById(Long id) {
        Patient p = repository.findById(id).orElseThrow(() -> new RuntimeException("Patient introuvable"));
        return toDTO(p);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PatientDto toDTO(Patient p) {
        PatientDto dto = new PatientDto();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setPrenom(p.getPrenom());
        dto.setNumSecuSoc(p.getNumSecuSoc());
        return dto;
    }

    private Patient toEntity(PatientDto dto) {
        Patient p = new Patient();
        p.setId(dto.getId());
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setNumSecuSoc(dto.getNumSecuSoc());
        return p;
    }
    @Override
    public Page<PatientDto> findAll(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patientsPage = repository.findByNomContainingIgnoreCaseOrNumSecuSocContaining(search, search, pageable);
        return patientsPage.map(this::toDTO);
    }
}
