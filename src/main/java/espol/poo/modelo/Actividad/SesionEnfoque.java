package espol.poo.modelo.actividad;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Clase que representa una sesión de enfoque
public class SesionEnfoque {
    // Identificador único
    private int id;
    // Técnica aplicada
    private TecnicaEnfoque tecnica;
    // Duración en minutos
    private int duracionMinutos;
    // Fecha de la sesión
    private LocalDate fecha;
    // Hora de la sesión
    private LocalTime hora;
    // Observaciones adicionales
    private String observaciones;

    // Constructor con todos los campos
    public SesionEnfoque(int id, TecnicaEnfoque tecnica, int duracionMinutos, LocalDate fecha, LocalTime hora, String observaciones) {
        this.id = id;
        this.tecnica = tecnica;
        this.duracionMinutos = duracionMinutos;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
    }

    // Métodos accesores (getters)
    public int getId() { return id; }
    public TecnicaEnfoque getTecnica() { return tecnica; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getObservaciones() { return observaciones; }

    // Genera una representación formateada para mostrar en tabla
    @Override
    public String toString() {
        // Formato de fecha dd/MM/yyyy
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("| %-14s | %-22s | %-14d |", fecha.format(df), tecnica.name(), duracionMinutos);
    }
}
