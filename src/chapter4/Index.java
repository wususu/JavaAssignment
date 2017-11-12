package chapter4;

import java.util.concurrent.CountDownLatch;

public class Index {


	public static void main(String[] args) {

		PersonManager<Person> teacherManager = new PersonManager<>();
		PersonManager<Person> studentManager = new PersonManager<>();
		
		Person aPerson  = new Teacher("janke");
		Person bPerson = new Student("wususu");
		teacherManager.add(aPerson);
		teacherManager.print();
		
		studentManager.add(bPerson);
		studentManager.print();
		
		System.out.println(teacherManager.findById((long)1));
		
		studentManager.remove(bPerson);
		studentManager.print();
	}
}
