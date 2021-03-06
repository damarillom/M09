package Exercisi1;

//import static Leonov.Kristall.bloquejarPantalla;
import com.sun.xml.internal.messaging.saaj.util.Base64;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gmartinez
 */
public class SPU_9_server {
    static final int PORT = 9090;
    static final String separador = "$##$";
    private static boolean fiComunicacio = false;
    
    static SecretKey clauSecretaSimetrica = null;
    static byte[] dadesEncriptadesEnByte = null;
    static KeyPair clauPublicaIPrivada = null;
    static byte[][] dadesIClauEncriptadesEnByte = new byte[2][];

    static String ruta = "";

    
    
    private static String[] procesarMissatgeDelClient(String missatgeDelClient, BufferedReader entradaPelClientSocket, Socket clientSocket) throws IOException {
        String dadesAEnviarAlClient[] = {"", ""};
        String opcio;
        boolean opcioCorrecta;
        String resposta = "-1";
        String tipusMissatge = "";
        StringTokenizer st;
        String missatge = "";
        
        //Encriptacio objEncriptacio = new Encriptacio();
        String dadesEncriptadesEnString = new String();
        String clauEncriptadaEnString;
        String clauPublicaDelClientEnString;
        byte[] clauPublicaDelClientEnByte = null;
        PublicKey clauPublica = null;
        
       
       
        System.out.println("SERVER.procesarMissatgeDelClient(): rebut del client el missatge: '" + missatgeDelClient + "'.");
        st = new StringTokenizer(missatgeDelClient, separador);
        tipusMissatge = st.nextToken();
        System.out.println("SERVER.procesarMissatgeDelClient(): tipusMissatge rebut= '" + tipusMissatge + "'."); 
        System.out.println();
        

        if (tipusMissatge.equals("CLAUPUBLICA")){
        	clauPublicaDelClientEnString = "";
        }        
        if (tipusMissatge.equals("FITXER")) {
            System.out.println("Hola");
            ruta = "data/server/" + missatgeDelClient.substring(10);
            File archivo = new File(ruta);
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("");
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("");
            }

            missatgeDelClient = entradaPelClientSocket.readLine();
            st = new StringTokenizer(missatgeDelClient, separador);
            tipusMissatge = st.nextToken();

            System.out.println(missatgeDelClient.substring(15));

            while (tipusMissatge.equals("LINE_FITXER")) {
                bw.write(missatgeDelClient.substring(15) + "\n");

                missatgeDelClient = entradaPelClientSocket.readLine();
                st = new StringTokenizer(missatgeDelClient, separador);
                tipusMissatge = st.nextToken();
                //String dades = st.nextToken();
                //System.out.println(missatgeDelServer.substring(15));
                //System.out.println(dades);
            }

            bw.close();
            /**byte [] mybytearray  = new byte [6022386]; //Hardcodeado el tamaño del archivo
             InputStream is = clientSocket.getInputStream();
             FileOutputStream fos = new FileOutputStream(ruta);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             int bytesRead = is.read(mybytearray,0,mybytearray.length);
             int current = bytesRead;

             do {
             bytesRead =
             is.read(mybytearray, current, (mybytearray.length-current));
             if(bytesRead >= 0) current += bytesRead;
             } while(bytesRead > -1);

             bos.write(mybytearray, 0 , current);
             bos.flush();
             //System.out.println("File " + "/home/users/inf/wiam2/iam26509397/M09/Exercisi1UF3/data/server + " downloaded (" + current + " bytes read)");*/
        }
        if (!tipusMissatge.equals("TANCARCONNEXIO")){
            Scanner sc = new Scanner(System.in);
            opcioCorrecta = false;
            do {
                System.out.println("---------------- SERVER ----------------");
                System.out.println("0. Desconnectar-se del CLIENT");
                System.out.println();
                System.out.println("   ENCRIPTACIÓ ASIMÈTRICA (RSA amb clau embolcallada)");
                System.out.println("1. Generar clau simètrica i público-privades");
                System.out.println("2. Enviar clau pública al CLIENT");
                System.out.println("3. Encriptar missatge amb RSA amb clau embolcallada");
                System.out.println("4. Enviar el missatge encriptat al CLIENT");
                System.out.println();
                System.out.println("    SENSE ENCRIPTACIÓ");
                System.out.println("11. Enviar un missatge al CLIENT (chat)");
                System.out.println("12. Retornar el control de les comunicacions al CLIENT");
                System.out.println("15. Enviar un missatge encriptat al CLIENT");
                System.out.println();
                System.out.println("21. Enviar un fitxer al CLIENT");
                System.out.println();
                System.out.println("50. Tancar el programa (equival al menú 0)");
                System.out.println();
                System.out.print("opció?: ");


                opcio = sc.next();

                switch (opcio) {
                    case "0":
                        resposta = "TANCARCONNEXIO" + separador + "El server tanca la comunicació.";
                        opcioCorrecta = true;
                        break;
                    case "1":
                    	clauSecretaSimetrica = GeneradorDeClaus.generadorDeClausSimetriques(128);
                        //opcioCorrecta = true;
                        break;
                    case "3":
                        
                        break;
                    case "4":

                        //opcioCorrecta = true;
                        break;                        
                    case "11":
                        Scanner sc2 = new Scanner(System.in);
                        System.out.print("SERVER.procesarMissatgeDelClient(): quin missatge vols enviar al server?: ");
                        missatge = sc2.nextLine();
                        resposta = "CHAT" + separador + missatge;
                        opcioCorrecta = true;
                        break;
                    case "12":
                        resposta = "RETORNCTRL" + separador + "El server retorna el control de les comunicacions al client.";
                        opcioCorrecta = true;
                        break;
                    case "15":
                        
                        //opcioCorrecta = true;
                        break;                    
                    case "21":
                        String path = "data/server/serverFile.txt";
                        resposta = "FITXER" + separador + path;


                        opcioCorrecta = true;
                        //opcioCorrecta = true;
                        break; 
                    case "50":
                        resposta = "TANCARCONNEXIO" + separador + "El server tanca la comunicació.";
                        opcioCorrecta = true;
                        break; 
                    default:
                        System.out.println("COMANDA NO RECONEGUDA");
                }   
            } while (opcioCorrecta != true);
            
            st = new StringTokenizer(resposta, separador);
            tipusMissatge = st.nextToken();
            
        } else {
            resposta = "TANCARCONNEXIO" + separador + "El server tanca la comunicació perquè el client a enviat un tancament de comunicacions.";
            tipusMissatge = "TANCARCONNEXIO";
        }
        
