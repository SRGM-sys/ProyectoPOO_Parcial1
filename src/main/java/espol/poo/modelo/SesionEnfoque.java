package espol.poo.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SesionEnfoque {
    private int id;
    private TecnicaEnfoque tecnica;
    private int duracionMinutos;
    private LocalDate fecha;
    private LocalTime hora;
    private String observaciones;

    public SesionEnfoque(int id, TecnicaEnfoque tecnica, int duracionMinutos, LocalDate fecha, LocalTime hora, String observaciones) {
        this.id = id;
        this.tecnica = tecnica;
        this.duracionMinutos = duracionMinutos;
        this.fecha = fecha;
        this.hora = hora;
        this.observaciones = observaciones;
    }

    public int getId() { return id; }
    public TecnicaEnfoque getTecnica() { return tecnica; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getObservaciones() { return observaciones; }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("| %-14s | %-22s | %-14d |", fecha.format(df), tecnica.name(), duracionMinutos);
    }
}
