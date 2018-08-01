package Game;

import java.util.HashMap;
import java.util.Scanner;

abstract class Game {

	private int maxRange;
	private int elementsNb;
	private int maxHint;
	private int hint;
	private String secretcode;
	private String opEntry;
	private Scanner entry;
	private boolean entryCheck;
	private HashMap<Integer, String> historic;

	public Game() {
		// TODO to modified after config.prpoperties set;
		this.maxRange = 0;
		this.elementsNb = 0;
		this.maxHint = 0;
		this.hint = 0;
		this.secretcode = null;
		this.opEntry = null;
		this.entry = new Scanner(System.in);
		this.entryCheck = true;
		this.historic = new HashMap<>();

		this.setMaxRange(9);// default value for test
		this.setElementsNb(4);// default value for test
		this.setMaxHint(4);// default value for test

	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		// TODO to modified after config.prpoperties set;
		// isOk = true;
		// while (isOk) {
		System.out.println("Set Max range(min=1 max=9): Fixed for test at 9");
		// maxRange = entry.nextInt();
		// isOk = (maxRange < 1 || maxRange > 9);
		// }
		this.maxRange = maxRange;
	}

	public int getElementsNb() {
		// TODO to throw out after config.prpoperties set;
		return elementsNb;
	}

	public void setElementsNb(int elementsNb) {
		// TODO to modified after config.prpoperties set;
		// isOk = true;
		// while (isOk) {
		System.out.println("Set elements number composing code(<= Max Range+1(Max range setted =" + maxRange
				+ ")): Fixed for test at 4");
		// elementsNb = entry.nextInt();
		// isOk = (elementsNb > maxRange + 1);
		// }
		this.elementsNb = elementsNb;
	}

	public int getHint() {
		return hint;
	}

	public void setHint(int hint) {
		this.hint = hint;
	}

	public int getMaxHint() {
		return maxHint;
	}

	public void setMaxHint(int maxHint) {
		System.out.println("Set maximum hint Fixed at 4 for test");
		this.maxHint = maxHint;

	}

	public void setOpEntry() {
		do {
			System.out.println("Proposal   ");
			opEntry = entry.next();
			if (!(opEntry.length() == elementsNb)) {
				System.out.println("Enter a proposal length equals to " + elementsNb + " , you have entered a "
						+ opEntry.length() + " Length proposal");
				entryCheck = true;
			} else
				entryCheck = false;
		} while (entryCheck);

	}

	public String getOpEntry() {
		return opEntry;
	}

	public HashMap<Integer, String> getHistoric() {
		historic.forEach((key, value) -> System.out.println("Proposition " + key + value));
		System.out.print("\n");
		return historic;
	}

	public void setHistoric(int key, String value) {
		this.historic.put(key, value);

	}

	public String getSecretcode() {
		return secretcode;
	}

	public void setSecretcode(String secretcode) {
		this.secretcode = secretcode;
	}

	public void check(int maxHint) {

		if (opEntry.equals(secretcode)) {
			System.out.println("\n!!!!!CONGRATULATION!!!!! \nYou win ");

		} else if (hint + 1 >= maxHint) {
			System.out.println("?????Sorry you loose????? \nMaximum hint of " + maxHint + " reached"
					+ "\nThe secret code was : " + secretcode + "\nBelow your proposal summary :");

		} else {
			System.out.println("\nSorry this is not the secret code " + (maxHint - (hint + 1)) + " hint(s) left");

		}
		hint++;

	}

	public abstract void comparison(String secretcode, String codeTocompare);// ne pas utilise Code code dans les
																				// parametre me permet d utiliser la
																				// comparaison en mode challenger
																				// defenseur et duel
}
