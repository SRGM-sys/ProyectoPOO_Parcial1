package espol.poo.controlador;
import espol.poo.modelo.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class ControladorSesiones {
    private List<SesionEnfoque> sesiones = new ArrayList<>();
    private int proximoIdSesion = 1;
    private ControladorActividades controladorActividades;
    public ControladorSesiones(ControladorActividades controladorActividades) { this.controladorActividades = controladorActividades; }
    public SesionEnfoque crearYAsociarSesion(int actividadId, TecnicaEnfoque tecnica, int duracionMinutos, LocalDate fecha, LocalTime hora, String obs) {
        SesionEnfoque s = new SesionEnfoque(proximoIdSesion++, tecnica, duracionMinutos, fecha, hora, obs);
        sesiones.add(s);
        Optional<Actividad> oa = controladorActividades.buscarPorId(actividadId);
        if (oa.isPresent()) {
            oa.get().agregarSesionEnfoque(s);
            double tiempoEstimado = oa.get().getTiempoEstimadoMinutos();
            if (tiempoEstimado > 0) {
                double incremento = (duracionMinutos / tiempoEstimado) * 100.0;
                double nuevo = oa.get().getAvance() + incremento;
                oa.get().setAvance(nuevo);
            }
        }
        return s;
    }
}