package poo;

public abstract class Prenda {
    protected String nombre;

    public abstract void getTipoPrenda();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
