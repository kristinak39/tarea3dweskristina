package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.servicios.PersonaServicio;



@Component
public class ViveroFachadaPersona {

	Scanner in = new Scanner(System.in);
	
	@Autowired
	private PersonaServicio personaServ;
	
	public void menuGestionPersonas() {
		String opcion;
		
		do {
			mostrarMenuPersonas();
			opcion=in.nextLine();
			
			switch(opcion) {
			case "1":
				registrarPersona();
				break;
			case "2":
				buscarPersonaPorEmail();
				break;
			case "3":
				System.out.println("Volviendo al menu principal...");
				break;
				default:
					System.out.println("Opcion no valida, intente nuevamente");
			}
		}while(!opcion.equals("3"));
	}
	
	  private void mostrarMenuPersonas() {
	        System.out.println("\n=== Gestión de Personas ===");
	        System.out.println("1. Registrar Persona");
	        System.out.println("2. Buscar Persona por Email");
	        System.out.println("3. Volver");
	        System.out.print("Seleccione una opción: ");
	    }
	  
	  private void registrarPersona() {
	        System.out.print("Ingrese el nombre de la persona: ");
	        String nombre = in.nextLine();
	        System.out.print("Ingrese el email de la persona: ");
	        String email = in.nextLine();

	        try {
	            personaServ.registrarPersona(nombre, email);
	            System.out.println("Persona registrada con éxito.");
	        } catch (RuntimeException e) {
	            System.out.println("Error al registrar a la persona: " + e.getMessage());
	        }
	    }
	  private void buscarPersonaPorEmail() {
	        System.out.print("Ingrese el email de la persona a buscar: ");
	        String email = in.nextLine();

	        try {
	            var persona = personaServ.buscarPorEmail(email);
	            System.out.println("Persona encontrada: " + persona.getNombre() + " - " + persona.getEmail());
	        } catch (RuntimeException e) {
	            System.out.println("Error al buscar la persona: " + e.getMessage());
	        }
	    }
	}