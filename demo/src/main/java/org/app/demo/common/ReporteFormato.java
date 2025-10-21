package org.app.demo.common;

public enum ReporteFormato {
    PDF("01", "FORMATO PDF", "application/pdf", ".pdf"),
    EXCEL("02", "FORMATO EXCEL", "application/vnd.openxmlformats-officedocument.spreadsheetml", ".xlsx"),
    WORD("03", "FORMATO WORD", "application/vnd.openxmlformats-officedocument.wordprocessingml", ".docx"),
    HTML("04", "FORMATO HTML", "text/html", ".htm"),
    TXT("05", "FORMATO TEXTO PLANO", "text/plain; charset=UTF-8", ".txt"),
    CSV("06", "FORMATO CSV", "text/csv; charset=UTF-8", ".csv");

    private String codigo;
    private String descripcion;
    private String aplicacion;
    private String extension;

    ReporteFormato(String codigo, String descripcion, String aplicacion, String extension) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.aplicacion = aplicacion;
        this.extension = extension;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

