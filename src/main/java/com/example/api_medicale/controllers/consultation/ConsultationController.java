package com.example.api_medicale.controllers.consultation;


import com.example.api_medicale.dto.ConsultationDto;
import com.example.api_medicale.services.consultation.ConsulationService;
import io.swagger.v3.oas.annotations.Operation;
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


}
