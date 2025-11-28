package espol.poo.controlador;

// Importa el MODELO y la VISTA
import espol.poo.modelo.Juego.Carta;
import espol.poo.modelo.Juego.JuegoMemoria;
import espol.poo.vista.VistaJuego;
import java.util.List;

public class ControladorJuego {
    private JuegoMemoria modelo;

    public ControladorJuego(int dimension, List<String> valores) {
        // Inicializa el Modelo (7) El controlador llama al modelo
        this.modelo = new JuegoMemoria(dimension, valores); 
    }

    public void iniciarJuego(VistaJuego vista) {
        // Lógica de inicio...
        int numCartas = modelo.getTablero().getDimension() * modelo.getTablero().getDimension();
        int totalPares = numCartas / 2;

        modelo.getTablero().imprimirUbicacionDePares();

        while (!modelo.juegoTerminado()) {
            jugarTurno(vista);
        }

        vista.mostrarMensaje("✅ ¡JUEGO TERMINADO! Has encontrado los " + totalPares + " pares en " + modelo.getIntentos() + " intentos.");
    }

    private void jugarTurno(VistaJuego vista) {
        int numCartas = modelo.getTablero().getDimension() * modelo.getTablero().getDimension();
        vista.mostrarMensaje("\n--- TURNO " + (modelo.getIntentos() + 1) + " | SELECCIÓN ---");
        
        // El Controlador pide las posiciones a la Vista
        int pos1 = vista.solicitarEntrada("Ingrese el número de la PRIMERA carta: ", 1, numCartas);
        int pos2 = vista.solicitarEntrada("Ingrese el número de la SEGUNDA carta: ", 1, numCartas);
        
        vista.mostrarMensaje("Volteando cartas...");

        // El Controlador invoca la lógica del Modelo
        boolean acierto = modelo.seleccionarCartas(pos1, pos2);
        
        // El Controlador pide a la Vista que se actualice
        vista.mostrarTablero(modelo); 

        Carta c1 = modelo.getTablero().getCarta(pos1);
        Carta c2 = modelo.getTablero().getCarta(pos2);
        
        if (acierto) {
            vista.mostrarMensaje("✅ ¡PAR ENCONTRADO! El par \"" + c1.getValor() + "\" (Cartas " + pos1 + " y " + pos2 + ") se mantiene visible.");
        } else {
            vista.mostrarMensaje("❌ ¡NO COINCIDEN! Las cartas se ocultarán.");
            vista.esperarEnter();
            
            // Ocultar cartas (Método de Model, invocado por Controller)
            // Solo oculta si no está encontrada, previniendo errores de re-ocultamiento
            if (c1 != null && !c1.isEstaEncontrada()) c1.ocultar();
            if (c2 != null && !c2.isEstaEncontrada()) c2.ocultar();
        }
        
        vista.mostrarTablero(modelo);
    }
}