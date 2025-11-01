package espol.poo.modelo;
import java.time.LocalDateTime;
public class ActividadPersonal extends Actividad {
    private String lugar;
    public ActividadPersonal(int id, String nombre, String descripcion, LocalDateTime fechaVencimiento, String lugar) {
        super(id, nombre, descripcion, fechaVencimiento);
        this.lugar = lugar;
    }
    public String getLugar() { return lugar; }
}
