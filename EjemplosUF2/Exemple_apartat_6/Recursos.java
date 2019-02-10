/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gines
 */

// Si hi ha 5 recursos i els volem fer servir tots, a llavors el semàfor és de tamany 5 ([0-4]). 
// Segons anem agafant recursos fem semafor.acquire() lo qual incrementarà el semàfor (+ 1) fins que 
// arribi a 4, en aquest cas el següent semafor.acquire() bloqueijarà l'execució fins que algú fassi
// un semafor.release() i baixi el nº de semafor en 1.
// El semàfor es un contador i mentre no arribem al seu tope, tot anirà OK. Quan arribem al seu 
// tope, l'execució del programa es bloqueija en aquell punt (on fem el semafor.acquire()).

public class Recursos {

    private int tamanyRecursos;
    private Semaphore semafor;
    private Integer[] recursos;     //Són els recursos als quals es vol accedir.
    private boolean[] utilitzats;   //Indica si cadascun dels recursos s'està fent servir o no.

    
    public Integer getRecursos(int i) {
        return recursos[i];
    }


    public Recursos(int tamany) {
        tamanyRecursos = tamany;
        semafor = new Semaphore(tamany);    //Creem un semàfor per controlar l'accés als recursos.
        utilitzats = new boolean[tamany];   //Creem l'array indicador de l'ús dels recursos.
        recursos = new Integer[tamany];     //Creem l'array de recursos.

        //A l'array de recursos li assignem valors.
        for (int i = 0; i < tamany; i++) {
            recursos[i] = new Integer(i+100);
        }
    }

    //Busquem un recurs lliure.
    //Si el mètode no fós sincronitzat, diverses tarees podrien arribar a aquest punt a la vegada
    //i se les assignaria el mateix recurs a totes elles.
    public synchronized int asignarRecurs() {
        int i = 0;
        while (i < tamanyRecursos) {
            if (utilitzats[i] == false) {
                utilitzats[i] = true;
                break;
            }
            i++;
        }

        return (i);
    }

    //Mètode per quan algú vol un recurs.
    //Si tots els recursos estan utilitzats, es bloqueijarà fins que algun d'ells sigui alliberat.
    public Integer adquirirRecurs() {
        String name = Thread.currentThread().getName().toUpperCase();
        
        try {
            System.out.println("Recursos.adquirirRecurs(): " + name + " abans del semafor.");
            
            semafor.acquire();      //Sol·licitem el semàfor. Podrem comprobar que només poden entrar fins a 2 RecursosConsumidor.
            
            System.out.println("Recursos.adquirirRecurs(): " + name + " després del semafor.");
            
            //Thread.currentThread().sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (asignarRecurs());
    }

    //Mètode per quan algú acaba de fer servir un recurs i l'allibera.
    public synchronized void lliberarRecurs(Integer numRecurs) {
        utilitzats[numRecurs] = false;
        
        semafor.release();
    }

}
