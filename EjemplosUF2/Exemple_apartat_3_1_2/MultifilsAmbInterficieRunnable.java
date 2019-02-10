package Exemple_apartat_3_1_2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gines
 */
public class MultifilsAmbInterficieRunnable implements Runnable{
    String strImprimir;

    public MultifilsAmbInterficieRunnable(String strP) {
        strImprimir = strP;
    }

    public void run(){
        for(int x = 0; x < 5; x++){
            System.out.println(strImprimir + " " + x);
        }
    }

    public static void main(String[] args) {
        // Creem dos objecte de la classe MultifilsAmbInterficieRunnable.
        MultifilsAmbInterficieRunnable objRunnable1 = new MultifilsAmbInterficieRunnable("Fil 1");
        MultifilsAmbInterficieRunnable objRunnable2 = new MultifilsAmbInterficieRunnable("Fil 2");

        // Creem dos fils i li passem per paràmetres els objectes (objRunnable1 i
        // objRunnable2) de la classe  MultifilsAmbInterficieRunnable.
        Thread primer = new Thread(objRunnable1);
        Thread segon = new Thread(objRunnable2);

        // Per poder−los executar s’ha de cridar al mètode start() que cridarà
        // automàticament al mètode run() que és qui té el codi d'execució del fil.
        primer.start();
        segon.start();

        System.out.println("Final Fil Principal");
    }    
}
