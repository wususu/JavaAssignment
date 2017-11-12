package chapter3.one;

import static chapter3.one.FileUtils.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Person的普通代理类
 * @author janke
 *
 */
public class PersonProxy implements PersonAcn{

	protected String path;
	
	protected PersonAcn person;
	
	protected SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public PersonProxy(PersonAcn per, String path) {
		// TODO Auto-generated constructor stub
		this.person = per;
		this.path = path;
	}
	
	public void setName(String name){
		person.setName(name);
		
		StringBuffer sf = new StringBuffer();
		Date dNow = new Date();
		sf.append("时间: ")
		.append(ft.format(dNow))
		.append("; 方法名称: setName; 参数: ")
		.append(name);
		
		try {
			save(path, sf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ;
	}
	
	public void setAge(Integer age){
		person.setAge(age);
		
		StringBuffer sf = new StringBuffer();
		Date dNow = new Date();
		sf.append("时间: ")
		.append(ft.format(dNow))
		.append("; 方法名称: setAge; 参数: ")
		.append(age);
		
		try {
			save(path, sf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ;
	}
	
	public void setSex(String sex){
		person.setSex(sex);
		
		StringBuffer sf = new StringBuffer();
		Date dNow = new Date();
		sf.append("时间: ")
		.append(ft.format(dNow))
		.append("; 方法名称: setSex; 参数: ")
		.append(sex);
		
		try {
			save(path, sf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ;
	}
	
	public void setIsMerried(Boolean isMerried){
		person.setIsMerried(isMerried);
		
		StringBuffer sf = new StringBuffer();
		Date dNow = new Date();
		sf.append("时间: ")
		.append(ft.format(dNow))
		.append("; 方法名称: setIsMerried; 参数: ")
		.append(isMerried);
		
		try {
			save(path, sf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ;
	}
	
	public void setIdNo(String idNo){
		person.setIdNo(idNo);
		
		StringBuffer sf = new StringBuffer();
		Date dNow = new Date();
		sf.append("时间: ")
		.append(ft.format(dNow))
		.append("; 方法名称: setIdNo; 参数: ")
		.append(idNo);
		
		try {
			save(path, sf.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ;
	}
}
