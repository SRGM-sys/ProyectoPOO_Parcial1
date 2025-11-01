package espol.poo.vista;

import java.time.LocalTime;
import java.util.Scanner;

import espol.poo.controlador.ControladorHidratacion;

public class VistaHidratacion {
    // Creo un objeto del tipo controlador para que la vista lo manipule
    // Lo pondre como final ya que su valor o nombre no cambiará a lo largo de esta clase
    private final ControladorHidratacion controlador;

    // Inicializo el controlador
    public VistaHidratacion(ControladorHidratacion controlador) {
        this.controlador = controlador;
    }

    // Muestra el menú principal de Hidratación
    public void mostrarMenu(){
        System.out.println("\n\t\t\t.:BIENVENIDO/A A CONTROL HIDRATACIÓN:.");
        System.out.println("1. Registrar ingesta de agua");
        System.out.println("2. Establecer meta diaria");
        System.out.println("3. Ver progreso diario y meta");
        System.out.println("4. Volver al menú principal");
    }

    // Mensajes a presentar al momento de Registrar una ingesta
    public void registrarIngesta(Scanner scanner){
        System.out.println("\n\t\t\t--- REGISTRAR INGESTA AGUA ---");
        // Pedir la cantidad al usuario
        System.out.print("Ingrese la cantidad de agua que ha tomado (en **mililitros**): ");
        double cantidadAgua = scanner.nextDouble();
        // Con LocalTime.now se guarda el tiempo en donde se registra
        controlador.registarIngesta(LocalTime.now(), cantidadAgua);
        System.out.println("\nRegistro de "+cantidadAgua+" ml añadido");
        
        System.out.println("\n\t\t\t--- PROGRESO RÁPIDO ---");
        System.out.println("Meta diaria: "+controlador.getMetaDiariaML()+" ml");
        System.out.println("Acumulado hoy: "+controlador.acumuladoActual()+" ml (Antes eran "+controlador.acumuladoAnterior()+" ml)");
        System.out.println("Progreso: "+controlador.generarBarra());
        
        System.out.print("\nPresione [ENTER] para continuar...");
        scanner.nextLine(); // Se limpia el Buffer
        scanner.nextLine(); // Esperar hasta ingresar ENTER
    }

    // Segunda opción del subMenu Hidratación
    public void establecerMetaDiaria(Scanner scanner){
        System.out.println("\n\t\t\t--- ESTABLECER META DIARIA DE HIDRATACIÓN ---");
        System.out.println("Meta diaria actual: "+ controlador.getMetaDiariaML() +" ml");
        System.out.print("Ingrese la nueva meta diaria de hidratación (en **mililitros**): ");
        double nuevaMetaDiaria = scanner.nextDouble();
        System.out.print("\n¿Confirma que la nueva meta diaria es "+nuevaMetaDiaria+" ml? (S/N): ");
        char confirmacion = scanner.next().charAt(0);
        
        if(controlador.confirmacion(confirmacion, nuevaMetaDiaria)){
            progresoActualizado(scanner);
        } else{
            System.out.println("\nNo se realizaron los cambios");
            System.out.print("\nPresione [ENTER] para continuar...");
            scanner.nextLine(); // Se limpia el Buffer
            scanner.nextLine(); // Esperar hasta ingresar ENTER
        }
    }

    // Se va a presnetar este bloque de mensaje si el usuario confirma la actualización de la nueva Meta diaria
    public void progresoActualizado(Scanner scanner){
        System.out.println("\nMeta diaria de hidratación actualizada a "+controlador.getMetaDiariaML()+" ml con éxito.");
        System.out.println("\n\t\t\t--- PROGRESO ACTUALIZADO ---");
        System.out.println("Acumulado hoy: "+controlador.acumuladoActual());
        System.out.println("Nueva meta: "+controlador.getMetaDiariaML());
        System.out.println("Progreso: "+controlador.generarBarra());
        
        System.out.print("\nPresione [ENTER] para continuar...");
        scanner.nextLine(); // Se limpia el Buffer
        scanner.nextLine(); // Esperar hasta ingresar ENTER
    }

}
