package p3.Game;

import java.util.ArrayList;

class Tools extends CreateCode {

    protected static int toolsHint = 0;
    private static int toolsMaxHint = 0;
    private static String lastHint = null;
    private static String phrase = null;
    private static String looseByHintPhrs = null;
    private static String wrongCodePhrs = null;
    private static String hintLeftPhrs = null;
    private static String winPhrs = null;// Phrs = phrase
    private static final ArrayList<String> HISTORIC = new ArrayList<>();

    public static void setToolsMaxHint(int iToolsMaxHint) {
        toolsMaxHint = iToolsMaxHint;
    }

    public static void setLastHint(String strLastHint) {
        lastHint = strLastHint;
    }

    public static void setLooseByHintAndWinPhrs(String strLooseByHintPhrs, String strWinPhrs) {
        looseByHintPhrs = strLooseByHintPhrs;
        winPhrs = strWinPhrs;
    }

    public static void setWrongCodePhrs(String strWrongCodePhrs, String strHintLeftPhrs) {
        wrongCodePhrs = strWrongCodePhrs;
        hintLeftPhrs = strHintLeftPhrs;
    }

    public static void getChallengerStatus() {

        if (toolsHint + 1 > toolsMaxHint) {
            phrase = looseByHintPhrs;
            System.out.println(phrase);
            // TODO system exit will be replace by menu: 1:replay 2:main menu 3:quit
            System.exit(1);
        }
        getplayerProposal();
        if (playerProposal.equals(code)) {
            phrase = winPhrs;
            System.out.println(phrase);
            // TODO system exit will be replace by menu: 1:replay 2:main menu 3:quit
            System.exit(1);
        }
        if (!playerProposal.equals(code)) {
            System.out.println(wrongCodePhrs + (toolsMaxHint - (toolsHint + 1)) + hintLeftPhrs);
        }
        toolsHint++;
    }

    public static ArrayList<String> getHistoric() {

        HISTORIC.add(lastHint);
        HISTORIC.forEach(hint -> System.out.println(hint));
        System.out.print("\n");
        return HISTORIC;
    }

}
