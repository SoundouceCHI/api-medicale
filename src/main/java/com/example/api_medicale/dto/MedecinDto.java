package com.example.api_medicale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedecinDto extends UserDto{
    private String matricule;
    private String nom;
    private String prenom;

}
