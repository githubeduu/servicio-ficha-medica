package com.example.fichamedica.service;

import java.util.List;
import java.util.Optional;

import com.example.fichamedica.model.Paciente;

public interface PacienteService {
    List<Paciente> getAllPaciente();
    Optional<Paciente> getPacienteById(Long id);
    Paciente createPaciente(Paciente paciente); 
    Paciente updatePaciente(Long id, Paciente paciente);
    void deletePaciente(Long id);
    
}
