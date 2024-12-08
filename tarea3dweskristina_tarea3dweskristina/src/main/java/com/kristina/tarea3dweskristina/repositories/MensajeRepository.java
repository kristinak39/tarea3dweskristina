package com.kristina.tarea3dweskristina.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

	List<Mensaje> findByEjemplarId(Long ejemplarId);

	@Query("SELECT m FROM Mensaje m WHERE m.fechaHora BETWEEN :inicio AND :fin")
	List<Mensaje> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);

	@Query("SELECT m FROM Mensaje m WHERE m.ejemplar.planta.nombreComun = ?1")
	List<Mensaje> findByPlantaTipo(String tipoPlanta);


}
