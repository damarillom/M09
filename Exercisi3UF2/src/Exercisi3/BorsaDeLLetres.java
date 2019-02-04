package Exercisi3;

import java.util.Arrays;
import java.util.Random;

public class BorsaDeLLetres {
	char[] llistaDeLLetres = new char[10];
	int posicion = -1;
	private String letras = "abcdefghijklmnopqrstuvwxyz";
	public BorsaDeLLetres() {
		
	}
	
	
	/**public synchronized int space() {
		int pos;
		pos = 9 - posicion;
		return pos; 
	}*/
	public synchronized void modifyLlista(boolean add) {
		if (add) {	
			if ((-1 <= posicion) && (posicion <= 8)) {
			//if (posicion < 10) {
				addLetter();
				notify();
			} else {
				try {
					System.out.println("WAIT ADD");
					wait();
					addLetter();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (posicion >= 0 && posicion <= 9) {
				removeLetter();
				notify();
			} else {
				try {
					System.out.println("WAIT REMOVE");
					wait();
					removeLetter();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void addLetter() {
		for (int i = 0; i < 10; i++) {
			Random random = new Random();
		    int index = random.nextInt(letras.length());
		    this.posicion++;
			this.llistaDeLLetres[posicion] = letras.charAt(index);
			//System.out.println(Arrays.toString(this.llistaDeLLetres));
		}
		//System.out.println(llistaDeLLetres);
	}
	public synchronized void removeLetter() {
		posicion = -1;
		char[] llista = new char[10];
		//System.out.println(Arrays.toString(llista));
		setLlistaDeLLetres(llista);
	}
	

	public synchronized char[] getLlistaDeLLetres() {
		return llistaDeLLetres;
	}

	public void setLlistaDeLLetres(char[] llistaDeLLetres) {
		this.llistaDeLLetres = llistaDeLLetres;
	}
	
}
