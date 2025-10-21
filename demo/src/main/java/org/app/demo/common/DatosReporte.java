package org.app.demo.common;

import java.util.Map;

public class DatosReporte {

    private Boolean usuario;
    private String nombreReporte;
    private Map<String, Object> parametros;

    private String nombreArchivo;
    private String formato;
    private Boolean dataSource; //True si es por un modelo de datos : False si es x consulta SQL
    private Boolean gestorDocumental; //True si se necesita guardar el reporte en el gestor documental

    public DatosReporte() {
    }

    public Boolean getUsuario() {
        return usuario;
    }

    public void setUsuario(Boolean usuario) {
        this.usuario = usuario;
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

    public String getFormato() {
        if (this.formato == null) {
            this.formato = ReporteFormato.PDF.getCodigo();
        }
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Boolean getDataSource() {
        return dataSource;
    }

    public void setDataSource(Boolean dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getGestorDocumental() {
        return gestorDocumental;
    }

    public void setGestorDocumental(Boolean gestorDocumental) {
        this.gestorDocumental = gestorDocumental;
    }


    @Override
    public String toString() {
        return "DatosReporte{" + "usuario=" + usuario + ", nombreReporte=" + nombreReporte + ", parametros=" + parametros + ", nombreArchivo=" + nombreArchivo + ", formato=" + formato  + ", dataSource=" + dataSource + ", gestorDocumental=" + gestorDocumental  + '}';
    }

}
