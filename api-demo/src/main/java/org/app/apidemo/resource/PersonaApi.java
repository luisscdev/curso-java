package org.app.apidemo.resource;

import org.app.apidemo.entity.Persona;
import org.app.apidemo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaApi {

    @Autowired
    private PersonaService personaService;

    @GetMapping(value = "/persona/consultar/estado/{estado}")
    public ResponseEntity<List<Persona>> consultarPersonaXEstado(@PathVariable String estado) {
        return new ResponseEntity<>(personaService.consultarXEstado(estado), HttpStatus.OK);
    }

    @PostMapping(value = "/persona/guardar")
    public ResponseEntity<?> guardarPersona(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.guardar(persona), HttpStatus.OK);
    }

}
