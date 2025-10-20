package clases;

public class Proveedor extends Persona {
    private String empresa;

    public Proveedor() {
        super("Luis");
    }

    public void mostrar() {
        System.out.println("Es un proveedor");
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
