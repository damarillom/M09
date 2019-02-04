package Exercisi4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Caixes {
	private int quantitatDeCaixes;
    private Semaphore semafor;
    private boolean[] caixesUtilitzades;   //Indica si cadascun dels recursos s'està fent servir o no.

    

    public Caixes(int cantidad) {
        quantitatDeCaixes = cantidad;
        semafor = new Semaphore(cantidad);            //Creem un semàfor per controlar l'accés als recursos.
        caixesUtilitzades = new boolean[cantidad];    //Creem l'array indicador de l'ús dels recursos.
    }

    
    // Busquem una caixa lliure.
    // Si el mètode no fós sincronitzat, diversos consumidors (fins a 6) podrien arribar a aquest punt
    // a la vegada i sel's assignaria la mateixa caixa a totes ells.
    public synchronized int asignarCaixa() {
        int i = 0;
        while (i < quantitatDeCaixes) {
            if (caixesUtilitzades[i] == false) {
                caixesUtilitzades[i] = true;
                break;
            }
            i++;
        }

        return (i);
    }

    
    //Mètode per quan algú vol un recurs.
    //Si tots els recursos estan caixesUtilitzades, es bloqueijarà fins que algun d'ells sigui alliberat.
    public Integer adquirirCaixa() {
        String name = Thread.currentThread().getName().toUpperCase();
        
        try {
            System.out.println("Caixes.adquirirCaixa(): " + name + " abans del semafor.");
            
            semafor.acquire();      //Sol·licitem el semàfor. Podrem comprobar que només poden entrar fins a 6 consumidors.
            
            System.out.println("Caixes.adquirirCaixa(): " + name + " després del semafor.");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Caixes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (asignarCaixa());
    }

    
    //Mètode per quan algú acaba de fer servir un recurs i l'allibera.
    public synchronized void alliberarCaixa(Integer numCaixa) {
        caixesUtilitzades[numCaixa] = false;
        
        semafor.release();
    }

}
