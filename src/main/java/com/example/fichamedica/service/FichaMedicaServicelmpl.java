package com.example.fichamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.repository.FichaMedicaRepository;

@Service
public class FichaMedicaServicelmpl implements FichaMedicaService{     
    @Autowired
    private FichaMedicaRepository fichaMedicaRepository;

    @Override
    public List<FichaMedica> getAllFicha(){
        return fichaMedicaRepository.findAll();
    }

    @Override
    public Optional<FichaMedica> getFichaMedicaById(Long id){
        return fichaMedicaRepository.findById(id);
    }

    @Override
    public FichaMedica createFichaMedica(FichaMedica fichaMedica){         
        return fichaMedicaRepository.save(fichaMedica);
    }
} 