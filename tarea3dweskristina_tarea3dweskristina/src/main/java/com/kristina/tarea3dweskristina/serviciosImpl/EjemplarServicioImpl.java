package com.kristina.tarea3dweskristina.serviciosImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Planta;
import com.kristina.tarea3dweskristina.repositories.EjemplarRepository;
import com.kristina.tarea3dweskristina.repositories.PlantaRepository;
import com.kristina.tarea3dweskristina.servicios.EjemplarServicio;

@Service
public class EjemplarServicioImpl implements EjemplarServicio {

	@Autowired
	private EjemplarRepository ejemplarRepository;

	@Autowired
	private PlantaRepository plantaRepository;

	@Override
	public List<Ejemplar> buscarPorPlantas(List<String> codigoPlanta) {
		return ejemplarRepository.findByPlantaCodigoIn(codigoPlanta);
	}

	@Override
	public Ejemplar registrarEjemplar(String nombre, String codigoPlanta) {
		Planta planta = plantaRepository.findByCodigo(codigoPlanta);
		if (planta == null) {
			throw new RuntimeException("La planta especificada no existe.");
		}
		Ejemplar ejemplar = new Ejemplar(nombre, planta);
		return ejemplarRepository.save(ejemplar);
	}
}
