/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1028_2019_part_I;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gines
 */
public class Sistema_de_guia_Delta implements Runnable{
	public Radar_Orion_A radar;
	
	public Sistema_de_guia_Delta(Radar_Orion_A radar) {
		this.radar = radar;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("    " + Thread.currentThread().getName() + ".INICI");
		//radar = new Radar_Orion_A();
		
		
		//Thread.sleep(20000);
		
		
		int meteoritoId = -1;
		do {
			
			try {
				meteoritoId = radar.assignarObjectiu(Thread.currentThread().getName());
				System.out.println(meteoritoId);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("hhh");
				e.printStackTrace();
			}
			if (meteoritoId != -1) {
				try {
					System.out.println("    " + Thread.currentThread().getName() + " Destruyendo meteorito: " + meteoritoId);
					Meteorit meteorit = radar.carregarDadesObjectiu(meteoritoId);

					Thread.sleep(meteorit.getDistancia()/10);
					System.out.println("    " + Thread.currentThread().getName() + " Destruido meteorito: " + meteoritoId);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (meteoritoId == 4) {
					/**System.out.println("hola");


					try {
						System.out.println("hola");
						radar.notifyUnDelta(Thread.currentThread().getName());
						System.out.println("hola");
					} catch (InterruptedException e) {
						System.out.println("holaF");
						e.printStackTrace();
					}*/

				}
			}
			
		} while (meteoritoId > -1);
		
        //System.out.println("    " + Thread.currentThread().getName() + ".REALITZO L'OPERACIÓ");
        
        
        
        //System.out.println("    " + Thread.currentThread().getName() + ".FI");
		
		
		/**String nameThread = Thread.currentThread().getName();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");

		System.out.println(nameThread+".run(): INICI " + sdf.format(new Date()));


		int idObjectiu = -1;
		try {
			idObjectiu = radar.assignarObjectiu(nameThread);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (idObjectiu !=-1) {
			Meteorit meteorit;
			try {
				meteorit = radar.carregarDadesObjectiu(idObjectiu);
				Thread.sleep(meteorit.getDistancia());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		
	
			if (idObjectiu == 4) {
				try {
					radar.notifyUnDelta(nameThread);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				idObjectiu = radar.assignarObjectiu(nameThread);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		System.out.println(getClass() +".run() " +nameThread + ". Finalitzat!" + sdf.format(new Date()));
		//�s quan el Sistema_de_guia_Delta entendr� que no hi ha m�s
		//meteorits per a destruir i accabar� la seva execuci�
		//*/
		
		
	}



}
