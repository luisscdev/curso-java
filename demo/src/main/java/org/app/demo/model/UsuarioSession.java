package org.app.demo.model;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.Date;

@SessionScoped
public class UsuarioSession implements Serializable {
    private String username;
    private String nombres;
    private Date fechaNacimiento;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
