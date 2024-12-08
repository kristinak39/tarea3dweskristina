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
		if (existePlantaPorCodigo(codigo)) {
			throw new RuntimeException("El codigo de planta ya existe");
		}
		Planta planta = new Planta(codigo, nombreComun, nombreCientifico);
		return plantaRepository.save(planta);
	}

	@Override
	public Planta buscarPorCodigo(String codigo) {
		return plantaRepository.findByCodigo(codigo);
	}

    @Override
    public boolean modificarNombresPlanta(String codigo, String nuevoNombreComun, String nuevoNombreCientifico) {
        Planta planta = plantaRepository.findByCodigo(codigo);
        if (planta == null) {
            throw new RuntimeException("No se encontró la planta con el código proporcionado.");
        }
        planta.setNombreComun(nuevoNombreComun);
        planta.setNombreCientifico(nuevoNombreCientifico);
        plantaRepository.save(planta);
        return true;
    }
}