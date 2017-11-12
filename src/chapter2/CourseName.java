package chapter2;

/**
 * 课程
 * @author janke
 *
 */
public enum CourseName {
	Math("数学"),
	Chinese( "中文"),
	Java("Java程序设计");

	private String name;
	
	private CourseName(String name) {
		// TODO Auto-generated constructor stub
		this.setName(name);
	}

	public  String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
