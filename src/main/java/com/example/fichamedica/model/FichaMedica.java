package com.example.fichamedica.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fichamedica")
public class FichaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @Column()
    private Date fecha;

    @Column()
    private int diasLicencia;
        
    @Column()
    private String diagnostico;


    @Column(name = "paciente_id", insertable = false, updatable = false)
    private Long pacienteId;

    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    @Column(name = "medico_id", insertable = false, updatable = false)
    private Long medicoId; 

    @ManyToOne
    @JoinColumn(name = "medico_id", referencedColumnName = "id")
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
