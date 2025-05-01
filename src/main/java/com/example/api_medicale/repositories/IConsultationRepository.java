package com.example.api_medicale.repositories;

import com.example.api_medicale.entities.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByPatientId(Long patientId);
    Page<Consultation> findByPatientId(Long patientId, Pageable pageable);
    List<Consultation> findByMedecinId(Long medecinId);
}
