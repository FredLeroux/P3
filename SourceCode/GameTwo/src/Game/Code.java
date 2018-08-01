package Game;

import java.util.LinkedHashSet;
import java.util.Random;

public class Code extends LinkedHashSet<Integer> {

	private int getInt;
	private StringBuilder code;
	private Random RANDOM;

	/**
	 * @param maxRange
	 *            is the maximum value for each code elements.
	 * @param elementsNb
	 *            is the elements number composing the code.
	 * @param getint
	 *            is a random number used as element to compose the code.
	 * @throws Exceptions
	 */

	public Code(int maxRange, int elementsNb) {

		this.code = new StringBuilder();
		this.RANDOM = new Random();
		this.getInt = 0;
		
		// TODO add variable type check once config.prpoperties is set

		if (maxRange < 1 || maxRange > 9) {
			throw new RuntimeException("Maximum range value has to be between 1 and 9");
		}
		if (elementsNb < 1) {
			throw new RuntimeException("Elements number can not be less than 1");
		}
		while (this.size() < elementsNb) {//
			getInt = RANDOM.nextInt(maxRange + 1);//
			this.add(getInt);
		}
		this.forEach(nb -> code.append(nb));
		// if (maxRange == (int)maxRange ) {
		// throw new RuntimeException ("Maximum range value has to be between 1 and 9");
		// }
		// if (elementsNb == (int)elementsNb ) {
		// throw new RuntimeException ("Maximum range value has to be between 1 and 9");
		// }

	}

	public String toString() {
		return code.toString();
	}

}
