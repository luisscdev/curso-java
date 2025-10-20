package poo;

public class Pantalon extends Prenda {
    @Override
    public void getTipoPrenda() {
        System.out.println(getNombre().concat(" es una prenda de categoria Pantalon").toUpperCase());
    }
}
