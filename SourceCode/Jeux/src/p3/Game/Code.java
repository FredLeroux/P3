package p3.Game;
/**
 * the project heart 
 * <li> create a random number function of min and max range for each elements and composing of elements set
 */

import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Code extends ArrayList<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables declaration
	private int getInt = 0;
	private final StringBuilder CODE = new StringBuilder();
	private final Random RANDOM = new Random();
	private static final Logger CODE_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			CODE_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			CODE_LOGGER.trace("Out of method " + method);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation
	/**
	 * @return This method return a random number in a StringBuilder
	 * @param minRange
	 *            of each elements
	 * @param maxRange
	 *            of each elements
	 * @param elementsNb
	 *            element number composing the code
	 */
	public Code(int minRange, int maxRange, int elementsNb) {
		traceMethodLogger(0, "Code");
		while (this.size() < elementsNb) {
			getInt = RANDOM.nextInt((maxRange - minRange + 1) + minRange);//
			this.add(getInt);
		}
		this.forEach(nb -> CODE.append(nb));
		traceMethodLogger(1, "Code");
	}

	/**
	 * @return the StringBuilder Code into String
	 */
	public String toString() {
		traceMethodLogger(0, "toString");
		traceMethodLogger(1, "toString");
		return CODE.toString();
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End
}
