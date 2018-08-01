package p3.MasterMind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class MmMenuElements extends MmTerms {

    private String title, subTitle;
    protected static String choiceAquisition, titleSet;
    protected static int choice;
    private final ArrayList<String> menuTitle;
    private final ArrayList<String> menuSubtitle;
    private final ArrayList<String> appHead;
    private final HashMap<Integer, String> appWelcomeScreenOpt;
    private final HashMap<Integer, String> mainMenuOpt;
    private final HashMap<Integer, String> gameMenuOpt;
    private final HashMap<Integer, String> playMenuOpt;
    private HashMap<Integer, String> optionsTable;
    private final Scanner choiceEntry = new Scanner(System.in);

    public MmMenuElements() {
        this.title = null;
        this.subTitle = null;
        this.appHead = new ArrayList<>();
        this.menuTitle = new ArrayList<>();
        this.menuSubtitle = new ArrayList<>();
        this.appWelcomeScreenOpt = new HashMap<>();
        this.mainMenuOpt = new HashMap<>();
        this.gameMenuOpt = new HashMap<>();
        this.playMenuOpt = new HashMap<>();
        this.optionsTable = new HashMap<>();

    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setTitle(String nTitle) {
        this.title = nTitle;
    }

    public void setSubTitle(String nSubTitle) {
        this.subTitle = nSubTitle;
    }

    public ArrayList<String> dMenuTitle() {
        menuTitle.add(this.title + "\n");
        menuTitle.forEach(ptitle -> System.out.println(ptitle));
        return this.menuTitle;
    }

    public ArrayList<String> dMenuSubTitle() {
        menuSubtitle.add(this.subTitle + "\n");
        menuSubtitle.forEach(psubTitle -> System.out.println(psubTitle));
        return this.menuSubtitle;
    }

    public ArrayList<String> dappHead() {
        appHead.add(MmTerms.term1);
        appHead.add(MmTerms.term2);
        appHead.add(MmTerms.term3 + "\n");
        appHead.forEach(ptitle -> System.out.println(ptitle));
        return this.appHead;
    }

    public HashMap<Integer, String> dAppWelcomeScreen() {
        appWelcomeScreenOpt.put(1, MmTerms.term21);
        appWelcomeScreenOpt.put(2, MmTerms.term10 + "\n");
        optionsTable = appWelcomeScreenOpt;
        return this.appWelcomeScreenOpt;
    }

    public HashMap<Integer, String> dMainMenuOpt() {
        mainMenuOpt.put(1, MmTerms.term5 + " " + MmTerms.term15);
        mainMenuOpt.put(2, MmTerms.term6 + " " + MmTerms.term15);
        mainMenuOpt.put(3, MmTerms.term7 + " " + MmTerms.term16);
        mainMenuOpt.put(4, MmTerms.term8);
        mainMenuOpt.put(5, MmTerms.term9);
        mainMenuOpt.put(6, MmTerms.term10 + "\n");
        optionsTable = mainMenuOpt;
        return this.mainMenuOpt;
    }

    public HashMap<Integer, String> dGameMenuOpt() {
        gameMenuOpt.put(1, MmTerms.term17 + " " + MmTerms.term11 + " " + MmTerms.term16);
        gameMenuOpt.put(2, MmTerms.term17 + " " + MmTerms.term12 + " " + MmTerms.term16);
        gameMenuOpt.put(3, MmTerms.term17 + " " + MmTerms.term13 + " " + MmTerms.term16);
        gameMenuOpt.put(4, MmTerms.term8);
        gameMenuOpt.put(5, MmTerms.term4 + " " + MmTerms.term14);
        gameMenuOpt.put(6, MmTerms.term10 + "\n");
        optionsTable = gameMenuOpt;
        return this.gameMenuOpt;
    }

    public HashMap<Integer, String> dPlayGameMenu() {
        playMenuOpt.put(1, MmTerms.term24);
        playMenuOpt.put(2, MmTerms.term4 + " " + MmTerms.term14);
        playMenuOpt.put(3, MmTerms.term10 + "\n");
        optionsTable = playMenuOpt;
        return this.playMenuOpt;
    }

    public void display() {
        optionsTable.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    public void askChoice() {
        System.out.println(MmTerms.term18);
        MmMenuElements.choice = choiceEntry.nextInt();

    }

   
}
