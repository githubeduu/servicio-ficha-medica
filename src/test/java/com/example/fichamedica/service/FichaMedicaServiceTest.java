package com.example.fichamedica.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.repository.FichaMedicaRepository;

@ExtendWith(MockitoExtension.class)
public class FichaMedicaServiceTest {
    @InjectMocks
    private FichaMedicaServicelmpl fichaMedicaServicio;

    @Mock
    private FichaMedicaRepository fichaMedicaRepositoryMock;
    private FichaMedica fichaMedica;


    @BeforeEach
    void setUp() {
        fichaMedica = new FichaMedica();
        fichaMedica.setDiagnostico("Resfrio común");
        fichaMedica.setDiasLicencia(10);       
    }

    @Test
    public void CrearFichaMedicaTest(){

        when(fichaMedicaRepositoryMock.save(any())).thenReturn(fichaMedica);

        FichaMedica resultado = fichaMedicaServicio.createFichaMedica(fichaMedica);

        assertEquals("Resfrio común", resultado.getDiagnostico());
        assertEquals(10, resultado.getDiasLicencia());

        verify(fichaMedicaRepositoryMock).save(fichaMedica);
    }

    @Test
    public void CrearFichaMedicaErrorTest() {
        FichaMedica fichaMedica = new FichaMedica();
        fichaMedica.setDiasLicencia(2);

        when(fichaMedicaRepositoryMock.save(any())).thenThrow(new RuntimeException("Error de base de datos"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            fichaMedicaServicio.createFichaMedica(fichaMedica);
        });

        assertEquals("Error de base de datos", exception.getMessage());
    }

}
