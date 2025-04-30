package com.example.api_medicale.repositories;

import com.example.api_medicale.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultationRepository extends JpaRepository<Consultation, Long> {
}
