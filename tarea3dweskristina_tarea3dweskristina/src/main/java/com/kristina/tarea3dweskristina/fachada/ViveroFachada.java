package com.kristina.tarea3dweskristina.fachada;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kristina.tarea3dweskristina.modelo.Credenciales;
import com.kristina.tarea3dweskristina.servicios.CredencialServicio;

@Component
public class ViveroFachada {
    // Esto representa al usuario autenticado
    private Credenciales usuarioActual;

    @Autowired
    private CredencialServicio credencialServicio;

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
            opcion = in.nextLine().trim();

            switch (opcion) {
                case "1":
                    login();
                    break;
                case "2":
                    menuInvitado();
                    break;
                case "3":
                    System.out.println("Saliendo del sistema. ¡Hasta pronto!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida, inténtelo nuevamente.");
            }
        } while (true);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== Bienvenido al sistema del vivero ===");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Continuar como invitado");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void login() {
        System.out.print("Usuario: ");
        String usuario = in.nextLine();
        System.out.print("Contraseña: ");
        String password = in.nextLine();

        try {
            Credenciales credenciales = credencialServicio.autenticar(usuario, password);
            usuarioActual = credenciales;
            if ("admin".equals(credenciales.getUsuario())) {
                System.out.println("¡Bienvenido, administrador!");
                menuAdmin();
            } else {
                System.out.println("¡Bienvenido, personal del vivero!");
                menuPersonal();
            }
        } catch (RuntimeException e) {
            System.out.println("Credenciales inválidas. Intente de nuevo.");
        }
    }

    private void menuInvitado() {
        System.out.println("=== Modo Invitado ===");
        System.out.println("1. Ver plantas");
        System.out.println("2. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        String opcion = in.nextLine().trim();

        if ("1".equals(opcion)) {
            plantaFachada.listarPlantas();
        } else {
            System.out.println("Volviendo al menú principal...");
        }
    }

    private void menuAdmin() {
        String opcion;
        do {
            System.out.println("\n=== Menú Administrador ===");
            System.out.println("1. Gestionar personas");
            System.out.println("2. Gestionar plantas");
            System.out.println("3. Gestionar ejemplares");
            System.out.println("4. Gestionar mensajes");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = in.nextLine().trim();

            switch (opcion) {
                case "1":
                    personaFachada.menuGestionPersonas();
                    break;
                case "2":
                    plantaFachada.menuGestionPlantas();
                    break;
                case "3":
                	ejemplarFachada.menuGestionEjemplares();
                	break;
                case "4":
                    mensajeFachada.menuGestionMensajes(usuarioActual.getId());
                    break;
                case "5":
                    cerrarSesion();
                    return; // Salir del menú
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (true);
    }

    private void menuPersonal() {
        String opcion;
        do {
            System.out.println("\n=== Menú Personal ===");
            System.out.println("1. Gestionar ejemplares");
            System.out.println("2. Gestionar mensajes");
            System.out.println("3. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = in.nextLine().trim();

            switch (opcion) {
                case "1":
                    ejemplarFachada.menuGestionEjemplares();
                    break;
                case "2":
                    mensajeFachada.menuGestionMensajes(usuarioActual.getId());
                    break;
                case "3":
                    cerrarSesion();
                    return; // Salir del menú
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (true);
    }

    private void cerrarSesion() {
        usuarioActual = null; // Restablecer sesión
        System.out.println("Se ha cerrado la sesión. Ahora estás en modo invitado.");
        menuInvitado();
    }
}
