package claseInterface;

import clases.Coche;

import java.util.List;

public class MotoImpl implements CocheService {
    @Override
    public List<Coche> findByAll() {
        Coche coche = new Coche();
        coche.setNombre("MOTO 1");
        return List.of(coche);
    }
}
