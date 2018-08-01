package p3.MasterMind;

public class MmMain {
	public static MmMenuElements welcomeConstruct;
	public static MmMenuElements mainMenuConstruct;
	public static MmMenuElements gameMenuConstruct;
	public static MmMenuElements playMenuConstruct;
	public static MmMenuElements quitConstruct;
	public static String menuChoiceAquisition;
	public static String titleSetted;

	public static void main(String[] args) {

		welcomeConstruct = new MmMenuElements();
		welcomeConstruct.dappHead();
		welcomeConstruct.setTitle(MmTerms.term22);
		welcomeConstruct.dMenuTitle();
		welcomeConstruct.dAppWelcomeScreen();
		welcomeConstruct.display();
		welcomeConstruct.askChoice();

		menuChoiceAquisition = welcomeConstruct.dAppWelcomeScreen().get(MmMenuElements.choice);

		while (!menuChoiceAquisition.contains(MmTerms.term10)) {

			if (menuChoiceAquisition.contains(MmTerms.term21) || menuChoiceAquisition.contains(MmTerms.term4)) {
				mainMenuConstruct = new MmMenuElements();
				mainMenuConstruct.dappHead();
				mainMenuConstruct.setTitle(MmTerms.term19 + MmTerms.term4 + " " + MmTerms.term14 + MmTerms.term20);
				mainMenuConstruct.dMenuTitle();
				mainMenuConstruct.dMainMenuOpt();
				mainMenuConstruct.display();
				mainMenuConstruct.askChoice();
				titleSetted = mainMenuConstruct.getTitle();
				menuChoiceAquisition = mainMenuConstruct.dMainMenuOpt().get(MmMenuElements.choice);

			} else if (menuChoiceAquisition.contains(MmTerms.term15) && titleSetted.contains(MmTerms.term4)) {
				gameMenuConstruct = new MmMenuElements();
				gameMenuConstruct.dappHead();
				gameMenuConstruct.setTitle(MmTerms.term19 + menuChoiceAquisition + MmTerms.term20);
				gameMenuConstruct.dMenuTitle();
				gameMenuConstruct.dGameMenuOpt();
				gameMenuConstruct.display();
				gameMenuConstruct.askChoice();
				titleSetted = gameMenuConstruct.getTitle();
				menuChoiceAquisition = gameMenuConstruct.dGameMenuOpt().get(MmMenuElements.choice);

			} else if (menuChoiceAquisition.contains(MmTerms.term17) && titleSetted.contains(MmTerms.term15)) {
				playMenuConstruct = new MmMenuElements();
				playMenuConstruct.dappHead();
				playMenuConstruct.setTitle(titleSetted);
				playMenuConstruct.setSubTitle(MmTerms.term19 + menuChoiceAquisition + MmTerms.term20);
				playMenuConstruct.dMenuTitle();
				playMenuConstruct.dMenuSubTitle();
				playMenuConstruct.dPlayGameMenu();
				playMenuConstruct.display();
				playMenuConstruct.askChoice();
				menuChoiceAquisition = playMenuConstruct.dPlayGameMenu().get(MmMenuElements.choice);
			}

			
			/* else if (menuChoiceAquisition.contains(MmTerms.term24)){

			  new Num();}*/
			 
			else {
				// TODO to suppress when all implemented
				quitConstruct = new MmMenuElements();
				quitConstruct.dappHead();
				System.out.println(MmTerms.term23);
				System.exit(1);
			}
		}

		quitConstruct = new MmMenuElements();
		quitConstruct.dappHead();
		System.out.println(MmTerms.term23);
		System.exit(1);
		
		/*Num tes = new Num();
		tes.sDigitsTable();*/
		

	}

}
