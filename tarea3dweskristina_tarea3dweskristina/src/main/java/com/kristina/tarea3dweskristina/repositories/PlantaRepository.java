package com.kristina.tarea3dweskristina.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Planta;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, String> {

    List<Planta> findAllByOrderByNombreComunAsc(); 

    boolean existsByCodigo(String codigo); 

    @Query("SELECT p FROM Planta p WHERE p.codigo = :codigo")
    Optional<Planta> findByCodigoIgnoreCase(@Param("codigo") String codigo);

}