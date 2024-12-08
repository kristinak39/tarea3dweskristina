package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kristina.tarea3dweskristina.fachada.*;






@Component
public class ViveroFachada {

    @Autowired
    private ViveroFachadaPlanta plantaFachada;

    @Autowired
    private ViveroFachadaPersona personaFachada;

    @Autowired
    private ViveroFachadaEjemplar ejemplarFachada;

    @Autowired
    private ViveroFachadaMensaje mensajeFachada;

    private final Scanner in = new Scanner(System.in);

    public void iniciarSistema() {
        String opcion;

        do {
            mostrarMenuPrincipal();
            opcion = in.nextLine();

            switch (opcion) {
                case "1":
                    plantaFachada.menuGestionPlantas();
                    break;
                case "2":
                    personaFachada.menuGestionPersonas();
                    break;
                case "3":
                    ejemplarFachada.menuGestionEjemplares();
                    break;
                case "4":
                    mensajeFachada.menuGestionMensajes();
                    break;
                case "5":
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida, inténtelo nuevamente.");
            }
        } while (!opcion.equals("5"));

        cerrarRecursos();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== Menú Principal del Vivero ===");
        System.out.println("1. Gestión de Plantas");
        System.out.println("2. Gestión de Personas");
        System.out.println("3. Gestión de Ejemplares");
        System.out.println("4. Gestión de Mensajes");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void cerrarRecursos() {
        if (in != null) {
            in.close();
        }
        System.out.println("Recursos cerrados correctamente.");
    }
}