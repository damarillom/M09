/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import sun.misc.BASE64Encoder;

/**
 *
 * @author gmartinez
 */
public class Desencriptar {
    // Variable global que és el "vector d'inicialització (IV)" que es farà servir en el xifrat/desxifrat AES en mode CBC.
    public static final byte[] IV_PARAM = {0x00, 0x01, 0x02, 0x03, 
                                       0x04, 0x05, 0x06, 0x07, 
                                       0x08, 0x09, 0x0A, 0x0B, 
                                       0x0C, 0x0D, 0x0E, 0x0F}; 
    private static final String DIRECTORI_FITXERS = "fitxers/";
    private static final String fitxerDeDadesOriginal = "00_fitxerDeDadesOriginal.txt";
    private static final String fitxerDeDadesEncriptatAmbClauApartirDeContrasenya = "01_fitxerDeDadesEncriptatAmbClauApartirDeContrasenya.txt";
    private static final String fitxerDeDadesEncriptatAmbAES = "02_fitxerDeDadesEncriptatAmbAES.txt";
    private static final String fitxerAmbClauAESEncriptadaAmbClauRSA = "03_fitxerAmbClauAESEncriptadaAmbClauRSA.txt";
    private static final String fitxerDeDadesDesencriptatAmbAES = "04_fitxerDeDadesDesencriptatAmbAES.txt";
    private static final String SALT_LINIA = System.getProperty("line.separator");
    private static final String fitxerDeDadesDesencriptatAmbClauApartirDeContrasenya = "05_fitxerDeDadesDesencriptatAmbClauApartirDeContrasenya.txt";
    
    private static final String pass = "Larga vida al imperio Klingon";
    
    private static SecretKey passKey;

    public static void desencriptarFitxerAmbClauRSA(KeyPair clauPublicaIPrivada) {
    	System.out.println("desencriptarFitxerAmbClauRSA() : INICI");
    	
    	
    	PrivateKey clauPrivada;
        byte[] clauAESEncriptadaEnByte;
        String clauAESEncriptadaEnString;
        Key clauAESDesencriptada;

        try {
			// LLegim la clau AES del fitxer.
            File file = new File(DIRECTORI_FITXERS + fitxerAmbClauAESEncriptadaAmbClauRSA);
            FileInputStream fis = new FileInputStream(file);
            clauAESEncriptadaEnByte = new byte[(int) file.length()];
            fis.read(clauAESEncriptadaEnByte);
            fis.close();

            clauAESEncriptadaEnString = new BASE64Encoder().encode(clauAESEncriptadaEnByte);
            System.out.println("clauEncriptadaEnString = " + clauAESEncriptadaEnString);
            System.out.println();

            // Desencriptem la clau d'encriptaci� AES que es va fer servir per encriptar les dades amb RSA + la clau privada.
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            clauPrivada = clauPublicaIPrivada.getPrivate();
            cipher.init(Cipher.UNWRAP_MODE, clauPrivada);
            clauAESDesencriptada = cipher.unwrap(clauAESEncriptadaEnByte, "AES", Cipher.SECRET_KEY);

            // Desencriptem les dades amb AES en mode CBC.
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.DECRYPT_MODE, clauAESDesencriptada, iv);

            CipherInputStream cis = new CipherInputStream(new FileInputStream(DIRECTORI_FITXERS + fitxerDeDadesEncriptatAmbAES), cipher);
            //BufferedReader br = new BufferedReader(new InputStreamReader(cis));

            // Preparem el fitxer per guardar les dades desxifrades.
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DIRECTORI_FITXERS + fitxerDeDadesDesencriptatAmbAES)));

            System.out.println("dadesDesencriptadesEnString:");
            FileOutputStream fos = new FileOutputStream(new File(DIRECTORI_FITXERS + fitxerDeDadesDesencriptatAmbAES));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = cis.read(buffer, 0, buffer.length)) != -1) {
                fos.write(buffer, 0, len);
                System.out.println();
            }
            fos.close();
            /**System.out.println("****");
            System.out.println(cis.read());
            System.out.println("****");*/
            /**String temp;
            while ((temp = br.readLine()) != null) {
                bw.write(temp + SALT_LINIA);
                System.out.println(temp);
            }
            System.out.println();
            br.close();
            bw.close();*/

        } catch (Exception ex) {
            System.err.println("ERROR: desencriptarFitxerAmbClauRSA() " + ex);
        } finally {
        	System.out.println("desencriptarFitxerAmbClauRSA() : FI");
        }
    }
    
    
    
    public static void desencriptarFitxerAmbClauApartirDeContrasenya() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
    	//No he conseguido que esta función funcione correctamente
    	passKey = GeneradorDeClaus.generadorDeClauApartirDeContrasenya(pass, 128);
    	
    	try {
    		System.out.println("desencriptarFitxerAmbClauApartirDeContrasenya(): INICI");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
	        cipher.init(Cipher.DECRYPT_MODE, passKey, iv);
	
	        CipherInputStream cis = new CipherInputStream(new FileInputStream(DIRECTORI_FITXERS + fitxerDeDadesDesencriptatAmbAES), cipher);
	        BufferedReader br = new BufferedReader(new InputStreamReader(cis));
	
	        // Preparem el fitxer per guardar les dades desxifrades.
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DIRECTORI_FITXERS + fitxerDeDadesDesencriptatAmbClauApartirDeContrasenya)));
	
	        System.out.println("dadesDesencriptadesEnString:");
	        /**FileOutputStream fos = new FileOutputStream(new File(DIRECTORI_FITXERS + fitxerDeDadesDesencriptatAmbClauApartirDeContrasenya));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = cis.read(buffer, 0, buffer.length)) != -1) {
                fos.write(buffer, 0, len);
                System.out.println();
            }
            fos.close();*/
	        String temp;
	        while ((temp = br.readLine()) != null) {
	            bw.write(temp + SALT_LINIA);
	            System.out.println(temp);
	        }
	        System.out.println();
	        br.close();
	        bw.close();
		} catch (Exception e) {
			 System.err.println("ERROR: desencriptarFitxerAmbClauApartirDeContrasenya() " + e); 
        } finally {
            System.out.println("desencriptarFitxerAmbClauApartirDeContrasenya(): FI");
		}
    }
    
}
