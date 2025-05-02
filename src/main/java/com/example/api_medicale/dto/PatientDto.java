package com.example.api_medicale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto extends UserDto{
    private String numSecuSoc;
    private String nom;
    private String prenom;
}
