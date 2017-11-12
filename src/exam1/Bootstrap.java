package exam1;

public class Bootstrap {
	static{
		System.out.println("Start:");
	}
public static void main(String[] args) {
	ClassLoader loader = Bootstrap.class.getClassLoader();
	System.out.println(loader);
	System.out.println(loader.getParent());
	System.out.println(loader.getParent().getParent());
	Object bb;
}
}
