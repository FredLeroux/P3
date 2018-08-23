
package p3.Game;
/**
 * Class MoreLess extending Game
 * <li> This class put in place all the necessary methods to allow to play more/less game .
 * <li> As well in Challenger or Defender Mode
 * <li> In challenger mode this class will provide clues
 * <li> In defender mode this class will provide a code
 * <li> This class provide too a method which avoid cheating from player
 */

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Exception.EntryException;

public class MoreLess extends Game {

	// Variables declaration
	private String mLClues;
	private StringBuilder moreLessAnswer;
	private static final Logger MORELESS_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables instantiation
	public MoreLess() throws IOException, EntryException {
		this.mLClues = null;
		this.moreLessAnswer = new StringBuilder();
		historicChallengerTbl.clear();
		historicDefenderTbl.clear();
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables Getter an Setter
	public String getmLClues() {
		return mLClues;

	}

	public void setMLClues(String mLClues) {
		this.mLClues = mLClues;
	}

	public StringBuilder getMoreLessAnswer() {
		return moreLessAnswer;
	}

	public void setMoreLessAnswer(StringBuilder moreLessAnswer) {
		this.moreLessAnswer = moreLessAnswer;
	}

	public String answerToString() {
		return this.moreLessAnswer.toString();
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			MORELESS_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			MORELESS_LOGGER.trace("Out of method " + method);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation

	/**
	 *
	 * @return <i><b>Clues composed of +,-,= </b> </i>
	 * 
	 * 
	 */
	@Override
	public void comparison(String code, String codeTocompare) {
		traceMethodLogger(0, "Comparison");
		MORELESS_LOGGER.trace("code is" + code, "code to compare  is" + codeTocompare);
		char moreIndication = '+';
		char lessIndication = '-';
		char equalsIndication = '=';

		char moreLessAnswerElmt = 0;
		StringBuilder moreLessAnswer = new StringBuilder();

		for (int i = 0; i < code.length(); i++) {

			if (code.charAt(i) == codeTocompare.charAt(i)) {
				moreLessAnswerElmt = equalsIndication;
			}
			if (code.charAt(i) < codeTocompare.charAt(i)) {
				moreLessAnswerElmt = lessIndication;
			}
			if (code.charAt(i) > codeTocompare.charAt(i)) {
				moreLessAnswerElmt = moreIndication;
			}
			moreLessAnswer.append(moreLessAnswerElmt);
		}
		this.moreLessAnswer = moreLessAnswer;
		MORELESS_LOGGER.trace("moreLessAnswer =" + moreLessAnswer);
		traceMethodLogger(1, "Comparison");
	}

	/**
	 * @return <i><b>A code proposition based on a dichotomies approach </b> </i>
	 * 
	 *         <li>if the Pc proposal is null method will generate a code composed
	 *         of the elements number set and where each element is equal to the
	 *         range middle
	 *         <li>If the Pc Proposal is not null method will use a dichotomies
	 *         method in function of the clues provided by the player
	 *         <li>Note: method can loop to infinite if the player cheat, however
	 *         this method is to link with cheat stop method.
	 */
	@Override
	public void secretCodeResearch(String pcCodeEntry) {
		traceMethodLogger(0, "SecretCodeResearch");
		MORELESS_LOGGER.trace("pcProposal = " + pcCodeEntry);
		int maxRange = this.maxRange;
		int minRange = this.minRange;
		int nbElements = this.elementsNb;
		int middle = (this.maxRange / 2);
		int maxLimit = 0;
		int minLimit = 0;
		int pcEntryElmt = 0;
		StringBuilder pcEntrySb = new StringBuilder();

		if (pcCodeEntry == null) {
			for (int i = 0; i < nbElements; i++) {
				pcEntrySb.append(middle);
				this.pcProposal = pcEntrySb.toString();
			}
		} else {
			for (int i = 0; i < nbElements; i++) {
				if (mLClues.charAt(i) == '=')
					pcEntrySb.append(pcCodeEntry.charAt(i));
				else if (Character.getNumericValue(pcCodeEntry.charAt(i)) == middle) {
					if (mLClues.charAt(i) == '-')
						pcEntryElmt = middle - ((middle - minRange) / 2);
					else {
						pcEntryElmt = (((maxRange + 1) - middle) / 2) + middle;
					}
					pcEntrySb.append(pcEntryElmt);
				} else if (Character.getNumericValue(pcCodeEntry.charAt(i)) > middle) {
					if (mLClues.charAt(i) == '-') {
						minRange = middle;
						maxRange = Character.getNumericValue(pcCodeEntry.charAt(i)) + 1;
						pcEntryElmt = ((maxRange - minRange) / 2) + minRange;
					} else {
						minLimit = Character.getNumericValue(pcCodeEntry.charAt(i));
						maxLimit = (maxRange + 1);
						pcEntryElmt = ((maxLimit - minLimit) / 2) + minLimit;
					}
					pcEntrySb.append(pcEntryElmt);
				} else if (Character.getNumericValue(pcCodeEntry.charAt(i)) < middle) {
					if (mLClues.charAt(i) == '-') {
						maxLimit = Character.getNumericValue(pcCodeEntry.charAt(i));
						pcEntryElmt = maxLimit / 2;
					} else {
						minLimit = Character.getNumericValue(pcCodeEntry.charAt(i));
						maxLimit = middle + 1;
						pcEntryElmt = ((maxLimit - minLimit) / 2) + minLimit;
					}
					pcEntrySb.append(pcEntryElmt);
				}
			}
			this.pcProposal = pcEntrySb.toString();
		}
		traceMethodLogger(1, "SecretCodeResearch");
	}

	/**
	 * @return A string implemented in the corresponding historic table in game type
	 *         function.
	 *         <li>this method will fill the cheating tentative in case of cheating
	 *         detection.
	 *         <li>Historic will give the code proposal given and the clues
	 *         resulting
	 */
	@Override
	public void setHistoric(String codeProposal, int getHit) {
		traceMethodLogger(0, "setHistoric");
		String comment = null;
		String answer = null;

		if (cheatTentative && cheatCount > 0 || cheating)
			comment = "(Player cheating tentative or entry error).";

		else {
			comment = " .";

		}
		if (challengerMode) {
			answer = answerToString();
			historicChallengerTbl.add("Your proposition n°= " + String.format("%0" + 2 + "d", getHit) + " was : "
					+ codeProposal + " || The PC clues on this proposition are " + answer + comment);
		} else {
			answer = this.mLClues;
			historicDefenderTbl.add("Pc proposition n°= " + String.format("%0" + 2 + "d", getHit) + " was : "
					+ codeProposal + " || Your clues on this proposition are " + answer + comment);
		}
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
		traceMethodLogger(0, "cheatStop");
		boolean cheat = false;
		this.cheatTentative = false;
		comparison(code, codeToCompare);
		if (!(this.moreLessAnswer.toString().equals(mLClues))) {
			this.cheatCount++;
			if (this.cheatCount != 3) {
				cheatWarning();
				this.cheatTentative = true;
				cheat = true;
			} else
				this.cheating = true;

		} else
			cheat = false;
		MORELESS_LOGGER.trace("Cheat Tenative = " + cheat);
		traceMethodLogger(1, "cheatStop");
		return cheat;

	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End

}
