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
        // Inicialización de controladores y vistas
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
            vistaActividadSesion.mostrarMenu(); // Muestra el menú principal
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    // Gestión de Actividades - submenu loop
                    String sub = "";
                    do {
                        vistaActividadSesion.imprimirMenuActividades(); // Muestra el menú de actividades
                        sub = scanner.nextLine().trim();
                        switch (sub) {
                            case "1.1": vistaActividadSesion.visualizarActividades(scanner); break; // Visualiza actividades
                            case "1.2": vistaActividadSesion.crearActividad(scanner); break; // Crea una nueva actividad
                            case "1.3": vistaActividadSesion.registrarAvance(scanner); break; // Registra avance de actividad
                            case "1.4": vistaActividadSesion.eliminarActividad(scanner); break; // Elimina una actividad
                            case "0": break; // Regresa al menú anterior
                            default: System.out.println("Opción no válida."); break; // Opción no válida
                        }
                    } while (!sub.equals("0"));
                    break;
                case "2":
                    // Técnicas de enfoque
                    vistaActividadSesion.imprimirMenuTecnicas(); // Muestra el menú de técnicas
                    String t = scanner.nextLine().trim();
                    switch (t) {
                        case "1": vistaActividadSesion.iniciarPomodoro(scanner); break; // Inicia técnica Pomodoro
                        case "2": vistaActividadSesion.iniciarDeepWork(scanner); break; // Inicia técnica Deep Work
                        case "3": break; // Opción de salida
                        default: System.out.println("Opción no válida."); break; // Opción no válida
                    }
                    break;
                case "3":
                    int op;
                    do {
                        vistaHidratacion.mostrarMenu(); // Muestra el menú de hidratación
                        System.out.print("Elija una opcion: ");
                        op = scanner.nextInt();

                        // Submenú del control Hidratación
                        switch(op){
                            case 1: vistaHidratacion.registrarIngesta(scanner); break; // Registra ingesta de agua
                            case 2: vistaHidratacion.establecerMetaDiaria(scanner); break; // Establece meta diaria de hidratación
                            case 3: vistaHidratacion.procesoDiario(scanner); break; // Muestra proceso diario de hidratación
                            case 4: System.out.println("\nVolviendo al menú principal..."); break; // Regresa al menú principal
                            default: System.out.println("\nIngreso incorrecto"); // Ingreso incorrecto
                        }
                    } while (op != 4);
                    scanner.nextLine(); // Limpiando el Buffer
                    break;
                case "4":
                    // Control de sostenibilidad
                    vistaSostenibilidad.mostrarMenuSostenibilidad(); // Muestra el menú de sostenibilidad
                    break;
                case "5":
                    vistaJuego.iniciarNuevoJuego(DIMENSION, VALORES_ECOLOGICOS); // Inicia un nuevo juego
                    break;
                case "6":
                    System.out.println("Saliendo de la aplicación..."); // Mensaje de salida
                    break;
                default:
                    System.out.println("Opción no válida."); // Opción no válida
            }
        } while (!opcion.equals("6"));

        scanner.close(); // Cierra el escáner
    }
}
