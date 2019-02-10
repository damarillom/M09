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


public class Notifier implements Runnable {

    private Missatge missatge;
    
    public Notifier(Missatge msg) {
        this.missatge = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName().toUpperCase();
        
        System.out.println(name + ": inicialització + sleep(5000).");
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        missatge.primeraAturdada(name);
        
        // Si treiem el següent sleep(), el notifier s'executa abans que els waiters i per tant executa el notify()
        // que hi ha dins de Missatge.imprimirMissatge() abans que els waiters executin el wait() de manera que quan
        // els 2 waiters executan el wait(), es quedan pillats.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(name + " Missatge.imprimirMissatge() = " + missatge.imprimirMissatge(name));
        
    }
    
}
