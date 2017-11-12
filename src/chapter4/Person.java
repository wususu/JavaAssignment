package chapter4;

import java.util.Date;

public class Person {
	
	protected static long unique_counter = 0;
		
	protected long id;
	
	protected String name;
	
	protected String sex;
	
	protected Date birth ;
	
	protected String type;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name) {
		super();
		this.id =  ++unique_counter;
		this.name = name;
		this.type = this.getClass().getSimpleName();
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Person [unique="  + ", id=" + id + ", name=" + name + ", sex=" + sex + ", birth=" + birth
				+ ", type=" + type + "]";
	}


	
}
