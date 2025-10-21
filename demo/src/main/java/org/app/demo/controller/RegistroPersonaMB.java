package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.JsfUtils;
import org.app.demo.common.Utils;
import org.app.demo.model.Persona;
import org.app.demo.model.RespuestaWs;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.PersonaService;

import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class RegistroPersonaMB implements Serializable {
    @Inject
    private ServletSession ss;
    @Inject
    private PersonaService personaService;
    private Map<String, Object> param;
    private Persona persona;
    private String origen;
    private Boolean visualizar;

    @PostConstruct
    public void init() {
        visualizar = Boolean.FALSE;
        persona = new Persona();
        if (ss.getParametros() != null) {
            param = ss.getParametros();
            if (param.containsKey("persona")) {
                persona = (Persona) param.get("persona");
            }
            if (param.containsKey("origen")) {
                origen = (String) param.get("origen");
            }
            if (param.containsKey("visualizar")) {
                visualizar = (Boolean) param.get("visualizar");
            }
        }
    }


    public void guardarPersona() {
        RespuestaWs respuesta = personaService.guardar(persona);
        if (respuesta != null && respuesta.getEstado() != null && respuesta.getEstado()) {
            JsfUtils.addMessage(FacesMessage.SEVERITY_INFO, "Exito", respuesta.getMensaje());
        } else {
            JsfUtils.addMessage(FacesMessage.SEVERITY_WARN, "Error", "Error al registrar persona");
        }
    }

    public Boolean getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(Boolean visualizar) {
        this.visualizar = visualizar;
    }

    public void regresar() {

        JsfUtils.redirect(origen);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}

