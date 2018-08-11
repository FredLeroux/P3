package p3.Game;

import java.io.IOException;

class MoreLess extends Game {	
	private int cheatCount = 0;
	private String mLClues;
	private StringBuilder moreLessAnswer;
	

	public MoreLess() throws IOException,EntryException {		
		this.mLClues = null;
		this.moreLessAnswer = new StringBuilder();
		historicChallengerTbl.clear();
		historicDefenderTbl.clear();
	}

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

	// to overide
	public void cheatStop(String code, String codeToCompare) throws EntryException {
		cheatTentative = false;
		Comparison(code, codeToCompare);
		if (!(this.moreLessAnswer.toString().equals(mLClues))) {
			this.cheatCount++;
			try {
				if (cheatCount > 0)
					if (this.cheatCount < 3)
						throw new EntryException(cheatCount);
					else
						this.cheating = true;

			} catch (EntryException e) {
				cheatTentative = true;

			}
		}

	}

	// ----------------------------------------------------------------------------------------------------
	@Override
	public void Comparison(String code, String codeTocompare) {
		char moreIndication = '+';
		char lessIndication = '-';
		char equalsIndication ='=' ;

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
	}

	// ----------------------------------------------------------------------------------------------------
	@Override
	public void setHistoric(String code, int getHit) {
		String comment = null;
		String answer = null;

		if (cheatTentative == true && cheatCount > 0 || cheating == true) {
			comment = "(Cheating tentative or entry error).";
			answer = this.mLClues;
		} else {
			comment = " .";
			answer = answerToString();
		}
		if (challengerMode == true)
			historicChallengerTbl.add("Your proposition n°= " + getHit + " was : " + code
					+ " || The PC clues on this proposition are " + answer + comment);
		else
			historicDefenderTbl.add("Pc proposition n°= " + getHit + " was : " + code
					+ " || Your clues on this proposition are " + answer + comment);
	}

	// ----------------------------------------------------------------------------------------------------
	@Override
	public void SecretCodeResearch(String pcCodeEntry) {
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
						pcEntryElmt = ((maxRange - middle) / 2) + middle;
					}
					pcEntrySb.append(pcEntryElmt);
				} else if (Character.getNumericValue(pcCodeEntry.charAt(i)) > middle) {
					if (mLClues.charAt(i) == '-') {
						minRange = middle;
						maxRange = Character.getNumericValue(pcCodeEntry.charAt(i)) + 1;
						pcEntryElmt = ((maxRange - minRange) / 2) + minRange;
					} else {
						minLimit = Character.getNumericValue(pcCodeEntry.charAt(i));
						maxLimit = maxRange + 1;
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
	}

	// ----------------------------------------------------------------------------------------------------

}
