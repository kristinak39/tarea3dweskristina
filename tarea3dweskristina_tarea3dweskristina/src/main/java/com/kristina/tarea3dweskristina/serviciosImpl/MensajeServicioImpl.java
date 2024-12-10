package com.kristina.tarea3dweskristina.serviciosImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Mensaje;
import com.kristina.tarea3dweskristina.modelo.Persona;
import com.kristina.tarea3dweskristina.repositories.EjemplarRepository;
import com.kristina.tarea3dweskristina.repositories.MensajeRepository;
import com.kristina.tarea3dweskristina.repositories.PersonaRepository;
import com.kristina.tarea3dweskristina.servicios.MensajeServicio;

@Service
public class MensajeServicioImpl implements MensajeServicio {

	@Autowired
	private MensajeRepository mensajeRepository;

	@Autowired
	private EjemplarRepository ejemplarRepository;
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Mensaje> buscarPorEjemplar(Long ejemplarId) {
		return mensajeRepository.findByEjemplarId(ejemplarId);
	}

	

	@Override
	public Mensaje registrarMensaje(String contenido, Long ejemplarId, Long personaId) {
	    Ejemplar ejemplar = ejemplarRepository.findById(ejemplarId)
	            .orElseThrow(() -> new RuntimeException("El ejemplar especificado no existe."));
	    
	    Persona persona = personaRepository.findById(personaId)
	            .orElseThrow(() -> new RuntimeException("La persona especificada no existe."));
	    
	    Mensaje mensaje = new Mensaje(contenido, LocalDateTime.now(), ejemplar, persona);
	    return mensajeRepository.save(mensaje);
	}



	@Override
	public List<Mensaje> buscarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin) {
		return mensajeRepository.findByFechaHoraBetween(inicio, fin);
	}

	

}
