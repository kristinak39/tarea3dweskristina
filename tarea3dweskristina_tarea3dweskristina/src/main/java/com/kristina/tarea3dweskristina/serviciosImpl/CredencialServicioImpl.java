package com.kristina.tarea3dweskristina.serviciosImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Credenciales;
import com.kristina.tarea3dweskristina.repositories.CredencialRepository;
import com.kristina.tarea3dweskristina.repositories.PersonaRepository;
import com.kristina.tarea3dweskristina.servicios.CredencialServicio;

@Service
public class CredencialServicioImpl implements CredencialServicio {

	@Autowired
	private CredencialRepository credencialRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public boolean existeUsuario(String usuario) {

		return credencialRepository.existsByUsuario(usuario);
	}

	@Override
	public boolean existeEmail(String email) {

		return personaRepository.existsByEmail(email);
	}

	@Override
	public Credenciales autenticar(String usuario, String password) {
		System.out.println("Usuario ingresado: " + usuario + ", Contraseña ingresada: " + password);
		Credenciales credenciales = credencialRepository.findByUsuarioAndPassword(usuario, password);
		if (credenciales == null) {
			throw new RuntimeException("Usuario o contraseña incorrectos");
		}
		return credenciales;
	}

	@Override
	public Credenciales registrarCredencial(String usuario, String password, Long personaId) {

		var persona = personaRepository.findById(personaId)
				.orElseThrow(() -> new RuntimeException("Persona no encontrada"));

		Credenciales credenciales = new Credenciales(usuario, password, persona);
		return credencialRepository.save(credenciales);
	}
}
