package com.example.api_medicale.controllers.medecin;

import com.example.api_medicale.dto.MedecinDto;
import com.example.api_medicale.services.medecin.MedecinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/api/medecin")
public class MedecinController {
    private MedecinService medecinService;

    public MedecinController(MedecinService medecinService){
        this.medecinService = medecinService;
    }
    @GetMapping
    @Operation(summary = "Récuperer tous les medecins")
    public Page<MedecinDto> getAll(
            @Parameter(description = "Recherche par nom ou matricule") @RequestParam(defaultValue = "") String search,
            @Parameter(description = "Page actuelle (à partir de 0)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Taille des résultats") @RequestParam(defaultValue = "5") int size
    ) {
        return medecinService.findAll(search, page, size);
    }

    @PostMapping
    @Operation(summary = "Creer un nouveau medecin")
    public MedecinDto create(@RequestBody MedecinDto medecinDto){
        return medecinService.save(medecinDto);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Récuperer un medecin par id")
    public MedecinDto getById(@PathVariable Long id){
        return medecinService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un medecin par Id")
    public MedecinDto update(@PathVariable Long id,@RequestBody MedecinDto medecinDto){
        medecinDto.setId(id);
        return medecinService.update(medecinDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un medecinc par id")
    public void delete(@PathVariable Long id){
        medecinService.delete(id);
    }
}
