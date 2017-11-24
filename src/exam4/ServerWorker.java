package exam4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ServerWorker implements Runnable {
	
	final private Socket socket;
		
	private BufferedReader bfReader;
	
	private PrintWriter writer;
	
	private CopyOnWriteArrayList<String> userList;
	
	private ConcurrentHashMap<Integer, String> onlineUsers;
	
	private ConcurrentHashMap<String, CopyOnWriteArrayList<String>>msgCache;
	
	private Integer port;
	
	public ServerWorker(final Socket socket, CopyOnWriteArrayList<String> userList, ConcurrentHashMap< Integer, String> onlineUsers,
			ConcurrentHashMap<String, CopyOnWriteArrayList<String>> msgCache) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.userList = userList;
		this.onlineUsers = onlineUsers;
		this.msgCache = msgCache;
		this.port = socket.getPort();
	}
	
	// 添加用户
	protected boolean addUser(final String user){
		return this.userList.addIfAbsent(user);
	}
	
	// 注册
	protected String registerUser(final String user){
		if(addUser(user)){
			doLogin(user);
			return "<result command = \"register\" state = \"ok\"/>";
		}
		return "<result command = \"register\" state = \"error\" message = \"user already exists\"/>";
	}
	
	// 检查是否存在
	public boolean checkUser(final String user){
		return this.userList.contains(user);
	}
	
	// 删除登录状态
	protected void deleteIfLogin(final String user){
		for(Entry<Integer, String> entry: this.onlineUsers.entrySet()){
			if (entry.getValue().equals(user)) {
				this.onlineUsers.remove(entry.getKey());
			}
		}
	}
	
	// 用户是否登录状态
	protected boolean isLogin(final String user){
		for(Entry< Integer, String> entry: this.onlineUsers.entrySet()){
			if (entry.getValue().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	// 登录
	protected String loginUser(final String user){
		if (checkUser(user)) {
			deleteIfLogin(user);
			doLogin(user);
			return "<result  command = \"login\" state = \"ok\"/>";
		}
		return "<result command = \"login\" state = \"error\" message = \"user not exists\">";
	}
	
	
	protected void doLogin(final String user){
		if (checkUser(user)) {
			this.onlineUsers.put(this.socket.getPort(), user);
		}
	}
	
	// 登出
	protected String logoutUser(final String user){
		if (checkUser(user) && isLogin(user)) {
			doLogout(user);
			return "<result command = \"logout\" state = \"ok\"/>";
		}
		return "<result command = \"loginout\" state = \"error\" message = \"user not exists\">";
	}
	
	protected void doLogout(final String user){
		deleteIfLogin(user);
	}

	protected void refresh(final Element element){
		String user = element.attributeValue("name");
		deleteIfLogin(user);
		doLogin(user);
	}
	
	// 接收逻辑
	protected String valMessage(final String msg) {
		Element root = null;
		String result = null;
		try {
			root = DocumentHelper.parseText(msg).getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			result = "<result message = \"command unknow\">";
			return result;
		}		
		String type = root.getName();
		switch (type) {
		case "refresh":
			refresh(root);
			break;
		case "register":
			result = solveRegister(root);
			break;
		case "login":
			result = solveLogin(root);
			break;
		case "message":
			result = solveMessage(root, msg);
			break;
		case "logout":
			result = solveLogout(root);
			break;
		default:
			result = "<result message = \"command unknow\">";
			break;
		}
		return result;
	}
	
	protected String solveRegister(final Element element){
		String name = element.attributeValue("name");
		if(name != null){
			return registerUser(name);
		}
		return null;
	}

	protected String solveLogin(final Element element){
		String name = element.attributeValue("name");
		if(name != null){
			return loginUser(name);
		}
		return null;
	}
	
	protected String solveLogout(final Element element){
		String name = element.attributeValue("name");
		if(name != null){
			return logoutUser(name);
		}
		return null;
	}
	
	protected String solveMessage(final Element element, final String msg){
		String from = element.attributeValue("from");
		String to = element.attributeValue("to");
		String message = element.attributeValue("message");
		if(to != null && message != null){
			// 发送给另一个用户
			saveUserMsg(to, msg);
			return "<result command = \"message\" state = \"ok\">";
		}
		
		return "<result command = \"message\" state = \"error\">";
	}

	protected void saveUserMsg(final String to,final String msg){
		CopyOnWriteArrayList<String> msgs = msgCache.get(to);
		if (msgs == null) {
			synchronized(msgCache){
				if (msgCache.get(to) == null) {
					(msgs = new CopyOnWriteArrayList<>()).add(msg);
					msgCache.put(to, msgs);
				}
			}
		}else {
			msgs.add(msg);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void sendToUser() {
		CopyOnWriteArrayList<String> msgs = null;
		String user = null;
		List<String> sMsg = null;
		if (
				( user = onlineUsers.get(this.socket.getPort()) ) != null &&
				msgCache.get(user)  != null
				) {
			synchronized (msgCache.get(user)) {
				if ( (msgs = msgCache.get(user) ) != null) {
					sMsg = (List<String>) msgs.clone();
				}
			}
			for (String msg : sMsg) {
				send(msg);
				msgs.remove(msg);
			}
		}
	}
	
	protected void send(String msg){
		this.writer.println(msg);
	}
	
	protected void refresh(final String user){
		doLogin(user);
	}
	
	@SuppressWarnings("finally")
	public void start() throws IOException{
		try{
					String request = null;
			
					InputStreamReader reader = new InputStreamReader(socket.getInputStream());
					this.bfReader = new BufferedReader(reader);
					this. writer = new PrintWriter(socket.getOutputStream());
					
					while( (request = bfReader.readLine()) != null ){
						if (request.equals("")) {
							// 若遇到空行, 结束本次连接
							break;
						}
						String result = valMessage(request);
						if (result == null) {
							continue;
						}
						System.out.println("client say: " + request);
						System.out.println("i will say :" + result);
						send(result);
					}
					
					sendToUser();
					writer.println();
					writer.flush();
					writer.close();
					bfReader.close();
					this.socket.close();
				
		}finally{
			writer.close();
			bfReader.close();
			this.socket.close();
			return ;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().toString());
		try {
			this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
		}
	}
}
