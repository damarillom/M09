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
    
    
    
    

    public static void encriptarFitxerAmbClauApartirDeContrasenya() {

    }
    
    
    
    public static KeyPair encriptarFitxerAmbClauRSA() {

    }

    
}
