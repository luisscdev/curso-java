package org.app.demo.services;

import jakarta.ejb.Stateless;
import org.app.demo.model.RespuestaWs;

@Stateless(name = "catalogoEjb")
public class CatalogoEjb implements CrudService {
    @Override
    public RespuestaWs guardar() {
        return null;
    }
}
