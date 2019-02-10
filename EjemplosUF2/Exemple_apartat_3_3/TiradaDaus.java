/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_3_3;

/**
 *
 * @author gines
 */
public class TiradaDaus {
    private int tiradaDau;


    public TiradaDaus (int e) {
        tiradaDau = e;
    }

    public synchronized int getSumaTirada() {
        return tiradaDau;
    }

    public synchronized void setSumaTirada(int e) {
        tiradaDau = tiradaDau + e;
    }
    
}
