package org.app.apidemo.dto;

import java.util.Map;

public class ReporteDto {
    private String nombreReporte;
    private Map<String, Object> parametros;

    public ReporteDto() {

    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    @Override
    public String toString() {
        return "ReporteDto{" +
                "nombreReporte='" + nombreReporte + '\'' +
                ", parametros=" + parametros +
                '}';
    }
}
