package espol.poo.modelo.juego;

public class Carta {
    // Atributos
    private String valor; // Ej: "RECIC", "ENERGIA", etc.
    private boolean estaVolteada;
    private boolean estaEncontrada;

    // Constructor
    public Carta(String valor) {
        this.valor = valor;
        this.estaVolteada = false;
        this.estaEncontrada = false;
    }

    // Métodos (Getters y Setters)
    public String getValor() {
        return valor;
    }

    // Otros getters y setters como en el UML...
    public boolean isEstaVolteada() {
        return estaVolteada;
    }

    public void setEstaVolteada(boolean estaVolteada) {
        this.estaVolteada = estaVolteada;
    }
    
    public boolean isEstaEncontrada() {
        return estaEncontrada;
    }

    public void setEstaEncontrada(boolean estaEncontrada) {
        this.estaEncontrada = estaEncontrada;
    }

    // Métodos de acción
    public void voltear() {
        this.estaVolteada = true;
    }

    public void ocultar() {
        this.estaVolteada = false;
    }
}