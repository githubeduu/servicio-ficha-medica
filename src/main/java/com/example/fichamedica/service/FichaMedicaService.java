package com.example.fichamedica.service;

import java.util.List;
import java.util.Optional;

import com.example.fichamedica.model.FichaMedica;

public interface FichaMedicaService {
    List<FichaMedica> getAllFicha();
    Optional<FichaMedica> getFichaMedicaById(Long id);
    FichaMedica createFichaMedica(FichaMedica fichaMedica); 
}
