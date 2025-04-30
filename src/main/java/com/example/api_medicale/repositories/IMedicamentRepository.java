package com.example.api_medicale.repositories;

import com.example.api_medicale.entities.Medicament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicamentRepository extends JpaRepository<Medicament, Long> {
    Page<Medicament> findByLibelleContainsIgnoreCase(String libelle, Pageable pageable);
}
