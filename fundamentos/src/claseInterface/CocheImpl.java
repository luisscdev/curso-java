package claseInterface;

import clases.Coche;

import java.util.List;

public class CocheImpl implements CocheService {
    @Override
    public List<Coche> findByAll() {
        Coche coche = new Coche();
        coche.setNombre("FERRARI");
        Coche coche2 = new Coche();
        coche2.setNombre("TIGO");
        return List.of(coche, coche2);
    }
}
