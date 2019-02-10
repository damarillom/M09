/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_4_exemple_4;

/**
 *
 * @author gmartinez
 */

// En el main és crearà un objecte de tipus Missatge (msg) que es compartirà entre tots els fils.
// Dins de Waiter.java i Notifier.java hi ha "synchronized (missatge)" de manera que estan fent que
// l'objecte "missatge" (que és el mateix pels 3 fils) sigui el Monitor.
public class Waiter implements Runnable{
    
    private Missatge missatge;
    
    public Waiter(Missatge m){
        this.missatge = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName().toUpperCase();
        
        System.out.println(name + ": inicialització.");
        
        synchronized (missatge) {
            try{
                System.out.println(name + ": abans del 1r wait(), hora = " + System.currentTimeMillis());
                
                missatge.wait();
                
                System.out.println(name + ": després del 1r wait(). Hem rebut un notify(). Hora = " + System.currentTimeMillis());
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        
        synchronized (missatge) {
            try{
                System.out.println(name + ": abans del 2n wait(), hora = " + System.currentTimeMillis());
                
                missatge.wait();
                
                System.out.println(name + ": després del 2n wait(). Hem rebut un notify(). Hora = " + System.currentTimeMillis());
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }        
        
        System.out.println(name + " Missatge.getMsg() = " + missatge.getMsg());
    }
    
}
