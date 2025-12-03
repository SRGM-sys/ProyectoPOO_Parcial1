package espol.poo.modelo.actividad; 
import java.time.LocalDateTime;

public class ActividadAcademica extends Actividad { // Clase que extiende Actividad
    private String asignatura; // Atributo para la asignatura
    private TipoAcademica tipoAcademica; // Atributo para el tipo académico

    // Constructor de la clase
    public ActividadAcademica(int id, String nombre, String descripcion, LocalDateTime fechaVencimiento, String asignatura, TipoAcademica tipo) {
        super(id, nombre, descripcion, fechaVencimiento); // Llamada al constructor de la clase padre
        this.asignatura = asignatura; // Inicialización de la asignatura
        this.tipoAcademica = tipo; // Inicialización del tipo académico
    }

    // Método para obtener la asignatura
    public String getAsignatura() { return asignatura; } 
    // Método para obtener el tipo académico
    public TipoAcademica getTipoAcademica() { return tipoAcademica; } 
}