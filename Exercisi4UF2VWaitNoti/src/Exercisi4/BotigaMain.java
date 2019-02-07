package Exercisi4;

public class BotigaMain {
	public static void main(String[] args) throws InterruptedException {
		int cantidad = 20;
		Consumidor[] consumidores = new Consumidor[20];
		
		Caixes cajas = new Caixes(1);
		
		/**for (int i = 0; i < cantidad; i++){
            consumidores[i] = new Consumidor(i, cajas);
            consumidores[i].setName("consumidor" + i);
            consumidores[i].start();
            consumidores[i].join();
        }*/
		Consumidor consumidor1 = new Consumidor(1, cajas);
		Consumidor consumidor2 = new Consumidor(2, cajas);
		Consumidor consumidor3 = new Consumidor(3, cajas);
		Consumidor consumidor4 = new Consumidor(4, cajas);
		Consumidor consumidor5 = new Consumidor(5, cajas);
		Consumidor consumidor6 = new Consumidor(6, cajas);
		Consumidor consumidor7 = new Consumidor(7, cajas);
		Consumidor consumidor8 = new Consumidor(8, cajas);
		
		consumidor1.setName("consumidor1");
		consumidor2.setName("consumidor2");
		consumidor3.setName("consumidor3");
		consumidor4.setName("consumidor4");
		consumidor5.setName("consumidor5");
		consumidor6.setName("consumidor6");
		consumidor7.setName("consumidor7");
		consumidor8.setName("consumidor8");
		
		consumidor1.start();
		consumidor2.start();
		consumidor3.start();
		consumidor4.start();
		consumidor5.start();
		consumidor6.start();
		consumidor7.start();
		consumidor8.start();
		
		consumidor1.join();
		consumidor2.join();
		consumidor3.join();
		consumidor4.join();
		consumidor5.join();
		consumidor6.join();
		consumidor7.join();
		consumidor8.join();
		
	}
}
