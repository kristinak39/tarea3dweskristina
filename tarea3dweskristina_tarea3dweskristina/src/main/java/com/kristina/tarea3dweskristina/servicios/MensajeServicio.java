package com.kristina.tarea3dweskristina.servicios;

import java.time.LocalDateTime;
import java.util.List;

import com.kristina.tarea3dweskristina.modelo.Mensaje;

public interface MensajeServicio {

	List<Mensaje> buscarPorEjemplar(Long ejemplarId);

	List<Mensaje> buscarPorRangoDeFechas(LocalDateTime inicio, LocalDateTime fin);

	Mensaje registrarMensaje(String contenido, Long ejemplarId, Long personaId);
}
