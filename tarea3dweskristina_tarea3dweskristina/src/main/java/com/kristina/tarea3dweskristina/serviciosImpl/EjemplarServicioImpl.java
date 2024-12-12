package com.kristina.tarea3dweskristina.serviciosImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Planta;
import com.kristina.tarea3dweskristina.repositories.EjemplarRepository;
import com.kristina.tarea3dweskristina.repositories.MensajeRepository;
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

	@Autowired
	private MensajeRepository mensajeRepository;

	@Override
	public List<Ejemplar> buscarPorPlantas(List<String> codigoPlanta) {
		if (codigoPlanta == null || codigoPlanta.isEmpty()) {
			throw new RuntimeException("La lista de códigos de planta no puede estar vacía.");
		}
		return ejemplarRepository.findByPlantaCodigoIn(codigoPlanta);
	}

	@Override
	public Ejemplar registrarEjemplar(String codigoPlanta) {
		if (codigoPlanta == null || codigoPlanta.trim().isEmpty()) {
			throw new IllegalArgumentException("El código de planta no puede estar vacío.");
		}

		Planta planta = plantaRepository.findByCodigoIgnoreCase(codigoPlanta)
				.orElseThrow(() -> new RuntimeException("La planta especificada no existe: " + codigoPlanta));

		String nombreTemporal = "TEMP_" + UUID.randomUUID().toString();

		Ejemplar ejemplar = new Ejemplar();
		ejemplar.setPlanta(planta);
		ejemplar.setNombre(nombreTemporal);

		Ejemplar ejemplarGuardado = ejemplarRepository.save(ejemplar);

		String nombreGenerado = planta.getCodigo() + "_" + ejemplarGuardado.getId();
		ejemplarGuardado.setNombre(nombreGenerado);

		return ejemplarRepository.save(ejemplarGuardado);
	}

	@Override
	public boolean existeEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("El email no puede estar vacío.");
		}
		return personaRepository.existsByEmail(email);
	}

	@Override
	public List<Ejemplar> filtrarEjemplaresPorPlantas(List<String> codigosPlanta) {
		if (codigosPlanta == null || codigosPlanta.isEmpty()) {
			throw new RuntimeException("Debe proporcionar al menos un código de planta para filtrar.");
		}
		return ejemplarRepository.findByPlantaCodigoIn(codigosPlanta);
	}
}
