package chapter3;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import chapter3.one.ClassBody;
import chapter3.one.ClassStringBuilder;
import chapter3.one.Configurator;
import chapter3.one.DynamicProxy;
import chapter3.one.FileUtils;
import chapter3.one.ObjectBuilder;
import chapter3.one.Person;
import chapter3.one.PersonAcn;
import chapter3.one.PersonProxy;

import static chapter3.one.FileUtils.*;


public class Index {

	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		File directory = new File(".");//设定为当前文件夹
		Class<?> clazz = Person.class;
		String pkName = "chapter3";
		String rootPath = directory.getCanonicalPath() +"/src/"+pkName ;
		ClassStringBuilder stringBuilder = new ClassStringBuilder();
		ClassBody classBody = stringBuilder.build(clazz);
		classBody.setPkgNm(pkName);

		// 1. 生成Java代码 (在chapter3包中)
		String content = stringBuilder.toString(classBody);
		System.out.println(content);
		try {
		    String javaPath = rootPath + "/" + classBody.getName()+".java";
			FileUtils.create(javaPath, content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// 2. 实例值存入文本文件(在chapter3包中)
	    String propertiesPath = rootPath + "/" + classBody.getName()+".properties";
	
	    Person person = new Person("123ID");
	    person.setAge(21);
	    person.setIsMerried(false);
	    person.setName("janke");
	    
		stringBuilder.setFieldsValue(classBody,Person.class, person);
		String cString = stringBuilder.toString(classBody.getFields());
		System.out.println(cString);
	    create(propertiesPath, cString);
		
		//3. 从文本文件取值新建实例(读取在chapter3.one包中的文件)
		Configurator configurator = Configurator.getInstance().setPath("Person.properties");
		configurator.load();
		System.out.println(configurator.get("name"));
		try {
			Person personZ = (Person)(new ObjectBuilder(configurator)).build(Person.class);
			System.out.println(personZ);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 4. 使用代理
		PersonAcn personA = new PersonProxy(new Person(), rootPath+"/person.log");
		personA.setName("wususu");
		personA.setAge(21);
		personA.setIdNo("112121");
		
		//5. 动态代理
		PersonAcn person2 = (PersonAcn) Proxy.newProxyInstance(
				PersonAcn.class.getClassLoader(), 
				new Class<?>[]{PersonAcn.class}, 
				new DynamicProxy(new Person(),rootPath+"/person.log" )
				);
		person2.setAge(21);
		person2.setName("Janke");
		person2.setIdNo("1223");
		
		}
	}

