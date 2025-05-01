package com.example.api_medicale.dto;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MedicamentPrescritDTO {
    private Long medicamentId;
    private String libelle;
    private int nbPrises;
}
