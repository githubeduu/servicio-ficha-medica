package com.example.fichamedica.DTO;

import java.io.Serializable;

public class CreacionPacienteDTO implements Serializable {
    private Long id;
    private String nombre;
    private String rut;
    private String telefono;
    private String correo;
    private String direccion;

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

    public String getTelefono(){
        return telefono;
    }

    public String getCorreo(){
        return correo;
    }

    public String getDireccion(){
        return direccion;
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

    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

}