package exam3;

public class Index {

	public static void main(String[] args) {
		
		Worker worker = null;
		for(int i=0;i<10;i++){
			worker = new Worker();
			Thread thread = new Thread(worker);
			thread.start();
		}
		
		while(WorldCache.getInstance().isOldWorldEmpty() == false){
			continue;
		}
		WorldCache.getInstance().printNewWorld();
	}
}
