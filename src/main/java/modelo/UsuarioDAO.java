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
public interface UsuarioDAO {
    public void deleteUsuario(String cedula);
    public void addUsuario(Usuario usu);
    public Usuario buscarPorCedulaYContrasena(String cedula, String contrasena);
}
