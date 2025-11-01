package espol.poo.modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Actividad {
    protected int id;
    protected String nombre;
    protected String descripcion;
    protected LocalDateTime fechaVencimiento;
    protected Prioridad prioridad;
    protected EstadoActividad estado;
    protected double avance;
    protected double tiempoEstimadoMinutos;
    protected List<SesionEnfoque> sesionesEnfoque = new ArrayList<>();

    public Actividad(int id, String nombre, String descripcion, LocalDateTime fechaVencimiento) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.prioridad = Prioridad.MEDIA;
        this.estado = EstadoActividad.PENDIENTE;
        this.avance = 0.0;
        this.tiempoEstimadoMinutos = 0.0;
    }

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
    public void setAvance(double avance) {
        this.avance = Math.max(0.0, Math.min(100.0, avance));
        if (this.avance >= 100.0) this.estado = EstadoActividad.COMPLETADA;
        else if (this.avance > 0) this.estado = EstadoActividad.EN_CURSO;
        else this.estado = EstadoActividad.PENDIENTE;
    }
    public double getTiempoEstimadoMinutos() { return tiempoEstimadoMinutos; }
    public void setTiempoEstimadoMinutos(double t) { this.tiempoEstimadoMinutos = t; }
    public List<SesionEnfoque> getSesionesEnfoque() { return sesionesEnfoque; }
    public void agregarSesionEnfoque(SesionEnfoque s) { this.sesionesEnfoque.add(s); }
}