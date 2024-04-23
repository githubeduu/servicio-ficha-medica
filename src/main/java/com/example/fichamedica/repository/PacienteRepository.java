package com.example.fichamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fichamedica.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
} 

