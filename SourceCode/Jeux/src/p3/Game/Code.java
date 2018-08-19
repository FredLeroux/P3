package p3.Game;

import java.util.ArrayList;
import java.util.Random;

public class Code extends ArrayList<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int getInt = 0;
	private final StringBuilder CODE = new StringBuilder();
	private final Random RANDOM = new Random();

	public Code(int minRange, int maxRange, int elementsNb) {
		while (this.size() < elementsNb) {
			getInt = RANDOM.nextInt((maxRange - minRange + 1) + minRange);//
			this.add(getInt);
		}
		this.forEach(nb -> CODE.append(nb));

	}

	public String toString() {
		return CODE.toString();
	}

}
