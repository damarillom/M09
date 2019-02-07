package Exercisi4;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumidor extends Thread {
	int id;                 //Ara mateix no té cap utilitat aquest atribut.
    Caixes recursos;
    int i;
    
    
    public Consumidor(int id, Caixes recursos) {
        this.id = id;
        this.recursos = recursos;
    }
    
    
    public void run(){
        int numCaixaAdquirida;
        String name = Thread.currentThread().getName().toUpperCase();
        
        
        System.out.println(name + ": inicialitzat.");
        
        //numCaixaAdquirida = recursos.adquirirCaixa();

        //System.out.println(name + ": adquirida la caixa " + numCaixaAdquirida + " + sleep(7000)");

        // Fem un sleep() perquè es vegi que la resta de consumidors no poden agafar cap caixa i es queden bloquejats.
        
        /**try {
            Thread.currentThread().sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        //Si camuflem la següent linia, el semàfor no canvia i el recurs no queda alliberat.
        //recursos.alliberarCaixa(numCaixaAdquirida);

        //System.out.println(name + ": alliberat la caixa " + numCaixaAdquirida);
        
        System.out.println(name + ": asignando caja");
        
        
		try {
			i = recursos.pasarPorCaja();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(name + " esta pagando");
        try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        recursos.terminar(i);
        
        System.out.println(name + ": ha finalizado" );
        
    }
}
