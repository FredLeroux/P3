package p3.Game;

import java.io.IOException;
import java.util.ArrayList;

public class Mastermind extends Game {

	private int nbRightPlaced;
	private int nbPresent;
	private int clueRightPlaced;
	private int clueElmentPresent;
	private ArrayList<String> possibilitiesList;

	public Mastermind() throws IOException, EntryException {
		this.nbRightPlaced = 0;
		this.nbPresent = 0;
		this.clueRightPlaced = 0;
		this.clueElmentPresent = 0;
		this.possibilitiesList = new ArrayList<>();
		historicChallengerTbl.clear();
		historicDefenderTbl.clear();
	}

	public int getNbRightPlaced() {
		return nbRightPlaced;
	}

	public int getNbPresent() {
		return nbPresent;
	}

	public int getClueRightPlaced() {
		return clueRightPlaced;
	}

	public void setClueRightPlaced(String clueRightPlaced) {
		this.clueRightPlaced = Integer.parseInt(clueRightPlaced);
	}

	public int getClueElmentPresent() {
		return clueElmentPresent;
	}

	public void setClueElmentPresent(String clueElmentPresent) {
		this.clueElmentPresent = Integer.parseInt(clueElmentPresent);
	}

	/**
	 * @return a String ArrayList
	 */
	public ArrayList<String> getCodeList() {
		return possibilitiesList;
	}

	/**
	 * create an array list containing all the secret code possibility function of
	 * the number element, with or without code composed of duplicate. ( i.e if
	 * duplicate are allowed code list will contain this kind of secret code :
	 * 1122,1111,25446....)
	 * 
	 * @param nbElements
	 *            is an integer which indicated the number of elements composing the
	 *            secret code
	 */

	public void setPossibilitiesList() {
		ArrayList<String> codeList = new ArrayList<>();
		String code = null;
		int codeListElmt = 0;
		for (int i = 0; i < Math.pow(10, this.elementsNb); i++) {
			code = String.format("%0" + this.elementsNb + "d", codeListElmt++);
			if (variantVersion == false) {
				avoidDuplicate(code, this.elementsNb);
				if (duplicate == true)
					continue;
				else
					codeList.add(code);
			} else
				codeList.add(code);
		}
		this.possibilitiesList = codeList;
	}

	/**
	 * 
	 * @param possibilitiesList
	 *            is a string list
	 * 
	 * 
	 * @throws exception
	 *             IllegalArgumentException in case of a codeList with a size at 0 .
	 */

	public String pcProposition() {
		try {
			if (this.possibilitiesList.size() == 0)
				throw new EntryException("Fatal", 6);
		} catch (EntryException e) {
			System.exit(1);

		}
		return this.pcProposal = this.possibilitiesList.get(getRandom(0, (possibilitiesList.size() - 1)));
		// causse a codeliste size at 0 is an empty codelist and not a codelits with 1
		// elment so to avoid bug -1 //to be clear a codelist with 1 string have a size
		// of
		// 1 so random can be 1 but with a size of 1 the first element is at the entry
		// zero so if random is 1 we will ask for codelist.get(1) but doesn't exist the
		// first place is 0 so whith -1 we will never have issue again

	}

	public void possibilitySelection(String pcProposal) {

		String secretCodePossible = null;

		for (int i = 0; i < this.possibilitiesList.size(); i++) {
			secretCodePossible = this.possibilitiesList.get(i);
			// secret code est un des code de la liste car concraitement
			// on compare un code à un code dela liste donc il faut garder le
			// meme sens pour etre plus claire on etablie les clue en
			// faisant pcentry versus opcode don on dois faire pcentry
			// versus un des code dela liste qui contient le opcode
			comparison(pcProposal, secretCodePossible);
			if (!(clueRightPlaced == nbRightPlaced && clueElmentPresent == nbPresent)) {
				this.possibilitiesList.remove(secretCodePossible);

			} else
				continue;
		}

		this.possibilitiesList.remove(this.pcProposal);

	}

	@Override
	public void comparison(String secretCode, String codeToCompare) {
		this.nbRightPlaced = 0;
		this.nbPresent = 0;
		int elementsNb = secretCode.length();
		ArrayList<Character> codeToCompareTbl = new ArrayList<Character>();
		ArrayList<Character> secretCodeTbl = new ArrayList<Character>();

		for (int i = 0; i < elementsNb; i++) {
			secretCodeTbl.add(secretCode.charAt(i));
			codeToCompareTbl.add(codeToCompare.charAt(i));
			if (secretCode.charAt(i) == codeToCompare.charAt(i)) {
				this.nbRightPlaced++;
			}
		}
		codeToCompareTbl.retainAll(secretCodeTbl);
		this.nbPresent = Math.abs(nbRightPlaced - codeToCompareTbl.size());
	}

	@Override
	public void secretCodeResearch(String pcProposal) {
		if (pcProposal == null) {
			setPossibilitiesList();
			pcProposition();
		}
		if (pcProposal != null) {
			comparison(this.opCode, pcProposal);
			possibilitySelection(pcProposal);
			pcProposition();
		}

	}

	@Override
	public void setHistoric(String codeProposal, int getHit) {
		// traceMethodLogger(0, "setHistoric");

		String comment = null;
		String answer = null;

		if (cheatTentative == true && cheatCount > 0 || cheating == true) {
			comment = "(Player cheating tentative or entry error).";
		} else {
			comment = " .";
		}
		if (challengerMode == true)
			historicChallengerTbl.add("Your proposition n°= " + String.format("%0" + 2 + "d", getHit) + " was : "
					+ codeProposal + " || The PC clues on this proposition are " + "Right placed element(s) number is "
					+ "{ " + this.nbRightPlaced + " }" + " Present element(s) number is " + "{ " + this.nbPresent + " }"
					+ comment);
		else
			historicDefenderTbl.add("Pc proposition n°= " + String.format("%0" + 2 + "d", getHit) + " was : "
					+ codeProposal + " || Your clues on this proposition are " + " Right placed element(s) number is "
					+ "{ " + this.clueRightPlaced + " }" + " Present element(s) number is " + "{ "
					+ this.clueElmentPresent + " }" + comment);
		// traceMethodLogger(1, "setHistoric");
	}

	@Override
	public void cheatStop(String code, String codeToCompare) throws EntryException {
		// traceMethodLogger(0, "cheatStop");
		this.cheatTentative = false;
		comparison(code, codeToCompare);
		if (this.nbRightPlaced != this.clueRightPlaced || this.nbPresent != this.clueElmentPresent) {
			this.cheatCount++;
			try {
				if (this.cheatCount > 0)
					if (this.cheatCount < 3)
						throw new EntryException(cheatCount);
					else
						this.cheating = true;

			} catch (EntryException e) {
				this.cheatTentative = true;

			}
		}
		// traceMethodLogger(1, "cheatStop");

	}
}
