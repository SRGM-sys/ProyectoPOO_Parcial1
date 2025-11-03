package espol.poo.vista;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import espol.poo.controlador.ControladorActividades;
import espol.poo.controlador.ControladorSesiones;
import espol.poo.modelo.Actividad;
import espol.poo.modelo.ActividadAcademica;
import espol.poo.modelo.Prioridad;
import espol.poo.modelo.SesionEnfoque;
import espol.poo.modelo.TecnicaEnfoque;
import espol.poo.modelo.TipoAcademica;

public class VistaActividadSesion {
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de fecha
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); // Formato de fecha y hora
    private ControladorActividades controladorA; // Controlador de actividades
    private ControladorSesiones controladorS; // Controlador de sesiones
    final String BOLD = "\u001B[1m"; // Estilo de texto en negrita
    final String RESET = "\u001B[0m"; // Restablecer estilo de texto

    public VistaActividadSesion(ControladorActividades controladorA, ControladorSesiones controladorS) {
        this.controladorA = controladorA; // Inicializa controlador de actividades
        this.controladorS = controladorS; // Inicializa controlador de sesiones
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.println(BOLD + "\t\t.:MENÚ:." + RESET); // Muestra el menú principal
        System.out.println("1 - Gestión de Actividades");
        System.out.println("2 - Técnicas de Enfoque (Manejo de tiempo)"); 
        System.out.println("3 - Control de hidratación");
        System.out.println("4 - Registro diario de Sostenibilidad");
        System.out.println("5 - Juego de memoria");
        System.out.println("6 - Salir");
        System.out.print("Ingrese su opción: ");
    }

    public void imprimirMenuActividades() {
        System.out.println();
        System.out.println(BOLD + "Gestión de Actividades" + RESET); // Muestra el menú de actividades
        System.out.println("1.1 Visualizar actividades");
        System.out.println("1.2 Crear actividad");
        System.out.println("1.3 Registrar avance de actividad");
        System.out.println("1.4 Eliminar actividad");
        System.out.println("0 Volver al menú principal");
        System.out.print("Seleccione sub-opción (ej. 1.1) o 0 para volver: ");
    }

    public void imprimirMenuTecnicas() {
        System.out.println();
        System.out.println(BOLD + "Técnicas de Enfoque" + RESET); // Muestra el menú de técnicas
        System.out.println("1. Iniciar Pomodoro (25 min Trabajo / 5 min Descanso)");
        System.out.println("2. Iniciar Deep Work (Sesión Larga de 90 min)"); 
        System.out.println("3. Volver al Menú Principal");
        System.out.print("Ingrese su opción: ");
    }

    // Muestra la lista de actividades y permite seleccionar una para ver detalles
    public void visualizarActividades(Scanner sc) {
        System.out.println();
        System.out.println("-------------------------------------- LISTADO DE ACTIVIDADES ---------------------------------------");
        List<Actividad> actividades = controladorA.listarActividades(); // Lista de actividades
        System.out.println("-----------------------------------------------------------------------------------------------------");
        mostrarListadoActividades(actividades); // Muestra las actividades
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.print("Ingrese el ID de la actividad para ver más detalle (0 para volver): ");
        String line = sc.nextLine().trim();
        int id = -1;
        try { id = Integer.parseInt(line); } catch(Exception e) { id = -1; }
        if (id > 0) {
            controladorA.buscarPorId(id).ifPresent(a -> mostrarDetalleActividad(a, sc)); // Muestra detalles de la actividad seleccionada
        }
    }

    // Muestra los detalles de una actividad
    public void mostrarDetalleActividad(Actividad a, Scanner sc) {
        System.out.println("============================================================");
        System.out.printf("                DETALLES DEL PROYECTO (ID %d)%n", a.getId());
        System.out.println("============================================================");
        System.out.println("Nombre: " + a.getNombre());
        String tipo = (a instanceof ActividadAcademica) ? ((ActividadAcademica)a).getTipoAcademica().name() : "PERSONAL"; // Determina el tipo de actividad
        System.out.println("Tipo: " + tipo);
        if (a instanceof ActividadAcademica) {
            ActividadAcademica aa = (ActividadAcademica)a;
            System.out.println("Asignatura: " + aa.getAsignatura()); // Muestra la asignatura si es académica
        }
        System.out.println("Prioridad: " + a.getPrioridad().name());
        System.out.println("Estado: " + (a.getEstado()==null?"" : a.getEstado().name()));
        System.out.println("Fecha Límite: " + a.getFechaVencimiento().format(df)); // Muestra la fecha límite
        double horas = a.getTiempoEstimadoMinutos() / 60.0;
        System.out.println("Tiempo Estimado Total: " + String.format("%.1f horas", horas)); // Muestra el tiempo estimado
        System.out.println("Avance Actual: " + String.format("%.0f", a.getAvance()) + "%");
        System.out.println("------------------------------------------------------------") ;
        System.out.println("              HISTORIAL DE GESTIÓN DEL TIEMPO               ");
        System.out.println("------------------------------------------------------------") ;
        System.out.println("| Fecha Sesión   | Técnica Aplicada       | Duración (min) |");
        System.out.println("|----------------|------------------------|----------------|") ;
        if (a.getSesionesEnfoque().isEmpty()) {
            System.out.println("| (sin sesiones registradas)                               |") ;
        } else {
            for (SesionEnfoque s : a.getSesionesEnfoque()) {
                System.out.println(s.toString()); // Muestra las sesiones de enfoque
            }
        }
        System.out.println("------------------------------------------------------------") ;
        System.out.println();
        System.out.print("Presione [ENTER] para volver a la lista...");
        sc.nextLine();
    }

    // Crea una nueva actividad
    public void crearActividad(Scanner sc) {
        System.out.println("==========================================");
        System.out.println("      F O C U S F L O W  | C R E A R");
        System.out.println("==========================================");
        System.out.println();
        System.out.println("----------- PASO 1: CATEGORÍA ------------");
        System.out.println("Seleccione la categoría de la actividad:");
        System.out.println("1. ACADÉMICA (Tarea, Examen, Proyecto)");
        System.out.println("2. PERSONAL (Citas, Ejercicio, Hobbies)");
        System.out.print("Ingrese opción (1 o 2): ");
        String cat = sc.nextLine().trim();
        if (cat.equals("2")) {
            System.out.println();
            System.out.println("----------- PASO 2: TIPO (Personal) -----------");
            System.out.println("Ha seleccionado: PERSONAL.");
            System.out.println();
            System.out.println("----------- PASO 3: DETALLES -----------");
            System.out.print("Ingrese el Nombre de la Actividad: "); String nombre = sc.nextLine();
            System.out.print("Ingrese la Descripción: "); String desc = sc.nextLine();
            System.out.print("Ingrese la Fecha de Vencimiento (DD/MM/AAAA HH:mm): "); String fv = sc.nextLine();
            System.out.print("Ingrese Prioridad (ALTA, MEDIA, BAJA): "); String pr = sc.nextLine().trim();
            System.out.print("Ingrese Tiempo Estimado (en minutos): "); String te = sc.nextLine().trim();
            System.out.print("Ingrese el Lugar (Opcional): "); String lugar = sc.nextLine();
            LocalDateTime fechaV = LocalDateTime.now();
            try { fechaV = LocalDateTime.parse(fv, dtf); } catch(Exception e) {}
            Prioridad prioridad = Prioridad.MEDIA;
            try { prioridad = Prioridad.valueOf(pr.toUpperCase()); } catch(Exception ex) {}
            double tiempoMin = 0;
            try { tiempoMin = Double.parseDouble(te); } catch(Exception ex) {}
            controladorA.crearActividadPersonal(nombre, desc, fechaV, lugar, prioridad, tiempoMin); // Crea actividad personal
            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("ACTIVIDAD PERSONAL '" + nombre + "' creada con éxito.");
            System.out.println("----------------------------------------");
            System.out.print("Presione [ENTER] para volver al menú principal..."); sc.nextLine();
        } else {
            System.out.println();
            System.out.println("==========================================");
            System.out.println("       C R E A R  A C T I V I D A D");
            System.out.println("==========================================");
            System.out.println();
            System.out.println("-------- PASO 2: TIPO (Académica) --------");
            System.out.println("Ha seleccionado: ACADÉMICA.");
            System.out.println("Seleccione el tipo específico:");
            System.out.println("1. TAREA");
            System.out.println("2. EXAMEN");
            System.out.println("3. PROYECTO");
            System.out.print("Ingrese opción (1-3): "); String t = sc.nextLine().trim();
            TipoAcademica tipo = TipoAcademica.TAREA;
            if (t.equals("2")) tipo = TipoAcademica.EXAMEN;
            else if (t.equals("3")) tipo = TipoAcademica.PROYECTO;
            System.out.println();
            System.out.println("------------ PASO 3: DETALLES ------------");
            System.out.print("Ingrese el Nombre de la Actividad: "); String nombre = sc.nextLine();
            System.out.print("Ingrese la Descripción: "); String desc = sc.nextLine();
            System.out.print("Ingrese la Asignatura: "); String asign = sc.nextLine();
            System.out.print("Ingrese la Fecha de Vencimiento (DD/MM/AAAA HH:mm): "); String fv = sc.nextLine();
            System.out.print("Ingrese Prioridad (ALTA, MEDIA, BAJA): "); String pr = sc.nextLine().trim();
            System.out.print("Ingrese Tiempo Estimado (en horas): "); String th = sc.nextLine().trim();
            LocalDateTime fechaV = LocalDateTime.now();
            try { fechaV = LocalDateTime.parse(fv, dtf); } catch(Exception e) {}
            Prioridad prioridad = Prioridad.MEDIA;
            try { prioridad = Prioridad.valueOf(pr.toUpperCase()); } catch(Exception ex) {}
            double tiempoHoras = 0;
            try { tiempoHoras = Double.parseDouble(th); } catch(Exception ex) {}
            controladorA.crearActividadAcademica(nombre, desc, fechaV, asign, tipo, prioridad, tiempoHoras); // Crea actividad académica
            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println(tipo.name() + " '" + nombre + "' creado con éxito.");
            System.out.println("------------------------------------------");
            System.out.print("Presione [ENTER] para volver al menú principal..."); sc.nextLine();
        }
    }

    public void registrarAvance(Scanner sc) {
        System.out.println("--------------------------------- LISTADO DE ACTIVIDADES PENDIENTES ---------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        mostrarListadoActividades(controladorA.listarPendientes()); // Muestra actividades pendientes
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.print("Ingrese el ID de la actividad a actualizar (o 0 para salir): ");
        String line = sc.nextLine().trim();
        int id = -1;
        try { id = Integer.parseInt(line); } catch(Exception e){ id = -1; }
        if (id > 0) {
            controladorA.buscarPorId(id).ifPresent(a -> {
                System.out.println("Ha seleccionado: " + (a instanceof ActividadAcademica ? ((ActividadAcademica)a).getTipoAcademica().name() : "PERSONAL") + " '" + a.getNombre() + "'.");
                System.out.println("Avance actual: " + (int)a.getAvance() + "%."); 
                System.out.print("Ingrese el nuevo porcentaje de avance (0 - 100): ");
                double nuevo = -1;
                try { nuevo = Double.parseDouble(sc.nextLine().trim()); } catch(Exception e) {}
                if (nuevo >= 0) {
                    System.out.print("¿Confirma que el nuevo avance para la actividad ID " + a.getId() + " es " + (int)nuevo + "%? (S/N): ");
                    String conf = sc.nextLine().trim();
                    if (conf.equalsIgnoreCase("S") || conf.equalsIgnoreCase("SI")) {
                        controladorA.actualizarAvance(a.getId(), nuevo); // Actualiza el avance de la actividad
                    }
                }
            });
        }
        System.out.print("Presione [ENTER] para volver al menú de Gestión de Actividades..."); sc.nextLine();
    }

    public void eliminarActividad(Scanner sc) {
        System.out.println("-------------------------------------- LISTADO DE ACTIVIDADES ---------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        mostrarListadoActividades(controladorA.listarActividades()); // Muestra las actividades
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.print("Ingrese el ID de la actividad a eliminar (o 0 para cancelar): ");
        int id = -1;
        try { id = Integer.parseInt(sc.nextLine().trim()); } catch(Exception e) {}
        if (id > 0) {
            controladorA.buscarPorId(id).ifPresent(a -> {
                System.out.print("¿Está seguro que desea ELIMINAR PERMANENTEMENTE esta actividad? (S/N): ");
                String conf = sc.nextLine().trim();
                if (conf.equalsIgnoreCase("S") || conf.equalsIgnoreCase("SI")) {
                    controladorA.eliminarActividad(a.getId()); // Elimina la actividad
                }
            });
        }
        System.out.print("Presione [ENTER] para continuar..."); sc.nextLine();
    }

    public void iniciarPomodoro(Scanner sc) {
        System.out.println("--------------------------------- LISTADO DE ACTIVIDADES PENDIENTES ---------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        mostrarListadoActividades(controladorA.listarPendientes()); // Muestra actividades pendientes
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.print("Ingrese ID de la actividad (o 0 para salir): ");
        int id = -1;
        try { id = Integer.parseInt(sc.nextLine().trim()); } catch(Exception e) {}
        if (id > 0) {
            controladorA.buscarPorId(id).ifPresent(a -> {
                for (int ciclo = 1; ciclo <= 4; ciclo++) {
                    System.out.println();
                    System.out.println(">>> INICIANDO TRABAJO EN '" + a.getNombre() + "' <<<");
                    System.out.println("Técnica: Pomodoro | Ciclo: " + ciclo + "/4");
                    System.out.println("Tiempo de Trabajo: 25:00 minutos restantes");
                    System.out.println("[Simulación: presione ENTER cuando termine el tiempo de trabajo]");
                    sc.nextLine();
                    LocalDate fecha = LocalDate.now();
                    LocalTime hora = LocalTime.now();
                    controladorS.crearYAsociarSesion(a.getId(), TecnicaEnfoque.POMODORO, 25, fecha, hora, "Pomodoro registrado (simulación)"); // Registra sesión de Pomodoro
                    System.out.println("--- ¡TIEMPO DE TRABAJO TERMINADO! ---");
                    System.out.println("Sesión registrada. (Avance de la actividad actualizado en base al tiempo).");
                    if (ciclo < 4) {
                        System.out.println("Ahora toma un DESCANSO: 05:00 minutos restantes");
                        System.out.print("Presione [ENTER] para iniciar el siguiente ciclo..."); sc.nextLine();
                    } else {
                        System.out.println("Todos los ciclos completados (4/4).");
                    }
                }
                System.out.print("Presione [ENTER] para volver al menú principal..."); sc.nextLine();
            });
        }
    }

    public void iniciarDeepWork(Scanner sc) {
        System.out.println("--------------------------------- LISTADO DE ACTIVIDADES PENDIENTES ---------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        mostrarListadoActividades(controladorA.listarPendientes()); // Muestra actividades pendientes
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.print("Ingrese ID de la actividad (o 0 para salir): ");
        int id = -1;
        try { id = Integer.parseInt(sc.nextLine().trim()); } catch(Exception e) {}
        if (id > 0) {
            controladorA.buscarPorId(id).ifPresent(a -> {
                System.out.println(">>> INICIANDO DEEP WORK EN '" + a.getNombre() + "' <<<");
                System.out.print("Presione [ENTER] para registrar la sesión de Deep Work (simulación)...");
                sc.nextLine();
                LocalDate fecha = LocalDate.now();
                LocalTime hora = LocalTime.now();
                controladorS.crearYAsociarSesion(a.getId(), TecnicaEnfoque.DEEPWORK, 90, fecha, hora, "DeepWork registrado (simulación)"); // Registra sesión de Deep Work
                System.out.println("Sesión registrada.");
                System.out.print("Presione [ENTER] para continuar..."); sc.nextLine();
            });
        }
    }

    // Muestra un listado de actividades
    public void mostrarListadoActividades(List<Actividad> actividades) {
        System.out.println("ID  | TIPO        | NOMBRE                                   | VENCE       | ESTADO      | AVANCE (%)");
        System.out.println("----|-------------|------------------------------------------|-------------|-------------|-----------");
        for (Actividad a: actividades) {
            String tipo = (a instanceof ActividadAcademica) ? ((ActividadAcademica)a).getTipoAcademica().name() : "PERSONAL"; // Determina el tipo de actividad
            String nombre = a.getNombre();
            String fecha = a.getFechaVencimiento().toLocalDate().format(df);
            String estado = a.getEstado().name();
            String avance = String.format("%.0f%%", a.getAvance());
            System.out.printf("%-3d | %-11s | %-40s | %-11s | %-11s | %10s%n", a.getId(), tipo, nombre, fecha, estado, avance); // Muestra la información de la actividad
        }
    }
}
