package com.kristina.tarea3dweskristina.servicios;

import java.util.List;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;

public interface EjemplarServicio {

	List<Ejemplar> buscarPorPlantas(List<String> codigoPlanta);

	Ejemplar registrarEjemplar(String nombre, String codigoPlanta);
}
