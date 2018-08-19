
package p3.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class Game extends GameParameters {
	private static final Logger GAME_LOGGER = LogManager.getLogger(GameParameters.class.getName());
	protected int hit = 0;
	private String code = null;
	protected String opCode = null;
	protected String opProposal = null;
	protected String pcProposal = null;
	protected String opEntry = null;
	private String gameAnswer = null;
	protected boolean cheating;
	protected boolean duplicate;
	protected boolean challengerMode;
	protected boolean cheatTentative = false;
	private ArrayList<String> previousHit = new ArrayList<>();
	protected ArrayList<String> historicChallengerTbl = new ArrayList<>();
	protected ArrayList<String> historicDefenderTbl = new ArrayList<>();
	protected boolean duelMode = false;
	protected int maxHit = 0;
	protected int minRange = 0;
	private int minMinRange = 0;
	private int maxMinRange = 8;
	private int minMaxRange = 1;
	private int maxMaxRange = 9;
	private int[] booleanIndiceList = { 0, 5, 6 };
	private int[] IntegerIndiceList = { 1, 2, 3, 4 };
	protected int maxRange = 0;
	protected int elementsNb = 0;
	protected boolean devMode;
	protected boolean autoMode;
	protected boolean variantVersion;
	protected int cheatCount = 0;

	public Game() throws IOException, EntryException {
		GAME_LOGGER.trace("*****NEW GAME*****");
		checkParametersBooleanType(booleanIndiceList);
		checkParametersIntegerType(IntegerIndiceList);
		checkParamatersSpecificValue(1, 2, 3);

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
		if (mode == 0)
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
		if (mode == 0)
			this.challengerMode = false;
	}

	public boolean isDevmode() {
		return devMode;
	}

	public void setDevmode(boolean devMode) {
		this.devMode = devMode;
	}

	public boolean isAutoMode() {
		return autoMode;
	}

	public void setAutoMode(boolean autoMode) {
		this.autoMode = autoMode;
	}

	public boolean isVariantVersion() {
		return variantVersion;
	}

	public void setVariantVersion(boolean variantVersion) {
		this.variantVersion = variantVersion;
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
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int maxRange) {
		this.minRange = maxRange;
	}

	public String getCode() {
		return code;
	}

	public void setCode() {
		String codetoCheck = null;
		Code code = new Code(this.minRange, this.maxRange, this.elementsNb);
		if (challengerMode == true) {
			if (this.variantVersion == false) {
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
		return this.maxHit;
	}

	public void setMaxHit(int maxHit) {
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
		if (this.devMode == true)
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
			setChallengerMode(0);
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
			if (opProposal.equals(code))
				this.gameAnswer = "\n!!!!YOU MAY HAVE WIN!!!!\nIt seems that you have found the Pc secret code, lets check if Pc has found yours.\n";
			else
				this.gameAnswer = "\nSorry this is not the secret code\n" + (maxHit - (hit))
						+ " tentative(s) remaining  on the " + maxHit + " initially attributed.\n";

		} else {
			this.gameAnswer = (maxHit - (hit)) + "  tentative(s) remaining  on the " + maxHit
					+ " initially attributed.\n";
		}

	}

	public boolean codeEquivalenceCheck(String code, String codeToCompare) {
		boolean equal = false;
		boolean equality = code.equals(codeToCompare);
		if (equality == true)
			equal = true;
		return equal;

	}

	public boolean hitCheck() {
		boolean equal = false;
		if (this.hit == this.maxHit)
			equal = true;
		return equal;
	}

	public boolean gameStatu() {
		ArrayList<Boolean> gameStatuResults = new ArrayList<>();
		boolean end = false;
		boolean endItsTrue = false;
		if (this.cheating == true)
			end = true;
		else {
			gameStatuResults.add(hitCheck());
			if (duelMode == true) {
				gameStatuResults.add(codeEquivalenceCheck(this.opProposal, this.code));
				gameStatuResults.add(codeEquivalenceCheck(this.opCode, this.pcProposal));
			} else if (challengerMode == true)
				gameStatuResults.add(codeEquivalenceCheck(this.opProposal, this.code));
			else
				gameStatuResults.add(codeEquivalenceCheck(this.opCode, this.pcProposal));

			endItsTrue = gameStatuResults.contains(true);
			if (endItsTrue == true)
				end = true;
		}
		return end;

	}

	public void Conclusion() {
		ArrayList<String> summary = new ArrayList<>();
		String comment = null;
		if (cheating == true) {
			comment = "\n????!!Who cheat loose!!??? \nThree cheating tentatives";
		} else {
			if (duelMode == false) {
				if (this.challengerMode == true) {

					if (this.opProposal.equals(this.code)) {
						comment = "\n!!!!CONGRATULATION YOU WIN!!!!\nYou managed to find Pc secret code which was : "
								+ this.opProposal;
					} else {
						comment = "\n????SORRY YOU LOOSE???\nYou did not find Pc secret code which was : " + this.code;
					}
				}
				if (this.challengerMode == false) {

					if (this.pcProposal.equals(this.opCode)) {
						comment = "\n????SORRY YOU LOOSE???\nPc managed to find your secret code which was : "
								+ this.pcProposal;
					} else {
						comment = "\n!!!!CONGRATULATION YOU WIN!!!!\nPc did not find you secret code which was : "
								+ this.opCode;
					}
				}

			} else {
				if (this.opProposal.equals(this.code) && !this.pcProposal.equals(this.opCode)) {
					comment = "\n!!!!CONGRATULATION YOU WIN!!!!\nYou managed to find Pc secret code which was : "
							+ this.opProposal + "\nPc did not find you secret code which was : " + this.opCode;
				} else if (!this.opProposal.equals(this.code) && !this.pcProposal.equals(this.opCode)) {
					comment = "\n****DRAW GAME****\nYou did not find Pc secret code which was : " + this.code
							+ "\nPc did not find you secret code which was : " + this.opCode;
				} else if (this.opProposal.equals(this.code) && this.pcProposal.equals(this.opCode)) {
					comment = "\n****DRAW GAME****\nYou managed to find Pc secret code which was : " + this.opProposal
							+ "\nPc managed to find your secret code which was : " + this.pcProposal;
				} else {
					comment = "\n????SORRY YOU LOOSE???\nPc managed to find your secret code which was : "
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

	public boolean entryCheckLength(String entry, int elementsNb) throws EntryException {
		// TODO Question is it better to add multiple try catch or only one try with
		// multiple catch
		boolean pass = true;
		try {
			if (entry.length() != elementsNb)
				throw new EntryException(entry, 0);
		} catch (EntryException e) {
			pass = false;
		}
		return pass;
	}

	public boolean entryIntegerCheck(String entry) throws EntryException {
		boolean pass = true;

		boolean digitOnly = entry.matches("[0-9]{" + entry.length() + "}");
		try {
			if (digitOnly == false)
				throw new EntryException(entry, 2);
		} catch (EntryException e) {
			pass = false;
		}
		return pass;

	}

	public boolean entryIntegerRangeCheck(String entry) throws EntryException {
		boolean pass = true;
		boolean RangeCheck = entry.matches("[" + minRange + "-" + maxRange + "]{" + entry.length() + "}");
		try {
			if (RangeCheck == false)
				throw new EntryException(entry, 4);
		} catch (EntryException e) {
			pass = false;
		}
		return pass;
	}

	public boolean entryDuplicateCheck(String entry) throws EntryException {
		boolean pass = true;
		try {
			if (this.variantVersion == false)
				avoidDuplicate(entry, entry.length());
			if (duplicate == true)
				throw new EntryException(opEntry, 3);
		} catch (EntryException e) {
			pass = false;
		}
		return pass;
	}

	// TODO All entry check replace value by a booleanand change condition while in
	// main
	// value
	public boolean entryContentsCheck(String entry, String contains) throws EntryException {
		// System.out.println(moreLess.getOpcodeEntry().matches("[[+][-][=]]+"));// n
		// est accepter que les string ave des +-=, - entre crochet car considerer come
		// la marque qui indique le Range
		boolean pass = true;

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
		return pass;
	}

	public boolean opEntryCodeCheck() throws EntryException {
		ArrayList<Boolean> testresults = new ArrayList<>();

		boolean pass = true;
		testresults.add(entryCheckLength(this.opEntry, this.elementsNb));
		testresults.add(entryIntegerCheck(this.opEntry));
		if (this.challengerMode == false) {
			testresults.add(entryDuplicateCheck(this.opEntry));
			testresults.add(entryIntegerRangeCheck(this.opEntry));
		}

		boolean failed = testresults.contains(false);
		if (failed == true)
			pass = false;

		return pass;
	}

	public void setParametersInfo(int paramatersTableIndice) {
		setParameterKey(paramatersTableIndice);
		setParameterCurrentValue(paramatersTableIndice);
		setParameterDefaultValueValue(paramatersTableIndice);
	}

	public void checkParametersBooleanType(int[] indiceList) {
		// traceMethodLogger(0, "checkParametersBooleanType");
		for (int i = 0; i < indiceList.length; i++) {
			setParametersInfo(indiceList[i]);

			try {
				if (valueBooleanCheck(this.parameterCurrentValue) == false)
					throw new EntryException(this.parameterKey, parameterCurrentValue, 0);
			} catch (EntryException e0) {
				writeConfiguration(this.parameterKey, parameterDefaultValue);
			}
		}
		// traceMethodLogger(1, "checkParametersBooleanType");
	}

	public void checkParametersIntegerType(int[] indiceList) {

		for (int i = 0; i < indiceList.length; i++) {
			setParametersInfo(indiceList[i]);

			try {
				if (valueIntegerCheck(this.parameterCurrentValue) == false)
					throw new EntryException(this.parameterKey, parameterCurrentValue, 1);
			} catch (EntryException e1) {
				writeConfiguration(this.parameterKey, parameterDefaultValue);
			}
		}
	}

	public void checkParamatersSpecificValue(int minRangeIndice, int maxRangeIndice, int elementsNbIndice) {
		// TODO Find a better way to treat this specific case
		// load parameters to check validity
		setGameParameters();
		try {
			setParametersInfo(minRangeIndice);
			if (valueRangeCheck(parameterCurrentValue, this.minMinRange, this.maxMinRange) == false
					|| stringToInteger(this.parameterCurrentValue) >= this.maxRange)
				throw new EntryException(parameterKey, parameterCurrentValue, this.minMinRange, this.maxMinRange, 0);
		} catch (EntryException e2) {
			writeConfiguration(this.parameterKey, parameterDefaultValue);
		}
		try {
			setParametersInfo(maxRangeIndice);
			;
			if (valueRangeCheck(parameterCurrentValue, this.minMaxRange, this.maxMaxRange) == false
					|| stringToInteger(this.parameterCurrentValue) <= this.minRange)
				throw new EntryException(parameterKey, parameterCurrentValue, this.minMaxRange, this.maxMaxRange, 1);
		} catch (EntryException e3) {
			writeConfiguration(this.parameterKey, this.parameterDefaultValue);
		}
		try {
			setParametersInfo(elementsNbIndice);
			if (stringToInteger(parameterCurrentValue) > this.maxRange && this.isVariantVersion() == false)
				throw new EntryException(parameterKey, parameterCurrentValue, 2);
		} catch (EntryException e4) {
			setParametersInfo(0);
			writeConfiguration(this.parameterKey, "true");
		}
		// after check load correction if necessary
		setGameParameters();
	}

	public void setGameParameters() {

		setVariantVersion(booleanConverter(getProperty(parametersTxtKeys.get(0))));
		setMinRange(stringToInteger(getProperty(parametersTxtKeys.get(1))));
		setMaxRange(stringToInteger(getProperty(parametersTxtKeys.get(2))));
		setElementsNb(stringToInteger(getProperty(parametersTxtKeys.get(3))));
		setMaxHit(stringToInteger(getProperty(parametersTxtKeys.get(4))));
		setDevmode(booleanConverter(getProperty(parametersTxtKeys.get(5))));
		setAutoMode(booleanConverter(getProperty(parametersTxtKeys.get(6))));

	}

	public String intToString(int i) {
		String intToString = String.valueOf(i);
		return intToString;
	}

	public String cheatWarning() {

		ArrayList<String> number = new ArrayList<>();
		number.add("First");
		number.add("Second");
		String warning = number.get(this.cheatCount - 1)
				+ " Cheating tentative detection or entry error, the third will be the last\n";

		return warning;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * @param Code
	 *            define the secret code to find generate by PC or by the Operator
	 * @param codeTocompare
	 *            define the code to compare generate by PC or by the Operator;
	 */

	public abstract void comparison(String secretCode, String codeToCompare);

	public abstract void secretCodeResearch(String pcProposal);

	public abstract boolean cheatTentative(String code, String codeToCompare);

	public abstract void setHistoric(String code, int getHit);

}