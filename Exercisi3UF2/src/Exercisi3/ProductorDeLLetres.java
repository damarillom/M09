package Exercisi3;

import java.util.Arrays;
import java.util.Random;

public class ProductorDeLLetres implements Runnable {
	private String letras = "abcdefghijklmnopqrstuvwxyz";
	private BorsaDeLLetres borsa;
	
	public ProductorDeLLetres(BorsaDeLLetres borsa) {
		this.borsa = borsa;
	}
	
	/**public void addLetterToBorsa() {
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
		    int index = random.nextInt(letras.length());
			//System.out.println(letras.charAt(index));
		    this.borsa.addLetter(i, letras.charAt(index));
		}
		//this.addLetterToBorsa();
	}*/

	@Override
	public void run() {
		System.out.println("    " + Thread.currentThread().getName() + ".INICI");
        //System.out.println("    " + Thread.currentThread().getName() + ".movimentDinners = "  + movimentDinners);
        System.out.println("    " + Thread.currentThread().getName() + ".borsa.getLlistaDeLLetres() = "  + Arrays.toString(borsa.getLlistaDeLLetres()));
        System.out.println("    " + Thread.currentThread().getName() + ".REALITZO L'OPERACIÓ");
        
        
        this.borsa.modifyLlista(true);
		
        
        System.out.println("    " + Thread.currentThread().getName() + ".borsa.getLlistaDeLLetres() = "  + Arrays.toString(borsa.getLlistaDeLLetres()));
        System.out.println("    " + Thread.currentThread().getName() + ".FI");
		
	}
}
