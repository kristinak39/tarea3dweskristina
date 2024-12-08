package com.kristina.tarea3dweskristina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, String> {

    List<Planta> findAllByOrderByNombreComunAsc(); 

    boolean existsByCodigo(String codigo); 

    Planta findByCodigo(String codigo); 
}