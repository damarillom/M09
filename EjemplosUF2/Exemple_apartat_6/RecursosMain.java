/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_6;

/**
 *
 * @author gmartinez
 */

// Hi ha 3 consumidors però només 2 recursos. Per això el semàfor és de tamany 2. Es llançan els 3 consumidors
// a l'hora però només 2 podran accedir als recursos, el tercer s'haurà d'esperar a que una dels altres 2 alliberi
// el recurs que hagi agafat.

public class RecursosMain {
    public static void main(String[] args) {
        Recursos recursos = new Recursos(2);
        
        Consumidor consumidor1 = new Consumidor(1, recursos);
        Consumidor consumidor2 = new Consumidor(2, recursos);
        Consumidor consumidor3 = new Consumidor(3, recursos);
        consumidor1.setName("consumidor1");
        consumidor2.setName("consumidor2");
        consumidor3.setName("consumidor3");
        
        consumidor1.start();
        consumidor2.start();
        consumidor3.start();
    }
    
}
