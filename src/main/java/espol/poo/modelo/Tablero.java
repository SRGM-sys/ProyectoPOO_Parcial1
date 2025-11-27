package espol.poo.modelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void imprimirUbicacionDePares() {
        // Usaremos un Map para agrupar las cartas por su valor y mostrar sus posiciones.
        Map<String, List<Integer>> ubicaciones = new HashMap<>();

        for (int i = 0; i < cartas.size(); i++) {
            Carta c = cartas.get(i);
            String valor = c.getValor();
            int posicion = i + 1; // Posición 1-based

            // Agrega la posición a la lista de ubicaciones para ese valor
            ubicaciones.computeIfAbsent(valor, k -> new ArrayList<>()).add(posicion);
        }

        System.out.println("\n---  UBICACIÓN DE PARES ---");
        // Itera sobre el mapa para imprimir los resultados
        for (Map.Entry<String, List<Integer>> entry : ubicaciones.entrySet()) {
            String valor = entry.getKey();
            List<Integer> pos = entry.getValue();

            // Formatea la salida para mostrar el par de posiciones
            if (pos.size() == 2) {
                System.out.println("Par \"" + valor + "\": Cartas " + pos.get(0) + " y " + pos.get(1));
            } else {
                // En un juego normal, esto no debería ocurrir.
                System.out.println("Valor \"" + valor + "\": Posiciones " + pos);
            }
        }
        System.out.println("------------------------------------------");
    }

}