package com.example.fichamedica.service;

import java.util.List;
import java.util.Optional;

import com.example.fichamedica.model.Medico;

public interface MedicoService {
    List<Medico> getAllMedico();
    Optional<Medico> getMedicoById(Long id);
    Medico createMedico(Medico medico); 
    void deleteMedico(Long id);
}

