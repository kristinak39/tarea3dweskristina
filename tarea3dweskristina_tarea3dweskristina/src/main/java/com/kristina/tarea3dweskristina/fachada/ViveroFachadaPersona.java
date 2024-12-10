package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Persona;
import com.kristina.tarea3dweskristina.repositories.PersonaRepository;
import com.kristina.tarea3dweskristina.servicios.CredencialServicio;
import com.kristina.tarea3dweskristina.servicios.PersonaServicio;

@Component
public class ViveroFachadaPersona {

	private final Scanner in = new Scanner(System.in);
	
	@Autowired
	private PersonaServicio personaServicio;
	
	@Autowired
	private CredencialServicio credencialServicio;
	
	
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
		}while(true);
	}
	
	  private void mostrarMenuPersonas() {
	        System.out.println("\n=== Gestión de Personas ===");
	        System.out.println("1. Registrar Persona");
	        System.out.println("2. Buscar Persona por Email");
	        System.out.println("3. Volver");
	        System.out.print("Seleccione una opción: ");
	    }
	  
	  public void registrarPersona() {
		    System.out.print("Ingrese el nombre de la persona: ");
		    String nombre = in.nextLine();
		    System.out.print("Ingrese el email de la persona: ");
		    String email = in.nextLine();
		    System.out.print("Ingrese el nombre de usuario: ");
		    String usuario = in.nextLine();
		    System.out.print("Ingrese la contraseña: ");
		    String password = in.nextLine();

		    try {
		        // registrar persona
		        Persona persona = personaServicio.registrarPersona(nombre, email);

		        // registrar credenciales asociadas
		        credencialServicio.registrarCredencial(usuario, password, persona.getId());

		        System.out.println("Persona registrada con éxito.");
		    } catch (RuntimeException e) {
		        System.out.println("Error al registrar a la persona: " + e.getMessage());
		    }
		}



	  private void buscarPersonaPorEmail() {
	        System.out.print("Ingrese el email de la persona a buscar: ");
	        String email = in.nextLine();

	        try {
	            var persona = personaServicio.buscarPorEmail(email);
	           if(persona !=null) {
	        	   System.out.println("Persona encontrada: " + persona.getNombre() + " - " + persona.getEmail());
	           }else {
	        	   System.out.println("No se encontro ninguna persona con el email: " + email);
	           }
	        } catch (RuntimeException e) {
	            System.out.println("Error al buscar la persona: " + e.getMessage());
	        }
	    }
	}