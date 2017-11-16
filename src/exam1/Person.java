package exam1;

public class Person implements Speakable{

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void speak(String message) {
		// TODO Auto-generated method stub
		System.out.println(this.name + " say:  " + message);
	}
	
}
