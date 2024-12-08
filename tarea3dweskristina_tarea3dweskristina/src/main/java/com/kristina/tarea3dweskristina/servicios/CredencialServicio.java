package com.kristina.tarea3dweskristina.servicios;

import com.kristina.tarea3dweskristina.modelo.Credenciales;

public interface CredencialServicio {

	boolean existeUsuario(String usuario);

	boolean existeEmail(String email);

	Credenciales autenticar(String usuario, String password);

	Credenciales registrarCredencial(String usuario, String password, Long personaId);
}
