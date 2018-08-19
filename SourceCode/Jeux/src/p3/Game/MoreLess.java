
package p3.Game;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class MoreLess extends Game {

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
		traceVariableLogger(0, "mLClues");
		return mLClues;

	}

	public void setMLClues(String mLClues) {
		traceVariableLogger(1, "mLClues");
		this.mLClues = mLClues;
	}

	public StringBuilder getMoreLessAnswer() {
		traceVariableLogger(1, "moreLessAnswer");
		return moreLessAnswer;
	}

	public void setMoreLessAnswer(StringBuilder moreLessAnswer) {
		traceVariableLogger(0, "moreLessAnswer");
		this.moreLessAnswer = moreLessAnswer;
	}

	public String answerToString() {
		traceVariableLogger(1, "moreLessAnswer.toString()");
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

	public void traceVariableLogger(int i, String variable) {
		if (i == 0)
			MORELESS_LOGGER.trace("set " + variable);
		if (i == 1)
			MORELESS_LOGGER.trace("get " + variable);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation
	@Override
	public void comparison(String code, String codeTocompare) {
		traceMethodLogger(0, "Comparison");
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
		traceMethodLogger(1, "Comparison");
	}

	@Override
	public void secretCodeResearch(String pcCodeEntry) {
		traceMethodLogger(0, "SecretCodeResearch");
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

	@Override
	public void setHistoric(String codeProposal, int getHit) {
		traceMethodLogger(0, "setHistoric");
		String comment = null;
		String answer = null;

		if (cheatTentative == true && cheatCount > 0 || cheating == true)
			comment = "(Player cheating tentative or entry error).";

		else {
			comment = " .";

		}
		if (challengerMode == true) {
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
