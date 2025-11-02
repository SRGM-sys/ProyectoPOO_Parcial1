package espol.poo.vista;

import espol.poo.controlador.ControladorJuego;
// 5) La vista no debe llamar al modelo (pero sí necesita el tipo JuegoMemoria para mostrar el estado)
// La Vista NO importa JuegoMemoria, en su lugar, el Controlador le pasa la información necesaria o usa la referencia
import java.util.List;
import java.util.Scanner;
import espol.poo.modelo.JuegoMemoria; // Necesario para el método mostrarTablero, que lee el estado del juego.
import espol.poo.modelo.Carta; // Necesario para el método mostrarTablero

public class VistaJuego {
    private Scanner scanner;
    
    public VistaJuego(Scanner scanner) {
        this.scanner = scanner;
    }
    
    // 6) Vista solo usa controlador (Este método inicia la cadena de llamadas)
    public void iniciarNuevoJuego(int dimension, List<String> valores) {
        ControladorJuego controlador = new ControladorJuego(dimension, valores); 
        controlador.iniciarJuego(this);
    }

    public int mostrarMenu() {
        // Lógica del menú
        System.out.println("\n--- MENÚ ---");
        System.out.println("1. Iniciar Juego");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarBienvenida(int totalPares) {
        System.out.println("--- JUEGO DE MEMORIA ECOLÓGICO ---");
        System.out.println("¡Encuentra los " + totalPares + " pares!");
    }
    
    // Recibe el MODELO completo para extraer el estado y mostrarlo
    public void mostrarTablero(JuegoMemoria juego) {
        int dim = juego.getTablero().getDimension();
        int numCartas = dim * dim;
        List<Carta> cartas = juego.getTablero().getCartas();

        // Implementación de impresión del tablero
        for (int i = 0; i < dim; i++) {
            System.out.print("+");
            for (int j = 0; j < dim; j++) {
                System.out.print("-----" + "+");
            }
            System.out.println();
            
            for (int j = 0; j < dim; j++) {
                int pos = i * dim + j + 1;
                Carta c = cartas.get(pos - 1);
                String contenido;
                
                if (c.isEstaVolteada() || c.isEstaEncontrada()) {
                    contenido = String.format(" %-4s", c.getValor().substring(0, Math.min(c.getValor().length(), 4)));
                } else {
                    contenido = String.format(" %-4s", pos);
                }
                
                System.out.print("|" + contenido);
            }
            System.out.println("|");
        }
        System.out.print("+");
        for (int j = 0; j < dim; j++) {
            System.out.print("-----" + "+");
        }
        System.out.println();
        
        System.out.println("Total de Intentos: " + juego.getIntentos() + 
                           " | Pares Encontrados: " + juego.getParesEncontrados() + "/" + (numCartas/2));
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    public int solicitarEntrada(String mensaje, int min, int max) {
        int posicion = -1;
        while (posicion < min || posicion > max) {
            System.out.print(mensaje);
            try {
                posicion = Integer.parseInt(scanner.nextLine());
                if (posicion < min || posicion > max) {
                    mostrarMensaje("Posición inválida. Ingrese un número entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Entrada no válida. Por favor, ingrese un número.");
                posicion = -1;
            }
        }
        return posicion;
    }

    public void esperarEnter() {
        System.out.println("Presione [ENTER] para continuar su intento...");
        scanner.nextLine();
    }
}