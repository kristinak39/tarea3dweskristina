package com.kristina.tarea3dweskristina.serviciosImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Planta;
import com.kristina.tarea3dweskristina.repositories.EjemplarRepository;
import com.kristina.tarea3dweskristina.repositories.PersonaRepository;
import com.kristina.tarea3dweskristina.repositories.PlantaRepository;
import com.kristina.tarea3dweskristina.servicios.EjemplarServicio;

@Service
public class EjemplarServicioImpl implements EjemplarServicio {

	@Autowired
	private EjemplarRepository ejemplarRepository;

	@Autowired
	private PlantaRepository plantaRepository;
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Ejemplar> buscarPorPlantas(List<String> codigoPlanta) {
		if (codigoPlanta == null || codigoPlanta.isEmpty()) {
			throw new RuntimeException("La lista de codigo de planta no puede estar vacia");
		}
		return ejemplarRepository.findByPlantaCodigoIn(codigoPlanta);
	}
	@Override
	public Ejemplar registrarEjemplar(String codigoPlanta) {
	    // Busca la planta por su código (case insensitive)
	    Planta planta = plantaRepository.findByCodigoIgnoreCase(codigoPlanta)
	            .orElseThrow(() -> new RuntimeException("La planta especificada no existe: " + codigoPlanta));

	    // Genera un nombre temporal único basado en el timestamp
	    String nombreTemporal = "TEMP_" + System.currentTimeMillis();

	    // Crea el ejemplar y asocia la planta
	    Ejemplar ejemplar = new Ejemplar();
	    ejemplar.setPlanta(planta);
	    ejemplar.setNombre(nombreTemporal); // Asigna el nombre temporal para evitar duplicados

	    // Guarda el ejemplar inicialmente
	    Ejemplar ejemplarGuardado = ejemplarRepository.save(ejemplar);

	    // Genera el nombre final basado en la planta y el ID
	    String nombreGenerado = planta.getCodigo() + "_" + ejemplarGuardado.getId();
	    ejemplarGuardado.setNombre(nombreGenerado);

	    // Actualiza el ejemplar con el nombre generado
	    return ejemplarRepository.save(ejemplarGuardado);
	}






	@Override
	public boolean existeEmail(String email) {

		return personaRepository.existsByEmail(email);
	}
}
