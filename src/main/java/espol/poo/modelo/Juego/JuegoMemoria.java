package espol.poo.modelo.Juego;

import java.util.List;

public class JuegoMemoria {
    // Atributos
    private Tablero tablero;
    private int intentos;
    private int paresEncontrados;

    // Constructor
    public JuegoMemoria(int dimension, List<String> valores) {
        this.tablero = new Tablero(dimension, valores);
        this.intentos = 0;
        this.paresEncontrados = 0;
    }

    // Métodos
    public boolean seleccionarCartas(int pos1, int pos2) {
        if (pos1 == pos2) return false; // No se puede seleccionar la misma carta

        Carta c1 = tablero.getCarta(pos1);
        Carta c2 = tablero.getCarta(pos2);
        
        if (c1 == null || c2 == null || c1.isEstaEncontrada() || c2.isEstaEncontrada()) {
             return false; // Cartas no válidas o ya encontradas
        }

        // Voltear cartas para el intento
        c1.voltear();
        c2.voltear();
        this.intentos++; // Aumentar intentos

        // Verificar coincidencia
        boolean coinciden = c1.getValor().equals(c2.getValor());

        if (coinciden) {
            c1.setEstaEncontrada(true);
            c2.setEstaEncontrada(true);
            this.paresEncontrados++;
            return true;
        } 
        
        // Si no coinciden, se ocultan nuevamente (lógica de ocultar se maneja en el Controlador)
        return false;
    }

    public boolean juegoTerminado() {
        // En un tablero 4x4 hay 16 cartas, 8 pares.
        return paresEncontrados == (tablero.getDimension() * tablero.getDimension()) / 2;
    }

    // Getters y Setters...
    public int getIntentos() {
        return intentos;
    }

    public int getParesEncontrados() {
        return paresEncontrados;
    }

    public Tablero getTablero() {
        return tablero;
    }
}