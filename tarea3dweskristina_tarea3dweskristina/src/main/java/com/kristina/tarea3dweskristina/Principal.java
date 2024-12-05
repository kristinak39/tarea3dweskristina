package com.kristina.tarea3dweskristina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.kristina.tarea3dweskristina.serviciosImpl.PlantaServicioImpl;

public class Principal implements CommandLineRunner{

	
	@Autowired
	private PlantaServicioImpl plantaServicio;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("INI");
		
		
		System.out.println(plantaServicio.findAll());
		
		
		
		System.out.println("FIN");
		
	}

}
