package com.example.fichamedica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fichamedica.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
}
