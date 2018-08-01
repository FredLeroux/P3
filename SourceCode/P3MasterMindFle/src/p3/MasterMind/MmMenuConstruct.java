package p3.MasterMind;

public class MmMenuConstruct {

    private final MmMenuElements welcomeConstruct;
    private final MmMenuElements mainMenuConstruct;
    private final MmMenuElements gameMenuConstruct;
    private final MmMenuElements playMenuConstruct;
    protected static String menuChoiceAquisition, titleSetted;

    public MmMenuConstruct() {
        this.welcomeConstruct = new MmMenuElements();
        this.mainMenuConstruct = new MmMenuElements();
        this.gameMenuConstruct = new MmMenuElements();
        this.playMenuConstruct = new MmMenuElements();

        menuChoiceAquisition = null;
        titleSetted = null;

        if (MmMenuElements.choice == 0) {
            welcomeConstruct.dappHead();
            welcomeConstruct.setTitle(MmTerms.term22);
            welcomeConstruct.dMenuTitle();
            welcomeConstruct.dAppWelcomeScreen();
            welcomeConstruct.display();
            welcomeConstruct.askChoice();
            titleSetted = welcomeConstruct.getTitle();
            menuChoiceAquisition = welcomeConstruct.dAppWelcomeScreen().get(MmMenuElements.choice);
            welcomeConstruct.loop();

        } else if (MmMenuElements.choiceAquisition.contains(MmTerms.term21)
                || MmMenuElements.choiceAquisition.contains(MmTerms.term4)) {
            mainMenuConstruct.dappHead();
            mainMenuConstruct.setTitle(MmTerms.term19 + MmTerms.term4 + " " + MmTerms.term14 + MmTerms.term20);
            mainMenuConstruct.dMenuTitle();
            mainMenuConstruct.dMainMenuOpt();
            mainMenuConstruct.display();
            mainMenuConstruct.askChoice();
            titleSetted = mainMenuConstruct.getTitle();
            menuChoiceAquisition = mainMenuConstruct.dMainMenuOpt().get(MmMenuElements.choice);
            welcomeConstruct.loop();

        } else if (MmMenuElements.choiceAquisition.contains(MmTerms.term15)
                && MmMenuElements.titleSet.contains(MmTerms.term4)) {
            gameMenuConstruct.dappHead();
            gameMenuConstruct.setTitle(MmTerms.term19 + MmMenuElements.choiceAquisition + MmTerms.term20);
            gameMenuConstruct.dMenuTitle();
            gameMenuConstruct.dGameMenuOpt();
            gameMenuConstruct.display();
            gameMenuConstruct.askChoice();
            titleSetted = gameMenuConstruct.getTitle();
            menuChoiceAquisition = gameMenuConstruct.dGameMenuOpt().get(MmMenuElements.choice);
            gameMenuConstruct.loop();
        } else if (MmMenuElements.choiceAquisition.contains(MmTerms.term17)
                && MmMenuElements.titleSet.contains(MmTerms.term15)) {

            playMenuConstruct.dappHead();
            playMenuConstruct.setTitle(MmMenuElements.titleSet);
            playMenuConstruct.setSubTitle(MmTerms.term19 + MmMenuElements.choiceAquisition + MmTerms.term20);
            playMenuConstruct.dMenuTitle();
            playMenuConstruct.dMenuSubTitle();
            playMenuConstruct.dPlayGameMenu();
            playMenuConstruct.display();
            playMenuConstruct.askChoice();
            menuChoiceAquisition = playMenuConstruct.dPlayGameMenu().get(MmMenuElements.choice);
            playMenuConstruct.loop();
        } else if (MmMenuElements.choiceAquisition.contains(MmTerms.term24)) {
            new MmMain();
        } else {
            welcomeConstruct.dappHead();
            System.out.println(MmTerms.term23);
            System.exit(1);
        }

    }

}
