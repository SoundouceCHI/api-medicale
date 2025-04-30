package com.example.api_medicale.controllers.medicament;

import com.example.api_medicale.dto.MedicamentDto;
import com.example.api_medicale.services.medicament.MedicamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/medicament")
public class MedicamentController {

    private final MedicamentService service;

    MedicamentController(MedicamentService service){
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Ajouter un medicament")
    public MedicamentDto create(@RequestBody MedicamentDto medicamentDto) {
        return service.save(medicamentDto);
    }

    @GetMapping
    @Operation(summary = "Lister les medicament avec pagination et recherche")
    public Page<MedicamentDto> getAll(
            @Parameter(description = "Recherche par libelle") @RequestParam(defaultValue = "") String search,
            @Parameter(description = "Page actuelle (à partir de 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Taille des résultats") @RequestParam(defaultValue = "5") int size
    ) {
        return service.findAll(search, page, size);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un medicament")
    public MedicamentDto update(@PathVariable Long id, @RequestBody MedicamentDto medicamentDto) {
        medicamentDto.setCode(id);
        return service.update(medicamentDto);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un medicament")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Retourner le medicament par id")
    public MedicamentDto getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
