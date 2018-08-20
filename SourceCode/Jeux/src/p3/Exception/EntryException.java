
package p3.Exception;
/**
 * This class centralized all exception messages 
 * All kind of Exception have different parameters and IDNumber
 */

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
		LOGGER.debug("\nENTRY EXCEPTION\n");
	}

	public EntryException(Throwable exception) {
		LOGGER.error("System Error :" + exception + "\nConfig.properties File created \nDefault values :");

	}

	/**
	 * <li>0 = length exception
	 * <li>1 = Clues error exception
	 * <li>2 = Number format exception
	 * <li>3 = Duplicate exception
	 * <li>4 = Range exception
	 * <li>5 = Config.properties damaged exception
	 * <li>6 = Fatal code list abs exception
	 * 
	 * @param str
	 * @param i
	 *            the EntryException ID
	 */
	public EntryException(String str, int i) {
		switch (i) {
		case 0:
			LOGGER.debug("\nENTRY LENGTH FORMAT EXCEPTION\n\nThis ENTRY : " + str
					+ "\nIs not composed of the elements number set" + retry);
			break;
		case 1:
			LOGGER.debug("\nENTRY CLUE(S)ERROR EXCEPTION\nIt seem's that you've made a mistake "
					+ "\nThe correct clue is : " + str + "\nYour clue(s) were set as null, please retry.\n");
			break;
		case 2:
			LOGGER.debug("\nENTRY NUMBER FORMAT EXCEPTION\nThis entry : " + str + " is not fully an integer" + retry);
			break;
		case 3:
			LOGGER.debug("\nENTRY DUPLICATE FORMAT EXCEPTION\nThis entry : " + str
					+ " contains duplicate which are not allowed in basis version " + retry);
			break;
		case 4:
			LOGGER.debug("\nENTRY RANGE EXCEPTION\nThis entry : " + str + " is not within the set range " + retry);
			break;
		case 5:
			LOGGER.debug("\nCONFIG.PROPERTIES EXCEPTION\n" + str + "\nCorrection done" + retry1);
			break;
		case 6:
			LOGGER.fatal(
					"FATAL ERROR\nMastermind defender mode need a code list initiated and not Empty, application close");
			break;

		}
	}

	/**
	 * Format exception
	 * 
	 * @param str
	 * @param contains
	 */
	public EntryException(String str, String contains) {
		LOGGER.debug("\nENTRY CONTENTS FORMAT EXCEPTION\n\nThis ENTRY : " + str + "\nIs not only composed of "
				+ contains + retry);
	}

	/**
	 * This exception is for option selection
	 * <li>0 = unknown
	 * <li>1 = not exist
	 * 
	 * @param i
	 * @param j
	 * @param entryExceptionNumber
	 */
	public EntryException(int i, int j, int entryExceptionNumber) {
		if (entryExceptionNumber == 0 && i > 0)
			LOGGER.debug("ENTRY ERROR\nThis options number :" + i + " is unknown" + retry);
		else if (i < 0)
			LOGGER.debug("ENTRY ERROR\nThis options do not exist " + retry);
	}

	/**
	 * Range exception
	 * <li>0=superior than range
	 * <li>1=inferior than range
	 * 
	 * @param parameterName
	 * @param parameterValueRead
	 * @param minparameter
	 * @param maxparameter
	 * @param entryExceptionNumber
	 */
	public EntryException(String parameterName, String parameterValueRead, int minparameter, int maxparameter,
			int entryExceptionNumber) {
		if (entryExceptionNumber == 0) {
			LOGGER.debug("\nENTRY RANGE EXCEPTION\nParameter: " + parameterName + " : " + parameterValueRead
					+ "\nIs not within the range " + minparameter + "-" + maxparameter
					+ "\nAnd/Or superior or equal than maximum range value" + retry1);
		} else if (entryExceptionNumber == 1) {
			LOGGER.debug("\nENTRY RANGE EXCEPTION\nParameter: " + parameterName + " : " + parameterValueRead
					+ "\nIs not within the range " + minparameter + "-" + maxparameter
					+ "\nAnd/Or inferior or equal than minimum range value" + retry1);
		}

	}

	/**
	 * Parameters check
	 * <li>1 = Boolean
	 * <li>2 = Number Format
	 * <li>3 =version
	 * 
	 * @param parametersName
	 * @param parameterValueRead
	 * @param entryExceptionNumber
	 */
	public EntryException(String parametersName, String parameterValueRead, int entryExceptionNumber) {
		if (entryExceptionNumber == 0)
			LOGGER.error("\nENTRY BOOLEAN VALUE EXCEPTION\nParameter: " + parametersName
					+ " accepte only true or false value\nThe value : " + parameterValueRead + " is not accepted"
					+ retry1);
		else if (entryExceptionNumber == 1)
			LOGGER.debug("\nENTRY NUMBER FORMAT EXCEPTION\nParameter: " + parametersName
					+ " accepte only integer value \nThe value : " + parameterValueRead + " is not accepted" + retry1);
		else if (entryExceptionNumber == 2)
			LOGGER.debug("\nENTRY VALUE EXCEPTION\nIn Basis version parameter: " + parametersName
					+ " cannot be set at : " + parameterValueRead
					+ "\nThis value is superior to Digits Maximum Range\nGame was set to variant version");
	}
}
