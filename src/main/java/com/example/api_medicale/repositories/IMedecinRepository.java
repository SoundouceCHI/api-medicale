package com.example.api_medicale.repositories;

import com.example.api_medicale.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IMedecinRepository extends JpaRepository<Medecin, Long> {
    Page<Medecin> findByNomContainingIgnoreCaseOrMatriculeContaining(String nom, String mat, Pageable pageable);
}
