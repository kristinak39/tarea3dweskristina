package com.kristina.tarea3dweskristina.fachada;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Mensaje;
import com.kristina.tarea3dweskristina.servicios.EjemplarServicio;
import com.kristina.tarea3dweskristina.servicios.MensajeServicio;

@Component
public class ViveroFachadaEjemplar {

	private final Scanner in = new Scanner(System.in);

	@Autowired
	private EjemplarServicio ejemplarServicio;

	@Autowired
	private MensajeServicio mensajeServicio;

	public void menuGestionEjemplares() {
		String opcion;

		do {
			mostrarMenuEjemplares();
			opcion = in.nextLine().trim();

			switch (opcion) {
			case "1":
				registrarEjemplar();
				break;
			case "2":
				listarEjemplaresPorPlanta();
				break;
			case "3":
				filtrarEjemplaresPorPlantas();
				break;
			case "4":
				verMensajesDeEjemplar();
				break;
			case "5":
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
		System.out.println("3. Filtrar Ejemplares por Tipos de Plantas");
		System.out.println("4. Ver Mensajes de un Ejemplar"); // Nueva opción
		System.out.println("5. Volver");
		System.out.print("Seleccione una opción: ");
	}

	private void registrarEjemplar() {
		System.out.print("Ingrese el código de la planta asociada: ");
		String codigoPlanta = in.nextLine().trim();

		try {
			// Registrar el ejemplar
			Ejemplar ejemplar = ejemplarServicio.registrarEjemplar(codigoPlanta);
			System.out.println("Ejemplar registrado con éxito. Nombre generado: " + ejemplar.getNombre());
		} catch (RuntimeException e) {
			System.out.println("Error al registrar el ejemplar: " + e.getMessage());
		}
	}

	private void listarEjemplaresPorPlanta() {
		System.out.print("Ingrese el código de la planta: ");
		String codigoPlanta = in.nextLine().trim();

		if (codigoPlanta.isEmpty()) {
			System.out.println("Debe ingresar un código válido.");
			return;
		}

		try {
			List<Ejemplar> ejemplares = ejemplarServicio.buscarPorPlantas(List.of(codigoPlanta));
			if (!ejemplares.isEmpty()) {
				System.out.println("\n=== Ejemplares Asociados ===");
				ejemplares.forEach(
						ejemplar -> System.out.printf("ID: %d | Nombre: %s%n", ejemplar.getId(), ejemplar.getNombre()));
			} else {
				System.out.println("No se encontraron ejemplares para la planta especificada.");
			}
		} catch (RuntimeException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void filtrarEjemplaresPorPlantas() {
		System.out.print("Ingrese los códigos de las plantas separados por comas: ");
		String input = in.nextLine().trim();

		if (input.isEmpty()) {
			System.out.println("Debe proporcionar al menos un código de planta.");
			return;
		}

		List<String> codigosPlanta = List.of(input.split(",")).stream().map(String::trim).toList();

		try {
			List<Ejemplar> ejemplares = ejemplarServicio.filtrarEjemplaresPorPlantas(codigosPlanta);
			if (ejemplares.isEmpty()) {
				System.out.println("No se encontraron ejemplares para los códigos de planta proporcionados.");
			} else {
				System.out.println("\n=== Ejemplares encontrados ===");
				ejemplares.forEach(ejemplar -> System.out.println("ID: " + ejemplar.getId() + ", Nombre: "
						+ ejemplar.getNombre() + ", Planta: " + ejemplar.getPlanta().getNombreComun()));
			}
		} catch (RuntimeException e) {
			System.out.println("Error al filtrar los ejemplares: " + e.getMessage());
		}
	}

	public void verMensajesDeEjemplar() {
		try {
			System.out.print("Ingrese el ID del ejemplar para ver sus mensajes: ");
			Long idEjemplar = Long.parseLong(in.nextLine().trim());

			List<Mensaje> mensajes = mensajeServicio.obtenerMensajesPorEjemplar(idEjemplar);

			if (mensajes.isEmpty()) {
				System.out.println("No hay mensajes asociados a este ejemplar.");
			} else {
				System.out.println("\n=== Mensajes del Ejemplar ===");
				mensajes.forEach(mensaje -> System.out.printf("Fecha: %s | Persona: %s | Mensaje: %s%n",
						mensaje.getFechaHora(), mensaje.getPersona().getNombre(), mensaje.getMensaje()));
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: El ID del ejemplar debe ser un número.");
		} catch (RuntimeException e) {
			System.out.println("Error al recuperar los mensajes: " + e.getMessage());
		}
	}

}
