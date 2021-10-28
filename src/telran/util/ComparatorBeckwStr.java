package telran.util;

import java.util.Comparator;

public class ComparatorBeckwStr implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {	
		return o2.compareToIgnoreCase(o1);
	}

}
