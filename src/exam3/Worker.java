package exam3;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable{

	private WorldCache worldCache;
	
	public Worker(WorldCache worldCache) {
		// TODO Auto-generated constructor stub
		this.worldCache = worldCache;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Integer num = null;
		boolean isN = true;
		
		while((num = worldCache.pollOldWorld()) != null){
			System.out.println(Thread.currentThread() + " 目前的素数" + worldCache.getNewWorlds() +"素数量: "+ worldCache.getNewWorlds().size() );
			isN = true;
			if (num < 4) {
				worldCache.addNewWorld(num);
				continue;
			}

			for(int i = 2; i<num; i++){
				if (num % i == 0) {
					isN = false;
					break;
				}
			}
			if (isN) {
				worldCache.addNewWorld(num);
			}
		}
	}
}
