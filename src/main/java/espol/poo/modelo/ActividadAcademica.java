package espol.poo.modelo;
import java.time.LocalDateTime;
public class ActividadAcademica extends Actividad {
    private String asignatura;
    private TipoAcademica tipoAcademica;
    public ActividadAcademica(int id, String nombre, String descripcion, LocalDateTime fechaVencimiento, String asignatura, TipoAcademica tipo) {
        super(id, nombre, descripcion, fechaVencimiento);
        this.asignatura = asignatura;
        this.tipoAcademica = tipo;
    }
    public String getAsignatura() { return asignatura; }
    public TipoAcademica getTipoAcademica() { return tipoAcademica; }
}