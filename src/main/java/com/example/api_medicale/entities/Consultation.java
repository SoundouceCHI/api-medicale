package com.example.api_medicale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consultation extends BaseEntity{

    private long numero;
    private LocalDate date;

    @ManyToOne
    private Medecin medecin;
    @ManyToOne
    private Patient patient;

    @OneToMany(mappedBy = "consultation")
    private List<Prescrit> prescriptions;

}
