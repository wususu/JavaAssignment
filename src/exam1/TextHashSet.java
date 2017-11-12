package exam1;

import java.util.HashSet;
import java.util.Set;

public class TextHashSet {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add("London");
		set.add("janke");
		set.add("wususu");
		for (String string : set) {
			System.out.println(string);
		}
	}
}
