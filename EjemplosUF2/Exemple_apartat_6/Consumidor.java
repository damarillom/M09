/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gmartinez
 */
public class Consumidor extends Thread {
    int id;                 //Ara mateix no té cap utilitat aquest atribut.
    Recursos recursos;

    
    
    public Consumidor(int id, Recursos recursos) {
        this.id = id;
        this.recursos = recursos;
    }
    
    
    public void run(){
        int numRecursAdquirit;
        
        String name = Thread.currentThread().getName().toUpperCase();
        
        System.out.println(name + ": inicialitzat.");
        
        numRecursAdquirit = recursos.adquirirRecurs();
        
        if (numRecursAdquirit != -1) {
            System.out.println(name + ": adquirit el recurs " + numRecursAdquirit + " amb valor " + recursos.getRecursos(numRecursAdquirit) + " + sleep(7000)");
            
            try {
                Thread.currentThread().sleep(7000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Si camuflem la següent linia, el semàfor no canvia i el recurs no queda alliberat.
            recursos.lliberarRecurs(numRecursAdquirit);
            
            System.out.println(name + ": alliberat el recurs " + numRecursAdquirit);
        } else {
            System.out.println(name + ": no s'ha adquirit cap recurs");
        }
        
    }
    
}
