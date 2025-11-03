package espol.poo.controlador;
import espol.poo.modelo.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControladorActividades {
    private List<Actividad> actividades = new ArrayList<>(); // Lista de actividades
    private int proximoId = 1; // ID para la próxima actividad

    public ControladorActividades() { 
        inicializarActividades(); // Inicializa las actividades predeterminadas
    }

    // Método para inicializar actividades con datos de ejemplo
    private void inicializarActividades() {
        ActividadPersonal personal = new ActividadPersonal(proximoId++, "Cita médica", "Cita médica con especialista", LocalDateTime.of(2025,11,30,10,0), "Clínica Central");
        personal.setTiempoEstimadoMinutos(45.0);
        
        ActividadAcademica proyecto = new ActividadAcademica(proximoId++, "Proyecto Final", "Proyecto integrador POO", LocalDateTime.of(2025,11,30,23,59), "Programación Orientada a Objetos", TipoAcademica.PROYECTO);
        proyecto.setPrioridad(Prioridad.ALTA); 
        proyecto.setTiempoEstimadoMinutos(3600.0); 
        proyecto.setAvance(70.0);
        proyecto.agregarSesionEnfoque(new SesionEnfoque(1, TecnicaEnfoque.POMODORO, 25, java.time.LocalDate.of(2025,10,28), java.time.LocalTime.of(9,0), "Pomodoro día 1"));
        proyecto.agregarSesionEnfoque(new SesionEnfoque(2, TecnicaEnfoque.POMODORO, 25, java.time.LocalDate.of(2025,10,29), java.time.LocalTime.of(9,30), "Pomodoro día 2"));
        
        ActividadAcademica tarea = new ActividadAcademica(proximoId++, "Tarea", "Entrega de ejercicios", LocalDateTime.of(2025,12,3,23,59), "Algoritmos", TipoAcademica.TAREA);
        tarea.setTiempoEstimadoMinutos(120.0);
        
        ActividadAcademica examen = new ActividadAcademica(proximoId++, "Examen Parcial", "Examen final parcial", LocalDateTime.of(2025,12,10,9,0), "Cálculo", TipoAcademica.EXAMEN);
        examen.setTiempoEstimadoMinutos(180.0);
        
        // Agrega las actividades inicializadas a la lista
        actividades.add(personal); 
        actividades.add(proyecto); 
        actividades.add(tarea); 
        actividades.add(examen);
    }

    // Devuelve la lista de todas las actividades
    public List<Actividad> listarActividades() { 
        return actividades; 
    }

    // Devuelve una lista de actividades pendientes (avance < 100%)
    public List<Actividad> listarPendientes() { 
        List<Actividad> r = new ArrayList<>(); 
        for (Actividad a: actividades) 
            if (a.getAvance() < 100.0) 
                r.add(a); 
        return r; 
    }

    // Busca una actividad por su ID
    public Optional<Actividad> buscarPorId(int id) { 
        return actividades.stream().filter(a->a.getId()==id).findFirst(); 
    }

    // Crea una nueva actividad personal
    public Actividad crearActividadPersonal(String nombre, String descripcion, LocalDateTime fechaVenc, String lugar, Prioridad prioridad, double tiempoEstimadoMinutos) {
        ActividadPersonal ap = new ActividadPersonal(proximoId++, nombre, descripcion, fechaVenc, lugar); 
        ap.setPrioridad(prioridad); 
        ap.setTiempoEstimadoMinutos(tiempoEstimadoMinutos); 
        actividades.add(ap); 
        return ap;
    }

    // Crea una nueva actividad académica
    public Actividad crearActividadAcademica(String nombre, String descripcion, LocalDateTime fechaVenc, String asignatura, TipoAcademica tipo, Prioridad prioridad, double tiempoEstimadoHoras) {
        ActividadAcademica aa = new ActividadAcademica(proximoId++, nombre, descripcion, fechaVenc, asignatura, tipo); 
        aa.setPrioridad(prioridad); 
        aa.setTiempoEstimadoMinutos(tiempoEstimadoHoras * 60.0); 
        actividades.add(aa); 
        return aa;
    }

    // Agrega una actividad a la lista
    public void agregarActividad(Actividad a) { 
        a.setId(proximoId++); 
        actividades.add(a); 
    }

    // Elimina una actividad por su ID
    public boolean eliminarActividad(int id) { 
        Optional<Actividad> oa = buscarPorId(id); 
        if (oa.isPresent()) { 
            actividades.remove(oa.get()); 
            return true; 
        } 
        return false; 
    }

    // Actualiza el avance de una actividad
    public boolean actualizarAvance(int id, double nuevo) { 
        Optional<Actividad> oa = buscarPorId(id); 
        if (oa.isPresent()){ 
            oa.get().setAvance(nuevo); 
            return true; 
        } 
        return false; 
    }
}