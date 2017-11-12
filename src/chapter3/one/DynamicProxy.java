package chapter3.one;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DynamicProxy implements InvocationHandler{

	protected Object proxy;
	
	protected String path;
	
	protected SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public DynamicProxy(Object proxy, String path) {
		// TODO Auto-generated constructor stub
		this.proxy = proxy;
		this.path = path;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		method.invoke(this.proxy, args);
		StringBuffer sf = new StringBuffer();
		Date dNow = new Date();
		sf.append("时间: ")
		.append(ft.format(dNow))
		.append("; 方法名称: ")
		.append(method.getName())
		.append("; 参数: ")
		.append(Arrays.toString(args));
		
		FileUtils.save(path, sf.toString());
		return null;
	}

}
