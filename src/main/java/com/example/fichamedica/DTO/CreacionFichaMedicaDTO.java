package com.example.fichamedica.DTO;

import java.sql.Date;

import com.example.fichamedica.model.Medico;
import com.example.fichamedica.model.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CreacionFichaMedicaDTO {
 
    private Long id;
    @JsonFormat(pattern="dd-mm-yyyy")  
    private Date fecha;
    private int diasLicencia;    
    private String diagnostico;
    private Long pacienteId;
    private Paciente paciente;
    private Long medicoId; 
    private Medico medico;
    
    //getters
    public Long getId(){
        return id;
    }
    
    public Date getFecha(){          
        return fecha;
    }

    public int getDiasLicencia(){
        return diasLicencia;
    }

    public String getDiagnostico(){
        return diagnostico;
    }

    public Long getPacienteId(){
        return pacienteId;
    }

    public Paciente getPaciente(){
        return paciente;
    }

    public Long getMedicoId(){
        return medicoId;
    }

    public Medico getMedico(){
        return medico;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

    public void setDiasLicencia(int diasLicencia){
        this.diasLicencia = diasLicencia;
    }

    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }

    public void setPacienteId(Long pacienteId){
        this.pacienteId = pacienteId;
    }

    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }

    public void setMedicoId(Long medicoId){
        this.medicoId = medicoId;
    }

    public void setMedico(Medico medico){
        this.medico = medico;
    }
}
