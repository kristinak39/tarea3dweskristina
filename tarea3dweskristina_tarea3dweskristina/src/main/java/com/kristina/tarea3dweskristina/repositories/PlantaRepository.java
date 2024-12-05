package com.kristina.tarea3dweskristina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {

}
