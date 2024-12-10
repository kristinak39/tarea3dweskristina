package com.kristina.tarea3dweskristina.serviciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Persona;
import com.kristina.tarea3dweskristina.repositories.PersonaRepository;
import com.kristina.tarea3dweskristina.servicios.PersonaServicio;

@Service
public class PersonaServicioImpl implements PersonaServicio {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Persona buscarPorEmail(String email) {
		return personaRepository.findByEmail(email);
	}

	@Override
	public Persona registrarPersona(String nombre, String email) {
		// para que el formato sea valido
		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			throw new RuntimeException("Formato de email inválido: " + email);
		}
		//que no este registrado previamente
		if (personaRepository.existsByEmail(email)) {
			throw new RuntimeException("El email ya esta registrado: " + email);
		}
		Persona persona = new Persona(nombre, email);
		return personaRepository.save(persona);
	}

}
