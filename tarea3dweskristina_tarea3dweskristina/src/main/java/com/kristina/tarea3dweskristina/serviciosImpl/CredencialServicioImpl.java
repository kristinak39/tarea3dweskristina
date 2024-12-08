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
        // Corrección del método: usar existsByUsuario
        return credencialRepository.existsByUsuario(usuario);
    }

    @Override
    public boolean existeEmail(String email) {
        // Usar PersonaRepository para verificar el email
        return personaRepository.existsByEmail(email);
    }

    @Override
    public Credenciales autenticar(String usuario, String password) {
        // Buscar credenciales con usuario y password
        return credencialRepository.findByUsuarioAndPassword(usuario, password);
    }

    @Override
    public Credenciales registrarCredencial(String usuario, String password, Long personaId) {
        // Buscar la persona por ID y lanzar excepción si no existe
        var persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        // Crear nueva instancia de Credenciales y guardar en el repositorio
        Credenciales credenciales = new Credenciales(usuario, password, persona);
        return credencialRepository.save(credenciales);
    }
}
