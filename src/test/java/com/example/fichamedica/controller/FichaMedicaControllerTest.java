package com.example.fichamedica.controller;
/*
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.MockMvc;

import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.service.FichaMedicaService;

@WebMvcTest(FichaMedicaController.class)
public class FichaMedicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FichaMedicaService fichaMedicaServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception {
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
}
*/
