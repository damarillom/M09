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
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author gines
 */


public class Radar_Orion_A {
    private SortedSet<Meteorit> setObjectius = new TreeSet<Meteorit>();
    private Semaphore semafor;
        
    
    public Radar_Orion_A() {
    	semafor = new Semaphore(1);
        setObjectius.add(new Meteorit(1, 2000));
        setObjectius.add(new Meteorit(3, 2000));
        setObjectius.add(new Meteorit(2, 2000));
        setObjectius.add(new Meteorit(4, 12000));
        setObjectius.add(new Meteorit(5, 7000));
        setObjectius.add(new Meteorit(6, 7000));
    }



	public void desconectarDeltasDelRadar() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.currentThread().sleep(2500);
		semafor.release();
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
		semafor.acquire();
		//objectius.first().setSistemaDeGuiaAssignat(nomSistemaDeGuia);
		return -1;
		/**Iterator<Meteorit> iterator = setObjectius.iterator();
		while (iterator.hasNext()){
		Meteorit nextMeteorit = iterator.next();
		Date date = new Date();
		if (nextMeteorit.getSistemaDeGuiaAssignat().equalsIgnoreCase("CAP")){
		//primer el set abans de retornar, pq si retorna no far� el set
		Date date_31 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");
		System.out.println(nomSistemaDeGuia + " asignat al meteoritID = " + nextMeteorit.getId() +
		". A distancia = " + nextMeteorit.getDistancia()+ ". " +
		sdf.format(date_31));
		nextMeteorit.setSistemaDeGuiaAssignat(nomSistemaDeGuia);
		return nextMeteorit.getId();
		}
		}
		try {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.SSS");

		System.out.println(nomSistemaDeGuia+": WAIT()" + sdf.format(date));
		semafor.acquire();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		return -1;*/
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



		SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");
		String jLabel1 = sdf.format(new Date(System.currentTimeMillis()));


		System.out.println(nomSistemaDeGuia + ": notify()" + jLabel1);
		semafor.release();
	}
    
    
    
}
