package chapter4;

import java.util.ArrayList;
import java.util.List;

public class PersonManager<T extends Person>{
	
	protected List<T> persons = new ArrayList<>(); 
	
	private BaseManager<T> manager = new BaseManager<>();
	
	public T findById(Long id){
		for (T person : persons) {
			if (person.getId().equals(id)) {
				return person;
			}
		}
		return null;
	}
	
	public List<T> findAll(){
		return persons;
	}
	
	public T need(Long id){
		T entity = findById(id);
		if (entity == null) {
			throw new NullPointerException();
		}
		return entity;
	}
	
	public void add(T entity){
		if (findById(entity.getId()) != null) {
			throw new RuntimeException();
		}
		manager.add(persons, entity);
	}
	
	public void remove(T entity){
		manager.remove(persons, entity);
	}
	
	public void update(T entity){
		T entity1 = need(entity.getId());
		manager.update(persons,entity1 , entity);
	}
	
	public void print(){
		System.out.println(persons);
	}
}
