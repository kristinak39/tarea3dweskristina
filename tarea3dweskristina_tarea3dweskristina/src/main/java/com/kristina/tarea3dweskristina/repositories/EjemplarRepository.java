package com.kristina.tarea3dweskristina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Mensaje;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

	@Query("SELECT e FROM Ejemplar e WHERE e.planta.codigo IN :codigosPlanta")
	List<Ejemplar> findByPlantaCodigoIn(List<String> codigosPlanta);

	@Query("SELECT e.nombre AS nombre, COUNT(m.id) AS numMensajes, MAX(m.fechaHora) AS ultimoMensaje "
			+ "FROM Ejemplar e LEFT JOIN e.mensajes m " + "WHERE e.planta.codigo IN :codigosPlanta " + "GROUP BY e.id")
	List<Object[]> obtenerEjemplaresFiltrados(List<String> codigosPlanta);

	@Query("SELECT m FROM Mensaje m JOIN m.ejemplar e JOIN m.persona p WHERE e.id = :idEjemplar ORDER BY m.fechaHora ASC")
	List<Mensaje> findMensajesByEjemplarId(@Param("idEjemplar") Long idEjemplar);

}