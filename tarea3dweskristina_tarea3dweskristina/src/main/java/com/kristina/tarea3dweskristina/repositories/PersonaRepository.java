package com.kristina.tarea3dweskristina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kristina.tarea3dweskristina.modelo.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	boolean existsByEmail(String email);

	Persona findByEmail(String email);
}
