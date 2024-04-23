package com.example.fichamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fichamedica.model.FichaMedica;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {
    
}
