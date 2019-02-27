package exercisi1;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Encoder;

public class Encriptacio {

	public static final byte[] IV_PARAM = {0x00,
			0x04,
			0x08,
			0x0C,
			0x01,
			0x05,
			0x09,
			0x0D,
			0x02,
			0x06,
			0x0A,
			0x0E,
			0x03,
			0x07,
			0x0B,
			0x0F};
	
	public static SecretKey generadorDeClausSimetriques(int i) {
		SecretKey sKey = null;
		if ((i == 128)||(i == 192)||(i == 256)) {
			try {
				KeyGenerator kgen = KeyGenerator.getInstance("AES");
				kgen.init(i);
				sKey = kgen.generateKey();
			} catch (NoSuchAlgorithmException ex) {
				System.err.println("Generador no disponible.");
			}
		}
		return sKey;
	}

	public static byte[] menu2(SecretKey clauSecretaSimetrica) {
		// TODO Auto-generated method stub
		byte[] encryptedData = null;
		String str = "hola me llamo Daniel";
		byte[] data = str.getBytes();
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica);
			encryptedData = cipher.doFinal(data);
		} catch (Exception ex) {
			System.err.println("Error xifrant les dades: " + ex);
		}
		return encryptedData;
	}

	public static void menu3(SecretKey clauSecretaSimetrica, byte[] dadesEncriptadesEnByte) {
		byte[] encryptedData = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, clauSecretaSimetrica);
			encryptedData = cipher.doFinal(dadesEncriptadesEnByte);
		} catch (Exception ex) {
			System.err.println("Error xifrant les dades: " + ex);
		}
		//System.out.println(encryptedData);
		//return encryptedData;
		String str = new String(encryptedData);
		System.out.println(str);
		
	}

	public KeyPair generadorDeClausAsimetriques(int i) {
		// TODO Auto-generated method stub
		KeyPair keys = null;
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(i);
			keys = keyGen.genKeyPair();
		} catch (Exception ex) {
			System.err.println("Generador no disponible.");
		}
		return keys;
	}

	public byte[][] menu12(SecretKey clauSecretaSimetrica, KeyPair clauPublicaIPrivada) {
		/**String str = "hola me llamo Daniel";
		byte[] data = str.getBytes();
		
		byte[][] encWrappedData = new byte[2][];
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128);
			SecretKey sKey = kgen.generateKey();
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, sKey);
			byte[] encMsg = cipher.doFinal(data);
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.WRAP_MODE, pub);
			byte[] encKey = cipher.wrap(sKey);
			encWrappedData[0] = encMsg;
			encWrappedData[1] = encKey;
		} catch (Exception ex) {
			System.err.println("Ha succeït un error xifrant: " + ex);
		}
		return encWrappedData;*/
		
		String dadesAEncriptarEnString = new String();
        byte[] dadesAEncriptarEnByte = null;
        byte[] dadesEncriptadesEnByte = null; 
        String dadesEncriptadesEnString = new String();
        
        PublicKey clauPublica = null;
        byte[] clauAESEncriptadaEnByte = null;
        byte[][] dadesIClauEncriptadesEnByte = new byte[2][];
        String clauEncriptadaEnString;
        
        dadesAEncriptarEnString = "El RT-2UTTH Tópol-M (en ruso Тополь-М) es un misil balístico intercontinental de fabricación rusa.\n" + 
"Según expertos es capaz de evadir el Sistema Antimisiles de EE. UU. debido a su fase de impulsión ultrarrápida, la pronta liberación de\n" +
"sus cabezas nucleares, la capacidad de sus ojivas para maniobrar en la fase terminal y otras técnicas especiales.\n" +
"El Bulava (SS-NX-30) es la versión del Topol-M para submarinos estratégicos, puede ser lanzado desde los nuevos submarinos de Rusia.\n" +
"El misil se halla montado sobre un vehículo que se construye en la fábrica de tractores de Minsk de Ucrania y Rusia. El peso del\n" + 
"vehículo de transporte con el misil supera las 90 toneladas. El vehículo es de ocho puentes de tracción con un motor diésel eléctrico\n" + 
"de 800 CV. La velocidad máxima del vehículo es 75 km/h y la autonomía es de 500 kilómetros.\n" +
"Según datos correspondientes a enero de 2015, estaban en servicio 78 misiles «Topol-M» emplazados en silos subterráneos, hangares con\n" + 
"el techo removible y camiones de transporte, en diferentes lugares de Rusia. A partir de 2010, se pasó a su sucesor el RS-24 Yars.";
        System.out.println("dadesAEncriptarEnString: \n" + dadesAEncriptarEnString);
        System.out.println();
        
        dadesAEncriptarEnByte = dadesAEncriptarEnString.getBytes();
        
        try { 
            //Encriptem les dades amb AES en mode CBC.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM); 
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv); 
            dadesEncriptadesEnByte = cipher.doFinal(dadesAEncriptarEnByte); 
            
            dadesEncriptadesEnString = new BASE64Encoder().encode(dadesEncriptadesEnByte);
            System.out.println("dadesEncriptadesEnString: \n" + dadesEncriptadesEnString);
            System.out.println();
            
            //Encriptem la clau d'encriptació que s'ha fet servir amb RSA + la clau pública.
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 
            clauPublica = clauPublicaIPrivada.getPublic();
            cipher.init(Cipher.WRAP_MODE, clauPublica); 
            clauAESEncriptadaEnByte = cipher.wrap(clauSecretaSimetrica); 
            dadesIClauEncriptadesEnByte[0] = dadesEncriptadesEnByte; 
            dadesIClauEncriptadesEnByte[1] = clauAESEncriptadaEnByte;
            
            clauEncriptadaEnString = new BASE64Encoder().encode(clauAESEncriptadaEnByte);
            System.out.println("clauEncriptadaEnString = " + clauEncriptadaEnString);

        } catch (Exception ex) { 
            System.err.println("ERROR: R.E.menu12() " + ex); 
        } finally {
            System.out.println("menu R.E.12(): FINAL");
        }
        
        return dadesIClauEncriptadesEnByte;
	}

	public void menu13(KeyPair clauPublicaIPrivada, byte[][] dadesIClauEncriptadesEnByte) {
		// TODO Auto-generated method stub
		PrivateKey clauPrivada = null;
        Key clauAESDesencriptada = null;
        byte[] dadesDesencriptadesEnByte = null; 
        String dadesDesencriptadesEnString = new String();

        
        try { 
            //Desencriptem la clau d'encriptació que s'ha fet servir amb RSA + la clau privada.
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); 
            clauPrivada = clauPublicaIPrivada.getPrivate();
            cipher.init(Cipher.UNWRAP_MODE, clauPrivada); 
            clauAESDesencriptada = cipher.unwrap(dadesIClauEncriptadesEnByte[1], "AES", Cipher.SECRET_KEY ); 
            //Una clau simètrica és una "SECRET_KEY".
            
            //Desencriptem les dades amb AES en mode CBC.
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM); 
            cipher.init(Cipher.DECRYPT_MODE, clauAESDesencriptada, iv); 
            dadesDesencriptadesEnByte = cipher.doFinal(dadesIClauEncriptadesEnByte[0]); 
            
            dadesDesencriptadesEnString = new String(dadesDesencriptadesEnByte);
            System.out.println("dadesDesencriptadesEnString: \n" + dadesDesencriptadesEnString);
            
        } catch (Exception ex) { 
            System.err.println("ERROR: R.E.menu13() " + ex); 
        } finally {
            System.out.println("menu R.E.13(): FINAL");
        }
	}

	public void menu21(SecretKey clauSecretaSimetrica) {
		// TODO Auto-generated method stub
		
		String dades;
        byte[] dadesAEncriptarEnByte;

        dades = extreureTripulantsDB();
        //System.out.println("dadesAEncriptarEnString: \n" + dades);
        //System.out.println();
        dadesAEncriptarEnByte = dades.getBytes();
        
        comprobarExistenciaDirectori(DIRECTORI_FITXERS);
        
        try {
            // Preparem l'encriptació.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);
            // Encriptem les dades amb AES en mode CBC directament a un fitxer.
            CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(FITXER_DADES_TRIPULANTS_XIFRADES_AES128), cipher);
            cos.write(dadesAEncriptarEnByte);
            cos.close();
        } catch (Exception ex) {
            System.err.println("ERROR: menu21() " + ex);
        } finally {
            System.out.println("menu 21(): FINAL");
        }
        
        /*
        FileOutputStream fos = null;
        CipherOutputStream cos = null;
        FileInputStream fis = null;
        try {
            // Preparem l'encriptació.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);
            
            fis = new FileInputStream(inputFile);   //SERA dadesAEncriptarEnByte.
            fos = new FileOutputStream(FITXER_DADES_TRIPULANTS_XIFRADES_AES128);
            cos = new CipherOutputStream(fos, cipher);
            byte[] data = new byte[1024];
            int read = fis.read(data);
            while (read != -1) {
                cos.write(data, 0, read);
                read = fis.read(data);
                System.out.println(new String(data, "UTF-8").trim());
            }
            cos.flush();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encriptacio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Encriptacio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Encriptacio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Encriptacio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Encriptacio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
                cos.close();
                fis.close();
            }
             catch (IOException ex) {
                Logger.getLogger(Encriptacio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
		
	}

	public void menu22(SecretKey clauSecretaSimetrica, KeyPair clauPublicaIPrivada) {
		// TODO Auto-generated method stub
		
	}

	public void menu31(SecretKey clauSecretaSimetrica) {
		// TODO Auto-generated method stub
		
	}

	public void menu32(KeyPair clauPublicaIPrivada) {
		// TODO Auto-generated method stub
		
	}

	public void menu41(SecretKey clauSecretaSimetrica) {
		// TODO Auto-generated method stub
		
	}

	public void menu42() {
		// TODO Auto-generated method stub
		
	}



}
