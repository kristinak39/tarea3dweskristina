package com.kristina.tarea3dweskristina.serviciosImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Planta;
import com.kristina.tarea3dweskristina.repositories.PlantaRepository;
import com.kristina.tarea3dweskristina.servicios.PlantaServicio;

@Service
public class PlantaServicioImpl implements PlantaServicio {

    @Autowired
    private PlantaRepository plantaRepository;

    @Override
    public List<Planta> listarPlantasOrdenadas() {
        return plantaRepository.findAllByOrderByNombreComunAsc();
    }

    @Override
    public boolean existePlantaPorCodigo(String codigo) {
        return plantaRepository.existsByCodigo(codigo);
    }

    @Override
    public Planta registrarPlanta(String codigo, String nombreComun, String nombreCientifico) {
        if (plantaRepository.existsByCodigo(codigo)) {
            throw new RuntimeException("El código de la planta ya está registrado: " + codigo);
        }
        Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
        return plantaRepository.save(planta);
    }

    @Override
    public Planta buscarPorCodigo(String codigo) {
        return plantaRepository.findByCodigoIgnoreCase(codigo) // Cambiamos a findByCodigoIgnoreCase
                .orElseThrow(() -> new RuntimeException("Planta no encontrada con el código: " + codigo));
    }

    @Override
    public Planta modificarNombresPlanta(String codigo, String nuevoNombreComun, String nuevoNombreCientifico) {
        Planta planta = plantaRepository.findByCodigoIgnoreCase(codigo) // Cambiamos a findByCodigoIgnoreCase
                .orElseThrow(() -> new RuntimeException("Planta no encontrada con el código: " + codigo));

        
        planta.setNombreComun(nuevoNombreComun);
        planta.setNombreCientifico(nuevoNombreCientifico);

        
        return plantaRepository.save(planta);
    }
}
