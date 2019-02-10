/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_5_2;

import static java.lang.Thread.yield;

/**
 *
 * @author gmartinez
 */
class CrearDimoni extends Thread {
    public CrearDimoni(int i, String nomDimoni) {
        // Si un fil no ens interessa que sigui dimoni, ho hem d’indicar aquí amb setDaemon(false)
        // abans del mètode start(), en cas contrari, hereta la qualitat de dimoni del seu pare.

        setName(nomDimoni);
        System.out.println("Dimoni creat: " + getName());
        start();
    }


    public void run() {
        while (true) yield();
    }
}