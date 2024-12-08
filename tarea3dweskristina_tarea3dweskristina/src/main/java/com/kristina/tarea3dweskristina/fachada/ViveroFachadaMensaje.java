package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.servicios.MensajeServicio;

@Component
public class ViveroFachadaMensaje {
	
	Scanner in =new Scanner(System.in);
	
	 @Autowired
	 private MensajeServicio mensajeServ;
	 
	  public void menuGestionMensajes() {
	        String opcion;

	        do {
	            mostrarMenuMensajes();
	            opcion = in.nextLine();

	            switch (opcion) {
	                case "1":
	                    registrarMensaje();
	                    break;
	                case "2":
	                    listarMensajesPorEjemplar();
	                    break;
	                case "3":
	                    System.out.println("Volviendo al menú principal...");
	                    break;
	                default:
	                    System.out.println("Opción no válida, intente nuevamente.");
	            }
	        } while (!opcion.equals("3"));
	    }
	  private void mostrarMenuMensajes() {
	        System.out.println("\n=== Gestión de Mensajes ===");
	        System.out.println("1. Registrar Mensaje");
	        System.out.println("2. Listar Mensajes por Ejemplar");
	        System.out.println("3. Volver");
	        System.out.print("Seleccione una opción: ");
	    }
	  
	  private void registrarMensaje() {
	        System.out.print("Ingrese el ID del ejemplar: ");
	        Long ejemplarId = Long.parseLong(in.nextLine());
	        System.out.print("Ingrese el contenido del mensaje: ");
	        String contenido = in.nextLine();

	        try {
	            mensajeServ.registrarMensaje(contenido, ejemplarId);
	            System.out.println("Mensaje registrado con éxito.");
	        } catch (RuntimeException e) {
	            System.out.println("Error al registrar el mensaje: " + e.getMessage());
	        }
	    }
	  private void listarMensajesPorEjemplar() {
	        System.out.print("Ingrese el ID del ejemplar: ");
	        Long ejemplarId = Long.parseLong(in.nextLine());

	        var mensajes = mensajeServ.buscarPorEjemplar(ejemplarId);
	        if (!mensajes.isEmpty()) {
	            mensajes.forEach(mensaje ->
	                System.out.println(mensaje.getId() + " - " + mensaje.getMensaje() + " [" + mensaje.getFechaHora() + "]")
	            );
	        } else {
	            System.out.println("No se encontraron mensajes para el ejemplar especificado.");
	        }
	    }
	}