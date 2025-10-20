package estructuraDatos;

import clases.Empleado;
import clases.Persona;
import clases.Proveedor;
import poo.Camisa;
import poo.Pantalon;
import poo.Prenda;

import java.util.ArrayList;
import java.util.List;

public class PilaMain {
    public static void main(String[] args) {

        List<Prenda> prendas = new ArrayList<>();
        Prenda camisa = new Camisa();
        camisa.setNombre("CAMISA POLO");
        Prenda pantalon = new Pantalon();
        pantalon.setNombre("PANTALON TOMMY");

        prendas.add(camisa);
        prendas.add(pantalon);


        prendas.forEach(data -> data.getTipoPrenda());

    }
}
