/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_4_exemple_3;

/**
 *
 * @author gmartinez
 */

// En el main és crearà un objecte de tipus Missatge (msg) que es compartirà entre tots els fils.
// Dins de Waiter.java i Notifier.java hi ha "synchronized (missatge)" de manera que estan fent que
// l'objecte "missatge" (que és el mateix pels 3 fils) sigui el Monitor.
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
            
            synchronized (missatge) {
                System.out.println(name + " llança un notify().");
                
                missatge.notify();          //Amb això li fem el notify() a 1 dels 2 objectes Waiter.
                // missatge.notifyAll();    //Amb això li fariem en notify() als 2 objectes Waiter.
                
                missatge.setMsg("FI");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println(name + " Missatge.getMsg() = " + missatge.getMsg());
    }
    
}
