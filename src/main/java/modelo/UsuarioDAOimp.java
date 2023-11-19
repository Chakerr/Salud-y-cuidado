/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Luis Gabriel Romero
 * @author Andres Felipe Triviño
 * @author Tomas David
 */
public class UsuarioDAOimp implements UsuarioDAO {

    private String driver;
    private String url;
    private String login;
    private String password;
    private String sentencia;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    private Usuario usu;

    public UsuarioDAOimp() {
        //jdbc:derby://localhost:1527/sample [app on APP]
        driver = "org.apache.derby.jdbc.ClientDriver";
        url = "jdbc:derby://localhost:1527/sample";
        login = "app";
        password = "app";
        sentencia = "";
        connection = null;
        statement = null;
        rs = null;
        usu = new Usuario();
    }

    public void conectar() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAOimp.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error l- " + ex.getMessage());
        } catch (SQLException ex2) {
            Logger.getLogger(UsuarioDAOimp.class.getName()).log(Level.SEVERE, null, ex2);
            System.out.println("Error 2- " + ex2.getMessage());
        }
    }

    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException ex2) {
            Logger.getLogger(UsuarioDAOimp.class.getName()).log(Level.SEVERE, null, ex2);
            System.out.println("Error 2- " + ex2.getMessage());
        }
    }

    @Override
    public void deleteUsuario(String cedula) {
        if (connection == null) {
            conectar();
        }
        sentencia = "DELETE FROM usuario WHERE cedula = '" + cedula + "'";
        System.out.println("Eliminando usuario con cedula " + cedula + ": " + sentencia);

        try {
            statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int encontrado = statement.executeUpdate(sentencia);

            if (encontrado > 0) {
                System.out.println("Usuario con cedula " + cedula + " eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un usuario con la cedula " + cedula);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUsuario(Usuario usu) {
        if (connection == null) {
            conectar();
        }
        sentencia = "INSERT INTO usuario VALUES("
                + "'"
                + usu.getNombre() + "','"
                + usu.getCedula() + "','"
                + usu.getContrasena() + "','"
                + usu.getNumero() + "',"
                + usu.getGenero() + ""
                + ")";
        System.out.println("Ingreso al añadir el usuario " + sentencia);
        try {
            statement = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Usuario buscarPorCedulaYContrasena(String cedula, String contrasena) {
        Usuario usuarioEncontrado = null;

        if (connection == null) {
            conectar();
        }

        sentencia = "SELECT * FROM usuario WHERE cedula = ? AND contrasena = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sentencia)) {
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, contrasena);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioEncontrado = new Usuario(
                        resultSet.getString("nombre"),
                        resultSet.getString("cedula"),
                        resultSet.getString("contrasena"),
                        resultSet.getString("numero"),
                        resultSet.getInt("genero"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAOimp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarioEncontrado;
 }
}
