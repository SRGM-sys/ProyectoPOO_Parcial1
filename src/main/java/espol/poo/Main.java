package espol.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import espol.poo.controlador.ControladorActividades;
import espol.poo.controlador.ControladorHidratacion;
import espol.poo.controlador.ControladorSesiones;
import espol.poo.controlador.ControladorSostenibilidad;
import espol.poo.modelo.Actividad;
import espol.poo.modelo.ActividadAcademica;
import espol.poo.modelo.ActividadPersonal;
import espol.poo.modelo.EstadoActividad;
import espol.poo.modelo.Prioridad;
import espol.poo.modelo.RegistroIngestaAgua;
import espol.poo.modelo.RegistroSostenibilidad;
import espol.poo.modelo.TipoAcademica;
import espol.poo.vista.VistaActividadSesion;
import espol.poo.vista.VistaHidratacion;
import espol.poo.vista.VistaJuego;
import espol.poo.vista.VistaSostenibilidad;


public class Main {
        private static final int DIMENSION = 4;
        private static final List<String> VALORES_ECOLOGICOS = Arrays.asList(
                "RECIC", "SOLAR", "AGUA", "VIENT", 
                "BIO", "PELIG", "BOSQUE", "MAR"
                );


    List<Actividad> actividades = new ArrayList<>();
    List<RegistroIngestaAgua> registrosAgua = new ArrayList<>();
    List<RegistroSostenibilidad> accionesSostenibles = new ArrayList<>();

    int YEAR = 2025;
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime f23Nov = LocalDateTime.of(YEAR, 11, 23, 10, 0);
        LocalDateTime f24Nov = LocalDateTime.of(YEAR, 11, 24, 10, 0);
        LocalDateTime f28Nov = LocalDateTime.of(YEAR, 11, 28, 22, 0); 
        LocalDateTime f29Nov = LocalDateTime.of(YEAR, 11, 29, 22, 0); 
        LocalDateTime f30Nov = LocalDateTime.of(YEAR, 11, 30, 15, 30);
        LocalDateTime f3Dic = LocalDateTime.of(YEAR, 12, 3, 23, 59);
        LocalDateTime f10Dic = LocalDateTime.of(YEAR, 12, 10, 10, 0);


    public  void inicializarApp(){
            actividades.add(new ActividadPersonal(1, "Cita Médica", null, f30Nov, "Hospital Central"));
            actividades.add(new ActividadAcademica(2, "Proyecto", "Con uso de promodoro en dos dias.", f30Nov, "POO", TipoAcademica.PROYECTO));
            actividades.add(new ActividadAcademica(3, "Tarea", null, f3Dic, "POO", TipoAcademica.TAREA));
            actividades.add(new ActividadAcademica(4, "Examen", null, f10Dic, "POO", TipoAcademica.EXAMEN));

            registrosAgua.add(new RegistroIngestaAgua(f23Nov, 200.0));
            registrosAgua.add(new RegistroIngestaAgua(f24Nov,250.0));

            accionesSostenibles.add(new RegistroSostenibilidad(f23Nov));
            accionesSostenibles.add(new RegistroSostenibilidad(f24Nov));
    }



    public static void main(String[] args) {
    ControladorActividades controladorA = new ControladorActividades();
    ControladorSesiones controladorS = new ControladorSesiones(controladorA);
    VistaActividadSesion vistaActividadSesion = new VistaActividadSesion(controladorA, controladorS);
    ControladorHidratacion controladorH = new ControladorHidratacion();
    VistaHidratacion vistaHidratacion = new VistaHidratacion(controladorH);
    ControladorSostenibilidad controlSostenibilidad = new ControladorSostenibilidad();
    VistaSostenibilidad vistaSostenibilidad = new VistaSostenibilidad(controlSostenibilidad);



    
    Scanner scanner = new Scanner(System.in);
    String opcion = "";
    VistaJuego vistaJuego = new VistaJuego(scanner);
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
                // Control de hidratación
                break;
            case "4" :
            //Control de sostenibilidad
                vistaSostenibilidad.mostrarMenuSostenibilidad();
                break;
            case "5" :
                vistaJuego.iniciarNuevoJuego(DIMENSION, VALORES_ECOLOGICOS);
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