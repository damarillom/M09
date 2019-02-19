/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019_part_II;

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
            menu.append("2. Activar sistema IRST");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("50. Tornar al menú pare (PNS-24 Puma)");
            menu.append(System.getProperty("line.separator"));
            
            System.out.print(MenuConstructorPantalla.constructorPantalla(menu));
            
            opcio = sc.next();
            
            switch (opcio) {
                case "1":
                    break;
                case "2":
                    activarSistemaIRST();
                    break;
                case "50":
                    break; 
                default:
                    System.out.println("COMANDA NO RECONEGUDA");
            }   
        } while (!opcio.equals("50"));
    }

	private static void activarSistemaIRST() throws InterruptedException {
		Radar_Orion_A radar = new Radar_Orion_A();
		
		Sistema_de_guia_IRST sis1 = new Sistema_de_guia_IRST(1, radar);
		Sistema_de_guia_IRST sis2 = new Sistema_de_guia_IRST(2, radar);
		Sistema_de_guia_IRST sis3 = new Sistema_de_guia_IRST(3, radar);
		Sistema_de_guia_IRST sis4 = new Sistema_de_guia_IRST(4, radar);
		
		sis1.setName("IRST-1");
		sis2.setName("IRST-2");
		sis3.setName("IRST-3");
		sis4.setName("IRST-4");
		
		sis1.start();
		sis2.start();
		sis3.start();
		sis4.start();
		
		sis1.join();
		sis2.join();
		sis3.join();
		sis4.join();
	}
    
    
  
}
