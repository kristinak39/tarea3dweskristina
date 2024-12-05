package com.kristina.tarea3dweskristina.servicios;

import java.util.List;

import com.kristina.tarea3dweskristina.modelo.Planta;

public interface PlantaServicio {

	List<Planta> findAll();
	boolean save(Planta planta);
}
