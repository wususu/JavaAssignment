package chapter3.one;

/**
 * 属性实体
 * @author janke
 *
 */
public class Field {
	
	private Class<?> clazz;

	private String protect;
	
	private String type;
	
	private String name;
	
	private Object value;
	
	public Field() {
		// TODO Auto-generated constructor stub
	}

	public Field(String protect, String type, String name) {
		super();
		this.clazz =clazz;
		this.protect = protect;
		this.type = type;
		this.name = name;
	}

	public String getProtect() {
		return protect;
	}

	public void setProtect(String protect) {
		this.protect = protect;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Field [protect=" + protect + ", type=" + type + ", Name=" + name + ", value=" + value + "]";
	}
	
}
