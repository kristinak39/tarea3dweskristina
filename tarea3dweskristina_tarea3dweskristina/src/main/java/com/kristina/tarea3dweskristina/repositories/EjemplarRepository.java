package com.kristina.tarea3dweskristina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {

	@Query("SELECT e FROM Ejemplar e WHERE e.planta.codigo IN :codigosPlanta")
	List<Ejemplar> findByPlantaCodigoIn(List<String> codigosPlanta); // Filtrar ejemplares por planta

	@Query("SELECT e.nombre AS nombre, COUNT(m.id) AS numMensajes, MAX(m.fechaHora) AS ultimoMensaje "
			+ "FROM Ejemplar e LEFT JOIN e.mensajes m " + "WHERE e.planta.codigo IN :codigosPlanta " + "GROUP BY e.id")
	List<Object[]> obtenerEjemplaresFiltrados(List<String> codigosPlanta); // Filtrar y agrupar mensajes
}