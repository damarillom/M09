/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019_part_II;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gines
 */

public class Santabarbara {
    private TreeMap controlMisils;                   
    private Semaphore semafor;
    

    public Santabarbara() {
        int i;
        semafor = new Semaphore(1);
       
        ArrayList<R37> llistaR37_1 = new ArrayList<R37>(8);
        ArrayList<R37> llistaR37_2 = new ArrayList<R37>(8);
        ArrayList<R37> llistaR37_3 = new ArrayList<R37>(8);
        ArrayList<R37> llistaR37_4 = new ArrayList<R37>(8);
        controlMisils = new TreeMap();
        
        for(i = 0; i < 8; i++){
            llistaR37_1.add(new R37(i));
        }
        
        for(i = 8; i < 16; i++){
            llistaR37_2.add(new R37(i));
        }
        
        for(i = 16; i < 25; i++){
            llistaR37_3.add(new R37(i));
        }
        
        for(i = 25; i < 33; i++){
            llistaR37_4.add(new R37(i));
        }
        
        controlMisils.put(1, llistaR37_1);      // Pel IRST_1 hi ha els R37 amb id[0,7].
        controlMisils.put(2, llistaR37_2);      // Pel IRST_2 hi ha els R37 amb id[8,15].
        controlMisils.put(3, llistaR37_3);      //...
        controlMisils.put(4, llistaR37_4);      //...
    }
    


	public int assignarR37(int IRST_id, int objectiuID) throws InterruptedException{
    	TreeMap controlMisils = getControlMisils();
    	if (controlMisils.containsKey(IRST_id)) {
    		for (int i = 0; i < ((ArrayList<R37>) controlMisils.get(IRST_id)).size(); i++) {
    			if (((ArrayList<R37>) controlMisils.get(IRST_id)).get(i).isSenseUtilitzar()) {
    				((ArrayList<R37>) controlMisils.get(IRST_id)).get(i).setSenseUtilitzar(false);
    				return ((ArrayList<R37>) controlMisils.get(IRST_id)).get(i).getId();
    			}
    		}
    		return -1;
    	}
		return -1;
		
		
		
    }

    
    public TreeMap getControlMisils() {
		return controlMisils;
	}


	public void setControlMisils(TreeMap controlMisils) {
		this.controlMisils = controlMisils;
	}

    
    
}
