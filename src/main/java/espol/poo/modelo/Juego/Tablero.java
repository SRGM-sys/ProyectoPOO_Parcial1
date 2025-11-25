package espol.poo.modelo.Juego;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Tablero {
    // Atributos
    private List<Carta> cartas;
    private int dimension; // Ejemplo: 4 (para un tablero 4x4)

    // Constructor
    public Tablero(int dimension, List<String> valoresPares) {
        this.dimension = dimension;
        inicializarCartas(valoresPares);
        barajar();
    }

    // Métodos
    public void inicializarCartas(List<String> valoresPares) {
        this.cartas = new ArrayList<>();
        // Crear las parejas de cartas
        for (String valor : valoresPares) {
            cartas.add(new Carta(valor)); // Primer par
            cartas.add(new Carta(valor)); // Segundo par
        }
    }

    public void barajar() {
        Collections.shuffle(cartas); // Mezcla las cartas
    }

    public Carta getCarta(int posicion) {
        // La posición es 1-based en la vista, se ajusta a 0-based en el modelo
        if (posicion >= 1 && posicion <= cartas.size()) {
            return cartas.get(posicion - 1);
        }
        return null; // O manejar una excepción
    }

    // Otros getters y setters...
    public List<Carta> getCartas() {
        return cartas;
    }
    
    public int getDimension() {
        return dimension;
    }
}