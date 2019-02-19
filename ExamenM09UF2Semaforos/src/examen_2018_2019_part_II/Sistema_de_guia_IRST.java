/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019_part_II;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gines
 */
//public class Sistema_de_guia_IRST implements Runnable {
public class Sistema_de_guia_IRST extends Thread {
	public int id;
	public Radar_Orion_A radar;

	public Sistema_de_guia_IRST(int id, Radar_Orion_A radar) {
		this.id = id;
		this.radar = radar;
	}

	@Override
	public void run() {
		Santabarbara santabarbara = new Santabarbara();
		System.out.println("    " + Thread.currentThread().getName() + ".INICI");
		//radar = new Radar_Orion_A();
		
		
		//Thread.sleep(20000);
		
		
		int meteoritoId = -1;
		do {
			
			try {
				meteoritoId = radar.assignarObjectiu(Thread.currentThread().getName());
				//System.out.println(meteoritoId);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (meteoritoId != -1) {
				try {
					System.out.println("    " + Thread.currentThread().getName() + " Destruyendo meteorito: " + meteoritoId);
					Meteorit meteorit = radar.carregarDadesObjectiu(meteoritoId);
					
					//santabarbara.assignarR37(this.id, meteoritoId);
					System.out.println("    " + Thread.currentThread().getName() + " Misil asigando: " + santabarbara.assignarR37(this.id, meteoritoId));
					
					Thread.sleep(meteorit.getDistancia());
					System.out.println("    " + Thread.currentThread().getName() + " Destruido meteorito: " + meteoritoId);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		} while (meteoritoId > -1);
		
	}
}
