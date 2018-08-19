
package p3.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {

	private ArrayList<String> appHead = new ArrayList<>();
	private ArrayList<String> menuTitle = new ArrayList<>();
	private final String MENU = " Menu";
	private final String MAIN_ID = "Main ";
	private final String QUIT = "Quit";
	private HashMap<Integer, String> mainOptions = new HashMap<>();
	private HashMap<Integer, String> playOptions = new HashMap<>();
	private HashMap<Integer, String> ConfigurationOptions = new HashMap<>();
	private int opChoice = 0;

	public Menu() {
		// Application head default settings
		appHead.add("OpenClassRooms Projet 3: Mettez votre logique à l'épreuve.");
		appHead.add("Seak +/- and MasterMind Game");
		appHead.add("Author : Frederic Leroux");
		appHead.add("\n");
		// Application Main Menu default settings
		mainOptions.put(0, QUIT);
		mainOptions.put(1, "More/Less Game Challenger Mode");
		mainOptions.put(2, "Mastermind Game Challenger Mode");
		mainOptions.put(3, "More/Less Game Defender Mode");
		mainOptions.put(4, "Mastermind Game Defender Mode");
		mainOptions.put(5, "More/Less Game Duel Mode");
		mainOptions.put(6, "Mastermind Game Duel Mode");
		mainOptions.put(7, "Configuration");

	}

	public int getOpChoice() {
		return opChoice;
	}

	public void setOpChoice(int opChoice) {
		this.opChoice = opChoice;
	}

	public ArrayList<String> getAppHead() {
		return appHead;
	}

	public void setAppHead(ArrayList<String> appHead) {
		this.appHead = appHead;
	}

	public ArrayList<String> getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(ArrayList<String> menuTiltle) {
		this.menuTitle = menuTiltle;
	}

	public HashMap<Integer, String> getMainOptions() {
		return mainOptions;
	}

	public void setMainOptions(HashMap<Integer, String> mainOptions) {
		this.mainOptions = mainOptions;
	}

	public HashMap<Integer, String> getPlayOptions() {
		return playOptions;
	}

	public void setPlayOptions(HashMap<Integer, String> playOptions) {
		this.playOptions = playOptions;
	}

	public HashMap<Integer, String> getConfigurationOptions() {
		return ConfigurationOptions;
	}

	public void setConfigurationOptions(HashMap<Integer, String> configurationOptions) {
		ConfigurationOptions = configurationOptions;
	}

	public void arrayListDisplay(ArrayList<String> arrayList) {
		arrayList.forEach(string -> System.out.println(string));

	}

	public void hashMapDisplay(HashMap<Integer, String> hashMap) {
		hashMap.forEach((key, value) -> System.out.println(key + ": " + value));

	}

	public void mainMenu() {
		menuTitle.clear();
		menuTitle.add(MAIN_ID + MENU + "\n");
		arrayListDisplay(menuTitle);
		hashMapDisplay(mainOptions);
	}

	public void playMenu(int i, int choice) {
		menuTitle.clear();
		menuTitle.add("\n" + this.mainOptions.get(choice) + MENU + "\n");
		arrayListDisplay(menuTitle);
		this.playOptions.put(0, QUIT);
		this.playOptions.put(1, "Back to " + MAIN_ID);
		if (i == 0)
			this.playOptions.put(2, "Play  " + this.mainOptions.get(choice));
		if (i == 1)
			this.playOptions.put(2, "Replay  " + this.mainOptions.get(choice));
		if (i == 2)
			this.playOptions.put(2, "Change another parameter ");
		hashMapDisplay(this.playOptions);
	}

	public void configurationMenu(Configuration configuration) {
		menuTitle.clear();
		menuTitle.add("\nApplication Configuration" + MENU);
		menuTitle.add("Parameters list\n");
		arrayListDisplay(menuTitle);
		ConfigurationOptions.putAll(configuration.parametersList());
		ConfigurationOptions.put(ConfigurationOptions.size() + 1, "Back to " + MAIN_ID);
		ConfigurationOptions.put(ConfigurationOptions.size() + 1, QUIT);
		hashMapDisplay(this.ConfigurationOptions);
	}

	public void appQuit(int choice) {
		if (choice == 0)
			System.exit(1);

	}

	public boolean backToMain() {
		boolean backToMain = false;
		if (this.opChoice == 1)
			backToMain = true;
		return backToMain;
	}

	public boolean play() {
		boolean play = false;
		if (this.opChoice == 2)
			play = true;
		return play;
	}

	public void playMenuChoice(int choice) {
		this.opChoice = choice;
		appQuit(choice);
		backToMain();
		play();
	}

}