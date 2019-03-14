/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author gmartinez
 */
public class Kristall {
    public static void bloquejarPantalla() {
        Scanner in = new Scanner(System.in);
        System.out.print("Toca 'C' per a continuar ");
        while (in.hasNext()) {
            if ("C".equalsIgnoreCase(in.next())) break;
        }
        System.out.println();
    }
    
    
    public static void menuKristall() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException  {
        KeyPair clauPublicaIPrivada = null;
        String opcio;
        StringBuilder menu = new StringBuilder("");
        
        
        Scanner sc = new Scanner(System.in);
        
        do {
            menu.delete(0, menu.length());
            
            menu.append(System.getProperty("line.separator"));
            menu.append("Kristall");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("ENCRIPTACIÓ SIMÈTRICA (AES 128 bits)");
            menu.append(System.getProperty("line.separator"));
            menu.append("1. Generar clau simètrica");
            menu.append(System.getProperty("line.separator"));
            menu.append("2. Encriptar cadena de text amb AES de 128 bits");
            menu.append(System.getProperty("line.separator"));
            menu.append("3. Desencriptar cadena de text amb AES de 128 bits");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("ENCRIPTACIÓ ASIMÈTRICA (RSA amb clau embolcallada)");
            menu.append(System.getProperty("line.separator"));
            menu.append("11. Generar clau simètrica i público-privades");
            menu.append(System.getProperty("line.separator"));
            menu.append("12. Encriptar cadena de text amb RSA amb clau embolcallada");
            menu.append(System.getProperty("line.separator"));
            menu.append("13. Desencriptar cadena de text amb RSA amb clau embolcallada");
            menu.append(System.getProperty("line.separator"));
            menu.append("14. Emmagatzemar clau asimètrica en fitxer de claus");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("ENCRIPTACIÓ DE DADES (cap a fitxer)");
            menu.append(System.getProperty("line.separator"));
            menu.append("21. Encriptar amb AES 128 bits els tripulants");
            menu.append(System.getProperty("line.separator"));
            menu.append("22. Encriptar amb RSA amb clau embolcallada els tripulants");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("DESENCRIPTACIÓ DE DADES (desde fitxer)");
            menu.append(System.getProperty("line.separator"));
            menu.append("31. Desencriptar amb AES 128 bits els tripulants");
            menu.append(System.getProperty("line.separator"));
            menu.append("32. Desencriptar amb RSA amb clau embolcallada els tripulants");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("ENCRIPTACIÓ/DESENCRIPTACIÓ DE LES DADES (a/desde fitxer)");
            menu.append(System.getProperty("line.separator"));
            menu.append("41. Emmagatzemar les dades doblement encriptades en un fitxer");
            menu.append(System.getProperty("line.separator"));
            menu.append("42. Visualitza per pantalla el contingut desencriptat del fitxer doblement encriptat");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("50. Tornar al menú pare (PNS-24 Puma)");
            menu.append(System.getProperty("line.separator"));
            
            System.out.print(MenuConstructorPantalla.constructorPantalla(menu));
            /*
            System.out.println("---------------- Kristall ----------------");
            System.out.println("   ENCRIPTACIÓ SIMÈTRICA (AES 128 bits)");
            System.out.println("1. Generar clau simètrica");
            System.out.println("2. Encriptar cadena de text amb AES de 128 bits");
            System.out.println("3. Desencriptar cadena de text amb AES de 128 bits");
            System.out.println();
            System.out.println("    ENCRIPTACIÓ ASIMÈTRICA (RSA amb clau embolcallada)");
            System.out.println("11. Generar clau simètrica i público-privades");
            System.out.println("12. Encriptar cadena de text amb RSA amb clau embolcallada");
            System.out.println("13. Desencriptar cadena de text amb RSA amb clau embolcallada");
            System.out.println("14. Emmagatzemar clau asimètrica en fitxer de claus");
            System.out.println();
            System.out.println("    ENCRIPTACIÓ DE DADES (cap a fitxer)");
            System.out.println("21. Encriptar amb AES 128 bits els tripulants");
            System.out.println("22. Encriptar amb RSA amb clau embolcallada els tripulants");
            System.out.println();
            System.out.println("    DESENCRIPTACIÓ DE DADES (desde fitxer)");
            System.out.println("31. Desencriptar amb AES 128 bits els tripulants");
            System.out.println("32. Desencriptar amb RSA amb clau embolcallada els tripulants");
            System.out.println();
            System.out.println("    ENCRIPTACIÓ/DESENCRIPTACIÓ DE LES DADES (a/desde fitxer)");
            System.out.println("41. Emmagatzemar les dades doblement encriptades en un fitxer");
            System.out.println("42. Visualitza per pantalla el contingut desencriptat del fitxer doblement encriptat");
            System.out.println();            
            System.out.println("50. Tornar al menú pare (PNS-24 Puma)");
            System.out.println();
            System.out.print("opció?: ");*/
            
            opcio = sc.next();
            
            switch (opcio) {
                case "1":
                    break;
                case "32":
                    break;                    
                case "41":
                    Encriptar.encriptarFitxerAmbClauApartirDeContrasenya();
                    clauPublicaIPrivada = Encriptar.encriptarFitxerAmbClauRSA();
                    bloquejarPantalla();
                    break;                    
                case "42":
                    Desencriptar.desencriptarFitxerAmbClauRSA(clauPublicaIPrivada);
                    Desencriptar.desencriptarFitxerAmbClauApartirDeContrasenya();
                    bloquejarPantalla();
                    break;                                     
                case "50":
                    break; 
                default:
                    System.out.println("COMANDA NO RECONEGUDA");
            }   
        } while (!opcio.equals("50"));
    }    
}
