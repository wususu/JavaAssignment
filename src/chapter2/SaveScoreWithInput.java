package chapter2;

import java.util.List;
import java.util.Scanner;

/**
 * 界面输入
 * @author janke
 *
 */
public class SaveScoreWithInput implements SaveScore{

	protected Manager manager = Manager.getInstance();
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("请根据提示输入学生成绩,回车结束");
		List<Student> students = manager.getStudents();
		for (Student student : students) {
			System.out.println("学号: " + student.getNumber() + "   " + "姓名: " + student.getName());
			CourseName[] courseName = CourseName.values();
			for (CourseName course : courseName) {
				System.out.print(course.getName()+" 成绩 : ");
				Scanner scanner = new Scanner(System.in);
				Double grade = scanner.nextDouble();
				manager.addScore(student.getNumber(), new Score(course, grade));
			}
			System.out.println("---------------------------------------");
		}
	}

}
