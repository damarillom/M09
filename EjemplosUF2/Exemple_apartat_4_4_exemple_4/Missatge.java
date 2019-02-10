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
// Tot l'objecte msg és el monitor. 
// wait(): li indica al fil en curs que abandoni el monitor (que és l'objecte on es troba el wait()) i es 
// vagi a dormir fins que un altre fil entre en el mateix monitor (objecte) i truqui a notify().
// Hi haurà un objecte de la classe Missatge que serà compartit pels fils de Waiter i Notifier.
//
// Independentment des de quina funció del monitor es cridi al notify(), el notify() afectarà al primer fil
// que va llançar un wait() sense importar en quina funció es trobava el wait(). Els wait()’s i notify()’s
// funcionen a nivell d’objecte (classe) i no pas de funció.
//
// Segons els objectes Waiter vagin fent wait(), es va creant una cua d'espera en Missatge de tipus FIFO 
// de manera que cada vegada que Notifier faci un notify(), el 1r de la cua podrà continuar. Exemple: 
//      waiter_1 fa un wait() (cua en Missatge = waiter_1)
//      waiter_2 fa un wait() (cua en Missatge = waiter_2, waiter_1)
//      notifier fa un notify() (cua en Missatge = waiter_2)
//      waiter_1 fa un wait() (cua en Missatge = waiter_1, waiter_2)
//      notifier fa un notify() (cua en Missatge = waiter_1)

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
}
