package chapter3.one;

public enum ProtectEnum {

	PUBLIC("public"),
	PROTECTED("protected"),
	PRIVATE("private");
	
	private String name;
	
	private ProtectEnum(String name) {
		// TODO Auto-generated constructor stub
		this.name = name();
	}
	
	public String getName(){
		return this.name;
	}
	
}
