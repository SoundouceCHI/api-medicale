package com.example.api_medicale.repositories;

import com.example.api_medicale.entities.Prescrit;
import com.example.api_medicale.tools.PrescritId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPrescritRepository extends JpaRepository<Prescrit, PrescritId> {
    List<Prescrit> findByConsultationNumero(Long consultationId);
}
