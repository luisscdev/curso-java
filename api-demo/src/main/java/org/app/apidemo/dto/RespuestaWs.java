package org.app.apidemo.dto;

public class RespuestaWs {
    private Boolean estado;
    private Object data;
    private String mensaje;

    public RespuestaWs(){

    }

    public RespuestaWs(Boolean estado, Object data, String mensaje) {
        this.estado = estado;
        this.data = data;
        this.mensaje = mensaje;
    }



    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
