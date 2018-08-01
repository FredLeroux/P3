package p3.Game;


import java.util.ArrayList;

class MoreLess extends Game {
	private char moreIndication;
	private char lessIndication;
	private char equalsIndication;	
	private String mLClues;	
	private StringBuilder moreLessAnswer;
	private ArrayList<Character> answertable;
	private int cheatCount =0;

	public MoreLess() {
		this.moreIndication = '+';
		this.lessIndication = '-';
		this.equalsIndication = '=';		
		historic.clear();
	}

	public String getmLClues() {
		return mLClues;
	}

	public void setMLClues(String mLClues) {		
		
			this.mLClues = mLClues;
	}
	
	

	public String answerToString() {
		return moreLessAnswer.toString();
	}

	public ArrayList<Character> getAnswertable() {
		return this.answertable;
	}
	
	public void cheatStop(String code, String codeToCompare, String mLClues) throws EntryException {
		comparison(code, codeToCompare);		
		if (!(this.moreLessAnswer.toString().equals(mLClues))) {
			this.cheatCount++;
			try {
				if (cheatCount>0)
					if (this.cheatCount<3)
					throw new EntryException(cheatCount);
					else this.cheating = true;
						
			} catch (EntryException e) {				
				this.mLClues = null;
				 
					}}
		
		else 		
		 this.mLClues=mLClues;

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
	public void setHistoric(String code, String answer, int getHit) {
		String comment = null;
		if(mLClues==null&&cheatCount>0 || cheating ==true)
			comment = "(Cheating tentative).";
		else 
			comment =".";
		historic.put(getHit, " was : " + code + " || The clues on this proposition are " + answer + comment);
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
				this.pcCode= pcEntrySb.toString();
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

			this.pcCode = pcEntrySb.toString();
		}
	}

	// ----------------------------------------------------------------------------------------------------
	
	
}
