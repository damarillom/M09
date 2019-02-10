/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemple_apartat_4_2;

/**
 *
 * @author gines
 */
// Implementem runnable per poder crear fils sobre les seves instàncies
public class AgenciaViatges implements Runnable {
    private SeientsAvio sa = new SeientsAvio();

    public void run(){
        // Codi no sincronitzat.
        System.out.println("Seients Lliures: " + sa.getSeientsLliures());
        
        // Amb Java podem sincronitzar només una part de codi dins d’un mètode, aquella part que 
        // volem que s’executi en exclusió mútua.
        // Codi sincronitzat. Només 1 fil podrà executar-lo a l'hora.
		// TOT EL CODI QUE HI HA DINS DEL BLOC "synchronized(sa){...}" ESTÀ SYNCHRONIZED PERO A PARTIR DE TROBAR L'OBJECTE "sa" I
		// FINS A SORTIR DEL BLOC.
        synchronized(sa){
            gestioSeientsAvio(3);

            if(sa.getSeientsLliures()<=0)
            {
                System.out.println("No hi ha seients lliures");
            }
        }
    }



	// S'HAURIA DE TREURE EL synchronized PERQUE EL METODE gestioSeientsAvio() JA ESTA DINS DEL BLOC "synchronized(sa){...}"
	// I QUAN EL PRIMER FIL ARRIBI A TROBAR L'OBJECTE "sa" BLOQUEJARÀ EN AQUELL PUNT A LA RESTA DE FILS FINS QUE ACABI D'EXECUTAR
	// TOT EL CODI QUE HI HA DINS DEL BLOC "synchronized(sa){...}".
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
