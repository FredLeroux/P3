
package p3.Game;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	// TODO JAVADOC
	// TODO Clean and classified all methode
	// TODO methode to check integrity config.prop

	// TODO methode repaire config properties

	public static boolean entryPass = true;
	public static boolean end = false;
	public static Scanner entry = new Scanner(System.in);
	public static int gameChoice;
	public static int choice;

	public static void main(String[] args) throws EntryException, IOException {

		GameParameters gameConfiguration = new GameParameters();

		// gameConfiguration.readConfiguration();
		System.out.println("Application current settings");
		gameConfiguration.displayCurrentValue();
		System.out.println("\n");

		do {

			System.out.println("1: More Less Game Challenger Mode");
			System.out.println("2: Mastermind Game Challenger Mode");
			System.out.println("3: More Less Game Defender Mode");
			System.out.println("4: Mastermind Game Defender Mode");
			System.out.println("5: More Less Game Duel Mode");
			System.out.println("6: Configuration ");
			System.out.println("Gamechoice ?");
			gameChoice = entry.nextInt();

			// --------------------------------------------------------------------------------------------------------------
			if (gameChoice == 1) {
				MoreLess moreLess = new MoreLess();
				moreLess.setChallengerMode(1);
				moreLess.setCode();

				do {
					moreLess.setHit();
					do {

						moreLess.tableDisplay(moreLess.getPreviousHit());
						System.out.print("\n");
						moreLess.AskOpProposition(moreLess.getCode(),
								"Please enter your proposition n°= " + moreLess.getHit());
						moreLess.setOpEntry(entry.next());
						entryPass = moreLess.opEntryCodeCheck();
					} while (entryPass == false);
					moreLess.setOpProposal(moreLess.getOpEntry());
					moreLess.comparison(moreLess.getCode(), moreLess.getOpProposal());
					moreLess.setHistoric(moreLess.getOpProposal(), moreLess.getHit());
					moreLess.setPreviousHit("Player previous proposition : ");
					if (!moreLess.getOpProposal().equals(moreLess.getCode())) {
						moreLess.setGameAnswer();
						System.out.println(moreLess.getGameAnswer());
					}
					end = moreLess.gameStatu();
				} while (end == false);
				moreLess.Summary();
			}
			// --------------------------------------------------------------------------------------------------------------

			if (gameChoice == 2) {
				Mastermind masterMind = new Mastermind();
				masterMind.setChallengerMode(1);
				masterMind.setCode();

				do {
					masterMind.setHit();
					do {

						masterMind.tableDisplay(masterMind.getPreviousHit());
						System.out.print("\n");
						masterMind.AskOpProposition(masterMind.getCode(),
								"Please enter your proposition n°= " + masterMind.getHit());
						masterMind.setOpEntry(entry.next());
						entryPass = masterMind.opEntryCodeCheck();
					} while (entryPass == false);
					masterMind.setOpProposal(masterMind.getOpEntry());
					masterMind.comparison(masterMind.getCode(), masterMind.getOpProposal());
					masterMind.setHistoric(masterMind.getOpProposal(), masterMind.getHit());
					masterMind.setPreviousHit("Player previous proposition : ");
					if (!masterMind.getOpProposal().equals(masterMind.getCode())) {
						masterMind.setGameAnswer();
						System.out.println(masterMind.getGameAnswer());
					}
					end = masterMind.gameStatu();
				} while (end == false);
				masterMind.Summary();
			}
			// --------------------------------------------------------------------------------------------------------------
			if (gameChoice == 3) {

				MoreLess mLDefender = new MoreLess();
				mLDefender.setChallengerMode(0);
				do {
					System.out.println("set your code");
					mLDefender.setOpEntry(entry.next());
					entryPass = mLDefender.opEntryCodeCheck();
				} while (entryPass == false);
				mLDefender.setOpCode(mLDefender.getOpEntry());

				do {
					mLDefender.setHit();
					mLDefender.secretCodeResearch(mLDefender.getPcProposal());

					do {
						do {
							mLDefender.tableDisplay(mLDefender.getPreviousHit());
							if (mLDefender.isAutoMode() == false) {
								mLDefender.AskOpClues(mLDefender.getOpCode(), mLDefender.getPcProposal(),
										"Enter your clues on this proposition : ");
								mLDefender.setOpEntry(entry.next());
								entryPass = mLDefender.entryContentsCheck(mLDefender.getOpEntry(), "+-=");
							} else {
								mLDefender.comparison(mLDefender.getOpCode(), mLDefender.getPcProposal());
								mLDefender.setOpEntry(mLDefender.answerToString());
							}
						} while (entryPass == false);
						mLDefender.setMLClues(mLDefender.getOpEntry());
						mLDefender.cheatStop(mLDefender.getOpCode(), mLDefender.getPcProposal());

						if (mLDefender.isAutoMode() == false) {
							if (!mLDefender.getPcProposal().equals(mLDefender.getOpCode())) {
								mLDefender.setGameAnswer();
							}
							mLDefender.setHistoric(mLDefender.getPcProposal(), mLDefender.getHit());
							mLDefender.setPreviousHit("Pc previous proposition : ");
							System.out.println(mLDefender.getGameAnswer());
						} else {
							mLDefender.setHistoric(mLDefender.getPcProposal(), mLDefender.getHit());
						}

					} while (mLDefender.isCheatTentative() == true);

					end = mLDefender.gameStatu();
				} while (end == false);

				mLDefender.Summary();

			}
			// ------------------------------------------------------------------------------------------------------------------------------------------------------

			if (gameChoice == 4) {
				Mastermind mastermindDfndr = new Mastermind();
				mastermindDfndr.setChallengerMode(0);
				do {
					System.out.println("set your code");
					mastermindDfndr.setOpEntry(entry.next());
					entryPass = mastermindDfndr.opEntryCodeCheck();
				} while (entryPass == false);
				mastermindDfndr.setOpCode(mastermindDfndr.getOpEntry());

				do {
					mastermindDfndr.setHit();
					mastermindDfndr.secretCodeResearch(mastermindDfndr.getPcProposal());

					do {
						mastermindDfndr.tableDisplay(mastermindDfndr.getPreviousHit());
						if (mastermindDfndr.isAutoMode() == false) {
							do {
								mastermindDfndr.AskOpClues(mastermindDfndr.getOpCode(), mastermindDfndr.getPcProposal(),
										"Enter the Right placed element(s) number: ");
								mastermindDfndr.setOpEntry(entry.next());
								entryPass = mastermindDfndr.entryIntegerCheck(mastermindDfndr.getOpEntry());
							} while (entryPass == false);
							mastermindDfndr.setClueRightPlaced(mastermindDfndr.getOpEntry());
							do {
								mastermindDfndr.AskOpClues(mastermindDfndr.getOpCode(), mastermindDfndr.getPcProposal(),
										"Enter the Present element(s) number: ");
								mastermindDfndr.setOpEntry(entry.next());
								entryPass = mastermindDfndr.entryIntegerCheck(mastermindDfndr.getOpEntry());
							} while (entryPass == false);
							mastermindDfndr.setClueElmentPresent(mastermindDfndr.getOpEntry());
						} else {
							mastermindDfndr.comparison(mastermindDfndr.getOpCode(), mastermindDfndr.getPcProposal());
							mastermindDfndr.setClueRightPlaced(
									mastermindDfndr.intToString(mastermindDfndr.getNbRightPlaced()));
							mastermindDfndr
									.setClueElmentPresent(mastermindDfndr.intToString(mastermindDfndr.getNbPresent()));
						}

						mastermindDfndr.cheatStop(mastermindDfndr.getOpCode(), mastermindDfndr.getPcProposal());
						if (mastermindDfndr.isAutoMode() == false) {
							if (!mastermindDfndr.getPcProposal().equals(mastermindDfndr.getOpCode())) {
								mastermindDfndr.setGameAnswer();
							}
							mastermindDfndr.setHistoric(mastermindDfndr.getPcProposal(), mastermindDfndr.getHit());
							mastermindDfndr.setPreviousHit("Pc previous proposition : ");
							System.out.println(mastermindDfndr.getGameAnswer());
						} else {
							mastermindDfndr.setHistoric(mastermindDfndr.getPcProposal(), mastermindDfndr.getHit());
						}

					} while (mastermindDfndr.isCheatTentative() == true);

					end = mastermindDfndr.gameStatu();
				} while (end == false);

				mastermindDfndr.Summary();

			}

			// --------------------------------------------------------------------------------------------------------------------------------------------------------
			if (gameChoice == 5) {
				MoreLess mLDuel = new MoreLess();
				mLDuel.setDuelMode(1);
				mLDuel.setChallengerMode(1);
				mLDuel.setCode();

				do {
					System.out.println("set your code");
					mLDuel.setOpEntry(entry.next());
					mLDuel.entryCheckLength(mLDuel.getOpEntry(), mLDuel.getElementsNb());
					mLDuel.entryIntegerCheck(mLDuel.getOpEntry());
					mLDuel.entryDuplicateCheck(mLDuel.getOpEntry());
					mLDuel.entryIntegerRangeCheck(mLDuel.getOpEntry());
				} while (mLDuel.getOpEntry() == null);
				mLDuel.setOpCode(mLDuel.getOpEntry());
				do {
					mLDuel.setHit();

					do {
						mLDuel.setChallengerMode(1);
						mLDuel.setPreviousHit("Player previous proposition : ");
						mLDuel.tableDisplay(mLDuel.getPreviousHit());

						mLDuel.AskOpProposition(mLDuel.getCode(),
								"Please enter your proposition n°= " + mLDuel.getHit());
						mLDuel.setOpEntry(entry.next());
						mLDuel.entryCheckLength(mLDuel.getOpEntry(), mLDuel.getElementsNb());
						mLDuel.entryIntegerCheck(mLDuel.getOpEntry());
					} while (mLDuel.getOpEntry() == null);
					mLDuel.setOpProposal(mLDuel.getOpEntry());
					mLDuel.comparison(mLDuel.getCode(), mLDuel.getOpProposal());
					mLDuel.setHistoric(mLDuel.getOpProposal(), mLDuel.getHit());

					mLDuel.setGameAnswer();
					System.out.println(mLDuel.getGameAnswer());

					mLDuel.secretCodeResearch(mLDuel.getPcProposal());
					do {

						do {
							mLDuel.setChallengerMode(0);

							mLDuel.setPreviousHit("Pc previous proposition : ");
							mLDuel.tableDisplay(mLDuel.getPreviousHit());

							if (mLDuel.isAutoMode() == false) {
								mLDuel.AskOpClues(mLDuel.getOpCode(), mLDuel.getPcProposal(),
										"Please enter your clues on this proposition : ");
								mLDuel.setOpEntry(entry.next());
								entryPass = mLDuel.entryContentsCheck(mLDuel.getOpEntry(), "+=-");
							} else {
								mLDuel.comparison(mLDuel.getOpCode(), mLDuel.getPcProposal());
								mLDuel.setOpEntry(mLDuel.answerToString());
							}
						} while (entryPass == false);
						mLDuel.setMLClues(mLDuel.getOpEntry());
						mLDuel.cheatStop(mLDuel.getOpCode(), mLDuel.getPcProposal());

						if (mLDuel.isAutoMode() == false) {
							if (!mLDuel.getPcProposal().equals(mLDuel.getOpCode())) {
								mLDuel.setGameAnswer();
							}
							mLDuel.setHistoric(mLDuel.getPcProposal(), mLDuel.getHit());
							if (mLDuel.codeEquivalenceCheck(mLDuel.getCode(), mLDuel.getOpProposal()) == false)
								System.out.println(mLDuel.getGameAnswer());
						} else {
							mLDuel.setHistoric(mLDuel.getPcProposal(), mLDuel.getHit());
						}
					} while (mLDuel.isCheatTentative() == true);

					end = mLDuel.gameStatu();
				} while (end == false);

				mLDuel.Summary();
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			if (gameChoice == 6) {
				Mastermind mastermindDuel = new Mastermind();
				mastermindDuel.setDuelMode(1);
				mastermindDuel.setChallengerMode(1);
				mastermindDuel.setCode();

				do {
					System.out.println("set your code");
					mastermindDuel.setOpEntry(entry.next());
					mastermindDuel.entryCheckLength(mastermindDuel.getOpEntry(), mastermindDuel.getElementsNb());
					mastermindDuel.entryIntegerCheck(mastermindDuel.getOpEntry());
					mastermindDuel.entryDuplicateCheck(mastermindDuel.getOpEntry());
					mastermindDuel.entryIntegerRangeCheck(mastermindDuel.getOpEntry());
				} while (mastermindDuel.getOpEntry() == null);
				mastermindDuel.setOpCode(mastermindDuel.getOpEntry());
				do {
					mastermindDuel.setHit();

					do {
						mastermindDuel.setChallengerMode(1);
						mastermindDuel.setPreviousHit("Player previous proposition : ");
						mastermindDuel.tableDisplay(mastermindDuel.getPreviousHit());

						mastermindDuel.AskOpProposition(mastermindDuel.getCode(),
								"Please enter your proposition n°= " + mastermindDuel.getHit());
						mastermindDuel.setOpEntry(entry.next());
						mastermindDuel.entryCheckLength(mastermindDuel.getOpEntry(), mastermindDuel.getElementsNb());
						mastermindDuel.entryIntegerCheck(mastermindDuel.getOpEntry());
					} while (mastermindDuel.getOpEntry() == null);
					mastermindDuel.setOpProposal(mastermindDuel.getOpEntry());
					mastermindDuel.comparison(mastermindDuel.getCode(), mastermindDuel.getOpProposal());
					mastermindDuel.setHistoric(mastermindDuel.getOpProposal(), mastermindDuel.getHit());

					mastermindDuel.setGameAnswer();
					System.out.println(mastermindDuel.getGameAnswer());

					mastermindDuel.secretCodeResearch(mastermindDuel.getPcProposal());
					do {

						mastermindDuel.setChallengerMode(0);

						mastermindDuel.setPreviousHit("Pc previous proposition : ");
						mastermindDuel.tableDisplay(mastermindDuel.getPreviousHit());

						if (mastermindDuel.isAutoMode() == false) {
							do {
								mastermindDuel.AskOpClues(mastermindDuel.getOpCode(), mastermindDuel.getPcProposal(),
										"Enter the Right placed element(s) number: ");
								mastermindDuel.setOpEntry(entry.next());
								entryPass = mastermindDuel.entryIntegerCheck(mastermindDuel.getOpEntry());
							} while (entryPass == false);
							mastermindDuel.setClueRightPlaced(mastermindDuel.getOpEntry());
							do {
								mastermindDuel.AskOpClues(mastermindDuel.getOpCode(), mastermindDuel.getPcProposal(),
										"Enter the Present element(s) number: ");
								mastermindDuel.setOpEntry(entry.next());
								entryPass = mastermindDuel.entryIntegerCheck(mastermindDuel.getOpEntry());
							} while (entryPass == false);
							mastermindDuel.setClueElmentPresent(mastermindDuel.getOpEntry());
						} else {
							mastermindDuel.comparison(mastermindDuel.getOpCode(), mastermindDuel.getPcProposal());
							mastermindDuel
									.setClueRightPlaced(mastermindDuel.intToString(mastermindDuel.getNbRightPlaced()));
							mastermindDuel
									.setClueElmentPresent(mastermindDuel.intToString(mastermindDuel.getNbPresent()));
						}

						mastermindDuel.cheatStop(mastermindDuel.getOpCode(), mastermindDuel.getPcProposal());

						if (mastermindDuel.isAutoMode() == false) {
							if (!mastermindDuel.getPcProposal().equals(mastermindDuel.getOpCode())) {
								mastermindDuel.setGameAnswer();
							}
							mastermindDuel.setHistoric(mastermindDuel.getPcProposal(), mastermindDuel.getHit());
							if (mastermindDuel.codeEquivalenceCheck(mastermindDuel.getCode(),
									mastermindDuel.getOpProposal()) == false)
								System.out.println(mastermindDuel.getGameAnswer());
						} else {
							mastermindDuel.setHistoric(mastermindDuel.getPcProposal(), mastermindDuel.getHit());
						}
					} while (mastermindDuel.isCheatTentative() == true);

					end = mastermindDuel.gameStatu();
				} while (end == false);

				mastermindDuel.Summary();
			}

			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			if (gameChoice == 7) {

				gameConfiguration.readConfiguration();

				System.out.println("Configurable parameters list:\n");
				gameConfiguration.parametersList();
				do {
					System.out.println("\nWhat is the parameter you would like to change?");
					gameConfiguration.setKeyToSet(entry.nextInt());
				} while (gameConfiguration.optionNumberCheck(gameConfiguration.getKeyToSet(),
						gameConfiguration.getConfigElements().size()) == false);
				do {
					System.out.println("what is the value you want set on "
							+ gameConfiguration.getConfigElements().get(gameConfiguration.getKeyToSet())
							+ " parameter");

					gameConfiguration.setValueToSet(entry.next());
					gameConfiguration.saveConfiguration();
				} while (gameConfiguration.getValueToSet() == null);
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			System.out.println("1: Replay");
			System.out.println("2: Quit");
			System.out.println("choice ?");
			choice = entry.nextInt();
		} while (choice == 1);

	}

}
