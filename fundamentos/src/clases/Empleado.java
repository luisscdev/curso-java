package clases;

public class Empleado extends Persona{
    private String idEmpleado;
    private String emailInstitucion;
    public void mostrar() {
        System.out.println("Es un empleado");
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmailInstitucion() {
        return emailInstitucion;
    }

    public void setEmailInstitucion(String emailInstitucion) {
        this.emailInstitucion = emailInstitucion;
    }
}
