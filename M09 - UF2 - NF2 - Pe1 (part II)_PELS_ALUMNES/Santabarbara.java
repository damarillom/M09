/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_2018_2019_part_II;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gines
 */

public class Santabarbara {
    private TreeMap controlMisils;                   
    
    

    public Santabarbara() {
        int i;
        
       
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
    
    

    
    
}
