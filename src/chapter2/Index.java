package chapter2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {
	
	public static void main(String[] args) {
		Student student1 = new Student("1", "姓名1");
		Student student2 = new Student("2", "小黄");
		Manager manager = Manager.getInstance();
		manager.add(student1);
		manager.add(student2);
		
		SaveScore saveScore = new SaveScoreWithInput();
		// 输入所有成绩
		saveScore.save();
		
		// 课程平均成绩
		System.out.println(manager.getAverageGrade(CourseName.Math));
		// 课程最低成绩
		System.out.println(manager.getLowScore(CourseName.Math));
		// 课程最高成绩
		System.out.println(manager.getTopScore(CourseName.Math));
		// 查询某个学生的所有成绩
		System.out.println(manager.getScores(student1.getNumber()));
		
	}
	
}
