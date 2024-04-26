package com.example.fichamedica.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atencionMedica")
public class FichaMedicaController {   


    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private FichaMedicaService fichaMedicaService;


    @GetMapping("/paciente")
    public CollectionModel<EntityModel<Paciente>> getPaciente() {
        List<EntityModel<Paciente>> pacientes = pacienteService.getAllPaciente().stream()
            .map(paciente -> EntityModel.of(paciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(paciente.getId())).withSelfRel()))
            .collect(Collectors.toList());

        return CollectionModel.of(pacientes, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPaciente()).withRel("pacientes"));
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<?> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if (paciente.isPresent()) {
            EntityModel<Paciente> resource = EntityModel.of(paciente.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPaciente()).withRel("all-pacientes"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }

    @PostMapping("/paciente")
    public ResponseEntity<?> createPaciente(@Validated @RequestBody CreacionPacienteDTO pacienteDto) {
        try {
            Paciente paciente = new Paciente();
            paciente.setNombre(pacienteDto.getNombre());
            paciente.setRut(pacienteDto.getRut());
            paciente.setTelefono(pacienteDto.getTelefono());
            paciente.setCorreo(pacienteDto.getCorreo());
            paciente.setDireccion(pacienteDto.getDireccion());
            Paciente nuevoPaciente = pacienteService.createPaciente(paciente);

            EntityModel<Paciente> resource = EntityModel.of(nuevoPaciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(nuevoPaciente.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPaciente()).withRel("all-pacientes"));
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el paciente: " + e.getMessage());
        }
    }

    @PutMapping("/paciente/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        try {
            Paciente updatedPaciente = pacienteService.updatePaciente(id, paciente);
            EntityModel<Paciente> resource = EntityModel.of(updatedPaciente,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPacienteById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPaciente()).withRel("all-pacientes"));
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el paciente: " + e.getMessage());
        }
    }

    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        try {
            Optional<Paciente> paciente = pacienteService.getPacienteById(id);
            if (paciente.isPresent()) {
                pacienteService.deletePaciente(id);              
                Map<String, Object> response = Collections.singletonMap("message", "Paciente eliminado correctamente");
                EntityModel<Map<String, Object>> resource = EntityModel.of(response,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPaciente()).withRel("all-pacientes"),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createPaciente(null)).withRel("create-paciente"));

                return ResponseEntity.ok(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el paciente: " + e.getMessage());
        }
    }

  

    @GetMapping("/medico")
    public CollectionModel<EntityModel<Medico>> getMedico() {
        List<EntityModel<Medico>> medicos = medicoService.getAllMedico().stream()
            .map(medico -> EntityModel.of(medico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(medico.getId())).withSelfRel()))
            .collect(Collectors.toList());

        return CollectionModel.of(medicos, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedico()).withRel("medicos"));
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<?> getMedicoById(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.getMedicoById(id);
        if (medico.isPresent()) {
            EntityModel<Medico> resource = EntityModel.of(medico.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedico()).withRel("all-medicos"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico no encontrado");
        }
    }

    @PostMapping("/medico")
    public ResponseEntity<?> createMedico(@Validated @RequestBody CreacionMedicoDTO medicoDto) {
        try {
            Medico medico = new Medico();
            medico.setNombre(medicoDto.getNombre());
            medico.setRut(medicoDto.getRut());
            medico.setEspecialidad(medicoDto.getEspecialidad());
            Medico nuevoMedico = medicoService.createMedico(medico);

            EntityModel<Medico> resource = EntityModel.of(nuevoMedico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(nuevoMedico.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedico()).withRel("all-medicos"));
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el medico: " + e.getMessage());
        }
    }

    @PutMapping("/medico/{id}")
    public ResponseEntity<?> updateMedico(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            Medico updatedMedico = medicoService.updateMedico(id, medico);
            EntityModel<Medico> resource = EntityModel.of(updatedMedico,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedicoById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedico()).withRel("all-medicos"));
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el medico: " + e.getMessage());
        }
    }

    @DeleteMapping("/medico/{id}")
    public ResponseEntity<?> deleteMedico(@PathVariable Long id) {
        try {
            Optional<Medico> medico = medicoService.getMedicoById(id);
            if (medico.isPresent()) {
                medicoService.deleteMedico(id);

                // Usar un mapa para pasar un mensaje de confirmación
                Map<String, String> response = Collections.singletonMap("message", "Medico eliminado correctamente");
                EntityModel<Map<String, String>> resource = EntityModel.of(response,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMedico()).withRel("all-medicos"),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).createMedico(null)).withRel("create-medico"));

                return ResponseEntity.ok(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el medico: " + e.getMessage());
        }
    }

    @GetMapping("/ficha")
    public CollectionModel<EntityModel<FichaMedica>> getAllFicha() {
        List<EntityModel<FichaMedica>> fichas = fichaMedicaService.getAllFicha().stream()
            .map(ficha -> EntityModel.of(ficha,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFichaById(ficha.getId())).withSelfRel()))
            .collect(Collectors.toList());

        return CollectionModel.of(fichas, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFicha()).withRel("fichas"));
    }

    @GetMapping("/ficha/{id}")
    public ResponseEntity<?> getFichaById(@PathVariable Long id) {
        Optional<FichaMedica> ficha = fichaMedicaService.getFichaMedicaById(id);
        if (ficha.isPresent()) {
            EntityModel<FichaMedica> resource = EntityModel.of(ficha.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFichaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFicha()).withRel("all-fichas"));
            return ResponseEntity.ok(resource);
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

            EntityModel<FichaMedica> resource = EntityModel.of(nuevaFichaMedica,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getFichaById(nuevaFichaMedica.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllFicha()).withRel("all-fichas"));
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la ficha medica: " + e.getMessage());
        }
    }
}
