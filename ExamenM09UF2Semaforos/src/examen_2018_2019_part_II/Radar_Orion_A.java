/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019_part_II;


import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 *
 * @author gines
 */

public class Radar_Orion_A {
    private SortedSet<Meteorit> setObjectius = new TreeSet<Meteorit>();
    private Semaphore semafor1;
    private Semaphore semafor2;
    
    public Radar_Orion_A() {
        setObjectius.add(new Meteorit(1, 2000));
        setObjectius.add(new Meteorit(3, 2000));
        setObjectius.add(new Meteorit(2, 2000));
        setObjectius.add(new Meteorit(4, 12000));
        setObjectius.add(new Meteorit(5, 7000));
        setObjectius.add(new Meteorit(6, 7000));
        semafor1 = new Semaphore(1);
        semafor2 = new Semaphore(2);
    }
    
    public int assignarObjectiu(String nomSistemaDeGuia) throws InterruptedException{
    	SortedSet<Meteorit> objectius = getSetObjectius();
		java.util.Iterator<Meteorit> it = objectius.iterator();
		while (it.hasNext()) {
			Meteorit m = it.next();
			semafor1.acquire();
			if (m.getSistemaDeGuiaAssignat() == "CAP") {
				m.setSistemaDeGuiaAssignat(nomSistemaDeGuia);
				semafor1.release();
				return m.getId();
			}
			semafor1.release();
	        
	    }
		//objectius.first().setSistemaDeGuiaAssignat(nomSistemaDeGuia);
		//semafor1.release();
		return -1;
    }
    
    public Meteorit carregarDadesObjectiu(int objectiuID) throws InterruptedException {
    	semafor2.acquire();
    	SortedSet<Meteorit> objectius = getSetObjectius();
		java.util.Iterator<Meteorit> it = objectius.iterator();
		while (it.hasNext()) {
			Meteorit m = it.next();
			if (m.getId() == objectiuID) {
				Thread.currentThread().sleep(m.getDistancia());
				semafor2.release();
				return m;
			}
	        
	    }
		//objectius.first().setSistemaDeGuiaAssignat(nomSistemaDeGuia);
		semafor2.release();
		return null;
    }

    
    
	public SortedSet<Meteorit> getSetObjectius() {
		return setObjectius;
	}

	public void setSetObjectius(SortedSet<Meteorit> setObjectius) {
		this.setObjectius = setObjectius;
	}

	
    
}
