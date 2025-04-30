package com.example.api_medicale.services.medecin;

import com.example.api_medicale.dto.MedecinDto;
import com.example.api_medicale.entities.Medecin;
import com.example.api_medicale.mappers.IMedecinMapper;
import com.example.api_medicale.repositories.IMedecinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MedecinService implements IMedecinService{
    private IMedecinRepository repository;
    private IMedecinMapper mapper;

    public MedecinService(IMedecinRepository repository, IMedecinMapper mapper){
        this.repository= repository;
        this.mapper = mapper;
    }
    @Override
    public Page<MedecinDto> findAll(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Medecin> medecinsPage = repository.findByNomContainingIgnoreCaseOrMatriculeContaining(search, search, pageable);
        return medecinsPage.map(this::toDTO);
    }

    @Override
    public MedecinDto save(MedecinDto dto) {
        Medecin m =  this.toEntity(dto);
        return this.toDTO(repository.save(m));
    }

    @Override
    public MedecinDto findById(Long id) {
        Medecin m =  repository.findById(id).orElseThrow(() -> new RuntimeException("Medecin introuvable"));
        return this.toDTO(m);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MedecinDto update(MedecinDto dto) {
        Medecin existingMedecin = repository.findById(dto.getId()).
                orElseThrow(() -> new EntityNotFoundException("Medecin non trouvé avec l'id: " + dto.getId()));
        existingMedecin.setId(dto.getId());
        existingMedecin.setDateCreation(dto.getDateCreation());
        existingMedecin.setNom(dto.getNom());
        existingMedecin.setPrenom(dto.getPrenom());
        existingMedecin.setMatricule(dto.getMatricule());

        Medecin updated = repository.save(existingMedecin);
        return this.toDTO(updated);
    }

    private MedecinDto toDTO(Medecin m) {
        return mapper.toDto(m);
    }
    private Medecin toEntity(MedecinDto dto){
        return mapper.toEntity(dto);
    }
}
