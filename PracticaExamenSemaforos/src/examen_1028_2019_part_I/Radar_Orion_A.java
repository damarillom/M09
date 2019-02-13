/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen_1028_2019_part_I;


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
    
    

    
    
    
}
