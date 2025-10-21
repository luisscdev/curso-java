package org.app.demo.services.interfaces;

import jakarta.ejb.Local;
import org.app.demo.model.Persona;
import org.app.demo.model.RespuestaWs;

import java.util.List;

@Local
public interface PersonaService {

    public RespuestaWs guardar(Persona persona);

    public List<Persona> consultarXEstado(String estado);

}
