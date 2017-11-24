package exam4;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocket {

	private CopyOnWriteArrayList<String> userList;
	
	private ConcurrentHashMap<Integer, String> onlineUser;
	
	private ConcurrentHashMap<String, CopyOnWriteArrayList<String>> msgCache;
	
	private java.net.ServerSocket server;
	
	private Socket socket;
	
	public ServerSocket() throws IOException{
		this.userList = new CopyOnWriteArrayList<>();
		this.onlineUser = new ConcurrentHashMap<>();
		this.msgCache = new ConcurrentHashMap<>();
		this.server =  new java.net.ServerSocket(10889);

	}
	
	public void start() throws IOException {
		ExecutorService service = Executors.newCachedThreadPool();		
		while(true){
			System.err.println("user list: " + userList);
			System.err.println("online user: " + onlineUser );
			System.err.println("msg cache: " + msgCache);
			Socket socket = this.server.accept();
//			System.out.println(socket.getRemoteSocketAddress() +"  " +socket.getPort());
			service.submit(new ServerWorker(socket, userList, onlineUser, msgCache));
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		new ServerSocket().start();
	}
}
