package com.example.fichamedica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fichamedica.model.Medico;
import com.example.fichamedica.repository.MedicoRepository;

@Service
public class MedicoServicelmpl implements MedicoService{
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Medico> getAllMedico(){
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> getMedicoById(Long id){
        return medicoRepository.findById(id);
    }

    @Override
    public Medico createMedico(Medico medico){         
        return medicoRepository.save(medico);
    }

    @Override
    public void deleteMedico(Long id){
        medicoRepository.deleteById(id);
    } 
}
