package com.kristina.tarea3dweskristina.fachada;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Ejemplar;
import com.kristina.tarea3dweskristina.modelo.Mensaje;
import com.kristina.tarea3dweskristina.modelo.Persona;
import com.kristina.tarea3dweskristina.repositories.EjemplarRepository;
import com.kristina.tarea3dweskristina.repositories.MensajeRepository;
import com.kristina.tarea3dweskristina.repositories.PersonaRepository;
import com.kristina.tarea3dweskristina.servicios.MensajeServicio;

@Component
public class ViveroFachadaMensaje {

	private final Scanner in = new Scanner(System.in);

	@Autowired
	private MensajeServicio mensajeServicio;

	public void menuGestionMensajes(Long usuarioId) {
		String opcion;

		do {
			try {
				mostrarMenuMensajes();
				opcion = in.nextLine();

				switch (opcion) {
				case "1":
					registrarMensaje();
					break;
				case "2":
					filtrarMensajesPorPersona();
					break;
				case "3":
					filtrarMensajesPorRangoDeFechas();
					break;
				case "4":
					filtrarMensajesPorTipoDePlanta();
					break;
				case "5":
					System.out.println("Volviendo al menú principal...");
					return;
				default:
					System.out.println("Opción no válida, intente nuevamente.");
				}
			} catch (Exception e) {
				System.out.println("Ocurrió un error inesperado: " + e.getMessage());
			}
		} while (true);
	}

	private void mostrarMenuMensajes() {
		System.out.println("\n=== Gestión de Mensajes ===");
		System.out.println("1. Registrar Mensaje");
		System.out.println("2. Filtrar Mensajes por Persona");
		System.out.println("3. Filtrar Mensajes por Rango de Fechas");
		System.out.println("4. Filtrar Mensajes por Tipo de Planta");
		System.out.println("5. Volver");
		System.out.print("Seleccione una opción: ");
	}

	public void registrarMensaje() {
		try {
			System.out.print("Ingrese el ID del ejemplar: ");
			Long ejemplarId = Long.parseLong(in.nextLine().trim());

			System.out.print("Ingrese el ID de la persona: ");
			Long personaId = Long.parseLong(in.nextLine().trim());

			System.out.print("Ingrese el contenido del mensaje: ");
			String contenido = in.nextLine().trim();

			Mensaje mensaje = mensajeServicio.registrarMensaje(contenido, ejemplarId, personaId);
			System.out.println("Mensaje registrado con éxito: " + mensaje.getMensaje());
		} catch (Exception e) {
			System.out.println("Error al registrar el mensaje: " + e.getMessage());
		}
	}

	private void filtrarMensajesPorPersona() {
		System.out.print("Ingrese el ID de la persona: ");
		Long personaId = Long.parseLong(in.nextLine().trim());

		List<Mensaje> mensajes = mensajeServicio.buscarPorPersona(personaId);
		mostrarMensajes(mensajes);
	}

	private void filtrarMensajesPorRangoDeFechas() {
		try {
			System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD HH:mm): ");
			String inicioStr = in.nextLine().trim();
			LocalDateTime inicio = LocalDateTime.parse(inicioStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

			System.out.print("Ingrese la fecha de fin (YYYY-MM-DD HH:mm): ");
			String finStr = in.nextLine().trim();
			LocalDateTime fin = LocalDateTime.parse(finStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

			inicio = inicio.withMinute(0).withSecond(0).withNano(0);
			fin = fin.withMinute(59).withSecond(59).withNano(999_999_999);

			if (inicio.isAfter(fin)) {
				System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
				return;
			}

			List<Mensaje> mensajes = mensajeServicio.buscarPorRangoDeFechas(inicio, fin);
			mostrarMensajes(mensajes);
		} catch (Exception e) {
			System.out.println("Formato de fecha inválido. Por favor, utilice el formato: YYYY-MM-DD HH:mm.");
		}
	}

	private void filtrarMensajesPorTipoDePlanta() {
		System.out.print("Ingrese el tipo de planta: ");
		String tipoPlanta = in.nextLine().trim();

		List<Mensaje> mensajes = mensajeServicio.buscarPorTipoDePlanta(tipoPlanta);
		mostrarMensajes(mensajes);
	}

	private void mostrarMensajes(List<Mensaje> mensajes) {
		if (mensajes.isEmpty()) {
			System.out.println("No se encontraron mensajes.");
		} else {
			System.out.println("\n=== Mensajes Encontrados ===");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
			for (Mensaje mensaje : mensajes) {
				System.out.printf("Fecha: %s | Persona: %s | Mensaje: %s%n", mensaje.getFechaHora().format(formatter),
						mensaje.getPersona().getNombre(), mensaje.getMensaje());
			}
		}
	}

}
