package com.example.fichamedica.controller;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fichamedica.DTO.CreacionFichaMedicaDTO;
import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.model.Medico;
import com.example.fichamedica.model.Paciente;
import com.example.fichamedica.repository.MedicoRepository;
import com.example.fichamedica.repository.PacienteRepository;
import com.example.fichamedica.service.FichaMedicaService;
import com.example.fichamedica.service.MedicoService;
import com.example.fichamedica.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(FichaMedicaController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import({FichaMedicaService.class, MedicoService.class, PacienteService.class})
public class FichaMedicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FichaMedicaService fichaMedicaServiceMock;
    @MockBean
    private PacienteService pacienteService;
    @MockBean
    private MedicoService medicoService;    

    @Test
    public void ObtenerTodosTest() throws Exception {
        FichaMedica fichaMedica1 = new FichaMedica();
        fichaMedica1.setDiagnostico("Resfriado común");
        fichaMedica1.setId(1L);
        FichaMedica fichaMedica2 = new FichaMedica();
        fichaMedica2.setDiagnostico("Lumbago");
        fichaMedica2.setId(2L);
        List<FichaMedica> fichaMedicas = List.of(fichaMedica1, fichaMedica2);

        when(fichaMedicaServiceMock.getAllFicha()).thenReturn(fichaMedicas);

        mockMvc.perform(get("/atencionMedica/ficha").accept(MediaTypes.HAL_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$._embedded.fichaMedicaList[0].id").value(1))
               .andExpect(jsonPath("$._embedded.fichaMedicaList[0].diagnostico").value("Resfriado común"))
               .andExpect(jsonPath("$._embedded.fichaMedicaList[1].id").value(2))
               .andExpect(jsonPath("$._embedded.fichaMedicaList[1].diagnostico").value("Lumbago"));
    }

    @Test
    public void CrearFichaMedica() throws Exception {
        CreacionFichaMedicaDTO fichaMedicaDTO = new CreacionFichaMedicaDTO();
        fichaMedicaDTO.setFecha(new java.sql.Date(System.currentTimeMillis()));
        fichaMedicaDTO.setDiasLicencia(2);
        fichaMedicaDTO.setDiagnostico("Resfriado Común");
        fichaMedicaDTO.setMedicoId(1L);
        fichaMedicaDTO.setPacienteId(1L); 

        Medico medico = new Medico();
        medico.setId(1L);
        medico.setNombre("Juan Perez");
        medico.setRut("174262728");
        medico.setEspecialidad("Medico General");

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Maria Henriquez");
        paciente.setRut("85467124");

        when(medicoService.getMedicoById(1L)).thenReturn(Optional.of(medico));
        when(pacienteService.getPacienteById(1L)).thenReturn(Optional.of(paciente));
        when(fichaMedicaServiceMock.createFichaMedica(any(FichaMedica.class))).thenReturn(new FichaMedica());

        mockMvc.perform(post("/atencionMedica/ficha")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(fichaMedicaDTO)))
                .andExpect(status().isCreated()); // Verifica que el estado sea CREATED
    }


    @Test
    public void ObtenerFichaMedicaError() throws Exception {
        when(fichaMedicaServiceMock.getFichaMedicaById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/atencionMedica/ficha/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Ficha médica no encontrada"));
    }






}

