/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_4_exemple_1;


/**
 *
 * @author gmartinez
 */

// En el main és crearà un objecte de tipus Missatge (msg) que es compartirà entre tots els fils i que
// tindrà dins el monitor (té al menys 1 "synchronized"). Tot l'objecte msg és el monitor. Si volem que
// hi hagi un monitor diferent en diverses funcions dins de Missatge, s'han de fer servir semàfors.
// Hi haurà un objecte de la classe Missatge que serà compartit pels fils de Waiter i Notifier.
public class exempleNotifyWait {
    public static void main(String[] args) {
        System.out.println("MAIN: inici");
        
        Missatge msg = new Missatge("patata");
        
        Waiter waiter_1 = new Waiter(msg);
        new Thread(waiter_1, "waiter_1").start();

        Waiter waiter_2 = new Waiter(msg);
        new Thread(waiter_2, "waiter_2").start();

        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        
        System.out.println("MAIN: s'han llançat els 3 fils (2 waiters + 1 notifier)");
    }
}
    

