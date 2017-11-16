package exam3;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.poi.hssf.util.HSSFColor.INDIGO;

public class WorldCache {

	public static WorldCache getInstance(){
		return Inner.instance;
	}
	
	private static class Inner{
		public static WorldCache instance = new WorldCache();
	}
	
	private LinkedBlockingQueue<Integer> oldWorlds;
	
	private LinkedBlockingQueue<Integer> newWorlds;
	
	private WorldCache() {
		// TODO Auto-generated constructor stub
		this.oldWorlds =  new LinkedBlockingQueue<>(FileUtils.getWorlds());
		this.newWorlds = new LinkedBlockingQueue<>();
	}
	
	public boolean isOldWorldEmpty(){
		return oldWorlds.isEmpty();
	}
	
	public Integer pollOldWorld(){
		return oldWorlds.poll();
	}
	
	public void addNewWorld(Integer e){
		newWorlds.add(e);
	}
	
	public void printNewWorld(){
		System.out.println("总素数: "+ newWorlds + "=====" + "总数目" + newWorlds.size());
	}
	
	public synchronized LinkedBlockingQueue<Integer> getNewWorlds(){
		return newWorlds;
	}
	
	public synchronized LinkedBlockingQueue<Integer> getOldWorlds(){
		return oldWorlds;
	}
	
}
