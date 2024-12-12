package com.kristina.tarea3dweskristina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Credenciales;

@Repository
public interface CredencialRepository extends JpaRepository<Credenciales, Long> {

	boolean existsByUsuario(String usuario);

	Credenciales findByUsuarioAndPassword(String usuario, String password);
}
