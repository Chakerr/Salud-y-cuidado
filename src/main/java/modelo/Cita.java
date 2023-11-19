/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * @author Luis Gabriel Romero
 * @author Andres Felipe Triviño
 * @author Tomas David
 */
public class Cita {

    private String especialidad;
    private String fecha;
    private String hora;
    private double valor;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Cita{" + "especialidad=" + especialidad + ", fecha=" + fecha + ", hora=" + hora + ", valor=" + valor + '}';
    }

    public Cita(Builder builder){
        this.especialidad = builder.especialidad;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
        this.valor = builder.valor;
    }
    
    public static class Builder {
        private String especialidad;
        private String fecha;
        private String hora;
        private double valor;

        public Cita.Builder especialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public Cita.Builder fecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        public Cita.Builder hora(String hora) {
            this.hora = hora;
            return this;
        }

        public Cita.Builder valor(double valor) {
            this.valor = valor;
            return this;
        }
        
        public Cita build(){
            return new Cita(this);
        }
    
    }
}
