package com.kristina.tarea3dweskristina.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

	@Query("SELECT m FROM Mensaje m WHERE m.persona.id = :personaId")
	List<Mensaje> findByPersonaId(@Param("personaId") Long personaId);

	@Query("SELECT m FROM Mensaje m WHERE m.fechaHora BETWEEN :inicio AND :fin")
	List<Mensaje> findByFechaHoraBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

	@Query("SELECT m FROM Mensaje m WHERE m.ejemplar.planta.nombreComun = :tipoPlanta")
	List<Mensaje> findByPlantaTipo(@Param("tipoPlanta") String tipoPlanta);

	@Query("SELECT m FROM Mensaje m WHERE m.ejemplar.id = :idEjemplar ORDER BY m.fechaHora ASC")
	List<Mensaje> findMensajesByEjemplarId(@Param("idEjemplar") Long idEjemplar);

}
