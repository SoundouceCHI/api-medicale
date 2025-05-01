package com.example.api_medicale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicament")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String libelle;

    @ManyToMany
    private List<Consultation> consultations = new ArrayList<>();

    @OneToMany(mappedBy = "medicament")
    private List<Prescrit> prescriptions;
}
