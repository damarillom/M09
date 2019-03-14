/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;

/**
 *
 * @author gmartinez
 */
public class Encriptar {
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
    
    private static final String pass = "Larga vida al imperio Klingon";
    
    private static SecretKey passKey;
    private static SecretKey clauSecretaSimetrica;
	private static KeyPair clauPublicaIPrivada;

    public static void encriptarFitxerAmbClauApartirDeContrasenya() throws IOException {
    	System.out.println("encriptarFitxerAmbClauApartirDeContrasenya(): INICI	");
    	passKey = GeneradorDeClaus.generadorDeClauApartirDeContrasenya(pass, 128);
    	
    	String dades;
        byte[] dadesAEncriptarEnByte;

        System.out.println("	Contingut del 'fitxerDeDadesOriginal.txt':");
        dades = extreureTripulantsDB(DIRECTORI_FITXERS + fitxerDeDadesOriginal);
        //System.out.println("dadesAEncriptarEnString: \n" + dades);
        //System.out.println();
        dadesAEncriptarEnByte = dades.getBytes();
        
        //comprobarExistenciaDirectori(DIRECTORI_FITXERS + fitxerDeDadesOriginal);
        
        try {
            // Preparem l'encriptació.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, passKey, iv);
            // Encriptem les dades amb AES en mode CBC directament a un fitxer.
            CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(DIRECTORI_FITXERS + fitxerDeDadesEncriptatAmbClauApartirDeContrasenya), cipher);
            //String str = new String(dadesAEncriptarEnByte);
            cos.write(dadesAEncriptarEnByte);
            cos.close();
        } catch (Exception ex) {
            System.err.println("ERROR: encriptarFitxerAmbClauApartirDeContrasenya() " + ex);
        } finally {
            System.out.println("encriptarFitxerAmbClauApartirDeContrasenya(): FI	");
        }
    }
    
    
    
    public static KeyPair encriptarFitxerAmbClauRSA() throws IOException {
    	
    	System.out.println("encriptarFitxerAmbClauRSA(): INICI");
    	
    	clauSecretaSimetrica = GeneradorDeClaus.generadorDeClausSimetriques(128);
    	clauPublicaIPrivada = GeneradorDeClaus.generadorDeClausAsimetriques(1024);
    	
    	
    	String dades;
        byte[] dadesAEncriptarEnByte;
        byte[] clauAESEncriptadaEnByte;
        String clauAESEncriptadaEnString;
        PublicKey clauPublica;
        
        System.out.println("	(String) dadesAEncriptarEnByte = ");
        dades = extreureTripulantsDB(DIRECTORI_FITXERS + fitxerDeDadesEncriptatAmbClauApartirDeContrasenya);
        dadesAEncriptarEnByte = dades.getBytes();
        
        //comprobarExistenciaDirectori(DIRECTORI_FITXERS + fitxerDeDadesEncriptatAmbClauApartirDeContrasenya);

        try {
            // Preparem l'encriptaci� de les dades. Encriptarem amb la AES (clau sim�trica).
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);

            // Encriptem les dades amb AES en mode CBC directament a un fitxer.
            CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(DIRECTORI_FITXERS + fitxerDeDadesEncriptatAmbAES), cipher);
            cos.write(dadesAEncriptarEnByte);
            cos.close();

            // Encriptem la clau d'encriptaci� AES que s'ha fet servir amb RSA.
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            clauPublica = clauPublicaIPrivada.getPublic();
            cipher.init(Cipher.WRAP_MODE, clauPublica);
            clauAESEncriptadaEnByte = cipher.wrap(clauSecretaSimetrica);

            clauAESEncriptadaEnString = new BASE64Encoder().encode(clauAESEncriptadaEnByte);
            System.out.println("clauEncriptadaEnString = " + clauAESEncriptadaEnString);
            System.out.println();

            // Guardem la clau xifrada a un fitxer diferent a les dades.
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(DIRECTORI_FITXERS + fitxerAmbClauAESEncriptadaAmbClauRSA));
            bos.write(clauAESEncriptadaEnByte);
            bos.close();

        } catch (Exception ex) {
            System.err.println("ERROR: encriptarFitxerAmbClauRSA() " + ex);
        } finally {
            System.out.println("encriptarFitxerAmbClauRSA(): FI");
        }
    	return clauPublicaIPrivada;
    }

    
    
    private static void comprobarExistenciaDirectori(String directoriFitxers) {
		File af = new File(directoriFitxers);
		if (af.exists()) { 
			System.out.println("El fichero existe");
		} else {
			System.out.println("El fichero no existe");
		}
	}

	private static String extreureTripulantsDB(String fitxer) throws IOException {
		String texto = "";
		String cadena;
		File af = new File(fitxer);
        FileReader f = new FileReader(af);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            texto = texto + cadena + "\n";
        }
        b.close();
        System.out.println(texto);
		return texto;
	}
    
}
