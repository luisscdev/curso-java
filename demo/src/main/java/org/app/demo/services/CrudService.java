package org.app.demo.services;

import jakarta.ejb.Local;
import org.app.demo.model.RespuestaWs;

@Local
public interface CrudService {

    public RespuestaWs guardar();
}
