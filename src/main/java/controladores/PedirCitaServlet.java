/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cita;
import modelo.Proxy;
import modelo.Usuario;

/**
 * @author Luis Gabriel Romero
 * @author Andres Felipe Triviño
 * @author Tomas David
 */
@WebServlet(name = "PedirCitaServlet", urlPatterns = {"/PedirCitaServlet"})
public class PedirCitaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String cedula = request.getParameter("cedula");
        String contrasena = request.getParameter("contrasena");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String especialidad = request.getParameter("especialidad");
        int valor;
        Proxy proxy = Proxy.getInstance();
        switch (especialidad) {
            case "Medicina general":
                valor = 150000;
                break;
            case "Cardiologia":
                valor = 200000;
                break;
            case "Neurologia":
                valor = 210000;
                break;
            case "Nutricion":
                valor = 180000;
                break;
            default:
                valor = 170000;
                break;
        }
        Cita cita = new Cita.Builder().especialidad(especialidad).fecha(fecha).hora(hora).valor(valor).build();
        Usuario usuario = new Usuario.Builder().cedula(cedula).contrasena(contrasena).build();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PedirCitaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + proxy.login(usuario, cita, 4) + "</h1>");
            out.println("<a href=\"menu.html\"><button id=\"menu\">Menu</button></a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
