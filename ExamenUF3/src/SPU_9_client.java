/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gmartinez
 */
public class SPU_9_client {
    String clientNom ;
    
    static final String ADDRESS = "127.0.0.1";
    static final int PORT = 9090;
    static final String separador = "$##$";
    private static boolean fiComunicacio = false;

    private static boolean enviarFichero = false;

    
    
    private static String[] procesarMissatgeDelServidor(String missatgeDelServer, BufferedReader entradaPelSocket) throws IOException {
        StringTokenizer st;
        String tipusMissatge = "";

        boolean sortirDelMenu;
        String opcio;
        String resposta = "-1";
        String dadesAEnviarAlServer[] = {"", ""};

        
		// SI REBEM "tipusMissatge = FITXEREMAILADJUNTATDEMANAT", HEM DE REBRE LES LÍNIES DEL FITXER I TREURE-LES PER PANTALLA.


		// MENÚ 22:
		// SI VOLEM ENVIAR UN EMAIL HEM D'ENVIAR "tipusMissatge = EMAIL". EL FORMAT SERÀ: 
		//			"EMAIL" + separador + origen + separador + destinatari + separador + missatge + separador + nom del fitxer adjuntat
		// PER TANT HEM DE DEMANAR EL NOM DEL ORIGEN, EL DEL DESTINATARI, EL TEXT DEL MISSATGE I EL NOM DEL FITXER QUE VOLEM ADJUNTAR (EN
		// EL CAS QUE VULGUEM ADJUNTAR UN FITXER).
		// SI NO VOLEM ADJUNTAR UN FITXER A L'EMAIL, EL FORMAT SERÀ:
		//			"EMAIL" + separador + origen + separador + destinatari + separador + missatge + separador
		// SE SUPOSA QUE ELS FITXERS QUE S'ADJUNTEN ESTARAN EN LA CARPETA fitxersClients.
		// QUAN TINGUEM LES DADES, CRIDAREM A LA FUNCIÓ enviarEmail() PER A ENVIAR L'EMAIL I EL FITXER ADJUNTAT SI N'HI HAGUÉS CAP.


		// MENÚ 23:
		// DEMANARÀ EL NOM DE L'USUARI DEL QUAL VOLEM VEURE ELS EMAILS QUE HA REBUT (PER TANT SERÀ EL "destinatari" EN ELS EMAILS) I
		// ELS VISUALITZARÀ. PER A FER AIXÒ CRIDAREM A LA FUNCIÓ "Email.consultarEmails()".
		// DESPRÉS TINDRÀ L'OPCIÓ DE VEURE EL CONTINGUT DEL FITXER ADJUNTAT A UN DELS EMAILS. "Email.consultarEmails()" RETORNARÀ EL
		// NOM DEL FITXER ADJUNTAT A L'EMAIL QUE S'HAGI ESCOLLIT (SI L'USUARI VOL).
		// SI ES VOLGUÉS VEURE EL CONTINGUT D'UN FITXER ADJUNTAT, A LLAVORS S'HAURÀ D'ENVIAR UN MISSATGE AL SERVER DEMANANT EL FITXER
		// AMB "tipusMissatge = CLIENT_DEMANA_FITXER_EMAIL". EL SERVER RESPONDRÀ AMB "tipusMissatge = FITXEREMAILADJUNTATDEMANAT" I 
		// A LLAVORS REBREM LES LÍNIES DEL FITXER I LES TREUREM PER PANTALLA.

        
        System.out.println("CLIENT.procesarMissatgeDelServidor(): rebut del server el missatge = '" + missatgeDelServer + "'.");
        st = new StringTokenizer(missatgeDelServer, separador);
        tipusMissatge = st.nextToken();			//CADA VEGADA QUE FEM "st.nextToken()" AGAFEM EL SEGÜENT ELEMENT SEPARAT PER "separador".
        System.out.println("CLIENT.procesarMissatgeDelServidor(): tipusMissatge rebut = '" + tipusMissatge + "'.");

        if (tipusMissatge.equals("FITXEREMAILADJUNTATDEMANAT")) {
            //System.out.println("Hola");
            /**ruta = "data/client/" + missatgeDelServer.substring(10);
            File archivo = new File(ruta);
            BufferedWriter bw;
            if (archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("");
            } else {
                bw = new BufferedWriter(new FileWriter(archivo));
                bw.write("");
            }*/

            System.out.println("");
            System.out.println("Contingut del fitxer adjuntad al mail: ");
            //File archivo = new File(ruta);
            //System.out.println(ruta);
            //FileWriter fw = new FileWriter(archivo.getAbsoluteFile(),true);
            //BufferedWriter bw = new BufferedWriter(fw);

            missatgeDelServer = entradaPelSocket.readLine();
            st = new StringTokenizer(missatgeDelServer, separador);
            tipusMissatge = st.nextToken();

            //System.out.println(missatgeDelServer.substring(15));

            while (tipusMissatge.equals("LINE_FITXER")) {
                System.out.println(missatgeDelServer.substring(15));

                missatgeDelServer = entradaPelSocket.readLine();
                st = new StringTokenizer(missatgeDelServer, separador);
                tipusMissatge = st.nextToken();
                //String dades = st.nextToken();
                //System.out.println(missatgeDelServer.substring(15));
                //System.out.println(dades);
            }

            //bw.close();
        }
        
        if (!tipusMissatge.equals("TANCARCONNEXIO")){
            Scanner sc = new Scanner(System.in);
            sortirDelMenu = false;
            
            do {
                System.out.println("---------------- CLIENT ----------------");
                System.out.println("0. Desconnectar-se del SERVER");
                System.out.println();
                System.out.println("    EMAIL");
                System.out.println("22. Enviar un email a un client (es pot adjuntar un fitxer)");
                System.out.println("23. Veure els emails rebuts");
                System.out.println();
                System.out.println("50. Tancar el programa (equival al menú 0)");
                System.out.println();
                System.out.print("opció?: ");

                opcio = sc.next();

                switch (opcio) {
                    case "0":
                        resposta = "TANCARCONNEXIO" + separador + "El client tanca la comunicació.";
                        sortirDelMenu = true;
                        break;
                    case "22":
                        System.out.println("Origen: ");
                        String origen = sc.next();
                        System.out.println("Destinatari: ");
                        String destinatari = sc.next();
                        System.out.println("Missatge a enviar: ");
                        String missatge = sc.next();
                        System.out.println("Vols adjuntar algun fitxer? (S/N): ");
                        String fitxer = sc.next();

                        if (fitxer.equals("S") || fitxer.equals("s")) {
                            System.out.println("Quin nom te el fitxer?: ");
                            String nomFitxer = sc.next();
                            resposta = "EMAIL" + separador + origen + separador + destinatari + separador + missatge + separador + nomFitxer;
                            enviarFichero = true;
                        } else {
                            resposta = "EMAIL" + separador + origen + separador + destinatari + separador + missatge + separador;
                        }
                        sortirDelMenu = true;
                        break; 
                    case "23":
                        System.out.println("Quin es el teu nom?: ");
                        String nomDestinatari = sc.next();
                        String peticionFichero = Email.consultarEmails(nomDestinatari);
                        //System.out.println(peticionFichero);
                        if (!peticionFichero.equals("")) {
                            resposta = "CLIENT_DEMANA_FITXER_EMAIL" + separador + nomDestinatari + separador + peticionFichero;
                        }
                        sortirDelMenu = true;
                        break;                         
                    case "50":
                        resposta = "TANCARCONNEXIO" + separador + "El client tanca la comunicació.";
                        sortirDelMenu = true;
                        break; 
                    default:
                        System.out.println("COMANDA NO RECONEGUDA");
                }   
            } while (sortirDelMenu != true);

            st = new StringTokenizer(resposta, separador);
            tipusMissatge = st.nextToken();
            
        } else {
            resposta = "TANCARCONNEXIO" + separador + "El client tanca la comunicació perquè el server a enviat un tancament de comunicacions.";
            tipusMissatge = "TANCARCONNEXIO";
        }
        
        
        dadesAEnviarAlServer[0] = tipusMissatge;
        dadesAEnviarAlServer[1] = resposta;
        
        return(dadesAEnviarAlServer);
    }
    
    
    
