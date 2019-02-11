/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1028_2019_part_I;

import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 *
 * @author gines
 */
public class Sistema_de_guia_Delta implements Runnable{
	public Radar_Orion_A radar;
	
	public Sistema_de_guia_Delta(Radar_Orion_A radar) {
		this.radar = radar;
	}
	/**public Sistema_de_guia_Delta() {
		
	}*/

	@Override
	public void run() {
		System.out.println("    " + Thread.currentThread().getName() + ".INICI");
		//radar = new Radar_Orion_A();
		
		
		//Thread.sleep(20000);
		
		System.out.println("    " + Thread.currentThread().getName() + " assignando a un meorito");
		try {
			System.out.println("    " + Thread.currentThread().getName() + " asignado al meteorito " + radar.assignarObjectiu(Thread.currentThread().getName()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(objectius.first().getId());
		
		/**try {
			radar.desconectarDeltasDelRadar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//try {
			//if (radar.carregarDadesObjectiu(radar.assignarObjectiu(Thread.currentThread().getName())).getId() == 4) { 
				try {
					radar.notifyUnDelta(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
        System.out.println("    " + Thread.currentThread().getName() + ".REALITZO L'OPERACIÓ");
        
        
        
        System.out.println("    " + Thread.currentThread().getName() + ".FI");
		
	}
	
	


}
