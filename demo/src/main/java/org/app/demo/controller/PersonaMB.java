package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.app.demo.common.JsfUtils;
import org.app.demo.common.Utils;
import org.app.demo.model.Persona;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PersonaMB implements Serializable {
    private Persona persona;
    private List<Persona> personas;
    private Integer index;
    private Boolean visualizar;

    @PostConstruct
    public void init() {
        visualizar = false;
        persona = new Persona();
        personas = new ArrayList<Persona>();
    }


    public void editar(Persona persona, Integer index, Boolean visualizar) {
        this.visualizar = visualizar;
        this.persona = new Persona();
        this.index = null;
        if (persona != null) {
            this.persona = persona;
            this.index = index;
        }
    }

    public void guardar() {
        try {
            if (this.index == null) {
                personas.add(persona);
                JsfUtils.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Datos creados correctamente");
            } else {
                personas.set(this.index, persona);
                JsfUtils.addMessage(FacesMessage.SEVERITY_INFO, "Info", "Datos actualizados correctamente");
            }

            PrimeFaces.current().executeScript("PF('dlgForm').hide()");

            if (Utils.isNotEmpty(personas)) {
                personas.forEach(data -> {
                    System.out.println("---> Persona " + data);
                });
            }

        } catch (Exception e) {
            JsfUtils.addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());

        }
    }

    public void eliminar(int index) {
        personas.remove(index);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Boolean getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(Boolean visualizar) {
        this.visualizar = visualizar;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
