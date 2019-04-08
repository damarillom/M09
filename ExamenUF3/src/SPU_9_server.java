import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author gmartinez
 */
public class SPU_9_server {
    static final int PORT = 9090;
    static final String separador = "$##$";
    private static boolean fiComunicacio = false;

    static String ruta;
    
    //private static String[] procesarMissatgeDelClient(String missatgeDelClient, BufferedReader entradaPelClientSocket, Socket clientSocket, PrintWriter sortidaCapAlClientSocket) throws IOException {
    private static String[] procesarMissatgeDelClient(String missatgeDelClient, BufferedReader entradaPelClientSocket, Socket clientSocket) throws IOException {
        /**String tipusMissatge = "";
        StringTokenizer st;

		// SI REBEM "tipusMissatge = EMAIL", HEM DE CREAR UN OBJECTE DE TIPUS "Email_Dades" I FER UN "Email.insertarNouEmail(nouEmail)" 
		// PER ENMAGATZEMAR L'EMAIL EN LA BASE DE DADES.
		// SI L'EMAIL TÉ ADJUNTAT UN FITXER, HEM DE REBRE LES LÍNIES DEL FITXER I GUARDAR-LES EN UN FITXER AMB EL MATEIX NOM EN LA 
		// CARPETA "fitxersServer".
		

		// SI REBEM "tipusMissatge = CLIENT_DEMANA_FITXER_EMAIL", HEM DE BUSCAR EN LA CARPETA "fitxersServer" EL FITXER QUE ENS DEMANEN
		// I ENVIAR-LI AL CLIENT FENT SERVIR LA FUNCIÓ "enviarFitxerAlClient()".


		// EN CAP MOMENT L'USUARI INTERACTUA AMB EL SERVER. EL SERVER NO TÉ CAP MENÚ, ÉS TOT AUTOMÀTIC.


        
        System.out.println(LocalTime.now() + ": SERVER.procesarMissatgeDelClient(): rebut del client el missatge = '" + missatgeDelClient + "'.");
        st = new StringTokenizer(missatgeDelClient, separador);
        tipusMissatge = st.nextToken();			//CADA VEGADA QUE FEM "st.nextToken()" AGAFEM EL SEGÜENT ELEMENT SEPARAT PER "separador".
        System.out.println(LocalTime.now() + ": SERVER.procesarMissatgeDelClient(): tipusMissatge rebut = '" + tipusMissatge + "'."); 
        System.out.println();

        String dadesAEnviarAlClient[] = {"", ""};
        dadesAEnviarAlClient[0] = tipusMissatge;
        //dadesAEnviarAlClient[1] = st.nextToken();
        return dadesAEnviarAlClient;*/


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



        if (tipusMissatge.equals("EMAIL")) {
            st = new StringTokenizer(missatgeDelClient, separador);
            tipusMissatge = st.nextToken();
            System.out.println(tipusMissatge);
            String origen = st.nextToken();
            String destinatari = st.nextToken();
            String mensaje = st.nextToken();
            String nomFitxer = "";
            try {
                nomFitxer = st.nextToken();

                ruta = "fitxersServer/" + nomFitxer;
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
                    System.out.println(missatgeDelClient.substring(15));
                    missatgeDelClient = entradaPelClientSocket.readLine();
                    st = new StringTokenizer(missatgeDelClient, separador);
                    tipusMissatge = st.nextToken();
                    //String dades = st.nextToken();
                    //System.out.println(missatgeDelServer.substring(15));
                    //System.out.println(dades);
                }

                bw.close();

            } catch (Exception e) {
                System.out.println("No esta enviando ningun fichero");
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            //System.out.println(dateFormat.format(cal));
            Email_Dades email = new Email_Dades(origen, destinatari, cal, cal, mensaje, nomFitxer);
            Email.insertarNouEmail(email);
            tipusMissatge="EMAILINSERTAT";
            resposta = tipusMissatge + separador + "El server ha insertat el nou email.";
        }
        if (tipusMissatge.equals("CLIENT_DEMANA_FITXER_EMAIL")) {



            st = new StringTokenizer(missatgeDelClient, separador);
            tipusMissatge = st.nextToken();
            System.out.println(tipusMissatge);
            st.nextToken();
            resposta = "FITXEREMAILADJUNTATDEMANAT" + separador + st.nextToken();
            System.out.println(resposta);
            tipusMissatge="FITXEREMAILADJUNTATDEMANAT";

        }
        if (!tipusMissatge.equals("TANCARCONNEXIO")){
            Scanner sc = new Scanner(System.in);
            opcioCorrecta = false;
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
    
    
    
    private static void enviarFitxerAlClient(String nomFitxerAEnviar, PrintWriter sortidaCapAlClientSocket) throws IOException {
		//AQUÍ ESTARÀ TOT EL CODI QUE PERMET ENVIAR TOT EL CONTINGUT DEL FITXER, LÍNIA A LÍNIA.
		//S'HA D'ENVIAR EL "tipusMissatge = FITXEREMAILADJUNTATDEMANAT" ABANS D'ENVIAR LES LÍNIES DEL FITXER.
        StringTokenizer st;
        st = new StringTokenizer(nomFitxerAEnviar, separador);
        st.nextToken();
        String path = "fitxersServer/" + st.nextToken();
        File myFile = new File(path);



        String patata = "FITXEREMAILADJUNTATDEMANAT" + separador;
        sortidaCapAlClientSocket.println(patata);
        //sortidaCapAlSocket.flush();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null) {
            patata = "LINE_FITXER" + separador + line;
            sortidaCapAlClientSocket.println(patata);
            sortidaCapAlClientSocket.flush();
            line = reader.readLine();
        }
        reader.close();
        patata = "FI_FITXER" + separador + line;
        sortidaCapAlClientSocket.println(patata);
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
                System.out.println("Hola ++" + dadesAEnviarAlClient[0]);
                if (dadesAEnviarAlClient[0].equals("FITXEREMAILADJUNTATDEMANAT")){
                    System.out.println("Hola, estoy aqui");
                    //Aun no terminado
                    String fichero = dadesAEnviarAlClient[1];
                    enviarFitxerAlClient(fichero, sortidaCapAlClientSocket);
                } else if (!dadesAEnviarAlClient[0].equals("MISSATGEENCRIPTAT")){
                    sortidaCapAlClientSocket.println(dadesAEnviarAlClient[1]);
                    sortidaCapAlClientSocket.flush();
                }

            } while (!dadesAEnviarAlClient[0].equals("TANCARCONNEXIO"));

        } catch (IOException ex) {
            Logger.getLogger(SPU_9_server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private static void tancarClient(Socket clientSocket) {
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
        
        System.out.println(LocalTime.now() + ": SERVER.main(): INICI");
        escoltar();
        System.out.println(LocalTime.now() + ": SERVER.main(): FI");
    }
    
}
