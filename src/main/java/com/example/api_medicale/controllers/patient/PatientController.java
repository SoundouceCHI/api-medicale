package com.example.api_medicale.controllers.patient;

import com.example.api_medicale.dto.PatientDto;
import com.example.api_medicale.services.patient.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @PostMapping
    @Operation(summary = "Creer un nouveau patient")
    public PatientDto create(@RequestBody PatientDto dto) {
        return patientService.save(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retourner le patient par id")
    public PatientDto getById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer le patient par id")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Lister les patients avec pagination et recherche")
    public Page<PatientDto> getAll(
            @Parameter(description = "Recherche par nom ou numéro de sécu") @RequestParam(defaultValue = "") String search,
            @Parameter(description = "Page actuelle (à partir de 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Taille des résultats") @RequestParam(defaultValue = "5") int size
    ) {
        return patientService.findAll(search, page, size);
    }


}
