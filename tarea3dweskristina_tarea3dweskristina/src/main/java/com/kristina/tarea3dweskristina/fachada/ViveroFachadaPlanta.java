package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.servicios.PlantaServicio;

@Component
public class ViveroFachadaPlanta {

    private final Scanner in = new Scanner(System.in);

    @Autowired
    private PlantaServicio plantaServ;

    public void menuGestionPlantas() {
        String opcion;

        do {
            mostrarMenuPlantas();
            opcion = in.nextLine().trim();

            switch (opcion) {
                case "1":
                    listarPlantas();
                    break;
                case "2":
                    registrarPlanta();
                    break;
                case "3":
                    modificarPlanta();
                    break;
                case "4":
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (!opcion.equals("4"));
    }

    private void mostrarMenuPlantas() {
        System.out.println("\n=== Gestión de Plantas ===");
        System.out.println("1. Listar Plantas");
        System.out.println("2. Registrar Planta");
        System.out.println("3. Modificar Planta");
        System.out.println("4. Volver");
        System.out.print("Seleccione una opción: ");
    }

    private void listarPlantas() {
        var plantas = plantaServ.listarPlantasOrdenadas();
        if (plantas.isEmpty()) {
            System.out.println("No hay plantas registradas.");
        } else {
            plantas.forEach(planta -> 
                System.out.println(planta.getCodigo() + " - " + planta.getNombreComun())
            );
        }
    }

    private void registrarPlanta() {
        try {
            System.out.print("Ingrese el código de la planta: ");
            String codigo = in.nextLine().trim();
            if (codigo.isEmpty()) throw new IllegalArgumentException("El código no puede estar vacío.");

            System.out.print("Ingrese el nombre común: ");
            String nombreComun = in.nextLine().trim();
            if (nombreComun.isEmpty()) throw new IllegalArgumentException("El nombre común no puede estar vacío.");

            System.out.print("Ingrese el nombre científico: ");
            String nombreCientifico = in.nextLine().trim();
            if (nombreCientifico.isEmpty()) throw new IllegalArgumentException("El nombre científico no puede estar vacío.");

            plantaServ.registrarPlanta(codigo, nombreComun, nombreCientifico);
            System.out.println("Planta registrada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error al registrar la planta: " + e.getMessage());
        }
    }

    private void modificarPlanta() {
        try {
            System.out.print("Ingrese el código de la planta a modificar: ");
            String codigo = in.nextLine().trim();
            if (codigo.isEmpty()) throw new IllegalArgumentException("El código no puede estar vacío.");

            System.out.print("Ingrese el nuevo nombre común: ");
            String nuevoNombreComun = in.nextLine().trim();
            if (nuevoNombreComun.isEmpty()) throw new IllegalArgumentException("El nuevo nombre común no puede estar vacío.");

            System.out.print("Ingrese el nuevo nombre científico: ");
            String nuevoNombreCientifico = in.nextLine().trim();
            if (nuevoNombreCientifico.isEmpty()) throw new IllegalArgumentException("El nuevo nombre científico no puede estar vacío.");

            plantaServ.modificarNombresPlanta(codigo, nuevoNombreComun, nuevoNombreCientifico);
            System.out.println("Planta modificada con éxito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error al modificar la planta: " + e.getMessage());
        }
    }
}