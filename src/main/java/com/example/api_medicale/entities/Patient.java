package com.example.api_medicale.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="patient")
public class Patient extends BaseEntity{

    private String numSecuSoc;
    private String nom;
    private String prenom;


    public String getNumSecuSoc() {
        return numSecuSoc;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumSecuSoc(String numSecuSoc) {
        this.numSecuSoc = numSecuSoc;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
