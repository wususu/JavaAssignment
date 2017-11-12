package chapter4;

import java.util.List;

public  class BaseManager<T> implements Manager<T>{
	
	@Override
	public void add(List<T> list, T entity){
		list.add(entity);
	}

	@Override
	public void remove(List<T> list, T entity) {
		// TODO Auto-generated method stub
		list.remove(entity);
	}

	@Override
	public T findByIndex(List<T> list, int id) {
		// TODO Auto-generated method stub
		return list.get(id);
	}

	@Override
	public void update(List<T> list, T entityOld, T entity) {
		// TODO Auto-generated method stub
		list.remove(entity);
		list.add(entity);
	}

	@Override
	public List<T> findAll(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print(List<T> list) {
		// TODO Auto-generated method stub
		
	}

}
