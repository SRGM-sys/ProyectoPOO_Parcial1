package espol.poo.controlador;
import espol.poo.modelo.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Controlador para manejar sesiones de enfoque
public class ControladorSesiones {
    // Lista para almacenar las sesiones
    private List<SesionEnfoque> sesiones = new ArrayList<>();
    // Contador para IDs únicos de sesiones
    private int proximoIdSesion = 1;
    private ControladorActividades controladorActividades;

    public ControladorSesiones(ControladorActividades controladorActividades) { this.controladorActividades = controladorActividades; }

    // Crea una nueva sesión y la asocia a una actividad
    public SesionEnfoque crearYAsociarSesion(int actividadId, TecnicaEnfoque tecnica, int duracionMinutos, LocalDate fecha, LocalTime hora, String obs) {
        // Crear nueva sesión
        SesionEnfoque s = new SesionEnfoque(proximoIdSesion++, tecnica, duracionMinutos, fecha, hora, obs);
        sesiones.add(s);
        
        Optional<Actividad> oa = controladorActividades.buscarPorId(actividadId);
        if (oa.isPresent()) {
            // Asociar sesión a la actividad
            oa.get().agregarSesionEnfoque(s);
            
            // Calcular y actualizar el avance de la actividad
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