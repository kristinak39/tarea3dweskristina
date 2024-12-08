package com.kristina.tarea3dweskristina;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.fachada.ViveroFachada;

@Component
public class Principal implements CommandLineRunner {

	@Autowired
	private ViveroFachada viveroFachada;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("=== Bienvenido al Sistema de Gestión del Vivero ===");
		viveroFachada.iniciarSistema();
		System.out.println("=== Sistema Finalizado ===");

	}

}
