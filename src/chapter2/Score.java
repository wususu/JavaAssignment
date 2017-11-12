package chapter2;

/**
 * 成绩实体
 * @author janke
 *
 */
public class Score {

	private String course;
	
	private double grade;

	public Score() {
		// TODO Auto-generated constructor stub
	}
	
	public Score(CourseName course, Double grade){
		this.course = course.getName();
		this.grade = grade;
	}
	
	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Score [course=" + course + ", grade=" + grade + "]";
	}
	
	
}
