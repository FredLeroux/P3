
package p3.Game;
/**
 * Class Game extending GameParameters
 * <li> This class put in place all the necessary variables to allow  a specific construction for each game type(moreless mastermind).
 * <li> Provide too the check of all entry provide by player and parameters from config.file
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Exception.EntryException;

//Variables declaration
abstract class Game extends GameParameters {
	private ArrayList<String> previousHit = new ArrayList<>();
	private int minMinRange = 0;
	private int maxMinRange = 8;
	private int minMaxRange = 1;
	private int maxMaxRange = 9;
	private int[] booleanIndiceList = { 0, 5 };
	private int[] IntegerIndiceList = { 1, 2, 3, 4 };
	private String code = null;
	private String gameAnswer = null;
	protected ArrayList<String> historicChallengerTbl = new ArrayList<>();
	protected ArrayList<String> historicDefenderTbl = new ArrayList<>();
	protected boolean duelMode = false;
	protected boolean cheating = false;
	protected boolean duplicate = false;
	protected boolean challengerMode = true;
	protected boolean cheatTentative = false;

	protected boolean autoMode;
	protected boolean variantVersion;
	protected int cheatCount = 0;
	protected int hit = 0;
	protected int maxRange = 0;
	protected int elementsNb = 0;
	protected int maxHit = 0;
	protected int minRange = 0;
	protected String opCode = null;
	protected String opProposal = null;
	protected String pcProposal = null;
	protected String opEntry = null;
	private static final Logger GAME_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables instantiation
	public Game() throws IOException, EntryException {
		GAME_LOGGER.trace("*****NEW GAME*****");
		checkParametersBooleanType(this.booleanIndiceList);
		checkParametersIntegerType(this.IntegerIndiceList);
		checkParamatersSpecificValue(1, 2, 3);

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables Getter an Setter
	public boolean isChallengerMode() {
		return this.challengerMode;
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

	public boolean isDuelMode() {
		return this.duelMode;
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

	public boolean isDevmode() {
		return this.devMode;
	}

	public void setDevmode(boolean devMode) {
		this.devMode = devMode;
	}

	public boolean isAutoMode() {
		return this.autoMode;
	}

	public void setAutoMode(boolean autoMode) {
		this.autoMode = autoMode;
	}

	public boolean isVariantVersion() {
		return this.variantVersion;
	}

	public void setVariantVersion(boolean variantVersion) {
		this.variantVersion = variantVersion;
	}

	public boolean isCheating() {
		return this.cheating;
	}

	public boolean isCheatTentative() {
		return this.cheatTentative;
	}

	public void setCheatTentative(boolean cheatTentative) {
		this.cheatTentative = cheatTentative;
	}

	public int getMaxRange() {
		return this.maxRange;
	}

	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return this.minRange;
	}

	public void setMinRange(int maxRange) {
		this.minRange = maxRange;
	}

	public String getCode() {
		return this.code;
	}

	public String getOpCode() {
		return this.opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getOpProposal() {
		return this.opProposal;
	}

	public void setOpProposal(String opProposal) {
		this.opProposal = opProposal;
	}

	public String getPcProposal() {
		return this.pcProposal;
	}

	public void setPcProposal(String pcCodeEntry) {
		this.pcProposal = pcCodeEntry;

	}

	public int getHit() {
		return this.hit;
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

	public ArrayList<String> getHistoricChallengerTbl() {
		return this.historicChallengerTbl;
	}

	public void setHistoricChallengerTbl(ArrayList<String> historicChallengerTbl) {
		this.historicChallengerTbl = historicChallengerTbl;
	}

	public ArrayList<String> getHistoricDefenderTbl() {
		return this.historicDefenderTbl;
	}

	public void setHistoricDefenderTbl(ArrayList<String> historicDefenderTbl) {
		this.historicDefenderTbl = historicDefenderTbl;
	}

	public ArrayList<String> getPreviousHit() {
		return this.previousHit;
	}

	public int getElementsNb() {
		return this.elementsNb;
	}

	public void setElementsNb(int elementsNb) {
		this.elementsNb = elementsNb;
	}

	public String getOpEntry() {
		return this.opEntry;

	}

	public void setOpEntry(String opEntry) {

		this.opEntry = opEntry;

	}

	public String getGameAnswer() {
		return gameAnswer;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			GAME_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			GAME_LOGGER.trace("Out of method " + method);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation
	/**
	 * Generate a random number in a range
	 * 
	 * @param minValue
	 *            range min
	 * @param maxValue
	 *            range max
	 * @return a random number
	 */
	public int getRandom(int minValue, int maxValue) {
		traceMethodLogger(0, "getRandom");
		Random random = new Random();
		int randomNb = 0;
		randomNb = random.nextInt(((maxValue - minValue) + 1)) + minValue;
		traceMethodLogger(1, "getRandom");
		return randomNb;
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

	public void setCode() {
		traceMethodLogger(0, "setCode");
		String codetoCheck = null;
		Code code = new Code(this.minRange, this.maxRange, this.elementsNb);
		if (this.challengerMode) {
			if (!this.variantVersion) {
				do {
					Code basiscode = new Code(this.minRange, this.maxRange, this.elementsNb);
					codetoCheck = basiscode.toString();
					avoidDuplicate(codetoCheck, this.elementsNb);
				} while (this.duplicate);
				this.code = codetoCheck;
			} else {
				this.code = code.toString();
			}
		}
		traceMethodLogger(1, "setCode");
	}

	/**
	 * This method will check the presence of duplicate in the string tested
	 * function ogf the element number set
	 * 
	 * @return true if at least one elements is repeated.
	 * @param string
	 *            is the string composed of digits which will be checked
	 * @param elementsNb
	 *            is the number of elements composing the string
	 */
	public void avoidDuplicate(String string, int elementsNb) {
		traceMethodLogger(0, "avoidDuplicate");
		HashSet<Character> codeTbl = new HashSet<>();
		for (int i = 0; i < elementsNb; i++) {
			codeTbl.add(string.charAt(i));
		}
		if (codeTbl.size() < elementsNb)
			this.duplicate = true;

		else
			this.duplicate = false;
		traceMethodLogger(1, "avoidDuplicate");
	}

	/**
	 * @return Display a table whit a question to player and in case of dev-mode on
	 *         will provide the secret code.
	 *         <li>Note this method is mainly use in challenger mode.
	 * 
	 * @param secretcode
	 *            is the secret code set by Defender
	 * @param question
	 *            is the question displayed in console for the player
	 */
	public void AskOpProposition(String secretcode, String question) {
		traceMethodLogger(0, "AskOpProposition");
		ArrayList<String> askOpPrpoposition = new ArrayList<>();
		askOpPrpoposition.add("\nPlayer as Challenger");
		askOpPrpoposition.add(question);
		if (GameParameters.devMode == true)
			askOpPrpoposition.add("(The Secret Code is : " + secretcode + ")");
		askOpPrpoposition.forEach(elmt -> System.out.println(elmt));
		traceMethodLogger(1, "AskOpProposition");
	}

	/**
	 * @return Display a table whit a question to player and as help provide the
	 *         secret to ease the clues construction, in case of dev-mode on will
	 *         provide the secret code.
	 *         <li>Note this method is mainly use in challenger mode.
	 * 
	 * @param secretcode
	 *            is the secret code set by Defender
	 * @param question
	 *            is the question displayed in console for the player
	 * @param pcProposition
	 *            is the code proposed by application
	 */

	public void AskOpClues(String opCode, String pcProposition, String question) {
		traceMethodLogger(0, "AskOpClues");
		ArrayList<String> askOpclues = new ArrayList<>();
		askOpclues.add("\nPlayer as Defender");
		askOpclues.add("Your secret code is : " + opCode);
		askOpclues.add("Pc proposition      : " + pcProposition);
		askOpclues.add(question);
		if (!this.autoMode)
			askOpclues.forEach(elmt -> System.out.println(elmt));
		traceMethodLogger(1, "AskOpClues");

	}

	/**
	 * This method create a table containing all information concerning the
	 * 
	 * @return An ArrayList of the previous hit performed by player and or
	 *         application.
	 * 
	 * @param title
	 *            is the title of the table
	 */
	public void setPreviousHit(String title) {
		traceMethodLogger(0, "setPreviousHit");
		ArrayList<String> previousHit = new ArrayList<>();

		if (this.challengerMode) {
			if (this.historicChallengerTbl.size() == 0)
				previousHit.clear();
			else {
				previousHit.add(title);
				previousHit.addAll(this.historicChallengerTbl);
			}

		} else {
			if (this.historicDefenderTbl.size() == 0)
				previousHit.clear();
			else {
				previousHit.add(title);
				previousHit.addAll(this.historicDefenderTbl);
			}

		}

		this.previousHit = previousHit;
		traceMethodLogger(1, "setPreviousHit");
	}

	/**
	 * This method generate a summary of the last game played.
	 * <li>and add the last hit.
	 */
	public void Summary() {
		traceMethodLogger(0, "Summary");
		Conclusion();
		if (!duelMode) {
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
		GAME_LOGGER.trace("****GAME END****");

	}

	/**
	 * This method display on console an ArrayList
	 * 
	 * @param tableToDisplay
	 */
	public void tableDisplay(ArrayList<String> tableToDisplay) {
		traceMethodLogger(0, "tableDisplay");
		tableToDisplay.forEach(string -> System.out.println(string));
		traceMethodLogger(1, "tableDisplay");
	}

	/**
	 * This method will set the answer after a proposition in function of the mode
	 * played
	 */
	public void setGameAnswer() {
		traceMethodLogger(0, "setGameAnswer");
		if (challengerMode) {
			if (opProposal.equals(code))
				this.gameAnswer = "\n!!!!YOU MAY HAVE WIN!!!!\nIt seems that you have found the Pc secret code, lets check if Pc has found yours.\n";
			else
				this.gameAnswer = "\nSorry this is not the secret code\n" + (this.maxHit - (this.hit))
						+ " tentative(s) remaining  on the " + this.maxHit + " initially attributed.\n";

		} else {
			this.gameAnswer = (this.maxHit - (this.hit)) + "  tentative(s) remaining  on the " + this.maxHit
					+ " initially attributed.\n";
		}
		traceMethodLogger(1, "setGameAnswer");
	}

	/**
	 * 
	 * @param code
	 * @param codeToCompare
	 * @return true if the code are equivalent
	 */
	public boolean codeEquivalenceCheck(String code, String codeToCompare) {
		traceMethodLogger(0, "codeEquivalenceCheck");
		boolean equal = false;
		boolean equality = code.equals(codeToCompare);
		if (equality)
			equal = true;
		traceMethodLogger(1, "codeEquivalenceCheck");
		return equal;

	}

	/**
	 * 
	 * @return true if the hit number has reach the maximum hit set
	 */

	public boolean hitCheck() {
		traceMethodLogger(0, "hitCheck");
		boolean equal = false;
		if (this.hit == this.maxHit)
			equal = true;
		traceMethodLogger(1, "hitCheck");
		return equal;
	}

	/**
	 * This method test the saus of the game in function of the mode
	 * <li>check hit number and the equivalence of the codes put in game.
	 * 
	 * @return false if at least one condition is false
	 */
	public boolean gameStatu() {
		traceMethodLogger(0, "gameStatu");
		ArrayList<Boolean> gameStatuResults = new ArrayList<>();
		boolean end = false;
		boolean endItsTrue = false;
		if (this.cheating)
			end = true;
		else {
			gameStatuResults.add(hitCheck());
			if (this.duelMode) {
				gameStatuResults.add(codeEquivalenceCheck(this.opProposal, this.code));
				gameStatuResults.add(codeEquivalenceCheck(this.opCode, this.pcProposal));
			} else if (this.challengerMode)
				gameStatuResults.add(codeEquivalenceCheck(this.opProposal, this.code));
			else
				gameStatuResults.add(codeEquivalenceCheck(this.opCode, this.pcProposal));

			endItsTrue = gameStatuResults.contains(true);
			if (endItsTrue)
				end = true;
		}
		traceMethodLogger(1, "gameStatu");
		return end;

	}

	/**
	 * This method return the conclusion of the game
	 * <li>define win loose condition on function of the mode played
	 * <li>and finally display a summary of the game (linked to the method summary)
	 */
	public void Conclusion() {
		traceMethodLogger(0, "Conclusion");
		ArrayList<String> summary = new ArrayList<>();
		String comment = null;
		if (this.cheating) {
			comment = "\n????!!Who cheat loose!!??? \nThree cheating tentatives";
		} else {
			if (!this.duelMode) {
				if (this.challengerMode) {

					if (this.opProposal.equals(this.code)) {
						comment = "\n!!!!CONGRATULATION YOU WIN!!!!\nYou managed to find Pc secret code which was : "
								+ this.opProposal;
					} else {
						comment = "\n????SORRY YOU LOOSE???\nYou did not find Pc secret code which was : " + this.code;
					}
				}
				if (!this.challengerMode) {

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
		traceMethodLogger(1, "Conclusion");
	}

	/**
	 * This method will set the parameters according to config.properties
	 * 
	 * @throws EntryException
	 */
	public void setGameParameters() throws EntryException {
		traceMethodLogger(0, "setGameParameters");
		setVariantVersion(booleanConverter(getProperty(parametersTxtKeys.get(0))));
		setMinRange(stringToInteger(getProperty(parametersTxtKeys.get(1))));
		setMaxRange(stringToInteger(getProperty(parametersTxtKeys.get(2))));
		setElementsNb(stringToInteger(getProperty(parametersTxtKeys.get(3))));
		setMaxHit(stringToInteger(getProperty(parametersTxtKeys.get(4))));
		setAutoMode(booleanConverter(getProperty(parametersTxtKeys.get(5))));
		traceMethodLogger(1, "setGameParameters");
	}

	/**
	 * 
	 * @param i
	 *            is the integer which will be transform in string
	 * @return an integer in a string
	 */
	public String intToString(int i) {
		traceMethodLogger(0, "intToString");
		String intToString = String.valueOf(i);
		traceMethodLogger(1, "intToString");
		return intToString;
	}

	/**
	 * 
	 * @return the cheat warning according to the number of cheat already done
	 */

	public String cheatWarning() {
		traceMethodLogger(0, "cheatWarning");
		ArrayList<String> number = new ArrayList<>();
		number.add("First");
		number.add("Second");
		String warning = number.get(this.cheatCount - 1)
				+ " Cheating tentative detection or entry error, the third will be the last\n";
		traceMethodLogger(0, "cheatWarning");
		return warning;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Check Methods Implementation

	/**
	 * 
	 * @param entry
	 *            to test
	 * @param elementsNb
	 *            set
	 * @return true if the length is equals to the element number set
	 * @throws EntryException
	 */
	public boolean entryCheckLength(String entry, int elementsNb) throws EntryException {
		traceMethodLogger(0, "entryCheckLength");
		boolean pass = true;
		try {
			if (entry.length() != elementsNb)
				throw new EntryException(entry, 0);
		} catch (EntryException e) {
			pass = false;
		}
		traceMethodLogger(1, "entryCheckLength");
		return pass;
	}

	/**
	 * 
	 * @param entry
	 * @return true if the integer tested is within a specified range
	 * @throws EntryException
	 */
	public boolean entryIntegerRangeCheck(String entry) throws EntryException {
		traceMethodLogger(0, "entryIntegerRangeCheck");
		boolean pass = true;
		boolean RangeCheck = entry.matches("[" + this.minRange + "-" + this.maxRange + "]{" + entry.length() + "}");
		try {
			if (!RangeCheck)
				throw new EntryException(entry, 4);
		} catch (EntryException e) {
			pass = false;
		}
		traceMethodLogger(1, "entryIntegerRangeCheck");
		return pass;
	}

	/**
	 * 
	 * @param entry
	 * @return true if a string contains duplicate
	 * @throws EntryException
	 */

	public boolean entryDuplicateCheck(String entry) throws EntryException {
		traceMethodLogger(0, "entryDuplicateCheck");
		boolean pass = true;
		try {
			if (!this.variantVersion)
				avoidDuplicate(entry, entry.length());
			if (this.duplicate)
				throw new EntryException(this.opEntry, 3);
		} catch (EntryException e) {
			pass = false;
		}
		traceMethodLogger(1, "entryDuplicateCheck");
		return pass;
	}

	/**
	 * 
	 * @param entry
	 * @param contains
	 * @return true if a string is only composed of the specified character
	 * @throws EntryException
	 */
	public boolean entryContentsCheck(String entry, String contains) throws EntryException {
		traceMethodLogger(0, "entryContentsCheck");
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
			if (!containsOnly)
				throw new EntryException(entry, contains);
		} catch (EntryException e) {
			pass = false;
		}
		traceMethodLogger(1, "entryContentsCheck");
		return pass;
	}

	/**
	 * 
	 * @return true only if the entry respect all the condition : length, integer
	 *         integer range and/or duplicate
	 * @throws EntryException
	 */
	public boolean opEntryCodeCheck() throws EntryException {
		traceMethodLogger(0, "opEntryCodeCheck");
		ArrayList<Boolean> testresults = new ArrayList<>();

		boolean pass = true;
		testresults.add(entryCheckLength(this.opEntry, this.elementsNb));
		testresults.add(entryIntegerCheck(this.opEntry));
		testresults.add(entryIntegerRangeCheck(this.opEntry));
		if (!this.challengerMode) {
			testresults.add(entryDuplicateCheck(this.opEntry));
		}

		boolean failed = testresults.contains(false);
		if (failed)
			pass = false;
		traceMethodLogger(1, "opEntryCodeCheck");
		return pass;
	}

	/**
	 * This method set the key,value and default value from config.prperties in
	 * function of a indices present in a int table
	 * 
	 * @param paramatersTableIndice
	 */
	public void setParametersInfo(int paramatersTableIndice) {
		traceMethodLogger(0, "setParametersInfo");
		setParameterKey(paramatersTableIndice);
		setParameterCurrentValue(paramatersTableIndice);
		setParameterDefaultValueValue(paramatersTableIndice);
		traceMethodLogger(1, "setParametersInfo");
	}

	/**
	 * this method check if the value at the indice key parameter in
	 * config.properties is exactly "true" or "false"
	 * 
	 * @param indiceList
	 */
	public void checkParametersBooleanType(int[] indiceList) throws IOException {
		traceMethodLogger(0, "checkParametersBooleanType");
		for (int i = 0; i < indiceList.length; i++) {
			setParametersInfo(indiceList[i]);

			try {
				if (!valueBooleanCheck(this.parameterCurrentValue))
					throw new EntryException(this.parameterKey, this.parameterCurrentValue, 0);
			} catch (EntryException e0) {
				writeConfiguration(this.parameterKey, this.parameterDefaultValue);
			}
		}
		traceMethodLogger(1, "checkParametersBooleanType");
	}

	/**
	 * this method check if the value at the indice key parameter in
	 * config.properties is an Integer
	 * 
	 * @param indiceList
	 */
	public void checkParametersIntegerType(int[] indiceList) throws IOException {
		traceMethodLogger(0, "checkParametersIntegerType");
		for (int i = 0; i < indiceList.length; i++) {
			setParametersInfo(indiceList[i]);

			try {
				if (!valueIntegerCheck(this.parameterCurrentValue))
					throw new EntryException(this.parameterKey, this.parameterCurrentValue, 1);
			} catch (EntryException e1) {
				writeConfiguration(this.parameterKey, this.parameterDefaultValue);
			}
		}
		traceMethodLogger(1, "checkParametersIntegerType");
	}

	/**
	 * this method check specific value in config.properties
	 * <li>1-if maximum and minimun range value are within the range set for each
	 * value
	 * <li>2- if the element number is superior than max range value in this case
	 * the app is set to variant version
	 * 
	 * @param minRangeIndice
	 * @param maxRangeIndice
	 * @param elementsNbIndice
	 * @throws EntryException
	 *             <Li>This method nedds some improvement still don't know how.
	 */
	public void checkParamatersSpecificValue(int minRangeIndice, int maxRangeIndice, int elementsNbIndice)
			throws EntryException, IOException {
		traceMethodLogger(0, "checkParamatersSpecificValue");

		// load parameters to check validity
		setGameParameters();
		try {
			setParametersInfo(minRangeIndice);
			if (!valueRangeCheck(this.parameterCurrentValue, this.minMinRange, this.maxMinRange)
					|| stringToInteger(this.parameterCurrentValue) >= this.maxRange)
				throw new EntryException(this.parameterKey, this.parameterCurrentValue, this.minMinRange,
						this.maxMinRange, 0);
		} catch (EntryException e2) {
			writeConfiguration(this.parameterKey, this.parameterDefaultValue);
		}
		try {
			setParametersInfo(maxRangeIndice);
			;
			if (!valueRangeCheck(this.parameterCurrentValue, this.minMaxRange, this.maxMaxRange)
					|| stringToInteger(this.parameterCurrentValue) <= this.minRange)
				throw new EntryException(this.parameterKey, this.parameterCurrentValue, this.minMaxRange,
						this.maxMaxRange, 1);
		} catch (EntryException e3) {
			writeConfiguration(this.parameterKey, this.parameterDefaultValue);
		}
		try {
			setParametersInfo(elementsNbIndice);
			if (stringToInteger(this.parameterCurrentValue) > this.maxRange && !this.isVariantVersion())
				throw new EntryException(this.parameterKey, this.parameterCurrentValue, 2);
		} catch (EntryException e4) {
			setParametersInfo(0);
			writeConfiguration(this.parameterKey, "true");
		}
		// after check load correction if necessary
		setGameParameters();
		traceMethodLogger(1, "checkParamatersSpecificValue");
	}

	/**
	 * <li><i><b>Comparison method will compare the secret code and the code</i></b>
	 * 
	 * @param Code
	 *            define the secret code to find generate by PC or by the Operator
	 * @param codeTocompare
	 *            define the code to compare generate by PC or by the Operator;
	 * 
	 */

	public abstract void comparison(String code, String codeToCompare);

	/**
	 * <li><i><b>secretCodeResearch method will seek the secret code provided by
	 * player as Defender </i></b>
	 * 
	 * @param pcProposal
	 *            define the secret code to find generate by PC or by the Operator
	 * 
	 * 
	 */

	public abstract void secretCodeResearch(String pcProposal);

	/**
	 * @return True or false
	 */

	public abstract boolean cheatTentative(String code, String codeToCompare);

	/**
	 * @return A string implemented in the corresponding historic table in game type
	 *         function.
	 *         <li>this method will fill the cheating tentative in case of cheating
	 *         detection.
	 *         <li>Historic will give the code proposal given and the clues
	 *         resulting
	 */
	public abstract void setHistoric(String code, int getHit);
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End
}