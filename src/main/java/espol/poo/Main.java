package espol.poo;

import java.util.Scanner;

import espol.poo.controlador.ControladorActividades;
import espol.poo.controlador.ControladorHidratacion;
import espol.poo.controlador.ControladorSesiones;
import espol.poo.vista.VistaActividadSesion;
import espol.poo.vista.VistaHidratacion;

public class Main {
  public static void main(String[] args) {
    ControladorActividades controladorA = new ControladorActividades();
    ControladorSesiones controladorS = new ControladorSesiones(controladorA);
    VistaActividadSesion vistaActividadSesion = new VistaActividadSesion(controladorA, controladorS);

    ControladorHidratacion controladorH = new ControladorHidratacion();
    VistaHidratacion vistaHidratacion = new VistaHidratacion(controladorH);

    Scanner scanner = new Scanner(System.in);
    String opcion = "";
    do {
        vistaActividadSesion.mostrarMenu();
        opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1" :
                // Gestión de Actividades - submenu loop
                String sub = "";
                do {
                    vistaActividadSesion.imprimirMenuActividades();
                    sub = scanner.nextLine().trim();
                    switch (sub) {
                        case "1.1": vistaActividadSesion.visualizarActividades(scanner); break;
                        case "1.2": vistaActividadSesion.crearActividad(scanner); break;
                        case "1.3": vistaActividadSesion.registrarAvance(scanner); break;
                        case "1.4": vistaActividadSesion.eliminarActividad(scanner); break;
                        case "0": break;
                        default: System.out.println("Opción no válida."); break;
                    }
                } while (!sub.equals("0"));
                break;
            case "2" :
                // Técnicas de enfoque
                vistaActividadSesion.imprimirMenuTecnicas();
                String t = scanner.nextLine().trim();
                switch (t) {
                    case "1": vistaActividadSesion.iniciarPomodoro(scanner); break;
                    case "2": vistaActividadSesion.iniciarDeepWork(scanner); break;
                    case "3": break;
                    default: System.out.println("Opción no válida."); break;
                }
                break;


            case "3" : // Control de hidratación
                int op;
                do {
                    
                    vistaHidratacion.mostrarMenu();
                    System.out.print("Elija una opcion: ");
                    op = scanner.nextInt();

                    // Submenú del control Hidratación
                    switch(op){
                        case 1: vistaHidratacion.registrarIngesta(scanner); break;
                        case 2: vistaHidratacion.establecerMetaDiaria(scanner); break;
                        case 3: vistaHidratacion.procesoDiario(scanner); break;
                        case 4: System.out.println("\nVolviendo al menú principal..."); break;

                        default: System.out.println("\nIngreso incorrecto");
                    }
                } while (op != 4);
                scanner.nextLine(); //Limpiando el Buffer
                break;

            case "4" :
                System.out.println("holaaaaa");
                break;
            case "5" :
                System.out.println("holaaaaa");
                break;
            case "6" :
                System.out.println("Saliendo de la aplicación...");
                break;
            default :
                System.out.println("Opción no válida.");
        }
    } while (!opcion.equals("6"));

    scanner.close();
  }
}