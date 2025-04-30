package com.example.api_medicale.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicamentDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long code;
    private String libelle;
}
