package p3.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

abstract class Game {

	private int maxHit;
	protected int minRange = 0;
	protected int maxRange = 0;
	protected int elementsNb = 0;
	protected int hit = 0;
	private String code = null;
	private String opCode = null;
	private String opProposal = null;
	protected String pcProposal = null;
	private String opEntry = null;
	private String gameAnswer = null;
	protected boolean basisVersion;
	protected boolean cheating;
	protected boolean duplicate;
	private boolean devmode;
	protected boolean autoMode;
	protected boolean challengerMode;
	protected boolean cheatTentative;
	private ArrayList<String> previousHit = new ArrayList<>();
	protected ArrayList<String> historicChallengerTbl = new ArrayList<>();
	protected ArrayList<String> historicDefenderTbl = new ArrayList<>();
	protected boolean duelMode = false;

	public Game() {

		this.setMinRange(0);
		this.setMaxRange(9);
		this.setElementsNb(4);
		this.setMaxHit(20);
		this.challengerMode = true;
		this.duplicate = false;
		this.autoMode = false;
		this.devmode = true;
		this.cheating = false;

	}

	public boolean isChallengerMode() {
		return challengerMode;
	}

	/**
	 * 
	 * @param mode
	 *            1= challenger mode // 2= defender mode
	 */
	public void setDuelMode(int mode) {
		if (mode == 1)
			this.duelMode = true;
		if (mode == 2)
			this.duelMode = false;
	}

	public boolean isDuelMode() {
		return this.duelMode;
	}

	/**
	 * 
	 * @param mode
	 *            1= challenger mode // 2= defender mode
	 */
	public void setChallengerMode(int mode) {
		if (mode == 1)
			this.challengerMode = true;
		if (mode == 2)
			this.challengerMode = false;
	}

	public boolean isDevmode() {
		return devmode;
	}

	public void setDevmode(int mode) {
		if (mode == 1)
			this.devmode = true;
		else
			this.devmode = false;
	}

	public boolean isAutoMode() {
		return autoMode;
	}

	public void setAutoMode(int mode) {
		if (mode == 1)
			this.autoMode = true;
		else
			this.autoMode = false;

	}

	public boolean isBasisVersion() {
		return basisVersion;
	}

	public void setVersion(int version) {
		if (version == 1) {
			this.basisVersion = true;
		} else
			this.basisVersion = false;
	}

	public boolean isCheating() {
		return cheating;
	}

	public boolean isCheatTentative() {
		return cheatTentative;
	}

	public void setCheatTentative(boolean cheatTentative) {
		this.cheatTentative = cheatTentative;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		System.out.println("Set Max range(min=1 max=9): Fixed for test at 9");
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int maxRange) {
		System.out.println("Set Min range(min=0 max=8): Fixed for test at 0");
		this.minRange = maxRange;
	}

	public String getCode() {
		return code;
	}

