package p3.Game;

import java.util.Scanner;

public class Launch {

	/**
	 * @param maxRange
	 *            is the maximum digit range value used at each elements composing
	 *            the code.
	 * @param nbElements
	 *            is the number of elements composing the code*
	 */
	public int maxRange;
	public int nbElements;
	public int maxHint = 0;
	public int game;
	public String settedCode;
	public boolean moreless = true;

	public Scanner ENTRY = new Scanner(System.in);

	public Launch() {

		maxRange = 0;
		nbElements = 0;
		settedCode = "null";
	}

	public static void main(String[] args) {
		// TODO replace settings by
		// config.properties------------------------------------------------------------------
		System.out.println("Please set the applcation:\n");
		System.out.println("Digit maximum range value is");
		maxRange = ENTRY.nextInt();

		System.out.println("Elements number to use to compose the secret code is");
		nbElements = ENTRY.nextInt();

		System.out.println("Maximum hint is");
		maxHint = ENTRY.nextInt();
		// -------------------------------------------------------------------------------------
		// Initializing
		// elements-------------------------------------------------------------
		CreateCode.setCodeMaxRange(maxRange);
		CreateCode.setCodeNbElements(nbElements);

		Tools.setToolsMaxHint(maxHint);
		Tools.setLooseByHintAndWinPhrs(
				"Sorry you loose, maximum hint of " + maxHint + " reached" + "\nThe secret code was : " + settedCode,
				"\n!!!!!CONGRATULATION!!!!! \nYou win ");

		Tools.setWrongCodePhrs("\nSorry this is not the secret code, ", " hint left");

		CompareMoreLess.setMoreLessEqualsIndication('+', '-', '=');

		CompareMastermind.setRightPlacedPresentIndication(" Element(s) right placed", " Element(s) presents");

		// will be replace by
		// menu---------------------------------------------------------------
		System.out.println("Select game");
		System.out.println("1: +/-");
		System.out.println("2:Mastemind");
		System.out.println("3:Defender mode +/-");
		// ---------------------------------------------------------------------------------------
		game = ENTRY.nextInt();
		CreateCode.setSelectedGamme(game);
		if (game == 1) {
			settedCode = CreateCode.generateRANDOMNb();
			CreateCode.setAskplayerProposal("Player proposal (the secretcode is " + settedCode + ")");
			do {
				Tools.getChallengerStatus();
				Tools.setLastHint("Your proposition n�=" + (Tools.toolsHint) + " was " + CreateCode.playerProposal
						+ ", the clues on this proposition are : " + CompareMoreLess.getMoreLessAnswer() + ".");
				Tools.getHistoric();

			} while (!CreateCode.playerProposal.equals(settedCode));
		}

		if (game == 2) {
			do {
				Tools.getChallengerStatus();
				Tools.setLastHint("Your proposition n�=" + (Tools.toolsHint) + " was " + CreateCode.playerProposal
						+ ", the clues on this proposition are : " + CompareMastermind.getMastermindAnswwer() + ".");
				Tools.getHistoric();

			} while (!CreateCode.playerProposal.equals(settedCode));
		}
		if (game == 3) {
			CreateCode.setAskDefenderProposal("Enter your secret code (the secretcode is )");
			CreateCode.getplayerProposal();
		}

	}

}
