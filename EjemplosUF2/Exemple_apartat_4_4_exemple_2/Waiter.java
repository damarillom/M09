/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_4_exemple_2;

/**
 *
 * @author gmartinez
 */


public class Waiter implements Runnable{
    
    private Missatge missatge;
    
    public Waiter(Missatge m){
        this.missatge = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName().toUpperCase();
        
        System.out.println(name + ": inicialització.");
        
        missatge.primeraAturdada(name);
        
        System.out.println(name + " Missatge.imprimirMissatge() = " + missatge.imprimirMissatge(name));
    }
    
}
