/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import tdas.Lista;

/**
 *
 * @author felip
 */
public class ConcretSubject implements Observable{
    private Lista <Observer> observadores = new Lista <>();

    public String setFlag(Usuario usuario, Cita cita, boolean opc) {
        return notifyObservers(usuario, cita, opc); 
    }
    
    @Override
    public void register(Observer o) {
        observadores.adicionarUltimo(o);
    }

    @Override
    public void unregister(Observer o) {
        observadores.remove(o);
    }

    @Override
    public String notifyObservers(Usuario usuario, Cita cita, boolean opc) {
        String salida = "";
        for(Observer o : observadores){
            if(usuario.equals(o))
            salida += o.update(cita,opc) + "\n";
        }
        return salida;
    }
}
