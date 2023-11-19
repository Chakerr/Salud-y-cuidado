/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Luis Gabriel Romero
 * @author Andres Felipe Triviño
 * @author Tomas David
 */
public abstract class Subject {
    public abstract String login(Usuario usuario, Cita cita, int opc);
}
