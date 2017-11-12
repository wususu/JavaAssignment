package chapter1;

public class HelloWorld {

	protected Object newObject() {
		return new String("Hello World");
	}
	
	protected void printMethod(Object object) {
		System.out.println(object.toString());
	}
	
	public void printObject(){
		printMethod(newObject());
	}
	
	public static void main(String[] args) {
		new HelloWorld().printObject();
	}

}
