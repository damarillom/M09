package Exercisi3;

public class ProgramaPrincipal {
	public static void main(String[] args) throws InterruptedException {
		BorsaDeLLetres borsa = new BorsaDeLLetres();
		
		ProductorDeLLetres productor1 = new ProductorDeLLetres(borsa);
		
		Thread filOperacio1 = new Thread(productor1);
        filOperacio1.setName("Productor 1");
		
		ConsumidorDeLLetres consumidor1 = new ConsumidorDeLLetres(borsa);
		
		Thread filOperacio2 = new Thread(consumidor1);
        filOperacio2.setName("Consumidor 1");
        
        ProductorDeLLetres productor2 = new ProductorDeLLetres(borsa);
		
		Thread filOperacio3 = new Thread(productor1);
        filOperacio3.setName("Productor 2");
		
		ConsumidorDeLLetres consumidor2 = new ConsumidorDeLLetres(borsa);
		
		Thread filOperacio4 = new Thread(consumidor1);
        filOperacio4.setName("Consumidor 2");
        
        filOperacio1.start();
        filOperacio2.start();
        filOperacio3.start();
        filOperacio4.start();
        
        filOperacio1.join();
        filOperacio2.join();
        filOperacio3.join();
        filOperacio4.join();
        
        System.out.println("Final Fil Principal");
		//productor1.addLetterToBorsa();
		
		//System.out.println(borsa.getLlistaDeLLetres());
	}
}
