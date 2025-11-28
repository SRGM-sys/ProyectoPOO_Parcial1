package espol.poo.controlador;

import java.time.LocalDateTime;
import java.time.LocalDate;
//import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import espol.poo.modelo.Agua.RegistroIngestaAgua;

public class ControladorHidratacion {
    // Crerando el objeto de arreglos
    private ArrayList<RegistroIngestaAgua> registros = new ArrayList<>();
    private double metaDiariaML = 2500; // Inicializado en 2500 como el ejemplo
    
    // Setters y Getters como buena practica
    public ArrayList<RegistroIngestaAgua> getRegistros() {
        return registros;
    }

    public void setRegistros(ArrayList<RegistroIngestaAgua> registros) {
        this.registros = registros;
    }
    

    public double getMetaDiariaML() {
        return metaDiariaML;
    }

    public void setMetaDiariaML(double metaDiariaML) {
        this.metaDiariaML = metaDiariaML;
    }
    
    // Creo un objeto del tipo RegistroIngestaAgua y lo añado al ArrayList de registros
    public void registarIngesta(LocalDateTime hora, double cantidadML){
        RegistroIngestaAgua registro = new RegistroIngestaAgua(hora, cantidadML);
        registros.add(registro);
    }
    
    // Suma la cantidad de ML en todos los registros
    public double acumuladoActual(){
        double acumuladoML = 0;
        for(RegistroIngestaAgua registro: registros){
            acumuladoML += registro.getCantidadML();
        }
        return acumuladoML;
    }
    
    // Suma la cantidad de ML registrados excluyendo al más reciente
    public double acumuladoAnterior(){
        double acumuladoML = 0;
        int iterador = 1;
        
        for(RegistroIngestaAgua registro: registros){
            if(iterador < registros.size()){
                acumuladoML += registro.getCantidadML();
            }
            iterador++;
        }
        return acumuladoML;
    }

    // Con esto genero un String que crea la barra de progreso
    public String generarBarra(){
        double porcentaje = acumuladoActual() / metaDiariaML;
        int bloquesLlenos = (int)(porcentaje * 20);
        int bloquesVacios = 20 - bloquesLlenos;

        // StringBuilder es una clase de programación que permite crear y manipular cadenas 
        // de caracteres de manera más eficiente y flexible. En este caso la concatenación
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<bloquesLlenos; i++) sb.append("=");
        for(int i=0; i<bloquesVacios; i++) sb.append("-");
        sb.append("] ");
        sb.append((int)(porcentaje * 100)).append("% (")
          .append((int)acumuladoActual()).append("/")
          .append((int)metaDiariaML).append(")");

        return sb.toString();
    }

    // Este bloque se comprueba la confirmación del usuario y de ser verdadero se actualiza el valor de metaDiaria
    public Boolean confirmacion(char letra, double nuevaMetaDiaria){
        if(letra == 'S' || letra == 's'){
            this.metaDiariaML = nuevaMetaDiaria;
            return true;
        } else{
            return false;
        }
    }

    // Este método retorna la fecha local actual
    public LocalDate fechaActual(){
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }

    // Este método retornará la cantidad de agua que falta para completar la menta
    public double faltante(){
        double faltante = metaDiariaML - acumuladoActual();
        if(faltante > 0){
            return faltante;
        // Si ya completo la meta, entonces no falta nada
        } else{
            return 0;
        }
    }

    // Este método va a presentar todos los registros que se han hecho
    public void mostrarRegistros(){
        // Esto de aquí sirve para dar el formato de hora: 09:30 AM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        
        for(RegistroIngestaAgua registro: registros){
            String hora = registro.getHora().format(formatter);
            System.out.println("- "+ hora +": " +registro.getCantidadML() +" ml");
        } 
         
    }

    
}
