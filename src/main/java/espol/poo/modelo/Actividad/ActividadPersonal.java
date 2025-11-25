package espol.poo.modelo.Actividad; 
import java.time.LocalDateTime; 

public class ActividadPersonal extends Actividad { // Clase que extiende Actividad
    private String lugar; // Atributo para almacenar el lugar

    // Constructor de la clase
    public ActividadPersonal(int id, String nombre, String descripcion, LocalDateTime fechaVencimiento, String lugar) {
        super(id, nombre, descripcion, fechaVencimiento); // Llamada al constructor de la clase padre
        this.lugar = lugar; // Inicialización del atributo lugar
    }

    public String getLugar() { return lugar; } // Método para obtener el lugar
}