        dadesAEnviarAlClient[0] = tipusMissatge;
        dadesAEnviarAlClient[1] = resposta;
        
        return dadesAEnviarAlClient;
    }
    
    
    
    private static void enviarTextXifratIClauEncriptacio(String[] dadesAEnviarAlClient, PrintWriter sortidaCapAlClientSocket) {
        
        //Enviem el codi MISSATGEENCRIPTAT.
        
        //Enviem el text encriptat. Fem 1 enviamente de cada linia que conté.
 
        //Enviem el codi CLAUENCRIPTADA.
        
        //Enviem la clau AES encriptada. Fem 1 enviamente de cada linia que conté.
        
        //Enviem el codi MISSATGEENCRIPTATFI.
        sortidaCapAlClientSocket.println("MISSATGEENCRIPTATFI" + separador);
        sortidaCapAlClientSocket.flush();
    }
    
    
    private static void procesarComunicacionsAmbClient(Socket clientSocket) {
        String missatgeDelClient = "";
        BufferedReader entradaPelClientSocket = null;
        PrintWriter sortidaCapAlClientSocket = null;
        String dadesAEnviarAlClient[] = {"", ""};
        
        
        try {
            entradaPelClientSocket = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sortidaCapAlClientSocket = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            
            // Processa la petició de connexió del client.
            do {
                missatgeDelClient = entradaPelClientSocket.readLine();
                System.out.println("************"+missatgeDelClient);
                dadesAEnviarAlClient = procesarMissatgeDelClient(missatgeDelClient, entradaPelClientSocket, clientSocket);
                if (dadesAEnviarAlClient[0].equals("FITXER")){
                    enviarFitxerAlClient(dadesAEnviarAlClient, sortidaCapAlClientSocket);
                } else if (!dadesAEnviarAlClient[0].equals("MISSATGEENCRIPTAT")){
                    sortidaCapAlClientSocket.println(dadesAEnviarAlClient[1]);
                    sortidaCapAlClientSocket.flush();
                } else {
                    enviarTextXifratIClauEncriptacio(dadesAEnviarAlClient, sortidaCapAlClientSocket);
                }
                
            } while (!dadesAEnviarAlClient[0].equals("TANCARCONNEXIO"));

        } catch (IOException ex) {
            Logger.getLogger(SPU_9_server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    private static void enviarFitxerAlClient(String[] dadesAEnviarAlClient, PrintWriter sortidaCapAlClientSocket) throws IOException {
        String path = dadesAEnviarAlClient[1].substring(10);
        File myFile = new File(path);
        String patata = "FITXER" + separador + myFile.getName();
        sortidaCapAlClientSocket.println(patata);
        sortidaCapAlClientSocket.flush();

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null) {
            patata = "LINE_FITXER" + separador + line;
            System.out.println(patata);
            sortidaCapAlClientSocket.println(patata);
            sortidaCapAlClientSocket.flush();
            line = reader.readLine();
        }
        reader.close();

        patata = "FI_FITXER" + separador;
        sortidaCapAlClientSocket.println(patata);
        sortidaCapAlClientSocket.flush();

    }
    
    
    private static void tancarClient(Socket clientSocket) {
        //Si falla el tancament no podem fer gaire cosa, només enregistrar el problema.

        //Tancament de tots els recursos.
        if((clientSocket != null) && (!clientSocket.isClosed())){
            try {
                if(!clientSocket.isInputShutdown()){
                    clientSocket.shutdownInput();
                }
                if(!clientSocket.isOutputShutdown()){
                    clientSocket.shutdownOutput();
                }
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(SPU_9_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
    
    public static void escoltar(){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            //Es crea un ServerSocket que atendrà el port nº PORT a l'espera de
            //clients que demanin comunicar-se.
            serverSocket = new ServerSocket(PORT);

            while(!fiComunicacio){
                //El mètode accept() resta a l'espera d'una petició i en el moment
                //de produir-se crea una instància específica de sòcol per suportar
                //la comunicació amb el client acceptat.
                clientSocket = serverSocket.accept();

                //Processem la petició del client.
                procesarComunicacionsAmbClient(clientSocket);
                //SEMBLA QUE AQUEST SERVER NOMÉS POT TREBALLAR AMB 1 CLIENT A L'HORA.
                //Quan es desconnecti el client, esperarà a que es connecti un altre i per tant
                //el server no s'aturarà mai.

                //Tanquem el sòcol temporal per atendre el client.
                tancarClient(clientSocket);
                
                System.out.println("SERVER.escoltar(): el client s'ha desconnectat, esperant a que es connecti un altre client...");
            }

            //Tanquem el sòcol principal
            if((serverSocket != null) && (!serverSocket.isClosed())){
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SPU_9_server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println(Thread.currentThread().getName() + " - INICI");
        escoltar();
        System.out.println(Thread.currentThread().getName() + " - FI");
    }
    
}
