/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import tdas.*;

/**
 * @author Luis Gabriel Romero
 * @author Andres Felipe Triviño
 * @author Tomas David
 */
public class Registro extends Subject{

    private TablaSimbolos<String, Agenda> agenda;
    private ConcretSubject ocita;
            
    public Registro() {
        agenda = new TablaSimbolos<>();
        for (int k = 1; k <= 12; k++) {
            for (int i = 1; i <= 30; i++) {
                for (int j = 6; j <= 18; j++) {
                    agenda.put(k + "/" + i + "" + j + ":00", new Agenda.Builder().cita(new Cita.Builder().fecha(k + "/" + i).hora(j + ":00").build()).build());
                }
            }
        }
        ocita = new ConcretSubject();
    }

    public TablaSimbolos<String, Agenda> getAgenda() {
        return agenda;
    }

    @Override
    public String login(Usuario usuario, Cita cita, int opc) {
        switch (opc) {
            case 1:
                return registrarUsuario(usuario);
            case 2:
                return desregistrarUsuario(usuario);
            case 3:
                return consultarExtracto(usuario);
            case 4:
                return pedirCita(usuario, cita);
            case 5:
                return cancelarCita(usuario, cita);
        }
        return null;
    }

    private String pedirCita(Usuario usuario, Cita cita) {
        ocita.register(usuario);
        if (agenda.contains(cita.getFecha() + "" + cita.getHora()) && agenda.get(cita.getFecha() + cita.getHora()).getUsuario() == null) {
            agenda.put(cita.getFecha() + cita.getHora(), new Agenda.Builder().usuario(usuario).cita(cita).build());
            System.out.println("Antes del return");
            return ocita.setFlag(usuario, cita, true);
            //return "su cita fue agendada";
        }
        return "Ese horario ya está ocupado";
    }

    private String cancelarCita(Usuario usuario, Cita cita) {
        ocita.register(usuario);
        if (agenda.get(cita.getFecha() + cita.getHora()).getUsuario() != null) {
            agenda.put(cita.getFecha() + cita.getHora(), new Agenda.Builder().cita(cita).build());
            String mensaje = ocita.setFlag(usuario, cita, false);
            ocita.unregister(usuario);
            return mensaje;
        }
        return "Ese horario de cita no coincide con la cita agendada";
    }

    private String consultarExtracto(Usuario usuario) {
        String extracto = "";
        Iterable<String> cola = agenda.keys(agenda.min(), agenda.max());
        for (String s : cola) {
            if (agenda.get(s).getUsuario() != null) {
                if (agenda.get(s).getUsuario().getCedula().equals(usuario.getCedula())) {
                extracto += "\n" + agenda.get(s).getCita() ;
                }
            }
        }
        return "este es el extacto "+ extracto;
    }

    private String registrarUsuario(Usuario usuario) {
        ocita.register(usuario);
        UsuarioDAOimp usu = new UsuarioDAOimp();
        usu.addUsuario(usuario);
        return "Se ha agregado con éxito el usuario " + usuario;
    }

    private String desregistrarUsuario(Usuario usuario) {
        UsuarioDAOimp usu = new UsuarioDAOimp();
        usu.deleteUsuario(usuario.getCedula());
        return "Se ha eliminado el usuario " + usuario;
    }
}
