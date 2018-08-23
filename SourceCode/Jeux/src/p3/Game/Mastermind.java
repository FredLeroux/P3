package p3.Game;
/**
 * Class Mastermind extending Game
 * <li> This class put in place all the necessary methods to allow to play mastermind game .
 * <li> As well in Challenger or Defender Mode
 * <li> In challenger mode this class will provide clues
 * <li> In defender mode this class will provide a code
 * <li> This class provide too a method which avoid cheating from player
 */

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Exception.EntryException;

public class Mastermind extends Game {
	// Variables declaration
	private int nbRightPlaced;
	private int nbPresent;
	private int clueRightPlaced;
	private int clueElmentPresent;
	private ArrayList<String> possibilitiesList;
	private static final Logger MASTERMIND_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables instantiation
	public Mastermind() throws IOException, EntryException {
		this.nbRightPlaced = 0;
		this.nbPresent = 0;
		this.clueRightPlaced = 0;
		this.clueElmentPresent = 0;
		this.possibilitiesList = new ArrayList<>();
		this.historicChallengerTbl.clear();
		this.historicDefenderTbl.clear();
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables Getter an Setter

	public int getNbRightPlaced() {
		return this.nbRightPlaced;
	}

	public int getNbPresent() {
		return this.nbPresent;
	}

	public int getClueRightPlaced() {
		return this.clueRightPlaced;
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
	 * @return a String ArrayList of all code possibilities
	 */
	public ArrayList<String> getCodeList() {
		return this.possibilitiesList;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			MASTERMIND_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			MASTERMIND_LOGGER.trace("Out of method " + method);
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation

	/**
	 * create an array list containing all the secret code possibility function of
	 * the number element, with or without code composed of duplicate. ( i.e if
	 * duplicate are allowed code list will contain this kind of secret code :
	 * 1122,1111,25446....)
	 * 
	 * nbElements is an integer which indicated the number of elements composing the
	 * secret code
	 */

	public void setPossibilitiesList() {
		traceMethodLogger(0, "setPossibilitiesList");
		ArrayList<String> codeList = new ArrayList<>();
		String code = null;
		int codeListElmt = 0;
		for (int i = 0; i < Math.pow(10, this.elementsNb); i++) {
			code = String.format("%0" + this.elementsNb + "d", codeListElmt++);
			if (this.variantVersion == false) {
				avoidDuplicate(code, this.elementsNb);
				if (this.duplicate)
					continue;
				else
					codeList.add(code);
			} else
				codeList.add(code);
		}
		this.possibilitiesList = codeList;
		traceMethodLogger(1, "setPossibilitiesList");
	}

	/**
	 * This method will select randomly a code in the possibilities list
	 * <li>Note; if there is no code list instanciate and filled this method will
	 * close the app
	 * <li>Raised a FATAL exception
	 * 
	 * @param possibilitiesList
	 *            is a string list
	 * 
	 * 
	 * @throws exception
	 *             IllegalArgumentException in case of a codeList with a size at 0 .
	 */

	public String pcProposition() {
		traceMethodLogger(0, "pcProposition");
		try {
			if (this.possibilitiesList.size() == 0)
				throw new EntryException("Fatal", 6);
		} catch (EntryException e) {
			System.exit(1);

		}
		traceMethodLogger(1, "pcProposition");
		return this.pcProposal = this.possibilitiesList.get(getRandom(0, (this.possibilitiesList.size() - 1)));

	}

	/**
	 * This method will suppress all code which the clues compare to the Pc proposal
	 * are not the same than the comparison between secret code and Pc proposal
	 * <li>Note this method will also suppress the Pc Proposal.
	 * 
	 * @return a clean possibilities list i.e. with only probable code.
	 * @param pcProposal
	 *            is the code randomly selected in the possibilities list
	 * 
	 */

	public void possibilitySelection(String pcProposal) {
		traceMethodLogger(0, "possibilitySelection");
		String secretCodePossible = null;

		for (int i = 0; i < this.possibilitiesList.size(); i++) {
			secretCodePossible = this.possibilitiesList.get(i);
			comparison(pcProposal, secretCodePossible);
			if (!(this.clueRightPlaced == this.nbRightPlaced && this.clueElmentPresent == this.nbPresent)) {
				this.possibilitiesList.remove(secretCodePossible);

			} else
				continue;
		}

		this.possibilitiesList.remove(this.pcProposal);
		traceMethodLogger(1, "possibilitySelection");

	}

	/**
	 *
	 * @return <i><b>Clues composed of the number of right placed element and
	 *         present element (minus the right placed) </b> </i>
	 *         <li>i.e. if secret code = 1234 and proposal is 4251 the clues will be
	 *         1 right placed 2 present
	 * 
	 */

	@Override
	public void comparison(String secretCode, String codeToCompare) {
		traceMethodLogger(0, "comparison");
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
		traceMethodLogger(1, "comparison");
	}

	/**
	 * @return <i><b>A code proposition based on a suppression approach </b> </i>
	 * 
	 *         <li>if the Pc proposal is null method will select randomly a code
	 *         among the possibilities list
	 *         <li>If the Pc Proposal is not null method will compare all
	 *         possibilities to the Pc proposal then all possibilities which not
	 *         give the same clues will be deleted and so on until the secret code
	 *         is found
	 *         <li>Note: method can stop the application in case of player cheating,
	 *         this method has to be used with the cheat stop solution
	 *         <li>Improvement axe : try catch block on
	 *         possibilities.contains(secret code) if not raised an exception then
	 *         two approaches, add the secret code to the list or stop the game
	 */

	@Override
	public void secretCodeResearch(String pcProposal) {
		traceMethodLogger(0, "secretCodeResearch");
		if (pcProposal == null) {
			setPossibilitiesList();
			pcProposition();
		}
		if (pcProposal != null) {
			comparison(this.opCode, pcProposal);
			possibilitySelection(pcProposal);
			pcProposition();
		}
		traceMethodLogger(1, "secretCodeResearch");
	}

	@Override
	/**
	 * @return A string implemented in the corresponding historic table in game type
	 *         function.
	 *         <li>this method will fill the cheating tentative in case of cheating
	 *         detection.
	 *         <li>Historic will give the code proposal given and the clues
	 *         resulting
	 */
	public void setHistoric(String codeProposal, int getHit) {
		traceMethodLogger(0, "setHistoric");

		String comment = null;
		if (this.cheatTentative && this.cheatCount > 0 || this.cheating) {
			comment = "(Player cheating tentative or entry error).";
		} else {
			comment = " .";
		}
		if (this.challengerMode)
			this.historicChallengerTbl.add("Your proposition n°= " + String.format("%0" + 2 + "d", getHit) + " was : "
					+ codeProposal + " || The PC clues on this proposition are " + "Right placed element(s) number is "
					+ "{ " + this.nbRightPlaced + " }" + " Present element(s) number is " + "{ " + this.nbPresent + " }"
					+ comment);
		else
			this.historicDefenderTbl.add("Pc proposition n°= " + String.format("%0" + 2 + "d", getHit) + " was : "
					+ codeProposal + " || Your clues on this proposition are " + " Right placed element(s) number is "
					+ "{ " + this.clueRightPlaced + " }" + " Present element(s) number is " + "{ "
					+ this.clueElmentPresent + " }" + comment);
		traceMethodLogger(1, "setHistoric");
	}

	/**
	 * @return true if a cheating tentative is detected
	 *         <li>This method avoid cheating by comparison between player answer
	 *         and the result of method comparison, using the player secret code and
	 *         the PC proposal
	 */

	@Override
	public boolean cheatTentative(String code, String codeToCompare) {
		traceMethodLogger(0, "cheatTentative");
		boolean cheat = false;
		this.cheatTentative = false;
		comparison(code, codeToCompare);
		if (this.nbRightPlaced != this.clueRightPlaced || this.nbPresent != this.clueElmentPresent) {
			this.cheatCount++;
			if (this.cheatCount != 3) {
				cheatWarning();
				this.cheatTentative = true;
				cheat = true;
			} else
				this.cheating = true;

		} else
			cheat = false;
		MASTERMIND_LOGGER.trace("Cheat Tenative = " + cheat);
		traceMethodLogger(1, "cheatTentative");
		return cheat;

	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End
}
