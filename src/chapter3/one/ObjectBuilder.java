package chapter3.one;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static chapter3.one.StringUtils.*;

/**
 * 新建对象,将文件中的值赋给它
 * @author janke
 *
 */
public class ObjectBuilder {

	protected Configurator cgr;
	
	protected  Method[] methods;
	
	protected Field[] fields;
	
	public ObjectBuilder(Configurator cgr) {
		// TODO Auto-generated constructor stub
		this.cgr = cgr;
	}

	protected void diField(Object obj, Field field, Object value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for (Method method : methods) {
			if (generateSetter(field.getName()).equals(method.getName())){
				method.invoke(obj, value);
			}
		}
	}
	
	protected Object build(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for (Field field : fields) {
			if (field.getType().equals(Integer.class) || field.getType().equals(Integer.TYPE)){
				Integer value = this.cgr.getInt(field.getName());
				diField(obj, field, value);
				continue;
			}
			if (field.getType().equals(String.class)) {
				String value = this.cgr.get(field.getName());
				diField(obj, field, value);
				continue;
			}
			if (field.getType().equals(Character.class) || field.getType().equals(Character.TYPE)) {
				Character value = this.cgr.getChar(field.getName());
				diField(obj, field, value);
				continue;
			}
			if (field.getType().equals(Boolean.class) || field.getType().equals(Boolean.TYPE)) {
				Boolean value = this.cgr.getBoolean(field.getName());
				diField(obj, field, value);
				continue;
			}
			if (field.getType().equals(Long.class) || field.getType().equals(Long.TYPE)) {
				Long value = this.cgr.getLong(field.getName());
				diField(obj, field, value);
				continue;
			}
			if (field.getType().equals(Byte.class) || field.getType().equals(Byte.TYPE)) {
				Byte value = this.cgr.getByte(field.getName());
				diField(obj, field, value);
			}
		}
		return obj;
	}
	
	public Object build(Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{		
		Object obj =  clazz.newInstance();
		this.fields = clazz.getDeclaredFields();
		this.methods = clazz.getMethods();
		
		return build(obj);
	}
	
}
