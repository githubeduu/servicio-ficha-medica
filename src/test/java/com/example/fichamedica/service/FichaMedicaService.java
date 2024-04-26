package com.example.fichamedica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.repository.FichaMedicaRepository;

@ExtendWith(MockitoExtension.class)
public class FichaMedicaService {
    @InjectMocks
    private FichaMedicaServicelmpl fichaMedicaServicio;

    @Mock
    private FichaMedicaRepository fichaMedicaRepositoryMock;

    @Test
    public void guardarFichaMedicaTest() {

        FichaMedica fichaMedica = new FichaMedica();
        fichaMedica.setDiasLicencia(2);

        when(fichaMedicaRepositoryMock.save(any())).thenReturn(fichaMedica);

        FichaMedica resultado = fichaMedicaServicio.createFichaMedica(fichaMedica);

        assertEquals(2, resultado.getDiasLicencia());
    }
}
