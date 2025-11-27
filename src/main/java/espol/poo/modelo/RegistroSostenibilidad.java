package espol.poo.modelo;
//import java.time.LocalDate;
import java.time.LocalDateTime;
public class RegistroSostenibilidad {
     private LocalDateTime fecha;
    private boolean usoTransporteSostenible;
    private boolean evitoImpresiones;
    private boolean evitoEnvasesDescartables;
    private boolean separoResiduos;
    //Constructor
    public RegistroSostenibilidad(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    //Getters y Setters
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public boolean isUsoTransporteSostenible() {
        return usoTransporteSostenible;
    }

    public void setUsoTransporteSostenible(boolean usoTransporteSostenible) {
        this.usoTransporteSostenible = usoTransporteSostenible;
    }

    public boolean isEvitoImpresiones() {
        return evitoImpresiones;
    }

    public void setEvitoImpresiones(boolean evitoImpresiones) {
        this.evitoImpresiones = evitoImpresiones;
    }

    public boolean isEvitoEnvasesDescartables() {
        return evitoEnvasesDescartables;
    }

    public void setEvitoEnvasesDescartables(boolean evitoEnvasesDescartables) {
        this.evitoEnvasesDescartables = evitoEnvasesDescartables;
    }

    public boolean isSeparoResiduos() {
        return separoResiduos;
    }

    public void setSeparoResiduos(boolean separoResiduos) {
        this.separoResiduos = separoResiduos;
    }

    //Metodo para sumar puntos del dia
    public int getPuntosDia() {
        int puntos = 0;
        if (usoTransporteSostenible) puntos++;
        if (evitoImpresiones) puntos++;
        if (evitoEnvasesDescartables) puntos++;
        if (separoResiduos) puntos++;
        return puntos;
    }
}
