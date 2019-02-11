/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1028_2019_part_I;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author gines
 */


public class Radar_Orion_A {
    private SortedSet<Meteorit> setObjectius = new TreeSet<Meteorit>();
    
        
    
    public Radar_Orion_A() {
        setObjectius.add(new Meteorit(1, 2000));
        setObjectius.add(new Meteorit(3, 2000));
        setObjectius.add(new Meteorit(2, 2000));
        setObjectius.add(new Meteorit(4, 12000));
        setObjectius.add(new Meteorit(5, 7000));
        setObjectius.add(new Meteorit(6, 7000));
    }
    
    public synchronized void desconectarDeltasDelRadar() throws InterruptedException {
    	notifyAll();
    	
    	System.out.println("    " + Thread.currentThread().getName() + ".SLEEP");
    	Thread.currentThread().sleep(20000);
    	
    	
    }

	public SortedSet<Meteorit> getSetObjectius() {
		return setObjectius;
	}

	public void setSetObjectius(SortedSet<Meteorit> setObjectius) {
		this.setObjectius = setObjectius;
	}

	public synchronized int assignarObjectiu(String nomSistemaDeGuia) throws InterruptedException{
		SortedSet<Meteorit> objectius = getSetObjectius();
		java.util.Iterator<Meteorit> it = objectius.iterator();
		while (it.hasNext()) {
			Meteorit m = it.next();
			if (m.getSistemaDeGuiaAssignat() == "CAP") {
				m.setSistemaDeGuiaAssignat(nomSistemaDeGuia);
				return m.getId();
			}
	        
	    }
		wait();
		//objectius.first().setSistemaDeGuiaAssignat(nomSistemaDeGuia);
		return -1;
	}
	
	public Meteorit carregarDadesObjectiu(int objectiuID) throws InterruptedException{
		SortedSet<Meteorit> objectius = getSetObjectius();
		java.util.Iterator<Meteorit> it = objectius.iterator();
		while (it.hasNext()) {
			Meteorit m = it.next();
			if (m.getId() == objectiuID) {
				Thread.currentThread().sleep(m.getDistancia());
				return m;
			}
	        
	    }
		//objectius.first().setSistemaDeGuiaAssignat(nomSistemaDeGuia);
		return null;
		
	}
	
	public synchronized void notifyUnDelta(String nomSistemaDeGuia) throws InterruptedException{
		Thread.currentThread().sleep(2500);
		notify();



		SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss a");
		String jLabel1 = sdf.format(new Date(System.currentTimeMillis()));


		System.out.println(nomSistemaDeGuia + ": notify()" + jLabel1);
	}
    
    
}