    private static void tancarSocket(Socket clientSocket) {
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
                Logger.getLogger(SPU_9_client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
    
    
    
    private static void enviarEmail(String[] dadesAEnviar, PrintWriter sortidaCapAlSocket, Socket socket) throws IOException {

    }
    

    
    public static void connectar(String address, int port) {
        String missatgeDelServer = "";
        String dadesAEnviar[] = {"PETICIOCONNEXIODENEGADA", ""};
        Socket socket;
        BufferedReader entradaPelSocket;
        PrintWriter sortidaCapAlSocket = null;
        SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataResposta = "";
        String tipusMissatge;


        try {
            socket = new Socket(InetAddress.getByName(address), port);
            entradaPelSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sortidaCapAlSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            //Enviament de la petició. Inicialitzant la comunicació amb el server.
            sortidaCapAlSocket.println("patata");    //Assegurem que acaba amb un final de línia.
            sortidaCapAlSocket.flush();

            //El client atén el port fins que decideix finalitzar.
            do {
                missatgeDelServer = entradaPelSocket.readLine();

                //Processament de les dades rebudes i obtenció d’una nova petició.
                dadesAEnviar = procesarMissatgeDelServidor(missatgeDelServer, entradaPelSocket);
                //System.out.println("    " + Thread.currentThread().getName() + ": enviant les dades: " + dadesAEnviar[1]);
                if (enviarFichero) {
                    //PARA ENVIAR EL FICHERO AL SERVER
                    enviarFitxer(dadesAEnviar, sortidaCapAlSocket, socket);
                    enviarFichero = false;
                } else if (!dadesAEnviar[0].equals("CLAUPUBLICA")){
                    sortidaCapAlSocket.println(dadesAEnviar[1]);    //Assegurem que acaba amb un final de línia.
                    sortidaCapAlSocket.flush();                     //Assegurem que s’envia.
                } else {
                    //enviarClauPublica(dadesAEnviar, sortidaCapAlSocket, socket);
                }

                /**if (enviarFichero) {
                    enviarFitxer(dadesAEnviar, sortidaCapAlSocket, socket);
                }*/
            } while (!dadesAEnviar[0].equals("TANCARCONNEXIO"));
            tancarSocket(socket);
        } catch (UnknownHostException ex) {
            Logger.getLogger(SPU_9_client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SPU_9_client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void enviarFitxer(String[] dadesAEnviar, PrintWriter sortidaCapAlSocket, Socket socket) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(dadesAEnviar[1], separador);
        st.nextToken();
        st.nextToken();
        st.nextToken();
        st.nextToken();
        String nomFitxer = st.nextToken();
        System.out.println(nomFitxer);
        String path = "fitxersClients/" + nomFitxer;
        File myFile = new File(path);





        String patata = dadesAEnviar[1];
        sortidaCapAlSocket.println(patata);
        //sortidaCapAlSocket.flush();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null) {
            patata = "LINE_FITXER" + separador + line;
            sortidaCapAlSocket.println(patata);
            sortidaCapAlSocket.flush();
            line = reader.readLine();
        }
        reader.close();
        patata = "FI_FITXER" + separador + line;
        sortidaCapAlSocket.println(patata);
        sortidaCapAlSocket.flush();

        /**OutputStream os = socket.getOutputStream();
         System.out.println("Sending " + dadesAEnviar[1] + "(" + mybytearray.length + " bytes)");
         os.write(mybytearray,0,mybytearray.length);
         os.flush();
         System.out.println("Done.");*/




        /**if (bis != null) bis.close();
         if (os != null) os.close();*/

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("CLIENT - INICI");
        connectar(ADDRESS, PORT);
        System.out.println("CLIENT - FI");
        
    }
    
}
