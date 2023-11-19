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
public class Proxy extends Subject {

    private Registro registro;
    private static Proxy proxy;

    private Proxy() {
        registro = new Registro();
    }

    public static Proxy getInstance() {
        if (proxy == null) {
            proxy = new Proxy();
        }
        return proxy;
    }

    @Override
    public String login(Usuario usuario, Cita cita, int opc) {
        if (opc == 1) {
            return registro.login(usuario, cita, opc);
        } else {
            UsuarioDAOimp usu = new UsuarioDAOimp();
            usuario = usu.buscarPorCedulaYContrasena(usuario.getCedula(), usuario.getContrasena());
            if (usuario != null) {
                if (registro == null) {
                    registro = new Registro();
                }
                return registro.login(usuario, cita, opc);
            }else{
                return "Los datos no coinciden";
            }
        }
    }
}


