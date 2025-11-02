package espol.poo;

import espol.poo.vista.VistaActividadSesion;
import espol.poo.vista.VistaSostenibilidad;
import espol.poo.controlador.ControladorActividades;
import espol.poo.controlador.ControladorSesiones;
import espol.poo.controlador.ControladorSostenibilidad;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    ControladorActividades controladorA = new ControladorActividades();
    ControladorSesiones controladorS = new ControladorSesiones(controladorA);
    VistaActividadSesion vistaActividadSesion = new VistaActividadSesion(controladorA, controladorS);
    ControladorSostenibilidad controlSostenibilidad = new ControladorSostenibilidad();
    VistaSostenibilidad vistaSostenibilidad = new VistaSostenibilidad(controlSostenibilidad);
    
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
            case "3" :
                // Control de hidratación
                break;
            case "4" :
            //Control de sostenibilidad
                vistaSostenibilidad.mostrarMenuSostenibilidad();
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