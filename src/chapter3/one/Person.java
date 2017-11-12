package chapter3.one;

/**
 * 测试类
 * @author janke
 *
 */
public class Person implements PersonAcn{
	
	private String name;
	
	private String sex;
	
	private Integer age;
	
	private String idNo;
	
	private Boolean isMerried;

	public Person() {
		// TODO Auto-generated constructor stub
	}
	
	public Person(String idNo){
		this.idNo = idNo;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Boolean getIsMerried() {
		return isMerried;
	}

	public void setIsMerried(Boolean isMerried) {
		this.isMerried = isMerried;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + ", idNo=" + idNo + ", isMerried=" + isMerried
				+ "]";
	}
	
}
