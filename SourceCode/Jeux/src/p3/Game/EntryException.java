
package p3.Game;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String retry = "\nPlease retry.\n";
	private final String retry1 = "\nThe parameter(s) value(s) was set to default.\n";
	private static final Logger LOGGER = LogManager.getLogger(EntryException.class.getName());

	public EntryException() {
		System.out.println("\nENTRY EXCEPTION\n");
	}

	public EntryException(Throwable exception) {
		System.out.println("System Error :" + exception);
		System.out.println("Config.properties File created");
		System.out.println("Default values :");

	}

	public EntryException(String str, int i) {
		if (i == 0)
			System.out.println("\nENTRY LENGTH FORMAT EXCEPTION\n\nThis ENTRY : " + str
					+ "\nIs not composed of the elements number set" + retry);
		else if (i == 1)
			System.out.println("\nENTRY CLUE(S)ERROR EXCEPTION\nIt seem's that you've made a mistake "
					+ "\nThe correct clue is : " + str + "\nYour clue(s) were set as null, please retry.\n");
		else if (i == 2)
			System.out.println(
					"\nENTRY NUMBER FORMAT EXCEPTION\nThis entry : " + str + " is not fully an integer" + retry);
		else if (i == 3)
			System.out.println("\nENTRY DUPLICATE FORMAT EXCEPTION\nThis entry : " + str
					+ " contains duplicate which are not allowed in basis version " + retry);
		else if (i == 4)
			System.out
					.println("\nENTRY RANGE EXCEPTION\nThis entry : " + str + " is not within the set range " + retry);
		else if (i == 5)
			System.out.println("\nCONFIG.PROPERTIES EXCEPTION\n" + str + "\nCorrection done" + retry1);
		else if (i == 6)
			LOGGER.fatal(
					"FATAL ERROR\nMastermind defender mode need a code list initiated and not Empty, application close");

	}

	public EntryException(String str, String contains) {
		System.out.println("\nENTRY CONTENTS FORMAT EXCEPTION\n\nThis ENTRY : " + str + "\nIs not only composed of "
				+ contains + retry);
	}

	public EntryException(int cheatCount) {
		ArrayList<String> number = new ArrayList<>();
		number.add("First");
		number.add("Second");

		System.out.println(number.get(cheatCount - 1)
				+ " Cheating tentative detection or entry error, the third will be the last\n");

	}

	public EntryException(int i, int j, int entryExceptionNumber) {
		if (entryExceptionNumber == 0)
			System.out.println("ENTRY ERROR\nThis options number :" + i + " is unknown" + retry);

	}

	public EntryException(String parameterName, String parameterValueRead, int minparameter, int maxparameter,
			int entryExceptionNumber) {
		if (entryExceptionNumber == 0) {
			System.out.println("\nENTRY RANGE EXCEPTION\nParameter: " + parameterName + " : " + parameterValueRead
					+ "\nIs not within the range " + minparameter + "-" + maxparameter
					+ "\nAnd/Or superior or equal than maximum range value" + retry1);
		} else if (entryExceptionNumber == 1) {
			System.out.println("\nENTRY RANGE EXCEPTION\nParameter: " + parameterName + " : " + parameterValueRead
					+ "\nIs not within the range " + minparameter + "-" + maxparameter
					+ "\nAnd/Or inferior or equal than minimum range value" + retry1);
		}

	}

	public EntryException(String parametersName, String parameterValueRead, int entryExceptionNumber) {
		if (entryExceptionNumber == 0)
			LOGGER.error("\nENTRY BOOLEAN VALUE EXCEPTION\nParameter: " + parametersName
					+ " accepte only true or false value\nThe value : " + parameterValueRead + " is not accepted"
					+ retry1);
		else if (entryExceptionNumber == 1)
			LOGGER.debug("\nENTRY NUMBER FORMAT EXCEPTION\nParameter: " + parametersName
					+ " accepte only integer value \nThe value : " + parameterValueRead + " is not accepted" + retry1);
		else if (entryExceptionNumber == 2)
			System.out.println("\nENTRY VALUE EXCEPTION\nIn Basis version parameter: " + parametersName
					+ " cannot be set at : " + parameterValueRead
					+ "\nThis value is superior to Digits Maximum Range\nGame was set to variant version");
	}
}
