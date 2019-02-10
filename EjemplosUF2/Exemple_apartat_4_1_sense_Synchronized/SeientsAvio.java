/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_1_sense_Synchronized;

/**
 *
 * @author gines
 */
public class SeientsAvio {
    //comencem amb 5 seients lliures a l'avió
    private int seientsLliures = 5;
    
    public int getSeientsLliures(){
        return seientsLliures;
    }

    public boolean getSeientsLliures(int numPlaces){
        if (numPlaces <= seientsLliures)
            return true;
        else
            return false;
    }


    public void reservaSeients(int numSeientsReserva){
        seientsLliures = seientsLliures - numSeientsReserva;
    }

}
