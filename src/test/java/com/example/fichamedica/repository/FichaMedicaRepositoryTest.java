package com.example.fichamedica.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.model.Medico;
import com.example.fichamedica.model.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FichaMedicaRepositoryTest {
    @Autowired
    private FichaMedicaRepository fichaMedicaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void CrearFichaMedicaTest() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = sdf.parse("25-04-2024");
            java.sql.Date fecha = new java.sql.Date(parsedDate.getTime());

            Medico medico = medicoRepository.findById(21L).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

            Paciente paciente = pacienteRepository.findById(21L).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

            FichaMedica fichaMedica = new FichaMedica();
            fichaMedica.setFecha(fecha);
            fichaMedica.setDiasLicencia(2);
            fichaMedica.setDiagnostico("Resfriado común");
            fichaMedica.setMedico(medico);
            fichaMedica.setPaciente(paciente);

            FichaMedica resultado = fichaMedicaRepository.save(fichaMedica);

            assertNotNull(resultado.getId());
            assertEquals(fecha, resultado.getFecha());
            assertEquals(2, resultado.getDiasLicencia());
            assertEquals("Resfriado común", resultado.getDiagnostico());
            assertNotNull(resultado.getMedico());
            assertNotNull(resultado.getPaciente());
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
        }
    }

    @Test
    public void BuscarFichaMedicaPorIdTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsedDate = sdf.parse("30-04-2024");
        java.sql.Date fecha = new java.sql.Date(parsedDate.getTime());

        Medico medico = medicoRepository.findById(21L).orElseThrow(() -> new RuntimeException("Medico no encontrado"));

        Paciente paciente = pacienteRepository.findById(21L).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        FichaMedica fichaMedica = new FichaMedica();
        fichaMedica.setFecha(fecha);
        fichaMedica.setDiasLicencia(2);
        fichaMedica.setDiagnostico("Resfriado común");
        fichaMedica.setMedico(medico);
        fichaMedica.setPaciente(paciente);
 
        fichaMedica = fichaMedicaRepository.save(fichaMedica);    
        Optional<FichaMedica> resultado = fichaMedicaRepository.findById(fichaMedica.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Resfriado común", resultado.get().getDiagnostico());
    }

}
