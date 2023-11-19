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
public interface Observable {

    public void register(Observer o);

    public void unregister(Observer o);

    public String notifyObservers(Usuario usuario, Cita cita, boolean opc);
}
