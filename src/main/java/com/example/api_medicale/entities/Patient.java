package com.example.api_medicale.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="patient")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Patient extends BaseEntity{

    private String numSecuSoc;
    private String nom;
    private String prenom;

    @OneToMany( mappedBy = "patient", cascade = CascadeType.ALL)
    List<Consultation> consultations= new ArrayList<>();

}
