package exam3;

public class Index {

	public static void main(String[] args) {
		
		Worker worker = null;
		for(int i=0;i<3;i++){
			worker = new Worker(WorldCache.getInstance());
			Thread thread = new Thread(worker);
			thread.start();
		}

		
		while(WorldCache.getInstance().isOldWorldEmpty() == false){
			
		}
		WorldCache.getInstance().printNewWorld();

		


	}
}
