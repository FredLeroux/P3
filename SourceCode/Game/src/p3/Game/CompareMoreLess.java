package p3.Game;

class CompareMoreLess extends CreateCode {

    private static char moreIndication = 0;
    private static char lessIndication = 0;
    private static char equalsIndication = 0;
    private static char moreLessAnswerChar = 0;
    private static StringBuilder moreLessAnswer;

    public static void setMoreLessEqualsIndication(char strMoreIndication, char strLessIndication,
            char strEqualsIndication) {
        moreIndication = strMoreIndication;
        lessIndication = strLessIndication;
        equalsIndication = strEqualsIndication;
    }

    public static String getMoreLessAnswer() {
        moreLessAnswer = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            if (playerProposal.charAt(i) == code.charAt(i)) {
                moreLessAnswerChar = equalsIndication;
            }
            if (code.charAt(i) < playerProposal.charAt(i)) {
                moreLessAnswerChar = lessIndication;
            }
            if (code.charAt(i) > playerProposal.charAt(i)) {
                moreLessAnswerChar = moreIndication;
            }
            moreLessAnswer.append(moreLessAnswerChar);
        }

        return moreLessAnswer.toString();
    }
}

// TODO put exception in tools
/*
 * if (CODE.length() > 10) throw new RuntimeException("Range can't be >10");
 * 
 * if (!(CODE.length() == CHALLENGER_PROPOSAL.length())) { throw new
 * RuntimeException("Proposal size can not be different than code size."); }
 */
 /*
 * TODO check getter utility, I think not useful on MVVM pattern design
public * char getMoreIndication() { return moreIndication; }
 * 
 * public char getLessIndication() { return lessIndication; }
 * 
 * public char getEqualsIndication() { return equalsIndication; }
 * -----------------------------------------------------------
 */
