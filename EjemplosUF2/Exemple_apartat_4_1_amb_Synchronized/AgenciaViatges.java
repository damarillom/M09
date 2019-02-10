/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_1_amb_Synchronized;

/**
 *
 * @author gines
 */
// Implementem runnable per poder crear fils sobre les seves instàncies
public class AgenciaViatges implements Runnable {
    private SeientsAvio sa = new SeientsAvio();

    public void run(){
        gestioSeientsAvio(3);

        if(sa.getSeientsLliures()<=0)
        {
            System.out.println("No hi ha seients lliures");
        }
    }



    // Quan un fil executa un mètode sincronitzat el bloqueixa fins que acabi la seva execució (del mètode) i
    // s’alliberi del bloqueig. Si altre fil crida el mètode sincronitzat del mateix objecte, queda bloquejat 
    // fins que el fil que l’està executant l’alliberi del bloqueig.
    // Els mètode no sincronitzats del mateix objecte poden ser executats per diversos fils a la vegada.
    // La forma natural d’alliberar un bloqueig és que el fil arribi al final del codi del mètode sincronitzat o
    // trobi una sentència return. També pot alliberar-lo per altres motius, com ara que es llanci una excepció.
    public synchronized void gestioSeientsAvio(int numSeientsReserva){
        System.out.println(Thread.currentThread().getName() + " té la clau del cadenat");

        //Comprovem si hi ha seients suficients
        if(sa.getSeientsLliures() >= numSeientsReserva)
        {
            System.out.println(Thread.currentThread().getName() + " reservarà " +
                numSeientsReserva + " places");
            try {
                Thread.sleep(1000); //adormim el fil 1 segon
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //Fem la reserva dels seients
            sa.reservaSeients(numSeientsReserva);
            System.out.println(Thread.currentThread().getName() + " Reserva realitzada." + " Places lliures = " + sa.getSeientsLliures());

        } else {
            System.out.println("No hi ha places suficients pel client " + Thread.currentThread().getName() + ". Places lliures = " + sa.getSeientsLliures());
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        System.out.println(Thread.currentThread().getName() + " deixa la clau del cadenat");

    }


    public static void main(String[] args) {
        AgenciaViatges objAg = new AgenciaViatges();

        //creem els dos fils sobre la mateixa instància
        Thread Fil_1 = new Thread(objAg);
        Thread Fil_2 = new Thread(objAg);
        Fil_1.setName("Client 1");
        Fil_2.setName("Client 2");

        Fil_1.start();
        Fil_2.start();
    }
}
