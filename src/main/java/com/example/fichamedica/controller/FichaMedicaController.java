package com.example.fichamedica.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fichamedica.DTO.CreacionFichaMedicaDTO;
import com.example.fichamedica.DTO.CreacionMedicoDTO;
import com.example.fichamedica.DTO.CreacionPacienteDTO;
import com.example.fichamedica.model.FichaMedica;
import com.example.fichamedica.model.Medico;
import com.example.fichamedica.model.Paciente;
import com.example.fichamedica.service.FichaMedicaService;
import com.example.fichamedica.service.MedicoService;
import com.example.fichamedica.service.PacienteService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/atencionMedica")
public class FichaMedicaController {

    private static final Logger log = LoggerFactory.getLogger(FichaMedicaController.class);

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private FichaMedicaService fichaMedicaService;

    @GetMapping("/paciente")
    public List<Paciente> getPaciente() {
        return pacienteService.getAllPaciente();
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<?> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if (paciente.isPresent()) {
            return ResponseEntity.ok(paciente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }

    @GetMapping("/medico")
    public List<Medico> getMedico() {
        return medicoService.getAllMedico();
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<?> getMedicoyId(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        if (medico.isPresent()) {
            return ResponseEntity.ok(medico.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico no encontrado");
        }
    }

    @PostMapping("/paciente")
    public ResponseEntity<?> createPaciente(@Validated @RequestBody CreacionPacienteDTO pacienteDto) {
        try {
            Paciente paciente = new Paciente();
            paciente.setId(pacienteDto.getId());
            paciente.setNombre(pacienteDto.getNombre());
            paciente.setRut(pacienteDto.getRut());
            paciente.setTelefono(pacienteDto.getTelefono());
            paciente.setCorreo(pacienteDto.getCorreo());
            paciente.setDireccion(pacienteDto.getDireccion());

            Paciente nuevoPaciente = pacienteService.createPaciente(paciente);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el paciente:          " + e);
        }
    }

    @PutMapping("/paciente/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Optional<Paciente> pacienteExistente = pacienteService.getPacienteById(id);
            if (pacienteExistente.isPresent()) {
                Paciente pacienteActual = pacienteExistente.get();

                // Actualizar solo los campos que no son nulos
                if (paciente.getTelefono() != null) {
                    pacienteActual.setTelefono(paciente.getTelefono());
                }
                if (paciente.getCorreo() != null) {
                    pacienteActual.setCorreo(paciente.getCorreo());
                }
                if (paciente.getDireccion() != null) {
                    pacienteActual.setDireccion(paciente.getDireccion());
                }

                // Guardar el paciente actualizado
                Paciente pacienteActualizado = pacienteService.updatePaciente(pacienteActual);
                return ResponseEntity.ok(pacienteActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el paciente: " + e);
        }
    }

    @PostMapping("/medico")
    public ResponseEntity<?> createMedico(@Validated @RequestBody CreacionMedicoDTO medicoDto) {
        try {
            Medico medico = new Medico();
            medico.setId(medicoDto.getId());
            medico.setNombre(medicoDto.getNombre());
            medico.setRut(medicoDto.getRut());
            medico.setEspecialidad(medicoDto.getEspecialidad());

            Medico nuevoMedico = medicoService.createMedico(medico);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedico);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el medico:          " + e);
        }
    }

    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if (paciente.isPresent()) {
            pacienteService.deletePaciente(id);
            return ResponseEntity.ok("Paciente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }

    @DeleteMapping("/medico/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        if (medico.isPresent()) {
            medicoService.deleteMedico(id);
            return ResponseEntity.ok("Medico eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico no encontrado");
        }
    }

    @GetMapping("/ficha")
    public List<FichaMedica> getAllFicha() {
        return fichaMedicaService.getAllFicha();
    }

    @GetMapping("/ficha/{id}")
    public ResponseEntity<?> getfichaById(@PathVariable Long id) {
        Optional<FichaMedica> fichaMedica = fichaMedicaService.getFichaMedicaById(id);
        if (fichaMedica.isPresent()) {
            return ResponseEntity.ok(fichaMedica.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ficha no encontrada");
        }
    }

    @PostMapping("/ficha")
    public ResponseEntity<?> createFichaMedica(@Validated @RequestBody CreacionFichaMedicaDTO fichaMedicaDTO) {
        try {
            Medico medico = medicoService.getMedicoById(fichaMedicaDTO.getMedicoId())
                    .orElseThrow(() -> new RuntimeException("Medico no encontrado"));
            Paciente paciente = pacienteService.getPacienteById(fichaMedicaDTO.getPacienteId())
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    
            FichaMedica fichaMedica = new FichaMedica();
            fichaMedica.setFecha(fichaMedicaDTO.getFecha());
            fichaMedica.setDiasLicencia(fichaMedicaDTO.getDiasLicencia());
            fichaMedica.setDiagnostico(fichaMedicaDTO.getDiagnostico());       
            fichaMedica.setMedico(medico);
            fichaMedica.setPaciente(paciente);
            FichaMedica nuevaFichaMedica = fichaMedicaService.createFichaMedica(fichaMedica);
    
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFichaMedica);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la ficha medica: " + e.getMessage());
        }
    }
    

}
