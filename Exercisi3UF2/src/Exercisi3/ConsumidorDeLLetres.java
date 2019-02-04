package Exercisi3;

import java.util.Arrays;
import java.util.Random;

public class ConsumidorDeLLetres implements Runnable {
	private BorsaDeLLetres borsa;
	
	public ConsumidorDeLLetres(BorsaDeLLetres borsa) {
		this.borsa = borsa;
	}
	@Override
	public void run() {
		System.out.println("    " + Thread.currentThread().getName() + ".INICI");
        //System.out.println("    " + Thread.currentThread().getName() + ".movimentDinners = "  + movimentDinners);
        System.out.println("    " + Thread.currentThread().getName() + ".borsa.getLlistaDeLLetres() = "  + Arrays.toString(borsa.getLlistaDeLLetres()));
        System.out.println("    " + Thread.currentThread().getName() + ".REALITZO L'OPERACIÓ");
        if (this.borsa.getLlistaDeLLetres().length == 0) {
        	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        char[] borsaAux = new char[this.borsa.getLlistaDeLLetres().length];
        //borsaAux = borsa.getLlistaDeLLetres();
        //for (int i = 0; i < 10; i++) {
        this.borsa.modifyLlista(false);
		//}
        //System.out.println("hola: " + Arrays.toString(borsaAux));
        
        System.out.println("    " + Thread.currentThread().getName() + ".borsa.getLlistaDeLLetres() = "  + Arrays.toString(borsa.getLlistaDeLLetres()));
        System.out.println("    " + Thread.currentThread().getName() + ".FI");
		
	}
	
}
