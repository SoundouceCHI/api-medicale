package com.example.api_medicale.services.medicament;

import com.example.api_medicale.dto.MedicamentDto;
import com.example.api_medicale.entities.Medicament;
import com.example.api_medicale.mappers.IMedicamentMapper;
import com.example.api_medicale.repositories.IMedicamentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentService implements IMedicamentService{

    private final IMedicamentRepository repository;
    private final IMedicamentMapper mapper;

    public MedicamentService(IMedicamentMapper mapper, IMedicamentRepository repository){
        this.mapper = mapper;
        this.repository=repository;
    }
    @Override
    public List<MedicamentDto> findAll() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public MedicamentDto save(MedicamentDto dto) {
        Medicament m = repository.save(this.toEntity(dto));
        return this.toDTO(m);
    }

    @Override
    public MedicamentDto findById(Long id) {
        Medicament m = repository.findById(id).
                orElseThrow(()-> new RuntimeException("Medicament non trouvée"));
        return this.toDTO(m);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MedicamentDto update(MedicamentDto dto) {
        Medicament existingMed = repository.findById(dto.getCode()).
                orElseThrow(()-> new RuntimeException("Medicament non trouvée"));
        existingMed.setCode(dto.getCode());
        existingMed.setLibelle(dto.getLibelle());
        repository.save(existingMed);
        return  this.toDTO(existingMed);
    }

    @Override
    public Page<MedicamentDto> findAll(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByLibelleContainsIgnoreCase(search,pageable).map(this::toDTO);
    }
    private MedicamentDto toDTO(Medicament p) {
        return mapper.toDto(p);
    }

    private Medicament toEntity(MedicamentDto dto) {
        return mapper.toEntity(dto);
    }
}
