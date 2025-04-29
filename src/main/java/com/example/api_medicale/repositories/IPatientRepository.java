package com.example.api_medicale.repositories;

import com.example.api_medicale.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContainingIgnoreCaseOrNumSecuSocContaining(String nom, String nss, Pageable pageable);
}
