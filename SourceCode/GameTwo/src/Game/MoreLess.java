package Game;

import java.util.ArrayList;

class MoreLess extends Game {

	private String moreIndication;
	private String lessIndication;
	private String equalsIndication;
	private String moreLessAnswerElmt;
	private StringBuilder moreLessAnswer;
	/**
	 * answertable = ArrayList <String> to allow better flexibility on more,less,
	 * equals indication
	 */

	private ArrayList<String> answertable;

	public MoreLess() {
		/**
		 * more, less and equals are set by default here, getter and setter allow
		 * override in case of need
		 */
		this.moreIndication = "+";
		this.lessIndication = "-";
		this.equalsIndication = "=";
		this.moreLessAnswerElmt = "null";

	}

	public String getMoreIndication() {
		return this.moreIndication;
	}

	public void setMoreIndication(String strMoreIndication) {
		this.moreIndication = strMoreIndication;
	}

	public String getLessIndication() {
		return this.lessIndication;
	}

	public void setLessIndication(String strLessIndication) {
		this.lessIndication = strLessIndication;
	}

	public String getEqualsIndication() {
		return this.equalsIndication;
	}

	public void setEqualsIndication(String strEqualsIndication) {
		this.equalsIndication = strEqualsIndication;
	}

	public StringBuilder getMorelessAnswer() {
		return this.moreLessAnswer;
	}

	public void setMorelessAnswer() {
		this.moreLessAnswer = new StringBuilder();
		this.answertable.forEach(Elmt -> moreLessAnswer.append(Elmt));

	}

	public String answerToString() {
		return moreLessAnswer.toString();
	}

	public ArrayList<String> getAnswertable() {
		return this.answertable;
	}

	@Override
	/**
	 * @param secretCode
	 *            define the secret code to find generate by PC or by the Operator
	 * @param codeToComapre
	 *            define the code to compare generate by PC or by the Operator;
	 */
	public void comparison(String secretCode, String codeToComapre) {
		/** equivalent to setAnswertable */
		answertable = new ArrayList<>();
		for (int i = 0; i < secretCode.toString().length(); i++) {
			if (codeToComapre.charAt(i) == secretCode.toString().charAt(i)) {
				this.moreLessAnswerElmt = this.equalsIndication;
			}
			if (codeToComapre.charAt(i) > secretCode.toString().charAt(i)) {
				this.moreLessAnswerElmt = this.lessIndication;
			}
			if (codeToComapre.charAt(i) < secretCode.toString().charAt(i)) {
				this.moreLessAnswerElmt = this.moreIndication;
			}
			this.answertable.add(this.moreLessAnswerElmt);
		}

	}

}
