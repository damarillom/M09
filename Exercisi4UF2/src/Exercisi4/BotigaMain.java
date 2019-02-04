package Exercisi4;

public class BotigaMain {
	public static void main(String[] args) throws InterruptedException {
		int cantidad = 20;
		Consumidor[] consumidores = new Consumidor[20];
		
		Caixes cajas = new Caixes(6);
		
		for (int i = 0; i < cantidad; i++){
            consumidores[i] = new Consumidor(i, cajas);
            consumidores[i].setName("consumidor" + i);
            consumidores[i].start();
        }
	}
}
