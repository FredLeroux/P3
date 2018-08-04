package p3.Game;


import java.util.ArrayList;

class MoreLess extends Game {
	private char moreIndication;
	private char lessIndication;
	private char equalsIndication;	
	private String mLClues;
	private String mLCodeProposition;
	private StringBuilder moreLessAnswer;
	private ArrayList<Character> answertable;

	public MoreLess() {
		this.moreIndication = '+';
		this.lessIndication = '-';
		this.equalsIndication = '=';		
		this.mLCodeProposition = null;
		historic.clear();
	}

	public String getmLClues() {
		return mLClues;
	}

	public void setMLClues(String mLClues, String code, String codeToCompare, int elementNb) throws EntryException {
		boolean pass = true;
		try {
			// TODO Explain in presentation why the comparison allow to not check length and
			// composition
			comparison(code, codeToCompare);
			if (!answerToString().equals(mLClues))
				throw new EntryException(answerToString(), 1);
		} catch (EntryException e) {
			pass = false;
		}
		if (pass == false)
			this.mLClues = null;
		else
			this.mLClues = mLClues;
	}

	public String answerToString() {
		return moreLessAnswer.toString();
	}

	public ArrayList<Character> getAnswertable() {
		return this.answertable;
	}
	
//----------------------------------------------------------------------------------------------------
	@Override
	public void comparison(String code, String codeTocompare) {
		/** equivalent to setAnswertable */
		char moreLessAnswerElmt = 0;
		int elementsNb = code.length();
		answertable = new ArrayList<>();
		this.moreLessAnswer = new StringBuilder();

		for (int i = 0; i < elementsNb; i++) {

			if (code.charAt(i) == codeTocompare.charAt(i)) {
				moreLessAnswerElmt = this.equalsIndication;
			}
			if (code.charAt(i) < codeTocompare.charAt(i)) {
				moreLessAnswerElmt = this.lessIndication;
			}
			if (code.charAt(i) > codeTocompare.charAt(i)) {
				moreLessAnswerElmt = this.moreIndication;
			}
			this.answertable.add(moreLessAnswerElmt);
		}
		this.answertable.forEach(Elmt -> moreLessAnswer.append(Elmt));
	}
//----------------------------------------------------------------------------------------------------
	@Override
	public void autocompare(String code, String codeToCompare) {
		comparison(code, codeToCompare);
		mLClues = answerToString();
	}
//----------------------------------------------------------------------------------------------------
	@Override
	public void setHistoric(String code, String answer, int getHit) {
		historic.put(getHit, " was : " + code + " || The clues on this proposition are " + answer);
	}
	
	public String getmLCodeProposition() {
		return mLCodeProposition;
	}
//----------------------------------------------------------------------------------------------------	

	public void MorelessSecretCodeResearch(int maxRange, int minRange, int nbElements, String pcEntry) {
		int middle = (maxRange / 2);
		int maxLimit = 0;
		int minLimit = 0;
		int pcEntryElmt = 0;
		StringBuilder pcEntrySb = new StringBuilder();

		if (pcEntry == null) {
			for (int i = 0; i < nbElements; i++) {
				pcEntrySb.append(middle);
				this.mLCodeProposition = pcEntrySb.toString();
			}
		} else {
			for (int i = 0; i < nbElements; i++) {
				if (mLClues.charAt(i) == '=')
					pcEntrySb.append(pcEntry.charAt(i));
				else if (Character.getNumericValue(pcEntry.charAt(i)) == middle) {
					if (mLClues.charAt(i) == '-')
						pcEntryElmt = middle - ((middle - minRange) / 2);
					else {
						pcEntryElmt = ((maxRange - middle) / 2) + middle;
					}
					pcEntrySb.append(pcEntryElmt);
				}

				else if (Character.getNumericValue(pcEntry.charAt(i)) > middle) {
					if (mLClues.charAt(i) == '-') {
						minRange = middle;
						maxRange = Character.getNumericValue(pcEntry.charAt(i)) + 1;
						pcEntryElmt = ((maxRange - minRange) / 2) + minRange;
					} else {
						minLimit = Character.getNumericValue(pcEntry.charAt(i));
						maxLimit = maxRange + 1;
						pcEntryElmt = ((maxLimit - minLimit) / 2) + minRange;
					}
					pcEntrySb.append(pcEntryElmt);
				} else if (Character.getNumericValue(pcEntry.charAt(i)) < middle) {
					if (mLClues.charAt(i) == '-') {
						maxLimit = Character.getNumericValue(pcEntry.charAt(i));
						pcEntryElmt = maxLimit / 2;
					} else {
						minLimit = Character.getNumericValue(pcEntry.charAt(i));
						maxLimit = middle + 1;
						pcEntryElmt = ((maxLimit - minLimit) / 2) + minLimit;
					}
					pcEntrySb.append(pcEntryElmt);
				}

			}

			this.mLCodeProposition = pcEntrySb.toString();
		}
	}

	// ----------------------------------------------------------------------------------------------------
	@Override
	public void Summary(String code, String secretCode, boolean challengerMode) {
		ArrayList<String> summary = new ArrayList<>();
		String comment = null;

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
		}
		summary.add(comment);
		summary.add("Game summary : ");
		summary.forEach(elmt -> System.out.println(elmt));
	}

}
