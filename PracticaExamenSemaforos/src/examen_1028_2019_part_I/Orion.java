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
    public static void menuOrion() throws IOException  {
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

	private static void activarSistemaDelta() {
		// TODO Auto-generated method stub
		
	}
    
    

}
