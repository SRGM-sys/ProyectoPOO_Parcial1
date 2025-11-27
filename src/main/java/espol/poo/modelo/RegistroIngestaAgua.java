package espol.poo.modelo;

import java.time.LocalDateTime;

// Objeto principal de la opción Hidratación
public class RegistroIngestaAgua {
    // Un registro debe tener la hora en que se hace y lo que se va a registrar
    private LocalDateTime hora;
    private double cantidadML;

   // Constructor
    public RegistroIngestaAgua(LocalDateTime fechaHora, double cantidadML) {
        this.hora = fechaHora;
        this.cantidadML = cantidadML;
    }

    // Setters y Getters como buena práctica
    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public double getCantidadML() {
        return cantidadML;
    }

    public void setCantidadML(double cantidadML) {
        this.cantidadML = cantidadML;
    }

}
