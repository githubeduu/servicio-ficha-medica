package com.example.fichamedica.DTO;
import java.io.Serializable;

public class CreacionMedicoDTO implements Serializable { 
    private Long id;
    private String nombre;
    private String rut;
    private String especialidad;

    //getters
    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getRut(){
        return rut;
    }

    public String getEspecialidad(){
        return especialidad;
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setRut(String rut){
        this.rut = rut;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    

    
}
