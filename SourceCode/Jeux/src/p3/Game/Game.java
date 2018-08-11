package p3.Game;


import java.io.IOException;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.LinkedHashMap;

import java.util.Random;

abstract class Game extends GameParameters {

	protected int hit = 0;
	private String code = null;
	private String opCode = null;
	private String opProposal = null;
	protected String pcProposal = null;
	private String opEntry = null;
	private String gameAnswer = null;	
	protected boolean cheating;
	protected boolean duplicate;
	protected boolean challengerMode;
	protected boolean cheatTentative;
	private boolean valueCheckPass = true;
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
	protected int maxRange = 0;	
	protected int elementsNb = 0;
	protected boolean devMode;
	protected boolean autoMode;
	protected boolean variantVersion;
	private String parameterKey = null;
	private String parameterCheck = null;
	private String parameterDefaultValue = null;

	public Game() throws IOException, EntryException {

		checkParameters();
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
		Code code = new Code(minRange, maxRange, elementsNb);
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
			boolean RangeCheck = entry.matches("[" + minRange + "-" + maxRange + "]{" + entry.length() + "}");
			try {
				if (RangeCheck == false)
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
				if (this.variantVersion == false)
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

	// TODO All entry check replace value by a booleanand change condition while in main
	// value
	public void entryContentsCheck(String entry, String contains) {
		// System.out.println(moreLess.getOpcodeEntry().matches("[[+][-][=]]+"));// n
		// est accepter que les string ave des +-=, - entre crochet car considerer come
		// la marque qui indique le Range
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
	public int stringToInteger(String str) {
		int integer = Integer.parseInt(str);
		return integer;
	}
	
	public boolean booleanConverter(String str) {
	String itsTrue = "true";
	boolean bool = itsTrue.equals(str);
	return bool;
	}
	
	public String getProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}
	public void valueRangeCheck(String valueToSet, int minParameter, int maxParameter) {
		int value = stringToInteger(valueToSet);
		if (value < minParameter || value > maxParameter)
			this.valueCheckPass = false;
		else
			this.valueCheckPass = true;
			
	}

	public void valueIntegerCheck(String valueToSet) {
		boolean digitOnly = valueToSet.matches("[0-9]{" + valueToSet.length() + "}");
		if (digitOnly == false)
			this.valueCheckPass = false;
		else
			this.valueCheckPass = true;
	}

	public void valueBooleanCheck(String booleanValue) {
		boolean itsTrue = booleanValue.equalsIgnoreCase("true");
		boolean itsFalse = booleanValue.equalsIgnoreCase("false");
		if (itsTrue == true || itsFalse == true)
			this.valueCheckPass = true;
		else
			this.valueCheckPass = false;

	}

	public void setParametersInfo(int parametersTableIndice) {
		setDefaultGamevalue();
		this.parameterKey = parametersTxtKeys.get(parametersTableIndice);
		this.parameterCheck = getProperty(this.parameterKey);
		this.parameterDefaultValue = this.defaultValue.get(parametersTableIndice);
	}

	public void checkParameters() {
		ArrayList<Integer> indiceList = new ArrayList<>();
		indiceList.add(0);
		indiceList.add(5);
		indiceList.add(6);
		for (int i = 0; i < indiceList.size(); i++) {
			setParametersInfo(indiceList.get(i));
			valueBooleanCheck(this.parameterCheck);
			try {
				if (this.valueCheckPass == false)
					throw new EntryException(this.parameterKey, parameterCheck, 0);
			} catch (EntryException e0) {
				writeConfiguration(this.parameterKey, parameterDefaultValue);
			}
		}
		indiceList.clear();
		indiceList.add(1);
		indiceList.add(2);
		indiceList.add(3);
		indiceList.add(4);
		for (int i = 0; i < indiceList.size(); i++) {
			setParametersInfo(indiceList.get(i));
			valueIntegerCheck(this.parameterCheck);
			try {
				if (this.valueCheckPass == false)
					throw new EntryException(this.parameterKey, parameterCheck, 1);
			} catch (EntryException e1) {
				writeConfiguration(this.parameterKey, parameterDefaultValue);
			}
		}
		indiceList.clear();	
		//after parameter type check load parameters to check value
		setGameParameters();		
		try {
			setParametersInfo(1);			
			valueRangeCheck(parameterCheck, this.minMinRange,this.maxMinRange);			
			if (this.valueCheckPass == false || stringToInteger(this.parameterCheck)>= this.maxRange)
				throw new EntryException(parameterKey,parameterCheck, this.minMinRange, this.maxMinRange, 0);
		} catch (EntryException e2) {			
			writeConfiguration(this.parameterKey, parameterDefaultValue);
		}
		try {
			setParametersInfo(2);			
			valueRangeCheck(parameterCheck, this.minMaxRange,this.maxMaxRange);			
			if (this.valueCheckPass == false || stringToInteger(this.parameterCheck) <= this.minRange)
				throw new EntryException(parameterKey,parameterCheck, this.minMaxRange, this.maxMaxRange, 1);
		} catch (EntryException e3) {			
			writeConfiguration(this.parameterKey, this.parameterDefaultValue);
		}		
		try {
			setParametersInfo(3);			
			if(stringToInteger(parameterCheck)>this.maxRange&&this.isVariantVersion()==false)			
				throw new EntryException(parameterKey,parameterCheck, 2);
		} catch (EntryException e4) {
			setParametersInfo(0);
			writeConfiguration(this.parameterKey, "true");
		}
		//after check load correction
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
