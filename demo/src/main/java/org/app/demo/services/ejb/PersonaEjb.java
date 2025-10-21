package org.app.demo.services.ejb;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.app.demo.config.VariablesInicio;
import org.app.demo.model.Persona;
import org.app.demo.model.RespuestaWs;
import org.app.demo.services.interfaces.AppService;
import org.app.demo.services.interfaces.PersonaService;

import java.util.List;

@Stateless(name = "personaEjb")
public class PersonaEjb implements PersonaService {
    @Inject
    private AppService appService;



    @Override
    public RespuestaWs guardar(Persona persona) {
        return (RespuestaWs) appService.methodPOST(persona, VariablesInicio.wsDemo.concat("persona/guardar"), RespuestaWs.class);
    }

    @Override
    public List<Persona> consultarXEstado(String estado) {
        return appService.methodListGET(VariablesInicio.wsDemo.concat("persona/consultar/estado/").concat(estado), Persona[].class);
    }
}
