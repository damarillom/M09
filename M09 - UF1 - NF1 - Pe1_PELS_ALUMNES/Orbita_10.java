/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author gines
 */
public class Orbita_10 {
    public static void main(String[] args) throws IOException  {
        PNS24_Puma objPNS24_Puma = new PNS24_Puma();
        
        //PNS24_Puma.menuPNS24Puma();
        objPNS24_Puma.menuPNS24Puma();
    }
}
