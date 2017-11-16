package exam1;

public class Bootstrap {

	public static void main(String[] args) {
		BeanFactory factory = new XMLContext("beans.xml");
		Speakable speakable = (Speakable) factory.getBean("Person");

		speakable.speak("Lesson one");
	}
}
