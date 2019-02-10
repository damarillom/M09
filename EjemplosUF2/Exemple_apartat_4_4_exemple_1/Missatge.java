/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_4_exemple_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gmartinez
 */

// En el main és crearà un objecte de tipus Missatge (msg) que es compartirà entre tots els fils i que
// tindrà dins el monitor (té al menys 1 "synchronized"). Tot l'objecte msg és el monitor. 
// wait(): li indica al fil en curs que abandoni el monitor (que és l'objecte on es troba el wait()) i es 
// vagi a dormir fins que un altre fil entre en el mateix monitor (objecte) i truqui a notify().
// Hi haurà un objecte de la classe Missatge que serà compartit pels fils de Waiter i Notifier.
//
// Independentment des de quina funció del monitor es cridi al notify(), el notify() afectarà al primer fil
// que va llançar un wait() sense importar en quina funció es trobava el wait(). Els wait()’s i notify()’s
// funcionen a nivell d’objecte (classe) i no pas de funció.

public class Missatge {
    private String missatge;
    
    public Missatge(String str){
        this.missatge = str;
    }

    public String getMsg() {
        return missatge;
    }

    public void setMsg(String str) {
        this.missatge = str;
    }
    
    
    public synchronized String imprimirMissatge(String nomFil){
        if (nomFil.toUpperCase().contains("WAITER")) {
            try {
                System.out.println(nomFil + " entra en Missatge i llança un wait().");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Missatge.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            System.out.println(nomFil + " entra en Missatge i llança un notify().");
            notify();       //Amb aquest només 1 dels fils que estiguin esperant perquè han executat el wait() d'aquest mètode podrà continuar.
            //notifyAll();  //Amb aquest tots els fils que estiguin esperant perquè han executat el wait() d'aquest mètode, podran continuar.
        }
        
        return "FI";
    }
    
    
}
