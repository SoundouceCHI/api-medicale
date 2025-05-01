package com.example.api_medicale.controllers.consultation;


import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.dto.MedicamentPrescritDTO;
import com.example.api_medicale.services.consultation.ConsulationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {
    private final ConsulationService service;

    public ConsultationController(ConsulationService service){
        this.service=service;
    }

    @GetMapping
    @Operation(summary = "Récuperer toutes les consultation")
    public List<ConsultationDto> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récuperer une consultation par id")
    public ConsultationDto getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Creer une nouvelle consulation")
    public ConsultationDto create(@RequestBody ConsultationDto dto){
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une consulation par id")
    public ConsultationDto update(@PathVariable Long id, @RequestBody ConsultationDto dto){
        dto.setNumero(id);
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Supprimer une consulation par Id")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/consultations/patient/{id}")
    @Operation(summary = "Lister les consultations d’un patient avec pagination")
    public Page<ConsultationDto> getConsultationsByPatient(
            @PathVariable Long id,
            @PageableDefault(size = 10, sort = "date", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getConsultationsDtoByPatientId(id, pageable);
    }

    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "Récupérer toutes les consultations d'un médecin")
    public List<ConsultationDto> getConsultationsByMedecin(@PathVariable Long medecinId) {
        return service.getConsultationsDtoByMedecinId(medecinId);
    }
    @GetMapping("/{id}/medicaments")
    @Operation(summary = "Récuperer tous les medicaments prescrits dans une consultation")
    public List<MedicamentPrescritDTO> getMedicamentsPrescrits(@PathVariable Long id) {
        return service.getMedicamentsByConsultation(id);
    }

    @PostMapping("/{id}/prescrition")
    @Operation(summary = "Ajouter une prescription à une consultation")
    public ConsultationDto addPrescription(@PathVariable Long id, @RequestBody MedicamentPrescritDTO medPrescDto) {
        return service.addPrescription(id, medPrescDto);
    }

    @PutMapping("/{id}/prescription/{medicamentId}")
    @Operation(summary = "Modifier une prescription d’une consultation")
    public ConsultationDto updatePrescription(
            @PathVariable Long id,
            @PathVariable Long medicamentId,
            @RequestBody MedicamentPrescritDTO dto
    ) {
        return service.updatePrescription(id, medicamentId, dto);
    }

    @DeleteMapping("/{id}/prescription/{medicamentId}")
    @Operation(summary = "Supprimer une prescription d’une consultation")
    public void deletePrescription(
            @PathVariable Long id,
            @PathVariable Long medicamentId
    ) {
        service.deletePrescription(id, medicamentId);
    }


}
