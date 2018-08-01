package p3.Game;

import java.util.HashSet;

class CompareMastermind extends CreateCode {

    private static int nbRightPlaced;
    private static int nbPresent = 0;
    private static String rightPlacedIndication = null;
    private static String presentIndication = null;
    private static String mastermindAnswer = null;

    public static void setRightPlacedPresentIndication(String strRightPlacedIndication, String strPresentIndication) {
        rightPlacedIndication = strRightPlacedIndication;
        presentIndication = strPresentIndication;
    }

    public static String getMastermindAnswwer() {
        nbRightPlaced = 0;
        HashSet<Character> chlngrProposal = new HashSet<>();
        HashSet<Character> secretCode = new HashSet<>();

        for (int i = 0; i < code.length(); i++) {

            chlngrProposal.add(playerProposal.charAt(i));
            secretCode.add(code.charAt(i));
            if (code.charAt(i) == playerProposal.charAt(i)) {
                nbRightPlaced++;
            }
        }
        chlngrProposal.retainAll(secretCode);
        nbPresent = chlngrProposal.size();
        mastermindAnswer = nbRightPlaced + " " + rightPlacedIndication + ", " + nbPresent + " " + presentIndication;

        return mastermindAnswer;

    }

}
