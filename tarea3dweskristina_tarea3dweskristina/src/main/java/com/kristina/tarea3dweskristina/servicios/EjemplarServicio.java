package com.kristina.tarea3dweskristina.servicios;

import java.util.List;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;

public interface EjemplarServicio {

	List<Ejemplar> filtrarEjemplaresPorPlantas(List<String> codigosPlanta);

	List<Ejemplar> buscarPorPlantas(List<String> codigoPlanta);

	Ejemplar registrarEjemplar(String codigoPlanta);

	boolean existeEmail(String email);
}
