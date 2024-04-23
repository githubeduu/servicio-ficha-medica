package com.example.fichamedica.DTO;

import java.io.Serializable;

public class ActualizacionPacienteDTO implements Serializable{
    private String telefono;
    private String correo;
    private String direccion;

    
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


