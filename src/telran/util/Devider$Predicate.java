package telran.util;

import java.util.function.Predicate;

public class Devider$Predicate implements Predicate<Integer> {

	@Override
	public boolean test(Integer t) {

		return t%4 ==0;
	}

}
