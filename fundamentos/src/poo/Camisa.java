package poo;

public class Camisa extends Prenda {
    private String tipo;

    @Override
    public void getTipoPrenda() {
        System.out.println(getNombre().concat(" es una prenda de categoria Camisa").toUpperCase());
    }
}
