package exam1;

import java.util.LinkedHashSet;
import java.util.Set;

public class TestLinkedList {
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>();
		set.add("janke");
		set.add("wususu");
		set.add("London");
		for(Object element: set){
			System.out.println(element.toString().toLowerCase());
		}
	}
}
