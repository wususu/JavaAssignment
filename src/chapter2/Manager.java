package chapter2;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 学生成绩管理
 * @author janke
 *
 */
public class Manager {

	protected  Map<String, Student> students;
	
	private Manager(){
		students = Cache.students;
	}
	
	public static Manager getInstance(){
		return Inner.instance;
	}
	
	private static class Inner{
		public static final Manager instance = new Manager();
	}

	public List<Student> getStudents(){
		return new LinkedList<>(students.values());
	}
	
	public Student get(String number){
		return students.get(number);
	}
	
	public Student need(String number) throws RuntimeException{
		Student student = students.get(number);
		if (student == null) {
			throw new RuntimeException("student not fount");
 		}
		return student;
	}
	
	public void add(Student student){
		if (get(student.getNumber()) != null) {
			throw new RuntimeException("student already exists");
		}
		students.put(student.getNumber(), student);
	}
	
	public void remove(String number){
		students.remove(number);
	}
	
	public void addScore(String number, Score score){
		Student student = students.get(number);
		Map<String, Score> scores =  student.getScores();
		scores.put(score.getCourse(), score);
	}
	
	public Map<String, Score> getScores(String number){
		Student student = need(number);
		return student.getScores();
	}
	
	public Score getTopScore(CourseName courseName){
		String name = courseName.getName();
		Object[] numbers =  students.keySet().toArray();
		Score top = students.get(numbers[0]).getScores().get(name);
		for (Object number : numbers) {
			Score score = need((String)number).getScores().get(name);
			if (score == null) {
				continue;
			}
			if (score.getGrade() > top.getGrade()) {
				top = score;
			}
		}
		return top;
	}
	
	public Score getLowScore(CourseName courseName){
		String name = courseName.getName();
		Object[] numbers =  students.keySet().toArray();
		Score low = students.get(numbers[0]).getScores().get(name);
		for (Object number : numbers) {
			Score score = need((String)number).getScores().get(name);
			if (score == null) {
				continue;
			}
			if (score.getGrade() < low.getGrade()) {
				low = score;
			}
		}
		return low;
	}
	
	public double getAverageGrade(CourseName courseName){
		String name = courseName.getName();
		double sum=0;
		Object[] numbers =  students.keySet().toArray();
		for (Object number : numbers) {
			Score score = students.get(number).getScores().get(name);
			if (score == null) {
				continue;
			}
			sum += score.getGrade();
		}
		return sum/numbers.length;
	}
}
