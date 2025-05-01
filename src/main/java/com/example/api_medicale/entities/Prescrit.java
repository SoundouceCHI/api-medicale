package com.example.api_medicale.entities;

import com.example.api_medicale.tools.PrescritId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
    private Consultation consultation;

    @ManyToOne
    @MapsId("medicamentId")
    private Medicament medicament;

    private int nbPrises;
}
