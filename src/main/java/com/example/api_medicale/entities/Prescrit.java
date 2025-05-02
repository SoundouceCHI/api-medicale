package com.example.api_medicale.entities;

import com.example.api_medicale.tools.PrescritId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescrit {
    @EmbeddedId
    private PrescritId id;

    @ManyToOne
    @MapsId("consultationId")
    @JoinColumn(name = "consultation_id", referencedColumnName = "id")
    private Consultation consultation;

    @ManyToOne
    @MapsId("medicamentId")
    @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    private Medicament medicament;

    private int nbPrises;
}
