/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1028_2019_part_I;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gmartinez
 */
public class Orion {
    public static void menuOrion() throws IOException, InterruptedException  {
        String opcio;
        Scanner sc = new Scanner(System.in);
        StringBuilder menu = new StringBuilder("");
        
        
        do {
            menu.delete(0, menu.length());
            
            menu.append(System.getProperty("line.separator"));
            menu.append("RPO Orion-A");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("1. Activar sistema Delta");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("50. Tornar al menú pare (PNS-24 Puma)");
            menu.append(System.getProperty("line.separator"));
            
            System.out.print(MenuConstructorPantalla.constructorPantalla(menu));
            
            opcio = sc.next();
            
            switch (opcio) {
                case "1":
                    activarSistemaDelta();
                    break;
                case "50":
                    break; 
                default:
                    System.out.println("COMANDA NO RECONEGUDA");
            }   
        } while (!opcio.equals("50"));
    }
    
    
    
    public static void activarSistemaDelta() throws InterruptedException {
    	Radar_Orion_A radar = new Radar_Orion_A();
    	Sistema_de_guia_Delta sis1 = new Sistema_de_guia_Delta(radar);
    	Sistema_de_guia_Delta sis2 = new Sistema_de_guia_Delta(radar);
    	Sistema_de_guia_Delta sis3 = new Sistema_de_guia_Delta(radar);
    	Sistema_de_guia_Delta sis4 = new Sistema_de_guia_Delta(radar);
    	
    	/**Sistema_de_guia_Delta sis1 = new Sistema_de_guia_Delta();
    	Sistema_de_guia_Delta sis2 = new Sistema_de_guia_Delta();
    	Sistema_de_guia_Delta sis3 = new Sistema_de_guia_Delta();
    	Sistema_de_guia_Delta sis4 = new Sistema_de_guia_Delta();*/
    	Thread filOperacio1 = new Thread(sis1);
        filOperacio1.setName("Delta-1");
        Thread filOperacio2 = new Thread(sis2);
        filOperacio2.setName("Delta-2");
        Thread filOperacio3 = new Thread(sis3);
        filOperacio3.setName("Delta-3");
        Thread filOperacio4 = new Thread(sis4);
        filOperacio4.setName("Delta-4");
        
        filOperacio1.start();
        filOperacio2.start();
        filOperacio3.start();
        filOperacio4.start();

        filOperacio1.join(); 
        filOperacio2.join(); 
        filOperacio3.join(); 
        filOperacio4.join(); 
        
        radar.desconectarDeltasDelRadar();
        System.out.println("Fin programa principal");
        
    }
    
    

}
