package exam4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class ClientSocket {

	private boolean isLogin;
	
	private String prePost;
	
	private PrintWriter writer;
	
	private Integer defaultPort = 10889;
	
	public ClientSocket() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientSocket(Integer port){
		this.defaultPort = port;
	}
	
	protected String saySomething(){
		Scanner scanner = new Scanner(System.in);
		String say = scanner.nextLine();
		return say;
	}
	
	protected void checkSay(String say, List<String> resps){
		Document doc = null;
		Document respDoc= null;
		String name = null;
		String type = null;
		
		try {
			doc = DocumentHelper.parseText(say);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			return ;
		}
		type = doc.getRootElement().getName();
		name = doc.getRootElement().attributeValue("name");
		
		if ( type.equals("register") || type.equals("login") || type.equals("logout") ) {
			for (String resp : resps) {
				try {
					respDoc = DocumentHelper.parseText(resp);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					continue;
				}
				if(
						respDoc.getRootElement().getName().equals("result") 
						&& 
						respDoc.getRootElement().attributeValue("command").equals(type)
						&&
						respDoc.getRootElement().attributeValue("state").equals("ok")
						){
					this.isLogin = true;
					this.prePost = "<refresh name=\""+name+"\"/>";
					if (type.equals("logout")) {
						this.isLogin = false;
						this.prePost = null;
					}
				}
			}
		}
	}
	
	public void  run() throws UnknownHostException, IOException {
			String line = null;
			Socket socket = null;
			List<String> recMsg = new ArrayList<>();
			while(true){
				System.out.println("isLogin :" + isLogin);
				recMsg.clear();
				socket = new Socket("127.0.0.1", this.defaultPort);
				InputStreamReader reader = new InputStreamReader(socket.getInputStream());
				BufferedReader bfReader = new BufferedReader(reader);
				 writer = new PrintWriter(socket.getOutputStream());
				 
				 if (isLogin == true) {
					writer.println(this.prePost);
				}
				 
				String say = this.saySomething();
				writer.println(say);
				writer.println();
				writer.flush();
				
				while((line = bfReader.readLine()) != null){
					if (line.equals("")) {
						// 若遇到空行, 结束本次连接
						break;
					}
					System.out.println("Server: " + line);
					recMsg.add(line);
				}
				
				checkSay(say, recMsg);
				
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
			ClientSocket clientSocket  = new ClientSocket();
			clientSocket.run();
	}
}
