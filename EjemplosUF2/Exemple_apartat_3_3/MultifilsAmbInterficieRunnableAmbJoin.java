package Exemple_apartat_3_3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gines
 */
public class MultifilsAmbInterficieRunnableAmbJoin implements Runnable{
    private TiradaDaus xobj;


    public MultifilsAmbInterficieRunnableAmbJoin(TiradaDaus m) {
        xobj = m;
    }

    public void run(){
        try {
            Thread.sleep(1000);
            int resultatDau = (int) (Math.random()*6) + 1;
            xobj.setSumaTirada(resultatDau);
            System.out.println("Tirada fil del "+ Thread.currentThread().getName() + " = " + resultatDau);
        } catch (InterruptedException e){
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TiradaDaus ans = new TiradaDaus(0);

        MultifilsAmbInterficieRunnableAmbJoin obj1 = new MultifilsAmbInterficieRunnableAmbJoin(ans);
        MultifilsAmbInterficieRunnableAmbJoin obj2 = new MultifilsAmbInterficieRunnableAmbJoin(ans);
        MultifilsAmbInterficieRunnableAmbJoin obj3 = new MultifilsAmbInterficieRunnableAmbJoin(ans);

        Thread fil_1 = new Thread(obj1);
        fil_1.setName("dau 1");
        Thread fil_2 = new Thread(obj2);
        fil_2.setName("dau 2");
        Thread fil_3 = new Thread(obj3);
        fil_3.setName("dau 3");

        fil_1.start();
        fil_2.start();
        fil_3.start();

        // El fil principal espera a que acabin els fills abans de continuar.
        fil_1.join(); 
        fil_2.join();
        fil_3.join();

        System.out.println("Total tirada (main) = " + ans.getSumaTirada());
        System.out.println("Final Fil Principal");
    }    
    
}
