package chapter3.one;

/**
 * 字符串操作工具类
 * @author janke
 *
 */
public class StringUtils {

	public static String toFirstUpper(String s){
		return s.substring(0,1).toUpperCase() + s.substring(1, s.length());
	}
	
	public static String generateGetter(String field){
		return "get" + toFirstUpper(field);
	}
	
	public static String generateSetter(String field){
		return "set" + toFirstUpper(field);
	}
}
