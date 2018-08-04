package p3.Game;

import java.util.Scanner;

public class Main {

	public static boolean codeCompareCheck = true;
	public static boolean hintCheck = true;
	public static Scanner entry = new Scanner(System.in);
	public static int gameChoice;
	public static int choice;

	public static void main(String[] args) throws EntryException {

		do {
			System.out.println("1: More Less Game Challenger Mode");
			System.out.println("2: Mastermind Game Challenger Mode");
			System.out.println("3: More Less Game Defender Mode");
			System.out.println("4: Mastermind Game Defender Mode");
			System.out.println("5: More Less Game Duel Mode");
			System.out.println("Gamechoice ?");
			gameChoice = entry.nextInt();

			// --------------------------------------------------------------------------------------------------------------
			if (gameChoice == 1) {

				MoreLess moreLess = new MoreLess();

				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				moreLess.setVersion(entry.nextInt());
				moreLess.setChallengerMode(1);
				moreLess.setCode(moreLess.getMinRange(), moreLess.getMaxRange(), moreLess.getElementsNb());
				do {					
					do {
						moreLess.setHitCount();
						moreLess.getHistoric();
						moreLess.AskOpProposition(moreLess.getCode(), "Your proposition : ");
						moreLess.setOpEntry(entry.next());						
					} while (moreLess.getOpEntry() == null);					
					if (moreLess.getOpEntry().equals(moreLess.getCode())
					|| (moreLess.getHitCount() == moreLess.getMaxHit())) {		
				moreLess.comparison(moreLess.getCode(), moreLess.getOpEntry());
				moreLess.Summary(moreLess.getOpEntry(),moreLess.getCode(),moreLess.isChallengerMode());
				moreLess.setHistoric(moreLess.getOpEntry(),moreLess.answerToString(),moreLess.getHitCount());
				moreLess.getHistoric();
				break;
			}moreLess.setGameAnswer( moreLess.isChallengerMode());
					System.out.println(moreLess.getGameAnswer());
					moreLess.comparison(moreLess.getCode(), moreLess.getOpEntry());
					moreLess.setHistoric(moreLess.getOpEntry(),moreLess.answerToString(),moreLess.getHitCount());
					codeCompareCheck = (!moreLess.getOpEntry().equals(moreLess.getCode()));
					hintCheck = moreLess.getHitCount() < moreLess.getMaxHit();
				} while (codeCompareCheck && hintCheck);}
			// --------------------------------------------------------------------------------------------------------------
			if (gameChoice == 2) {
				Mastermind masterMind = new Mastermind();
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				masterMind.setVersion(entry.nextInt());
				masterMind.setChallengerMode(1);
				masterMind.setCode(masterMind.getMinRange(), masterMind.getMaxRange(), masterMind.getElementsNb());
				do {
					masterMind.AskOpProposition(masterMind.getCode(), "Your proposition : ");
					masterMind.setOpEntry(entry.next());
					//masterMind.setGameAnswer(masterMind.getMaxHit(), masterMind.isChallengerMode());
					System.out.println(masterMind.getGameAnswer());
					masterMind.comparison(masterMind.getCode(), masterMind.getOpEntry(), masterMind.getElementsNb());
					if (!(masterMind.getOpEntry().equals(masterMind.getCode()))) {
						//masterMind.setHistoric(masterMind.getHit(), masterMind.isChallengerMode());
						masterMind.getHistoric();
					}
					codeCompareCheck = (!masterMind.getOpEntry().equals(masterMind.getCode()));
					//hintCheck = masterMind.getHit() < masterMind.getMaxHit();
				} while (codeCompareCheck && hintCheck);
			}
			// --------------------------------------------------------------------------------------------------------------
			if (gameChoice == 3) {

				MoreLess mLDefender = new MoreLess();
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				mLDefender.setVersion(entry.nextInt());
				mLDefender.setChallengerMode(2);
				do {
					System.out.println("set your code");
					mLDefender.setOpcode(entry.next());
				} while (mLDefender.getOpcode() == null);

				do {
					mLDefender.setHitCount();
					mLDefender.secretCodeResearch(mLDefender.getMaxRange(), mLDefender.getMinRange(), mLDefender.getElementsNb(), mLDefender.getmLCodeProposition());;
					do {
						
						
						if (mLDefender.isAutoMode() == false) {
							mLDefender.AskOpClues(mLDefender.getOpcode(), mLDefender.getmLCodeProposition(),
									"Enter your clues on this proposition : ");
							mLDefender.setMLClues(entry.next(),mLDefender.getOpcode(),mLDefender.getmLCodeProposition(),mLDefender.getElementsNb());
						} else
							mLDefender.autocompare(mLDefender.getOpcode(),mLDefender.getmLCodeProposition(),mLDefender.getElementsNb());						
					} while (mLDefender.getmLClues() == null);
					if (mLDefender.getmLCodeProposition().equals(mLDefender.getOpcode())
							|| (mLDefender.getHitCount() == mLDefender.getMaxHit())) {								
						mLDefender.Summary(mLDefender.getmLCodeProposition(),mLDefender.getOpcode(),mLDefender.isChallengerMode());
						mLDefender.setHistoric(mLDefender.getmLCodeProposition(),mLDefender.answerToString(),mLDefender.getHitCount());
						mLDefender.getHistoric();
						break;
					}
					mLDefender.setGameAnswer(mLDefender.isChallengerMode());
					if (mLDefender.isAutoMode() == false)
						System.out.println(mLDefender.getGameAnswer());
					mLDefender.setHistoric(mLDefender.getmLCodeProposition(),mLDefender.answerToString(),mLDefender.getHitCount());
					System.out.println("Previous proposition(s) historic : ");
					mLDefender.getHistoric();
				} while (!mLDefender.getOpcode().equals(mLDefender.getmLCodeProposition()));
			}
			// ------------------------------------------------------------------------------------------------------------------------------------------------------
			if (gameChoice == 4) {
				Mastermind mastermindDfndr = new Mastermind();
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				mastermindDfndr.setVersion(entry.nextInt());
				mastermindDfndr.setChallengerMode(2);
				mastermindDfndr.setCodeList(mastermindDfndr.getElementsNb());
				do {
					System.out.println("set your code");
					mastermindDfndr.setOpcode(entry.next());
				} while (mastermindDfndr.getOpcode() == null);
				do {
					mastermindDfndr.pcProposition(mastermindDfndr.getCodeList());
				//	if (mastermindDfndr.getOpcode().equals(mastermindDfndr.getPcEntry())
						//	|| ((mastermindDfndr.getHit() + 1) == mastermindDfndr.getMaxHit())) {
						//mastermindDfndr.setGameAnswer(mastermindDfndr.getMaxHit(), mastermindDfndr.isChallengerMode());
						//mastermindDfndr.Summary(mastermindDfndr.getGameAnswer(),mastermindDfndr.isChallengerMode());
						//break;
					//}
					if (mastermindDfndr.isAutoMode() == false) {
						do {
							mastermindDfndr.AskOpClues(mastermindDfndr.getOpcode(), mastermindDfndr.getPcEntry(),
									"Rigth Placed Element(s) number  :");
							mastermindDfndr.setClueRightPlaced(entry.nextInt());
						} while (mastermindDfndr.getClueRightPlaced() < 0);
						do {
							mastermindDfndr.AskOpClues(mastermindDfndr.getOpcode(), mastermindDfndr.getPcEntry(),
									" Present Element(s) number :");
							mastermindDfndr.setClueElmentPresent(entry.nextInt());
						} while (mastermindDfndr.getClueElmentPresent() < 0);
					} else
						mastermindDfndr.autocompare();
					mastermindDfndr.secretCodeResearch(mastermindDfndr.getCodeList());
				//	mastermindDfndr.setGameAnswer(mastermindDfndr.getMaxHit(), mastermindDfndr.isChallengerMode());
					if (mastermindDfndr.isAutoMode() == false)
						System.out.println(mastermindDfndr.getGameAnswer());
					mastermindDfndr.setHistoric(mastermindDfndr.getHitCount(), mastermindDfndr.isChallengerMode());
				} while (!mastermindDfndr.getOpcode().equals(mastermindDfndr.getPcEntry()));
			}
			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			if (gameChoice == 5) {
				MoreLess mLDuelPlayer = new MoreLess();
				mLDuelPlayer.setChallengerMode(1);
				MoreLess mLDuelPC = new MoreLess();
				mLDuelPC.setChallengerMode(2);
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				mLDuelPlayer.setVersion(entry.nextInt());

				// --------------------- duel player set secret codde
				mLDuelPlayer.setCode(mLDuelPlayer.getMinRange(), mLDuelPlayer.getMaxRange(),
						mLDuelPlayer.getElementsNb());
				do {
					System.out.println("set your Secret code to be find by PC");
					mLDuelPC.setOpcode(entry.next());
				} while (mLDuelPC.getOpcode() == null);

				System.out.println("The game begin with your first search try on  PC secret code ");

				/* boucle test */ do {

					// -----------------------------player
					
					do {
						System.out.println("Challenger player" );
						mLDuelPlayer.getHistoric();
						mLDuelPlayer.AskOpProposition(mLDuelPlayer.getCode(), "Your proposition : ");
						mLDuelPlayer.setOpEntry(entry.next());
					} while (mLDuelPlayer.getOpEntry() == null);
				//	mLDuelPlayer.setGameAnswer(mLDuelPlayer.getMaxHit(), mLDuelPlayer.isChallengerMode());
					System.out.println(mLDuelPlayer.getGameAnswer());
					mLDuelPlayer.comparison(mLDuelPlayer.getCode(), mLDuelPlayer.getOpEntry()
							);
					//mLDuelPlayer.setHistoric(mLDuelPlayer.getHit(), mLDuelPlayer.isChallengerMode());

					// --------------------------pc
					do {
						System.out.println("Challenger PC" );
						// ----------------------duel pc set pc entry
						//mLDuelPC.secretCodeResearch();
						// ----------------------duel pc set clue
						if (mLDuelPC.isAutoMode() == false) {
							mLDuelPC.getHistoric();
							mLDuelPC.AskOpClues(mLDuelPC.getOpcode(), mLDuelPC.getPcEntry(),
									"Enter your clues on this proposition : ");
							//mLDuelPC.setMLClues(entry.next());
					} //else
							//mLDuelPC.autocompare();
					} while (mLDuelPC.getmLClues() == null);mLDuelPC.setGameAnswer( mLDuelPC.isChallengerMode());
					if (mLDuelPC.isAutoMode() == false)
						System.out.println(mLDuelPC.getGameAnswer());
					//mLDuelPC.setHistoric(mLDuelPC.getHit(), mLDuelPC.isChallengerMode());
					

					/* boucle test */ } while (!mLDuelPC.getPcEntry().equals(mLDuelPC.getOpcode()));

			}

			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			System.out.println("1: Replay");
			System.out.println("2: Quit");
			System.out.println("choice ?");
			choice = entry.nextInt();
		} while (choice == 1);

	}

}