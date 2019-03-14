/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author gmartinez
 */
public class GeneradorDeClaus {
// Variable global que és el "vector d'inicialització (IV)" que es farà servir en el xifrat/desxifrat AES en mode CBC.
    public static final byte[] IV_PARAM = {0x00, 0x01, 0x02, 0x03, 
                                       0x04, 0x05, 0x06, 0x07, 
                                       0x08, 0x09, 0x0A, 0x0B, 
                                       0x0C, 0x0D, 0x0E, 0x0F};     
    
    
    
    //Genera una clau secreta a partir de una contrasenya. És la que es farà servir per a 
    //encriptar/desencriptar el fitxer de dades.
    public static SecretKey generadorDeClauApartirDeContrasenya (String contrasenyaUsuari, int clauTamany) {
        SecretKey clauSecretaSimetricaApartirDeContrasenya = null; 

        if ((clauTamany == 128)||(clauTamany == 192)||(clauTamany == 256)) { 
            try {
                byte[] data = contrasenyaUsuari.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.reset();
                md.update(data);
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, clauTamany/8); 
                clauSecretaSimetricaApartirDeContrasenya = new SecretKeySpec(key, "AES");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(GeneradorDeClaus.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(GeneradorDeClaus.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } 

        return clauSecretaSimetricaApartirDeContrasenya;         
    }    
    
    
    // Genera una clau secreta simètrica AES que és la que es farà servir per a Encriptar/desencriptar textos.
    public static SecretKey generadorDeClausSimetriques (int clauTamany) {
        SecretKey clauSecretaSimetrica = null; 

        if ((clauTamany == 128)||(clauTamany == 192)||(clauTamany == 256)) { 
            try { 
                KeyGenerator generadorDeClaus = KeyGenerator.getInstance("AES"); 
                generadorDeClaus.init(clauTamany); 
                clauSecretaSimetrica = generadorDeClaus.generateKey(); 
            } catch (NoSuchAlgorithmException ex) { 
                System.err.println("ERROR: generadorDeClausSimetriques() - Generador de clau simètrica no disponible."); 
            } 
        } 

        return clauSecretaSimetrica;         
    }
    
    
    // Genera clau asimètrica RSA (la pública i la privada). Amb la pública encriptarem la clau AES i amb la privada
    // associada amb la pública, la desencriptarem.
    public static KeyPair generadorDeClausAsimetriques(int len) { 
        KeyPair clauPublicaIPrivada = null; 

        try { 
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
            keyGen.initialize(len); 
            clauPublicaIPrivada = keyGen.genKeyPair(); 
        } catch (Exception ex) { 
            System.err.println("ERROR: generadorDeClausAsimetriques() - Generador de claus asimètriques (pública i privada) no disponible."); 
        } 

        return clauPublicaIPrivada; 
    }  

    
}
