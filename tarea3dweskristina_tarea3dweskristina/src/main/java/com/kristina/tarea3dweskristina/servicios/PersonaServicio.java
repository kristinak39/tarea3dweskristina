package com.kristina.tarea3dweskristina.servicios;

import com.kristina.tarea3dweskristina.modelo.Persona;

public interface PersonaServicio {

	Persona buscarPorEmail(String email);

	Persona registrarPersona(String nombre, String email);

}
