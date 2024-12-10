package com.kristina.tarea3dweskristina.servicios;

import java.util.List;


import com.kristina.tarea3dweskristina.modelo.Planta;

public interface PlantaServicio {

	List<Planta> listarPlantasOrdenadas();

	boolean existePlantaPorCodigo(String codigo);

	Planta registrarPlanta(String codigo, String nombreComun, String nombreCientifico);

	Planta buscarPorCodigo(String codigo);
	
	Planta modificarNombresPlanta(String codigo, String nuevoNombreComun, String nuevoNombreCientifico);
}
