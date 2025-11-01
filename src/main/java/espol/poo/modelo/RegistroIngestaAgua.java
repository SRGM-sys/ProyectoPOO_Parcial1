package espol.poo.modelo;

import java.time.LocalTime;

// Objeto principal de la opción Hidratación
public class RegistroIngestaAgua {
    // Un registro debe tener la hora en que se hace y lo que se va a registrar
    private LocalTime hora;
   private double cantidadML;

   // Constructor
    public RegistroIngestaAgua(LocalTime fechaHora, double cantidadML) {
        this.hora = fechaHora;
        this.cantidadML = cantidadML;
    }

    // Setters y Getters como buena práctica
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getCantidadML() {
        return cantidadML;
    }

    public void setCantidadML(double cantidadML) {
        this.cantidadML = cantidadML;
    }

}
