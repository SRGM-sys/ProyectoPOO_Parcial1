package espol.poo.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Actividad {
    protected int id; // Identificador único de la actividad
    protected String nombre; // Nombre de la actividad
    protected String descripcion; // Descripción de la actividad
    protected LocalDateTime fechaVencimiento; // Fecha y hora de vencimiento
    protected Prioridad prioridad; // Prioridad de la actividad
    protected EstadoActividad estado; // Estado actual de la actividad
    protected double avance; // Porcentaje de avance de la actividad
    protected double tiempoEstimadoMinutos; // Tiempo estimado en minutos
    protected List<SesionEnfoque> sesionesEnfoque = new ArrayList<>(); // Lista de sesiones de enfoque

    // Constructor de la clase Actividad
    public Actividad(int id, String nombre, String descripcion, LocalDateTime fechaVencimiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.prioridad = Prioridad.MEDIA; // Prioridad por defecto
        this.estado = EstadoActividad.PENDIENTE; // Estado inicial
        this.avance = 0.0; // Avance inicial
        this.tiempoEstimadoMinutos = 0.0; // Tiempo estimado inicial
    }

    // Métodos getter y setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public java.time.LocalDateTime getFechaVencimiento() { return fechaVencimiento; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad p) { this.prioridad = p; }
    public EstadoActividad getEstado() { return estado; }
    public void setEstado(EstadoActividad e) { this.estado = e; }
    public double getAvance() { return avance; }
    public double getTiempoEstimadoMinutos() { return tiempoEstimadoMinutos; }
    public void setTiempoEstimadoMinutos(double t) { this.tiempoEstimadoMinutos = t; }
    public List<SesionEnfoque> getSesionesEnfoque() { return sesionesEnfoque; }
    
    // Método para establecer el avance y actualizar el estado
    public void setAvance(double avance) {
        this.avance = Math.max(0.0, Math.min(100.0, avance)); // Limitar el avance entre 0 y 100
        if (this.avance >= 100.0) this.estado = EstadoActividad.COMPLETADA; // Estado completada
        else if (this.avance > 0) this.estado = EstadoActividad.EN_CURSO; // Estado en curso
        else this.estado = EstadoActividad.PENDIENTE; // Estado pendiente
    }
    
    // Método para agregar una sesión de enfoque
    public void agregarSesionEnfoque(SesionEnfoque s) { this.sesionesEnfoque.add(s); }
}