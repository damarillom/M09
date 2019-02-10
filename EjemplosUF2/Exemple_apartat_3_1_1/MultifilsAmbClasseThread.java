package Exemple_apartat_3_1_1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gines
 */
public class MultifilsAmbClasseThread extends Thread {
     String strImprimir;

    public MultifilsAmbClasseThread(String strP) {
        strImprimir = strP;
    }

    public void run(){
        for(int x = 0; x < 5; x++){
            System.out.println(strImprimir + " " + x);
        }
    }

    public static void main(String[] args) {
        // Creem dos fils primer i segon, però encara no s’han executat.
        Thread primer = new MultifilsAmbClasseThread("Fil 1");
        Thread segon = new MultifilsAmbClasseThread("Fil 2");

        // Per poder−los executar s’ha de cridar al mètode start() que cridarà
        // automàticament al mètode run() que és qui té el codi d'execució del fil.
        primer.start();
        segon.start();

        System.out.println("Final Fil Principal");
    }  
}