	public void setCode() {
		String codetoCheck = null;
		Code code = new Code(minRange, maxRange, elementsNb);
		if (challengerMode == true) {
			if (this.basisVersion == true) {
				do {
					Code basiscode = new Code(minRange, maxRange, elementsNb);
					codetoCheck = basiscode.toString();
					avoidDuplicate(codetoCheck, elementsNb);
				} while (this.duplicate == true);
				this.code = codetoCheck;
			} else {
				this.code = code.toString();
			}
		}
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getOpProposal() {
		return opProposal;
	}

	public void setOpProposal(String opProposal) {
		this.opProposal = opProposal;
	}

	public String getPcProposal() {
		return pcProposal;
	}

	/**
	 * @return <b>In challenger mode :</b> <i>
	 *         <li>Basis version : a random code without duplicate is generated.
	 *         <li>Variant version : a random code with duplicate is generated.</i>
	 *         <br>
	 *         <b>In defender mode : </b> <i>
	 *         <li>Basis and variant return null.</i>
	 * 
	 */
	public void setPcProposal(String pcCodeEntry) {
		this.pcProposal = pcCodeEntry;

	}

	public int getHit() {
		return hit;
	}

	public void setHit() {
		this.hit++;
	}

	public int getMaxHit() {
		return maxHit;
	}

	public void setMaxHit(int maxHit) {
		System.out.println("Set maximum hit Fixed at 20 for test");
		this.maxHit = maxHit;

	}

	public void avoidDuplicate(String string, int elementsNb) {
		HashSet<Character> codeTbl = new HashSet<>();
		for (int i = 0; i < elementsNb; i++) {
			codeTbl.add(string.charAt(i));
		}
		if (codeTbl.size() < elementsNb)
			this.duplicate = true;

		else
			this.duplicate = false;

	}

	public void AskOpProposition(String secretcode, String question) {
		ArrayList<String> askOpPrpoposition = new ArrayList<>();
		askOpPrpoposition.add("\nPlayer as Challenger");
		askOpPrpoposition.add(question);
		if (isDevmode() == true)
			askOpPrpoposition.add("(The Secret Code is : " + secretcode + ")");
		askOpPrpoposition.forEach(elmt -> System.out.println(elmt));
	}

	public void AskOpClues(String opCode, String pcProposition, String question) {
		ArrayList<String> askOpclues = new ArrayList<>();
		askOpclues.add("\nPlayer as Defender");
		askOpclues.add("Your secret code is : " + opCode);
		askOpclues.add("Pc proposition      : " + pcProposition);
		askOpclues.add(question);
		if (autoMode == false)
			askOpclues.forEach(elmt -> System.out.println(elmt));

	}

	// TODO ask to Herbert : Convention tell to have getter and setter however if i
	// don't use it do i have to ceate them i think yes never kno waht will be the
	// futur

	public ArrayList<String> getHistoricChallengerTbl() {
		return historicChallengerTbl;
	}

	public void setHistoricChallengerTbl(ArrayList<String> historicChallengerTbl) {
		this.historicChallengerTbl = historicChallengerTbl;
	}

	public ArrayList<String> getHistoricDefenderTbl() {
		return historicDefenderTbl;
	}

	public void setHistoricDefenderTbl(ArrayList<String> historicDefenderTbl) {
		this.historicDefenderTbl = historicDefenderTbl;
	}

	public ArrayList<String> getPreviousHit() {
		return previousHit;
	}

	public void setPreviousHit(String title) {
		ArrayList<String> previousHit = new ArrayList<>();

		if (challengerMode == true) {
			if (historicChallengerTbl.size() == 0)
				previousHit.clear();
			else {
				previousHit.add(title);
				previousHit.addAll(historicChallengerTbl);
			}

		} else {
			if (historicDefenderTbl.size() == 0)
				previousHit.clear();
			else {
				previousHit.add(title);
				previousHit.addAll(historicDefenderTbl);
			}

		}

		this.previousHit = previousHit;
	}

	public void Summary() {
		Conclusion();
		if (duelMode == false) {
			setPreviousHit("\nGame Summary");
			tableDisplay(getPreviousHit());
		} else {
			setChallengerMode(1);
			setPreviousHit("\nPlayer Gamme Summary");
			tableDisplay(getPreviousHit());
			setChallengerMode(2);
			setPreviousHit("\nPc Gamme Summary");
			tableDisplay(getPreviousHit());
		}

	}

	public void tableDisplay(ArrayList<String> tableToDisplay) {
		tableToDisplay.forEach(string -> System.out.println(string));
	}

	public String getGameAnswer() {
		return gameAnswer;
	}

	public void setGameAnswer() {

		if (challengerMode == true) {

			this.gameAnswer = "\nSorry this is not the secret code\n" + (maxHit - (hit))
					+ " tentative(s) remaining  on the " + maxHit + " initially attributed.\n";

		} else {

			this.gameAnswer = (maxHit - (hit)) + " tentative(s) left.\n";

		}

	}

	public void Conclusion() {
		ArrayList<String> summary = new ArrayList<>();
		String comment = null;
		if (cheating == true) {
			comment = "????!!Who cheat loose!!??? \nThree cheating tentatives";
		} else {
			if (duelMode == false) {
				if (this.challengerMode == true) {

					if (this.opProposal.equals(this.code)) {
						comment = "!!!!CONGRATULATION YOU WIN!!!!\nYou managed to find Pc secret code which was : "
								+ this.opProposal;
					} else {
						comment = "????SORRY YOU LOOSE???\nYou did not find Pc secret code which was : " + this.code;
					}
				}
				if (this.challengerMode == false) {

					if (this.pcProposal.equals(this.opCode)) {
						comment = "????SORRY YOU LOOSE???\nPc managed to find your secret code which was : "
								+ this.pcProposal;
					} else {
						comment = "!!!!CONGRATULATION YOU WIN!!!!\nPc did not find you secret code which was : "
								+ this.opCode;
					}
				}

			} else {
				if (this.opProposal.equals(this.code) && !this.pcProposal.equals(this.opCode)) {
					comment = "!!!!CONGRATULATION YOU WIN!!!!\nYou managed to find Pc secret code which was : "
							+ this.opProposal + "\nPc did not find you secret code which was : " + this.opCode;
				} else if (!this.opProposal.equals(this.code) && !this.pcProposal.equals(this.opCode)) {
					comment = "****DRAW GAME****\nYou did not find Pc secret code which was : " + this.code
							+ "\nPc did not find you secret code which was : " + this.opCode;
				} else if (this.opProposal.equals(this.code) && this.pcProposal.equals(this.opCode)) {
					comment = "****DRAW GAME****\nYou managed to find Pc secret code which was : " + this.opProposal
							+ "\nPc managed to find your secret code which was : " + this.pcProposal;
				} else {
					comment = "????SORRY YOU LOOSE???\nPc managed to find your secret code which was : "
							+ this.pcProposal + "\nYou did not find Pc secret code which was : " + this.code;
				}
			}

		}
		summary.add(comment);
		summary.add("GAME OVER");
		summary.forEach(elmt -> System.out.println(elmt));
	}

	public int getElementsNb() {
		return elementsNb;
	}

	public void setElementsNb(int elementsNb) {

		System.out.println("Set elements number composing code(<= Max Range+1(Max range setted =" + maxRange
				+ ")): Fixed for test at 4");
		this.elementsNb = elementsNb;
	}

	// -----------------------------------------------------------------------------------------------------------------------

	public String getOpEntry() {
		return opEntry;

	}

	public void setOpEntry(String opEntry) {

		this.opEntry = opEntry;

	}

	public int getRandom(int minValue, int maxValue) {
		Random random = new Random();
		int randomNb = 0;
		randomNb = random.nextInt(((maxValue - minValue) + 1)) + minValue;
		return randomNb;
	}

	public void entryCheckLength(String entry, int elementsNb) throws EntryException {
		// TODO Question is it better to add multiple try catch or only one try with
		// multiple catch
		if (entry == null)
			this.opEntry = entry;
		else {

			boolean pass = true;
			try {
				if (entry.length() != elementsNb)
					throw new EntryException(entry, 0);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opEntry = null;
			else
				this.opEntry = entry;
		}
	}

	public void entryIntegerCheck(String entry) throws EntryException {
		boolean pass = true;
		if (entry == null)
			this.opEntry = entry;
		else {
			boolean digitOnly = entry.matches("[0-9]{" + entry.length() + "}");
			try {
				if (digitOnly == false)
					throw new EntryException(entry, 2);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opEntry = null;
			else
				this.opEntry = entry;
		}
	}

	public void entryIntegerRangeCheck(String entry) throws EntryException {
		boolean pass = true;
		if (entry == null)
			this.opEntry = entry;
		else {
			boolean rangeCheck = entry.matches("[" + minRange + "-" + maxRange + "]{" + entry.length() + "}");
			try {
				if (rangeCheck == false)
					throw new EntryException(entry, 4);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opEntry = null;
			else
				this.opEntry = entry;
		}
	}

	public void entryDuplicateCheck(String entry) throws EntryException {
		boolean pass = true;
		if (entry == null)
			this.opEntry = entry;
		else {
			try {
				if (this.basisVersion == true)
					avoidDuplicate(entry, entry.length());
				if (duplicate == true)
					throw new EntryException(opEntry, 3);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opEntry = null;
			else
				this.opEntry = entry;
		}
	}

	public void entryContentsCheck(String entry, String contains) {
		// System.out.println(moreLess.getOpcodeEntry().matches("[[+][-][=]]+"));// n
		// est accepter que les string ave des +-=, - entre crochet car considerer come
		// la marque qui indique le range
		boolean pass = true;
		if (entry == null)
			this.opEntry = entry;
		else {
			StringBuilder regexSb = new StringBuilder();
			for (int i = 0; i < contains.length(); i++) {
				regexSb.append("[" + contains.charAt(i) + "]");
			}
			boolean containsOnly = entry.matches("[" + regexSb.toString() + "]{" + entry.length() + "}");
			try {
				if (containsOnly == false)
					throw new EntryException(entry, contains);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opEntry = null;
			else
				this.opEntry = entry;
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * @param Code
	 *            define the secret code to find generate by PC or by the Operator
	 * @param codeTocompare
	 *            define the code to compare generate by PC or by the Operator;
	 */
	public abstract void Comparison(String secretcode, String codeToCompare);

	public abstract void SecretCodeResearch(String pcEntry);

	public abstract void setHistoric(String code, int getHit);

}
