package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.model.UsuarioSession;

import java.io.Serializable;

@ViewScoped
@Named
public class BienvenidaMB implements Serializable {
    @Inject
    private UsuarioSession us;
    private String nombres;

    @PostConstruct
    public void init() {
        this.nombres = us.getNombres();
    }

    public UsuarioSession getUs() {
        return us;
    }

    public void setUs(UsuarioSession us) {
        this.us = us;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}
