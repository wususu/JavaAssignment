package chapter3.one;

import static chapter3.one.StringUtils.generateGetter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ClassStringBuilder {
	
	public List<chapter3.one.Field> valFiledsString(Class<?> clazz) {
		Field[] fields =  clazz.getDeclaredFields();
		List<chapter3.one.Field> fields2  = new LinkedList<>();
		for (Field field : fields) {
			String name = field.getName();
			String protection = "public";
			try{
				clazz.getField(name);
			}catch (NoSuchFieldException e) {
				// TODO: handle exception
				protection = "private";
			}
			String type = field.getType().getName();
			fields2.add((new chapter3.one.Field(protection, type, name)));
		}
		return fields2;
	}
	
	public List<Method> valPMethod(Class<?> clazz){
		Method[] methods =  clazz.getMethods();
		return Arrays.asList(methods);
	}
	
	public List<Method> valAllMethod(Class<?> clazz){
		Method[] methods = clazz.getDeclaredMethods();
		return Arrays.asList(methods);
	}
	
	public List<String> valImpClass(Class<?> clazz){
		List<String> implClass = new LinkedList<>();
		Class<?>[] impClasses =  clazz.getInterfaces();
		for (Class<?> class1 : impClasses) {
			implClass.add(class1.getName());
		}
		return implClass;
	}
	
	public String valSuperClass(Class<?> clazz){
		String className = clazz.getSuperclass().getCanonicalName();
		return className;
	}
	
	public String valPackage(Class<?> clazz){
		return clazz.getPackage().getName();
	}
	
	public String valClassName(Class<?> clazz){
		return clazz.getSimpleName();
	}
	
	public ClassBody build(Class<?> clazz){
		String name = valClassName(clazz);
		String pkName = clazz.getPackage().getName();
		String sprClsNm = valSuperClass(clazz);
		List<String> implNms = valImpClass(clazz);
		List<chapter3.one.Field> fields = valFiledsString(clazz);
		return (new ClassBody(clazz, name, pkName, sprClsNm, implNms, fields));
	}
	
	public void setFieldsValue(ClassBody classBody, Class<?> clazz,  Object obj){
		List<chapter3.one.Field> fields = classBody.getFields();
		List<Method> methods = valPMethod(clazz);
		for (chapter3.one.Field field2 : fields) {
			String getter = generateGetter(field2.getName());
			for (Method method : methods) {
				if (method.getName().equals(getter)) {
					Object value;	
					try {
						value = method.invoke(obj);
						field2.setValue(value);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return ;
	}
	
	public String toString(List<chapter3.one.Field> fields){
		StringBuffer sf = new StringBuffer();
		for (chapter3.one.Field field : fields) {
			sf.append(field.getName()).append("=").append(field.getValue()).append("\n");
		}
		return sf.toString();
	}
	
	public String toString(ClassBody classBody){
		StringBuffer content = new StringBuffer();
		content.append("package ").append(classBody.getPkgNm()).append(";\n\n");
		content.append("public class ").append(classBody.getName());
		content.append(" extends ").append(classBody.getSprClsNm());
		if (!classBody.getImplNm().isEmpty()) {
			content.append(" implements ");
			for (String imp : classBody.getImplNm()) {
				content.append(imp).append(",");
			}
			StringBuffer sb2 = content.replace(content.length()-1, content.length(), "").append("{\n");
			content = sb2;
		}
		
		for (chapter3.one.Field field : classBody.getFields()) {
			content.append(field.getProtect()).append(" ").append(field.getType()).append(" ").append(field.getName()).append(";\n\n");
		}
		content.append("}");
		return content.toString();
	}
	
	
}
