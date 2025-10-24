package org.app.demo.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.app.demo.common.*;
import org.app.demo.model.Persona;
import org.app.demo.model.ServletSession;
import org.app.demo.services.interfaces.PersonaService;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class PersonaMB implements Serializable {
    @Inject
    private PersonaService personaService;
    @Inject
    private ServletSession ss;
    private Persona persona;
    private List<Persona> personas;

    @PostConstruct
    public void init() {
        loadPersonas(StatusType.ACTIVO.name());
    }

    public void loadPersonas(String tipo) {
        personas = new ArrayList<>();
        List<Persona> personaTmp = personaService.consultarXEstado(tipo);
        if (personaTmp != null && personaTmp.size() > 0) {
            personas.addAll(personaTmp);
        }
    }

    public void imprimir(String estado) {
        DatosReporte datosReporte = new DatosReporte();
        ss.borrarDatos();
        ss.instanciarParametros();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("NOMBRE_REPORTE", "CURSO DE JAVA AVANZADO");
        parametros.put("estado", estado);
        datosReporte.setFormato(ReporteFormato.PDF.getCodigo());
        datosReporte.setNombreArchivo("Personas" + ReporteFormato.PDF.getExtension());
        datosReporte.setParametros(parametros);
        datosReporte.setDataSource(Boolean.FALSE);
        datosReporte.setGestorDocumental(Boolean.FALSE);
        datosReporte.setNombreReporte("curso_java");
        ss.setDatosReporte(datosReporte);
        ss.setNombreDocumento("Personas");
        redireccionarReporte();
    }

    public void redireccionarReporte() {
        JsfUtils.redirectNewTab("/ReporteWS");
    }

    public void nuevo(Persona persona, String origen, Boolean visualizar) {
        ss.borrarDatos();
        ss.agregarParametro("origen", origen);
        ss.agregarParametro("visualizar", visualizar);
        if (persona != null) {
            ss.agregarParametro("persona", persona);
        }
        JsfUtils.redirect("/procesos/_registrarPersona.xhtml");
    }


    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

}
