package com.kristina.tarea3dweskristina.serviciosImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kristina.tarea3dweskristina.modelo.Planta;
import com.kristina.tarea3dweskristina.repositories.PlantaRepository;
import com.kristina.tarea3dweskristina.servicios.PlantaServicio;

@Service
public class PlantaServicioImpl implements PlantaServicio{
	
	@Autowired
	  private final PlantaRepository plantaRepository;

	  public PlantaServicioImpl(PlantaRepository plantaRepository) {
	        this.plantaRepository = plantaRepository;
	    }
	  
	    @Override
	    public List<Planta> findAll(){
	        return plantaRepository.findAll();
	    }

	    @Override
	    public boolean save(Planta planta) {
	        try {
	            plantaRepository.saveAndFlush(planta);
	            return true;
	        } catch (Exception e){
	            return false;
	        }
	    }
	}
