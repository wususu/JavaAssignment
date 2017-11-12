package chapter5.core;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

public class DataTypeMapper {
	
	private static Map<Class<?>, String> typeMap = new HashMap<>();
	static{
		typeMap.put(String.class, TableAttribute.VARCHAR.getTemplate());
		typeMap.put(Character.class, TableAttribute.CHAR.getTemplate());
		typeMap.put(Short.class, TableAttribute.MEDIUMINT.getTemplate());
		typeMap.put(Integer.class, TableAttribute.INT.getTemplate());
		typeMap.put(Long.class, TableAttribute.BIGINT.getTemplate());
		typeMap.put(Boolean.class, TableAttribute.TINYINT.getTemplate());
		typeMap.put(Double.class, TableAttribute.DOUBLE.getTemplate());
		typeMap.put(Float.class, TableAttribute.FLOAT.getTemplate());
		
		typeMap.put(Character.TYPE, TableAttribute.CHAR.getTemplate());
		typeMap.put(Short.TYPE, TableAttribute.MEDIUMINT.getTemplate());
		typeMap.put(Integer.TYPE, TableAttribute.INT.getTemplate());
		typeMap.put(Long.TYPE, TableAttribute.BIGINT.getTemplate());
		typeMap.put(Boolean.TYPE, TableAttribute.TINYINT.getTemplate());
		typeMap.put(Double.TYPE, TableAttribute.DOUBLE.getTemplate());
		typeMap.put(Float.TYPE, TableAttribute.FLOAT.getTemplate());
		
		typeMap.put(Enum.class, TableAttribute.SMALLINT.getTemplate());
		typeMap.put(Date.class, TableAttribute.DATETIME.getTemplate());
	}
	
	public static String getTypeFor(Class<?> clazz){
		return typeMap.get(clazz);
	}
	
	public static void setTypeFor(Class<?> clazz, String type) {
		typeMap.put(clazz, type);
	}
	
}
