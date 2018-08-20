
package p3.Configuration;
/**
 * This class create the menu and allow to loop 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Exception.EntryException;
import p3.Game.GameParameters;

public abstract class P3Menu extends Configuration {
	// Variables declaration
	private ArrayList<String> appHead = new ArrayList<>();
	private ArrayList<String> menuTitle = new ArrayList<>();
	private final String MENU = " Menu";
	private final String MAIN_ID = "Main ";
	private final String QUIT = "Quit";
	private HashMap<Integer, String> mainOptions = new HashMap<>();
	private HashMap<Integer, String> playOptions = new HashMap<>();
	private HashMap<Integer, String> ConfigurationOptions = new HashMap<>();
	private int opChoice = 0;
	private static final Logger MENU_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables instantiation
	public P3Menu() throws IOException, EntryException {
		// Application head default settings
		this.appHead.add("OpenClassRooms Projet 3: Mettez votre logique à l'épreuve.");
		this.appHead.add("Seak +/- and MasterMind Game");
		this.appHead.add("Author : Frederic Leroux");
		this.appHead.add("\n");
		// Application Main Menu default settings
		this.mainOptions.put(0, QUIT);
		this.mainOptions.put(1, "More/Less Game Challenger Mode");
		this.mainOptions.put(2, "Mastermind Game Challenger Mode");
		this.mainOptions.put(3, "More/Less Game Defender Mode");
		this.mainOptions.put(4, "Mastermind Game Defender Mode");
		this.mainOptions.put(5, "More/Less Game Duel Mode");
		this.mainOptions.put(6, "Mastermind Game Duel Mode");
		this.mainOptions.put(7, "Configuration");
		this.mainOptions.put(8, "Help and Rules");

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables Getter an Setter
	public int getOpChoice() {
		return this.opChoice;
	}

	public void setOpChoice(String opChoice) throws EntryException {
		int opchoiceint = stringToInteger(opChoice);
		this.opChoice = opchoiceint;
	}

	public ArrayList<String> getAppHead() {
		return this.appHead;
	}

	public void setAppHead(ArrayList<String> appHead) {
		this.appHead = appHead;
	}

	public ArrayList<String> getMenuTitle() {
		return this.menuTitle;
	}

	public void setMenuTitle(ArrayList<String> menuTiltle) {
		this.menuTitle = menuTiltle;
	}

	public HashMap<Integer, String> getMainOptions() {
		return this.mainOptions;
	}

	public void setMainOptions(HashMap<Integer, String> mainOptions) {
		this.mainOptions = mainOptions;
	}

	public HashMap<Integer, String> getPlayOptions() {
		return this.playOptions;
	}

	public void setPlayOptions(HashMap<Integer, String> playOptions) {
		this.playOptions = playOptions;
	}

	public HashMap<Integer, String> getConfigurationOptions() {
		return this.ConfigurationOptions;
	}

	public void setConfigurationOptions(HashMap<Integer, String> configurationOptions) {
		this.ConfigurationOptions = configurationOptions;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			MENU_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			MENU_LOGGER.trace("Out of method " + method);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation
	/**
	 * display an ArrayList on console
	 * 
	 * @param arrayList
	 */
	public void arrayListDisplay(ArrayList<String> arrayList) {
		traceMethodLogger(0, "arrayListDisplay");
		arrayList.forEach(string -> System.out.println(string));
		traceMethodLogger(1, "arrayListDisplay");
	}

	/**
	 * display an Hashmap on console
	 * 
	 * @param arrayList
	 */

	public void hashMapDisplay(HashMap<Integer, String> hashMap) {
		traceMethodLogger(0, "hashMapDisplay");
		hashMap.forEach((key, value) -> System.out.println(key + ": " + value));
		traceMethodLogger(1, "hashMapDisplay");
	}

	/**
	 * This method create the main menu
	 * <li>and display
	 */
	public void mainMenu() {
		traceMethodLogger(0, "mainMenu");
		this.menuTitle.clear();
		this.menuTitle.add(MAIN_ID + MENU + "\n");
		arrayListDisplay(this.menuTitle);
		hashMapDisplay(this.mainOptions);
		traceMethodLogger(1, "mainMenu");
	}

	/**
	 * This method create the submenu in function of the needs
	 * <li>add the quit and main menu to the options
	 * <li>0- play submenu
	 * <li>1- replay submenu
	 * <li>2- config submenu
	 * <li>3- rules and help submenu
	 * <li>and display
	 */
	public void subMenu(int i, String choice) throws EntryException {
		traceMethodLogger(0, "subMenu");
		int choiceint = stringToInteger(choice);
		this.menuTitle.clear();
		this.menuTitle.add("\n" + this.mainOptions.get(choiceint) + MENU + "\n");
		arrayListDisplay(menuTitle);
		this.playOptions.put(0, QUIT);
		this.playOptions.put(1, "Back to " + MAIN_ID);
		if (i == 0)
			this.playOptions.put(2, "Play  " + this.mainOptions.get(choiceint));
		if (i == 1)
			this.playOptions.put(2, "Replay  " + this.mainOptions.get(choiceint));
		if (i == 2)
			this.playOptions.put(2, "Change another parameter ");
		if (i == 3)
			this.playOptions.put(2, "Display Help and Rules ");
		hashMapDisplay(this.playOptions);
		traceMethodLogger(1, "subMenu");
	}

	/**
	 * this method load the configuration parameters as a menu
	 * <li>add quit, load default and back to main options
	 * <li>and display
	 * 
	 * @param configuration(class)
	 */

	public void configurationMenu(Configuration configuration) {
		traceMethodLogger(0, "configurationMenu");
		this.menuTitle.clear();
		this.menuTitle.add("\nApplication Configuration" + MENU);
		this.menuTitle.add("Parameters list\n");
		arrayListDisplay(this.menuTitle);
		if (!this.ConfigurationOptions.containsValue(QUIT)) {
			this.ConfigurationOptions.putAll(configuration.parametersList());
			this.ConfigurationOptions.put(ConfigurationOptions.size() + 1, "Load Default Setup");
			this.ConfigurationOptions.put(ConfigurationOptions.size() + 1, "Back to " + MAIN_ID);
			this.ConfigurationOptions.put(0, QUIT);
		}
		hashMapDisplay(this.ConfigurationOptions);
		traceMethodLogger(1, "configurationMenu");
	}

	/**
	 * allow to quit the application if choice= 0
	 * 
	 * @param choice
	 * @throws EntryException
	 */
	public void appQuit(String choice) throws EntryException {
		traceMethodLogger(0, "appQuit");
		int choiceint = stringToInteger(choice);
		if (choiceint == 0) {
			MENU_LOGGER.trace("****App close****");
			traceMethodLogger(1, "appQuit");
			System.exit(1);
		}

	}

	/**
	 * 
	 * @return true if this.opChoice = 1
	 */
	public boolean backToMain() {
		traceMethodLogger(0, "backToMain");
		boolean backToMain = false;
		if (this.opChoice == 1)
			backToMain = true;
		traceMethodLogger(1, "backToMain");
		return backToMain;
	}

	/**
	 * 
	 * @return true if this.opChoice = 2
	 */
	public boolean play() {
		traceMethodLogger(0, "play");
		boolean play = false;
		if (this.opChoice == 2)
			play = true;
		traceMethodLogger(1, "play");
		return play;
	}

	/**
	 * This method allow loop on main, play game in function o choice
	 * <li>This method allow to Quit
	 * <li>Centralized play, back to main and quit method
	 * 
	 * @param choice
	 * @throws EntryException
	 */

	public void playMenuChoice(String choice) throws EntryException {
		traceMethodLogger(0, "playMenuChoice");
		setOpChoice(choice);
		appQuit(choice);
		backToMain();
		play();
		traceMethodLogger(1, "playMenuChoice");
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Check Methods Implementation
	/**
	 * 
	 * @param option
	 * @param optionsNumber
	 * @return true true if choice is an integer and if it is within 0 and the
	 *         maximum option number = optionNumberAvaiable
	 */
	public boolean optionNumberCheck(int option, int optionsNumber) {
		traceMethodLogger(0, "optionNumberCheck");
		boolean pass = true;
		try {
			if (option < 0 || option > optionsNumber)
				throw new EntryException(option, optionsNumber, 0);
		} catch (EntryException e) {
			pass = false;
		}
		traceMethodLogger(1, "optionNumberCheck");
		return pass;
	}

	/**
	 * 
	 * @param choice
	 * @param optionNumberAvaiable
	 * @return true if choice is an integer and if it is within 0 and the maximum
	 *         option number = optionNumberAvaiable
	 * @throws EntryException
	 */

	public boolean optionChooseCheck(String choice, int optionNumberAvaiable) throws EntryException {
		ArrayList<Boolean> testresults = new ArrayList<>();
		int choiceint = stringToInteger(choice);
		boolean pass = true;
		testresults.add(optionNumberCheck(choiceint, optionNumberAvaiable));
		boolean failed = testresults.contains(false);
		if (failed == true)
			pass = false;
		return pass;
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End
}