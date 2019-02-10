/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_5_2;

import java.io.IOException;
import static java.lang.Thread.yield;

/**
 *
 * @author gines
 */
public class MulfitilsAmbFilsDimonis implements Runnable {
    private static final int MIDA = 10;
    private Thread[] fils = new Thread[MIDA];

    
    private MulfitilsAmbFilsDimonis() {
    }
    
    
    public void run() {
        String nomDimoniFill = "Dimoni fill nº ";
        
        
        for(int i = 0; i < MIDA; i++) {
            fils[i] = new CrearDimoni(i, nomDimoniFill + i);
        }
        
        for(int i = 0; i < MIDA; i++){
            System.out.println("fils[" + i + "].isDaemon()? = " + fils[i].isDaemon());
        }
        
        // El mètode yield() fa que un fil que s’està executant passi de nou a l'estat preparat 
        // i a la cua de preparats.
        while(true) yield();
    }
    
    
    public static void main(String[] args) throws IOException {
        MulfitilsAmbFilsDimonis filDimoniPrincipal = new MulfitilsAmbFilsDimonis();
        
        
        //Creem el fil
        Thread filDimoniPare = new Thread(filDimoniPrincipal);
        filDimoniPare.setName("filDimoniPare");
        
        // Per poder−los executar s’ha de cridar al mètode start() que cridarà
        // automàticament al mètode run() que és qui té el codi d'execució del fil.
        filDimoniPare.setDaemon(true);
        filDimoniPare.start();

        System.out.println(filDimoniPare.getName() + " isDaemon()? = " + filDimoniPare.isDaemon());
        System.out.println("Pica una tecla per finalitzar i presiona ENTER");
        System.in.read();
    }
    
}
