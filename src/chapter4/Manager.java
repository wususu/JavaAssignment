package chapter4;

import java.util.List;

public interface Manager <T>{

	void add(List<T> list, T entity);
	
	void remove(List<T> list, T entity);
	
	T findByIndex(List<T> list, int id);
	
	 void update(List<T> list, T entityOld, T entity);
	
	List<T> findAll(List<T> list);
	
	void print(List<T> list);
}
