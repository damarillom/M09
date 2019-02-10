/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_5_1;

/**
 *
 * @author gines
 */
public class MultifilsAmbPrioritat implements Runnable {
    String strImprimir;
    
    
    
    public MultifilsAmbPrioritat (String strP) {
        strImprimir = strP;
    }


    public void run() {
        for(int i = 0; i < 6; i++) {
            System.out.println(strImprimir + ", bucle " + i + " de 6.");
        }
    }
    
    
    public static void main(String[] args) {
        MultifilsAmbPrioritat objRunnable1 = new MultifilsAmbPrioritat("Fil 1");
        MultifilsAmbPrioritat objRunnable2 = new MultifilsAmbPrioritat("Fil 2");
        
        
        //Creem els fils i els hi assignem un nom amb l’objecte runnable
        Thread primer = new Thread(objRunnable1);
        primer.setName("Fil 1");
        Thread segon = new Thread(objRunnable2);
        segon.setName("Fil 2");
        
        //Canviem la prioritat de fil primer i li posem la més baixa. 
        primer.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Prioritat del " + primer.getName()+ " = " + primer.getPriority());
        
        //Canviem la prioritat de fil segon i li posem la més alta
        segon.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Prioritat del " + segon.getName()+ " = " + segon.getPriority());

        // Per poder−los executar s’ha de cridar al mètode start() que cridarà
        // automàticament al mètode run() que és qui té el codi d'execució del fil.
        primer.start();
        segon.start();
        
        System.out.println("Final Fil Principal");
    }
}
