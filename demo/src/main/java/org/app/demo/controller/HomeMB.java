package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;
import org.app.demo.model.UsuarioSession;

import java.io.Serializable;

@ViewScoped
@Named
public class HomeMB implements Serializable {
    @Inject
    private UsuarioSession us;
    private String nombresCompletos;


    @PostConstruct
    public void init() {
        nombresCompletos = us.getNombres();
    }




    public UsuarioSession getUs() {
        return us;
    }

    public void setUs(UsuarioSession us) {
        this.us = us;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }
}
