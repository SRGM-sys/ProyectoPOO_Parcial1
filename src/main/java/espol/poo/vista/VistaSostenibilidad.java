package espol.poo.vista;
import espol.poo.controlador.ControladorSostenibilidad;
import espol.poo.modelo.*;
import java.time.LocalDate;
import java.util.Scanner;

public class VistaSostenibilidad {

    private ControladorSostenibilidad control;
    private Scanner sc;

    public VistaSostenibilidad(ControladorSostenibilidad control) {
        this.control = control;
        this.sc = new Scanner(System.in);
    }
    /**
     * Muestra el menú diario de registro de sostenibilidad.
     * Permite al usuario seleccionar las acciones ecológicas realizadas en el día.
     * Crea un nuevo RegistroSostenibilidad y lo agrega al control.
     */
    public void mostrarMenuSostenibilidad() {
        LocalDate fechaHoy = LocalDate.now();
        RegistroSostenibilidad registro = new RegistroSostenibilidad(fechaHoy);
        //Interfaz del registro diario
        System.out.println("\n--- REGISTRO DIARIO DE SOSTENIBILIDAD (" + fechaHoy + ") ---");
        System.out.println("Marque las acciones que realizó hoy (ingrese los números separados por coma, ej: 1, 3):\n");
        System.out.println("1. Usé transporte público, bicicleta o caminé.");
        System.out.println("2. No realicé impresiones.");
        System.out.println("3. No utilicé envases descartables (usé mi termo/taza).");
        System.out.println("4. Separé y reciclé materiales (vidrio, plástico, papel).");
        System.out.print("\nIngrese sus selecciones: ");

        String entrada = sc.nextLine();
        String[] acciones = entrada.split(",");

        // Marcar las acciones seleccionadas como verdaderas en el registro
        for (String a : acciones) {
            int opcion = Integer.parseInt(a.trim());
            switch (opcion) {
                case 1 -> registro.setUsoTransporteSostenible(true);
                case 2 -> registro.setEvitoImpresiones(true);
                case 3 -> registro.setEvitoEnvasesDescartables(true);
                case 4 -> registro.setSeparoResiduos(true);
            }
        }
        // Guardar el registro diario en el control
        control.registrarAccionesDia(registro);

        // Mostrar resumen del día
        mostrarConfirmacion(registro);
    }

    private void mostrarConfirmacion(RegistroSostenibilidad registro) {
        System.out.println("--------------------------------------------");
        System.out.println("Acciones de sostenibilidad registradas:");
        if (registro.isUsoTransporteSostenible()) System.out.println("- Usé transporte público, bicicleta o caminé.");
        if (registro.isEvitoImpresiones()) System.out.println("- No realicé impresiones.");
        if (registro.isEvitoEnvasesDescartables()) System.out.println("- No utilicé envases descartables.");
        if (registro.isSeparoResiduos()) System.out.println("- Separé y reciclé materiales.");

        // Mostrar los puntos acumulados del día
        System.out.println("\n¡Excelente contribución al planeta hoy!");
        System.out.println("Puntos de Sostenibilidad Acumulados: +" + registro.getPuntosDia());
        System.out.println("\nPresione [ENTER] para ver el resumen semanal...");
        sc.nextLine();

         // Mostrar resumen semanal luego de registrar
        mostrarResumenSemanal(LocalDate.now());
    }

    /**
     * Muestra el resumen semanal de sostenibilidad con datos simulados.
     * En una versión futura, se podría conectar directamente al método getResumenSemanal del modelo.
     *fecha actual que marca el fin de la semana
     */
    private void mostrarResumenSemanal(LocalDate dia) {
        LocalDate inicioSemana = dia.minusDays(6);

        // Encabezado del reporte semanal
        System.out.println("\n--- RESUMEN SEMANAL DE SOSTENIBILIDAD (" + inicioSemana + " - " + dia + ") ---\n");
        System.out.println("---------------------------------------------------------------");
        System.out.println("FRECUENCIA DE ACCIONES");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-40s | %-15s | %-15s%n", "ACCIÓN", "VECES REALIZADA", "LOGRO");
        System.out.println("---------------------------------------------------------------");

         // Datos simulados de frecuencia semanal (aún no se calculan dinámicamente)
        int transporte = 5, impresiones = 7, envases = 4, residuos = 5;

        // Mostrar tabla resumen
        System.out.printf("%-40s | %2d / 7 Días | %-15s%n", "Usé transporte público/bici/caminé", transporte, "¡Gran Movilidad!");
        System.out.printf("%-40s | %2d / 7 Días | %-15s%n", "No realicé impresiones", impresiones, "Excelente");
        System.out.printf("%-40s | %2d / 7 Días | %-15s%n", "No utilicé envases descartables", envases, "Necesita mejorar");
        System.out.printf("%-40s | %2d / 7 Días | %-15s%n", "Separé y reciclé materiales", residuos, "Muy bien");

        // Análisis final
        System.out.println("---------------------------------------------------------------");
        System.out.println("ANÁLISIS ECOLÓGICO");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Días con al menos 1 acción de sostenibilidad: 7 de 7 (100%)");
        System.out.println("Días con las 4 acciones completas: 2 de 7 (28%)");
        
        // Tip ecológico final
        System.out.println("\n**Tip Ecológico de la Semana:** Para aumentar tu puntaje de \"Envases descartables\", ten siempre tu botella reutilizable a la mano antes de salir.");
        System.out.println("---------------------------------------------------------------");
        System.out.println("\nPresione [ENTER] para volver al menú de Sostenibilidad...");
        sc.nextLine();
    }
}

