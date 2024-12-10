package com.kristina.tarea3dweskristina.fachada;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.servicios.EjemplarServicio;

@Component
public class ViveroFachadaEjemplar {
	
	private final Scanner in = new Scanner(System.in);
	
	@Autowired
	private EjemplarServicio ejemplarServ;

	 public void menuGestionEjemplares() {
	        String opcion;

	        do {
	            mostrarMenuEjemplares();
	            opcion = in.nextLine();

	            switch (opcion) {
	                case "1":
	                    registrarEjemplar();
	                    break;
	                case "2":
	                    listarEjemplaresPorPlanta();
	                    break;
	                case "3":
	                    System.out.println("Volviendo al menú principal...");
	                    return;
	                default:
	                    System.out.println("Opción no válida, intente nuevamente.");
	            }
	        } while (true);
	    }
	 
	  private void mostrarMenuEjemplares() {
	        System.out.println("\n=== Gestión de Ejemplares ===");
	        System.out.println("1. Registrar Ejemplar");
	        System.out.println("2. Listar Ejemplares por Planta");
	        System.out.println("3. Volver");
	        System.out.print("Seleccione una opción: ");
	    }
	  
	  private void registrarEjemplar() {
		    System.out.print("Ingrese el código de la planta asociada: ");
		    String codigoPlanta = in.nextLine().trim();

		    try {
		        // Registrar el ejemplar
		        Ejemplar ejemplar = ejemplarServ.registrarEjemplar(codigoPlanta);
		        System.out.println("Ejemplar registrado con éxito. Nombre generado: " + ejemplar.getNombre());
		    } catch (RuntimeException e) {
		        System.out.println("Error al registrar el ejemplar: " + e.getMessage());
		    }
		}



	  private void listarEjemplaresPorPlanta() {
		    System.out.print("Ingrese el código de la planta: ");
		    String codigoPlanta = in.nextLine().trim();

		    try {
		        var ejemplares = ejemplarServ.buscarPorPlantas(List.of(codigoPlanta));
		        if (!ejemplares.isEmpty()) {
		            System.out.println("\n=== Ejemplares Asociados ===");
		            ejemplares.forEach(ejemplar -> 
		                System.out.printf("ID: %d | Nombre: %s%n", ejemplar.getId(), ejemplar.getNombre())
		            );
		        } else {
		            System.out.println("No se encontraron ejemplares para la planta especificada.");
		        }
		    } catch (RuntimeException e) {
		        System.out.println("Error: " + e.getMessage());
		    }
		}

	}
