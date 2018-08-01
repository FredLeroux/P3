package p3.Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CreateCode {

	protected String code = null;
	protected String playerProposal = null;

	protected int selectedGame = 0;
	private int codeMaxRange = 0;
	private int codeNbElements = 0;
	private int element = 0;
	private int gElement = 0;
	private String askplayerProposal = null;
	private String askDefenderProposal = null;
	private final StringBuilder PC_CODE = new StringBuilder();
	private final Random RANDOM = new Random();
	private final Scanner ENTRY = new Scanner(System.in);
	
	
	
	
	
	
	

	public void setCodeMaxRange(int iCodeMaxrange) {
		codeMaxRange = iCodeMaxrange;
	}

	public void setCodeNbElements(int iCodeNbElements) {
		codeNbElements = iCodeNbElements;
	}

	public void setAskplayerProposal(String strAskplayerProposal) {
		askplayerProposal = strAskplayerProposal;
	}

	public void setAskDefenderProposal(String strAskDefenderProposal) {
		askDefenderProposal = strAskDefenderProposal;
	}

	public void setSelectedGamme(int iSelectedGame) {
		selectedGame = iSelectedGame;
	}

	public String generateRANDOMNb() {
		if (selectedGame == 1 || selectedGame == 2) {
			ArrayList<Integer> sRange = new ArrayList<>();
			for (int i = 0; i < codeMaxRange; i++) {
				sRange.add(i);
			}
			for (int i = 0; i < codeNbElements; i++) {
				gElement = RANDOM.nextInt(sRange.size());
				element = sRange.get(gElement);
				PC_CODE.append(element);
				sRange.remove(gElement);
			}
			code = PC_CODE.toString();
		}
		if (selectedGame == 3 || selectedGame == 4) {
			code = playerProposal;
		}
		return code;
	}

	public void getplayerProposal() {
		if (selectedGame == 1 || selectedGame == 2) {
			System.out.println(askplayerProposal);
		}
		if (selectedGame == 3 || selectedGame == 4) {
			System.out.println(askDefenderProposal);

		}
		playerProposal = ENTRY.next();
	}

	/*
	 * TODO put exception in tools if (codeMaxRange< 1 || codeMaxRange > 10) { throw
	 * new RuntimeException("Digit maximum range have to be between 1 and 10"); } if
	 * (codeNbElements < 1 || codeNbElements > codeMaxRange) { throw new
	 * RuntimeException("Elements number to use have to be between 1 and digit maximum range"
	 * ); }
	 */
}
