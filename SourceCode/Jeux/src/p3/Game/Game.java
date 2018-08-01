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
	private String opCode= null;
	private String code = null;
	protected String pcCode =null;
	private String opCodeEntry = null;
	private String gameAnswer = null;

	protected boolean basisVersion;
	protected boolean cheating;

	protected boolean duplicate;
	private boolean devmode;
	protected boolean autoMode;
	protected boolean challengerMode;
	private int hit = 0;

	protected HashMap<Integer, String> historic = new HashMap<>();

	public Game() {

		this.setMinRange(0);
		this.setMaxRange(9);
		this.setElementsNb(4);
		this.setMaxHit(20);
		
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
		return this.code;
	}

	public void setCode(int minRange, int maxRange, int elementsNb) {
		String codetoCheck = null;
		Code code = new Code(minRange, maxRange, elementsNb);
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

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	

	public String getPcCode() {
		return pcCode;
	}

	public void setPcCode(String pcCode) {
		this.pcCode = pcCode;
	}

	public int getHitCount() {
		return hit;
	}

	public void setHitCount() {
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
		ArrayList<String> AskOpPrpoposition = new ArrayList<>();
		AskOpPrpoposition.add(question);
		if (isDevmode() == true)
			AskOpPrpoposition.add("(The Secret Code is : " + secretcode + ")");
		AskOpPrpoposition.forEach(elmt -> System.out.println(elmt));
	}

	public void AskOpClues(String opCode, String pcProposition, String question) {
		ArrayList<String> askOpclues = new ArrayList<>();
		askOpclues.add("Your secret code is : " + opCode);
		askOpclues.add("Pc proposition      : " + pcProposition);
		askOpclues.add(question);
		if (autoMode == false)
			askOpclues.forEach(elmt -> System.out.println(elmt));

	}

	/**
	 * display historic
	 */
	public void getHistoric() {
		if (!historic.isEmpty()) {
			historic.forEach((key, value) -> System.out.println("Proposition " + key + value));
			System.out.print("\n");
		}

	}

	public String getGameAnswer() {
		return gameAnswer;
	}

	public void setGameAnswer(boolean challengerMode) {
		

		if (challengerMode == true) {

			this.gameAnswer = "\nSorry this is not the secret code\n" + (maxHit - (hit))
					+ " tentative(s) remaining  on the " + maxHit + " initially attributed.\n";

		} else {

			this.gameAnswer = (maxHit - (hit)) + " tentative(s) left.\n";

		}

	}
	public void Summary(String code, String secretCode, boolean challengerMode) {
		ArrayList<String> summary = new ArrayList<>();
		String comment = null;
		
		if(cheating == true) {
			comment = "????!!Who cheat loose!!??? ";			
		}
		else {
		if (challengerMode == false) {
			if (code.equals(secretCode)) {
				comment = "????SORRY YOU LOOSE???\nPc managed to find your secret code which was : " + code;
			} else {
				comment = "!!!!CONGRATULATION YOU WIN!!!!\nPc did not find you secret code which was : " + secretCode;
			}
		}
		if (challengerMode == true) {
			if (code.equals(secretCode)) {
				comment = "!!!!CONGRATULATION YOU WIN!!!!\nYou managed to find Pc secret code which was : " + code;
			} else {
				comment = "????SORRY YOU LOOSE???\nYou did not find Pc secret code which was : " + secretCode;
			}
		}}
		summary.add(comment);
		summary.add("Game summary : ");
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

	public String getOpcodeEntry() {
		return opCodeEntry;

	}

	public void setOpcodeEntry(String opCodeEntry) {

		this.opCodeEntry = opCodeEntry;

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
			this.opCodeEntry = entry;
		else {

			boolean pass = true;
			try {
				if (entry.length() != elementsNb)
					throw new EntryException(entry, 0);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opCodeEntry = null;
			else
				this.opCodeEntry = entry;
		}
	}

	public void entryIntegerCheck(String entry) throws EntryException {
		boolean pass = true;
		if (entry == null)
			this.opCodeEntry = entry;
		else {
			boolean digitonly = entry.matches("[0-9]{"+entry.length()+"}");
			try {
				if (digitonly == false)
					throw new EntryException(entry, 2);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opCodeEntry = null;
			else
				this.opCodeEntry = entry;
		}
	}

	public void entryDuplicateCheck(String entry) throws EntryException {
		boolean pass = true;
		if (entry == null)
			this.opCodeEntry = entry;
		else {
			try {
				if (this.basisVersion == true)
					avoidDuplicate(entry, entry.length());
				if (duplicate == true)
					throw new EntryException(opCodeEntry, 3);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opCodeEntry = null;
			else
				this.opCodeEntry = entry;
		}
	}

	public void entryContentsCheck(String entry, String contains) {
		// System.out.println(moreLess.getOpcodeEntry().matches("[[+][-][=]]+"));// n
		// est accepter que les string ave des +-=
		boolean pass = true;
		if (entry == null)
			this.opCodeEntry = entry;
		else {
			StringBuilder regexSb = new StringBuilder();
			for (int i = 0; i < contains.length(); i++) {
				regexSb.append("[" + contains.charAt(i) + "]");
			}
			boolean containsOnly = entry.matches("[" + regexSb.toString() + "]+");
			try {
				if (containsOnly == false)
					throw new EntryException(entry, contains);
			} catch (EntryException e) {
				pass = false;
			}
			if (pass == false)
				this.opCodeEntry = null;
			else
				this.opCodeEntry = entry;
		}
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * @param Code
	 *            define the secret code to find generate by PC or by the Operator
	 * @param codeTocompare
	 *            define the code to compare generate by PC or by the Operator;
	 */
	public abstract void comparison(String secretcode, String codeToCompare);

	

	public abstract void setHistoric(String code, String answer, int getHit);


}
