package com.example.api_medicale.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="medecin")
@Getter
@Setter
public class Medecin extends BaseEntity{
    private String matricule;
    private String nom;
    private String prenom;
}
