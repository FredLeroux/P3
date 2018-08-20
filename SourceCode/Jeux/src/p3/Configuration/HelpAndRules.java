package p3.Configuration;
/**
 * This class give the rules and explain the parameters
 */

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Game.GameParameters;

public class HelpAndRules {
	// Variables declaration
	private final static String SEPARATOR = "\n*************************";
	private final static String TITLE_BORDER = "________________________\n";
	private ArrayList<String> helpAndRules = new ArrayList<>();
	private static final Logger HELP_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			HELP_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			HELP_LOGGER.trace("Out of method " + method);
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation

	/**
	 * Give a table containing rules and explanations on parameters
	 */
	public HelpAndRules() {
		traceMethodLogger(0, "HelpAndRules");
		this.helpAndRules.add(TITLE_BORDER);
		this.helpAndRules.add("Rules");
		this.helpAndRules.add(TITLE_BORDER);
		this.helpAndRules.add("	More/Less and Mastermind");
		this.helpAndRules.add("Aim : Challenger has to find a secret code using clues provided by Defender ");
		this.helpAndRules.add("Player can be Challenger, Defender, or both ");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add("	More/Less Challenger mode");
		this.helpAndRules.add("-Application as Defender will randomly generate a secret code ");
		this.helpAndRules.add("-Player as Challenger will provide is first proposition ");
		this.helpAndRules.add("-Application as Defender will give clues using +,-,= indication ");
		this.helpAndRules.add("-Player as Challenger will provide a new proposition based on the clues ");
		this.helpAndRules.add(
				"-And so on untill Challenger find the secret code or that the maximum hit number allowed is reach ");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add("	More/Less Defender mode");
		this.helpAndRules.add("-Player as Defender will give is secret code");
		this.helpAndRules.add("-Application as Challenger will provide is first proposition ");
		this.helpAndRules.add("-Player as Defender will give clues using +,-,= indication ");
		this.helpAndRules.add("-Applicationr as Challenger will provide a new proposition based on the clues ");
		this.helpAndRules.add(
				"-And so on untill Challenger find the secret code or that the maximum hit number allowed is reach ");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add("	Mastermind Challenger mode");
		this.helpAndRules.add("-Application as Defender will randomly generate a secret code ");
		this.helpAndRules.add("-Player as Challenger will provide is first proposition ");
		this.helpAndRules.add(
				"-Application as Defender will give clues indicating elements rigth placed number and the present element number  ");
		this.helpAndRules
				.add("****example secret code is 1234 proposition is 4531 the clue is 1 rigth place, 2 presents****");
		this.helpAndRules.add("-Player as Challenger will provide a new proposition based on the clues ");
		this.helpAndRules.add(
				"-And so on untill Challenger find the secret code or that the maximum hit number allowed is reach ");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add("	More/Less Defender mode");
		this.helpAndRules.add("-Player as Defender will give is secret code");
		this.helpAndRules.add("-Application as Challenger will provide is first proposition ");
		this.helpAndRules.add(
				"-Player as Defender wwill give clues indicating elements rigth placed number and the present element number  ");
		this.helpAndRules
				.add("****example secret code is 1234 proposition is 4531 the clue is 1 rigth place, 2 presents****");
		this.helpAndRules.add("-Applicationr as Challenger will provide a new proposition based on the clues ");
		this.helpAndRules.add(
				"-And so on untill Challenger find the secret code or that the maximum hit number allowed is reach ");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add(TITLE_BORDER);
		this.helpAndRules.add("Parameters");
		this.helpAndRules.add(TITLE_BORDER);
		this.helpAndRules.add(
				"Version : called in application as Variant Version, in variant version duplicate are allowed in the secret code");
		this.helpAndRules.add("Version default value : false");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add(
				"The range minimum value of an element composing the secret code called in application as Digits Minimum Range");
		this.helpAndRules.add(
				"Digits Minimum Range default value : 0\n(8 is the maximun value and 0 is the minimum value for this parameter");
		this.helpAndRules.add(
				"****example if Digits Minimum Range is 5 the secret code will be only composed of value  superior than 5****");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add(
				"The range maximum value of an element composing the secret code called in application as Digits Maximum Range");
		this.helpAndRules.add(
				"Digits Maximum Range default value : 9\n(9 is the maximun value and 1 is the minimum value for this parameter)");
		this.helpAndRules.add(
				"****example if Digits Maximun Range is 5 the secret code will be only composed of number inferior than 5****");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add(
				"Note: as Digits Minimum Range and Digits Maximun Range are linked and define the number range \nMinimun cannot be superior than maximum and maximum cannot be inferior than minimum");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add(
				"Element number composing the secret code called in application  Element(s) Number Composing Secret Code is as is name indicated the number of elements composing the secret code");
		this.helpAndRules.add("Element(s) Number Composing Secret Code default value : 4");
		this.helpAndRules.add(
				"Note: In basis mode elements composing the secret code can have the value 1 to 9 , beyond application will set the version to variant");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add("Developper Mode will in Challlenger mode display the secret code");
		this.helpAndRules.add("Developper Mode default value = false");
		this.helpAndRules.add(SEPARATOR);
		this.helpAndRules.add("Defender Auto Mode will in Defender mode let the application give the clues");
		this.helpAndRules.add(SEPARATOR);
		traceMethodLogger(1, "HelpAndRules");
	}

	/**
	 * Display the rules and help
	 */
	public void helpAndRulesDisplay() {
		traceMethodLogger(0, "helpAndRulesDisplay");
		this.helpAndRules.forEach(string -> System.out.println(string));
		traceMethodLogger(0, "helpAndRulesDisplay");
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End
}