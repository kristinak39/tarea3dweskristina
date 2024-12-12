package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Planta;
import com.kristina.tarea3dweskristina.servicios.PlantaServicio;

@Component
public class ViveroFachadaPlanta {

	private final Scanner in = new Scanner(System.in);

	@Autowired
	private PlantaServicio plantaServicio;

	public void menuGestionPlantas() {
		String opcion;

		do {
			mostrarMenuPlantas();
			opcion = in.nextLine();

			switch (opcion) {
			case "1":
				registrarPlanta();
				break;
			case "2":
				modificarPlanta();
				break;
			case "3":
				System.out.println("Volviendo al menú principal...");
				return;
			default:
				System.out.println("Opción no válida, intente nuevamente.");
			}
		} while (true);
	}

	private void mostrarMenuPlantas() {
		System.out.println("\n=== Gestión de Plantas ===");
		System.out.println("1. Registrar Nueva Planta");
		System.out.println("2. Modificar Planta");
		System.out.println("3. Volver");
		System.out.print("Seleccione una opción: ");
	}

	public void listarPlantas() {
		var plantas = plantaServicio.listarPlantasOrdenadas();
		if (plantas.isEmpty()) {
			System.out.println("No hay plantas registradas.");
		} else {
			plantas.forEach(planta -> System.out.println(planta.getCodigo() + " - " + planta.getNombreComun()));
		}
	}

	private void registrarPlanta() {
		System.out.print("Ingrese el código de la planta: ");
		String codigo = in.nextLine();
		System.out.print("Ingrese el nombre común de la planta: ");
		String nombreComun = in.nextLine();
		System.out.print("Ingrese el nombre científico de la planta: ");
		String nombreCientifico = in.nextLine();

		try {
			plantaServicio.registrarPlanta(codigo, nombreComun, nombreCientifico);
			System.out.println("Planta registrada con éxito.");
		} catch (RuntimeException e) {
			System.out.println("Error al registrar la planta: " + e.getMessage());
		}
	}

	private void modificarPlanta() {
		System.out.print("Ingrese el código de la planta a modificar: ");
		String codigo = in.nextLine();

		try {
			Planta planta = plantaServicio.buscarPorCodigo(codigo);

			System.out.println("Planta encontrada: ");
			System.out.println("Nombre común actual: " + planta.getNombreComun());
			System.out.println("Nombre científico actual: " + planta.getNombreCientifico());

			System.out.print("Ingrese el nuevo nombre común (o deje en blanco para no modificar): ");
			String nuevoNombreComun = in.nextLine();
			System.out.print("Ingrese el nuevo nombre científico (o deje en blanco para no modificar): ");
			String nuevoNombreCientifico = in.nextLine();

			if (!nuevoNombreComun.isEmpty()) {
				planta.setNombreComun(nuevoNombreComun);
			}
			if (!nuevoNombreCientifico.isEmpty()) {
				planta.setNombreCientifico(nuevoNombreCientifico);
			}

			plantaServicio.modificarNombresPlanta(codigo, planta.getNombreComun(), planta.getNombreCientifico());
			System.out.println("Planta modificada con éxito.");
		} catch (RuntimeException e) {
			System.out.println("Error al modificar la planta: " + e.getMessage());
		}
	}
}