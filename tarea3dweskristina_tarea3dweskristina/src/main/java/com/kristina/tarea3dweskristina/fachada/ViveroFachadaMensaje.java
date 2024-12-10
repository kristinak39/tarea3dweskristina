package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Mensaje;
import com.kristina.tarea3dweskristina.servicios.MensajeServicio;

@Component
public class ViveroFachadaMensaje {

    private final Scanner in = new Scanner(System.in);

    @Autowired
    private MensajeServicio mensajeServicio;

    public void menuGestionMensajes(Long usuarioId) {
        String opcion;

        do {
            mostrarMenuMensajes();
            opcion = in.nextLine().trim();

            switch (opcion) {
                case "1":
                    registrarMensaje(usuarioId);
                    break;
                case "2":
                    listarMensajesPorEjemplar();
                    break;
                case "3":
                    System.out.println("Volviendo al menú principal...");
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (true);
    }

    private void mostrarMenuMensajes() {
        System.out.println("\n=== Gestión de Mensajes ===");
        System.out.println("1. Registrar Mensaje");
        System.out.println("2. Listar Mensajes por Ejemplar");
        System.out.println("3. Volver");
        System.out.print("Seleccione una opción: ");
    }

    public void registrarMensaje(Long usuarioId) {
        try {
            Long ejemplarId = solicitarIdEjemplar();

            System.out.print("Ingrese el contenido del mensaje: ");
            String contenido = in.nextLine().trim();

            if (contenido.isEmpty()) {
                throw new IllegalArgumentException("El contenido del mensaje no puede estar vacío.");
            }

            Mensaje mensaje = mensajeServicio.registrarMensaje(contenido, ejemplarId, usuarioId);
            System.out.println("\nMensaje registrado con éxito. ID del mensaje: " + mensaje.getId());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error al registrar el mensaje: " + e.getMessage());
        }
    }

    private void listarMensajesPorEjemplar() {
        try {
            Long ejemplarId = solicitarIdEjemplar();

            var mensajes = mensajeServicio.buscarPorEjemplar(ejemplarId);

            if (!mensajes.isEmpty()) {
                System.out.println("\n=== Mensajes del Ejemplar ===");
                mensajes.forEach(mensaje ->
                        System.out.printf("ID: %d | Fecha: %s | Contenido: %s%n",
                                mensaje.getId(), mensaje.getFechaHora(), mensaje.getMensaje())
                );
            } else {
                System.out.println("No se encontraron mensajes para el ejemplar especificado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error al listar los mensajes: " + e.getMessage());
        }
    }

    private Long solicitarIdEjemplar() {
        while (true) {
            System.out.print("Ingrese el ID del ejemplar: ");
            String input = in.nextLine().trim();

            if (input.matches("\\d+")) {
                return Long.parseLong(input);
            }

            System.out.println("Error: El ID del ejemplar debe ser un número válido.");
        }
    }
}
