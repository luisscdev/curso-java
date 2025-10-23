package org.app.apidemo.service;

import org.app.apidemo.common.Constantes;
import org.app.apidemo.common.Utils;
import org.app.apidemo.dto.RespuestaWs;
import org.app.apidemo.entity.Persona;
import org.app.apidemo.repository.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepo personaRepo;


    public List<Persona> consultarXEstado(String estado) {
        return personaRepo.findAllByEstadoOrderByIdAsc(estado);
    }


    public RespuestaWs guardar(Persona persona) {

        Persona personaReponse = personaRepo.save(persona);

        return new RespuestaWs(Boolean.TRUE, personaReponse, Constantes.dataCorrecto);
    }

}
