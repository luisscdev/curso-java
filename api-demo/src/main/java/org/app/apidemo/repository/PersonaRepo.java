package org.app.apidemo.repository;

import org.app.apidemo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepo extends JpaRepository<Persona, Integer> {

    List<Persona> findAllByEstadoOrderByIdAsc(String estado);


}
