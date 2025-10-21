package org.app.demo.model;

public class RespuestaWs {
    private Long id;
    private String data;
    private String mensaje;
    private Boolean estado;

    public RespuestaWs() {

    }

    public RespuestaWs(Boolean estado, String data, String mensaje) {
        this.estado = estado;
        this.data = data;
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
