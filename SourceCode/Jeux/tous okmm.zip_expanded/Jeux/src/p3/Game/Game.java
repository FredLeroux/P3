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
	private String opEntry = null;
	private String code = null;
	protected String pcEntry;
	private String opCode = null;
	private String gameAnswer = null;

	protected boolean basisVer;

	
	protected boolean duplicate;
	private boolean devmode;
	protected boolean autoMode;
	private boolean challengerMode;
	private int hit = 0;

	protected HashMap<Integer, String> historic = new HashMap<>();

	public Game() {

		this.setMinRange(0);
		this.setMaxRange(9);
		this.setElementsNb(4);
		this.setMaxHit(20);
		this.pcEntry = null;
		this.duplicate = false;		
		this.autoMode = true;
		this.devmode = true;
		
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

	public void setVersion(int version) {
		if (version == 1) {
			this.basisVer = true;
		} else
			this.basisVer = false;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) {
		// isOk = true;
		// while (isOk) {
		System.out.println("Set Max range(min=1 max=9): Fixed for test at 9");
		// maxRange = entry.nextInt();
		// isOk = (maxRange < 1 || maxRange > 9);
		// }
		this.maxRange = maxRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public void setMinRange(int maxRange) {
		// isOk = true;
		// while (isOk) {
		System.out.println("Set Min range(min=0 max=8): Fixed for test at 0");
		// maxRange = entry.nextInt();
		// isOk = (maxRange < 1 || maxRange > 9);
		// }
		this.minRange = maxRange;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(int minRange, int maxRange, int elementsNb) {
		String codetoCheck = null;
		Code code = new Code(minRange, maxRange, elementsNb);
		if (this.basisVer == true) {
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
		// maxHit = entry.nextInt();

	}

	public String getOpEntry() {
		return opEntry;
	}

	public void setOpEntry(String entry) throws EntryException {
		// TODO optimized as opcode
		boolean pass = true;
		String code = entry;
		try {
			if (!(entry.length() == elementsNb))
				throw new EntryException();
		} catch (EntryException e) {
			System.out.println("Your Proposition : " + code + "\nIs not composed of " + elementsNb + " elements");
			pass = false;
		}
		if (pass == false)
			opEntry = null;
		else
			opEntry = entry.toString();
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

	public void setGameAnswer( boolean challengerMode) {		

		if (challengerMode == true) {
			
				this.gameAnswer = "\nSorry this is not the secret code\n" + (maxHit - (hit )) + " tentative(s) remaining  on the "+ maxHit +" initially attributed.\n";
			
		} else {
			
				this.gameAnswer = (maxHit - (hit )) + " tentative(s) left.\n";
			
		}
		

	}

	public int getElementsNb() {
		return elementsNb;
	}

	public void setElementsNb(int elementsNb) {
		// isOk = true;
		// while (isOk) {
		System.out.println("Set elements number composing code(<= Max Range+1(Max range setted =" + maxRange
				+ ")): Fixed for test at 4");
		// elementsNb = entry.nextInt();
		// isOk = (elementsNb > maxRange + 1);
		// }
		this.elementsNb = elementsNb;
	}

	// -----------------------------------------------------------------------------------------------------------------------

	public String getOpcode() {
		return opCode;

	}

	/**
	 * Impose to operator to fill a secret code with an elements number equals to
	 * the set elements number and not allow duplicate in case of basis version.
	 * 
	 * @param opCode
	 *            is the code filled by operator
	 * 
	 * @throws
	 * 
	 * 			@return
	 *             null if exception raised
	 */

	public void setOpcode(String opCode) throws NumberFormatException, EntryException {
		boolean pass = true;
		// TODO Question is it better to add multiple try catch or only one try with
		// multiple catch

		try {
			Integer.parseInt(opCode);
			if (opCode.length() != elementsNb)
				throw new EntryException(opCode, elementsNb);
			if (this.basisVer == true) {
				avoidDuplicate(opCode, opCode.length());
				if (duplicate == true) {
					pass = false;
					throw new EntryException(opCode, opCode.length(), "basis");
				}
			}

		} catch (NumberFormatException e) {
			System.out.println("This entry is not fully an integer\n");
			pass = false;
		} catch (EntryException e) {
			pass = false;
		}

		if (pass == false) {

			this.opCode = null;
		} else {
			this.opCode = opCode;
		}
	}

	public int getRandom(int minValue, int maxValue) {
		Random random = new Random();
		int randomNb = 0;
		randomNb = random.nextInt(((maxValue - minValue) + 1)) + minValue;
		return randomNb;
	}

	public String getPcEntry() {
		return pcEntry;
	}

	public void setPcEntry(String str) {
		this.pcEntry = str;
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * @param Code
	 *            define the secret code to find generate by PC or by the Operator
	 * @param codeTocompare
	 *            define the code to compare generate by PC or by the Operator;
	 */
	public abstract void comparison(String secretcode, String codeToCompare);
	
	public abstract void autocompare(String code, String codeToCompare);

	public abstract void setHistoric(String code,String answer, int getHit);

	/**
	 * @return A summary of the game
	 * @param getGameAnswer
	 *            Is a string generated via setGameAnswer p3.Game
	 *
	 */
	public abstract void Summary(String code, String secretCode,boolean challengerMode);

	
	}


