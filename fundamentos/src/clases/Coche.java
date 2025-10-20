package clases;

public class Coche {
    private String nombre;
    private String color;
    private Integer numRuedas;
    private String motor;

    public Coche() {

    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(Integer numRuedas) {
        this.numRuedas = numRuedas;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
