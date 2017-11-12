package chapter5.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 实体信息缓存
 * @author janke
 *
 */
public class TableInfoCache {

	public static final Map<String, TableInfo> cache = new HashMap<>();
	
	public void add(Class<?> clazz, TableInfo tableInfo){
		cache.put(clazz.getName(), tableInfo);
	}
	
	public TableInfo get(Class<?> clazz){
		return cache.get(clazz.getName());
	}
	
	public void print(){
		System.out.println(cache);
	}

}
