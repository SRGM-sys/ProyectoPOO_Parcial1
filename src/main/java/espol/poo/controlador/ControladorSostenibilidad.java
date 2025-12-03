package espol.poo.controlador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import espol.poo.modelo.sostenibilidad.RegistroSostenibilidad;

public class ControladorSostenibilidad {
    //Lista para guardar los registros
     private List<RegistroSostenibilidad> registros;

     //Constructor
    public ControladorSostenibilidad() {
        this.registros = new ArrayList<>();
    }
    //Getters y Setters
    public List<RegistroSostenibilidad> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroSostenibilidad> registros) {
        this.registros = registros;
    }

    //ARegistrando los registros
    public void registrarAccionesDia(RegistroSostenibilidad registro) {
        registros.add(registro);
    }
    //Metodo para retonar el registro que conrresponde con el dia
    public RegistroSostenibilidad getRegistroDia(LocalDate dia) {
        for (RegistroSostenibilidad r : registros) {
            if (r.getFecha().equals(dia)) {
                return r;
            }
        }
        return null;
    }

    //Metodo para resumir de los puntos de sostenibilidad obtenidos durante una semana.
    public Map<String, Integer> getResumenSemanal(LocalDate dia) {
        Map<String, Integer> resumen = new HashMap<>();
        int totalPuntos = 0;
        LocalDate inicioSemana = dia.minusDays(6);

        for (RegistroSostenibilidad r : registros) {
            if (!r.getFecha().isBefore(inicioSemana) && !r.getFecha().isAfter(dia)) {
                totalPuntos += r.getPuntosDia();
            }
        }

        resumen.put("TotalPuntos", totalPuntos);
        return resumen;
    }
}




