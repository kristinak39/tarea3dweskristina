package com.kristina.tarea3dweskristina.servicios;

import java.time.LocalDateTime;
import java.util.List;

import com.kristina.tarea3dweskristina.modelo.Mensaje;

public interface MensajeServicio {

	Mensaje registrarMensaje(String contenido, Long ejemplarId, Long personaId);

	List<Mensaje> buscarPorPersona(Long personaId);

	List<Mensaje> buscarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin);

	List<Mensaje> buscarPorTipoDePlanta(String tipoPlanta);

	List<Mensaje> obtenerMensajesPorEjemplar(Long idEjemplar);

}
