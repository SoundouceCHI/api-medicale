package com.example.api_medicale.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="medecin")
@Getter
@Setter
public class Medecin extends BaseEntity{
    private String matricule;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    List<Consultation> consultations= new ArrayList<>();
}
