
package p3.Game;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
//TODO JAVADOC
//TODO Clean and classified all methode
	//TODO methode to check integrity config.prop
	
	//TODO methode repaire config properties
	
	public static boolean entryPass = true;
	public static boolean end = false;	
	public static Scanner entry = new Scanner(System.in);
	public static int gameChoice;
	public static int choice;
	
	 
	
	public static void main(String[] args) throws EntryException, IOException {
		
		GameParameters gameConfiguration = new GameParameters();
		
		//gameConfiguration.readConfiguration();		
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
						entryPass = moreLess.opEntryCheck();
					} while (moreLess.getOpEntry() == null);
					moreLess.setOpProposal(moreLess.getOpEntry());
					moreLess.Comparison(moreLess.getCode(), moreLess.getOpProposal());
					moreLess.setHistoric(moreLess.getOpProposal(), moreLess.getHit());
					moreLess.setPreviousHit("Player previous proposition : ");
					if (!moreLess.getOpProposal().equals(moreLess.getCode())) {
						moreLess.setGameAnswer();
						System.out.println(moreLess.getGameAnswer());
					}
					end=moreLess.gameStatu();
				} while (end==false);
				moreLess.Summary();
			}
			// --------------------------------------------------------------------------------------------------------------
			/*
			 * if (gameChoice == 2) { Mastermind masterMind = new Mastermind();
			 * System.out.println("Select Version"); System.out.println("1: Basis");
			 * System.out.println("2: Variante"); masterMind.setVersion(entry.nextInt());
			 * masterMind.setChallengerMode(1); masterMind.setCode(masterMind.getMinRange(),
			 * masterMind.getMaxRange(), masterMind.getElementsNb()); do {
			 * masterMind.AskOpProposition(masterMind.getPcCode(), "Your proposition : ");
			 * masterMind.setOpcodeEntry(entry.next()); //
			 * masterMind.setGameAnswer(masterMind.getMaxHit(), //
			 * masterMind.isChallengerMode());
			 * System.out.println(masterMind.getGameAnswer());
			 * masterMind.comparison(masterMind.getPcCode(), masterMind.getOpcodeEntry());
			 * if (!(masterMind.getOpcodeEntry().equals(masterMind.getPcCode()))) { //
			 * masterMind.setHistoric(masterMind.getHit(), masterMind.isChallengerMode());
			 * masterMind.getHistoric(); } codeCompareCheck =
			 * (!masterMind.getOpcodeEntry().equals(masterMind.getPcCode())); // hintCheck =
			 * masterMind.getHit() < masterMind.getMaxHit(); } while (codeCompareCheck &&
			 * hintCheck); }
			 */
			// --------------------------------------------------------------------------------------------------------------
			if (gameChoice == 3) {
				
				MoreLess mLDefender = new MoreLess();				
				mLDefender.setChallengerMode(0);
				do {
					System.out.println("set your code");
					mLDefender.setOpEntry(entry.next());					
					entryPass = mLDefender.opEntryCheck();
				} while (entryPass==false);
				mLDefender.setOpCode(mLDefender.getOpEntry());

				do {
					mLDefender.setHit();
					mLDefender.SecretCodeResearch(mLDefender.getPcProposal());

					do {
						do {
						mLDefender.tableDisplay(mLDefender.getPreviousHit());
						if (mLDefender.isAutoMode() == false) {
							mLDefender.AskOpClues(mLDefender.getOpCode(), mLDefender.getPcProposal(),
									"Enter your clues on this proposition : ");
							mLDefender.setOpEntry(entry.next());
							mLDefender.entryCheckLength(mLDefender.getOpEntry(), mLDefender.getElementsNb());
							mLDefender.entryContentsCheck(mLDefender.getOpEntry(), "+-=");
						} else {
							mLDefender.Comparison(mLDefender.getOpCode(), mLDefender.getPcProposal());
							mLDefender.setOpEntry(mLDefender.answerToString());
						}
					} while (mLDefender.getOpEntry() == null);
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

				}while (mLDefender.isCheatTentative() == true);
						
					end=mLDefender.gameStatu();
				} while (end==false);

				mLDefender.Summary();

			}
			// ------------------------------------------------------------------------------------------------------------------------------------------------------

			/*
			 * if (gameChoice == 4) { Mastermind mastermindDfndr = new Mastermind();
			 * System.out.println("Select Version"); System.out.println("1: Basis");
			 * System.out.println("2: Variante");
			 * mastermindDfndr.setVersion(entry.nextInt());
			 * mastermindDfndr.setChallengerMode(2);
			 * mastermindDfndr.setCodeList(mastermindDfndr.getElementsNb()); do {
			 * System.out.println("set your code");
			 * mastermindDfndr.setOpcodeEntry(entry.next()); } while
			 * (mastermindDfndr.getOpcodeEntry() == null); do {
			 * mastermindDfndr.pcProposition(mastermindDfndr.getCodeList()); if
			 * (mastermindDfndr.getOpCode().equals(mastermindDfndr.getPcEntry()) ||
			 * ((mastermindDfndr.getHit() + 1) == mastermindDfndr.getMaxHit())) {
			 * mastermindDfndr.setGameAnswer(mastermindDfndr.getMaxHit(),
			 * mastermindDfndr.isChallengerMode());
			 * mastermindDfndr.Summary(mastermindDfndr.getGameAnswer(),
			 * mastermindDfndr.isChallengerMode()); break; } if
			 * (mastermindDfndr.isAutoMode() == false) { do {
			 * mastermindDfndr.AskOpClues(mastermindDfndr.getOpcodeEntry(),
			 * mastermindDfndr.getPcEntry(), "Rigth Placed Element(s) number  :");
			 * mastermindDfndr.setClueRightPlaced(entry.nextInt()); } while
			 * (mastermindDfndr.getClueRightPlaced() < 0); do {
			 * mastermindDfndr.AskOpClues(mastermindDfndr.getOpcodeEntry(),
			 * mastermindDfndr.getPcEntry(), " Present Element(s) number :");
			 * mastermindDfndr.setClueElmentPresent(entry.nextInt()); } while
			 * (mastermindDfndr.getClueElmentPresent() < 0); } else
			 * mastermindDfndr.autocompare();
			 * mastermindDfndr.secretCodeResearch(mastermindDfndr.getCodeList()); //
			 * mastermindDfndr.setGameAnswer(mastermindDfndr.getMaxHit(),
			 * mastermindDfndr.isChallengerMode()); if (mastermindDfndr.isAutoMode() ==
			 * false) System.out.println(mastermindDfndr.getGameAnswer());
			 * mastermindDfndr.setHistoric(mastermindDfndr.getHitCount(),
			 * mastermindDfndr.isChallengerMode()); } while
			 * (!mastermindDfndr.getOpcodeEntry().equals(mastermindDfndr.getPcEntry())); }
			 */

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
					mLDuel.Comparison(mLDuel.getCode(), mLDuel.getOpProposal());
					mLDuel.setHistoric(mLDuel.getOpProposal(), mLDuel.getHit());

					mLDuel.setGameAnswer();
					System.out.println(mLDuel.getGameAnswer());

					mLDuel.SecretCodeResearch(mLDuel.getPcProposal());
					do {

						do {
							mLDuel.setChallengerMode(0);

							mLDuel.setPreviousHit("Pc previous proposition : ");
							mLDuel.tableDisplay(mLDuel.getPreviousHit());

							if (mLDuel.isAutoMode() == false) {
								mLDuel.AskOpClues(mLDuel.getOpCode(), mLDuel.getPcProposal(),
										"Please enter your clues on this proposition : ");
								mLDuel.setOpEntry(entry.next());
								mLDuel.entryCheckLength(mLDuel.getOpEntry(), mLDuel.getElementsNb());
								mLDuel.entryContentsCheck(mLDuel.getOpEntry(), "+-=");
							} else {
								mLDuel.Comparison(mLDuel.getOpCode(), mLDuel.getPcProposal());
								mLDuel.setOpEntry(mLDuel.answerToString());
							}
						} while (mLDuel.getOpEntry() == null);
						mLDuel.setMLClues(mLDuel.getOpEntry());
						mLDuel.cheatStop(mLDuel.getOpCode(), mLDuel.getPcProposal());

						if (mLDuel.isAutoMode() == false) {
							if (!mLDuel.getPcProposal().equals(mLDuel.getOpCode())) {
								mLDuel.setGameAnswer();
							}
							mLDuel.setHistoric(mLDuel.getPcProposal(), mLDuel.getHit());
							System.out.println(mLDuel.getGameAnswer());
						} else {
							mLDuel.setHistoric(mLDuel.getPcProposal(), mLDuel.getHit());
						}
					} while (mLDuel.isCheatTentative() == true);
					end = mLDuel.gameStatu();
				} while (end==false);

				mLDuel.Summary();
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			
			if(gameChoice==6) {
				
				gameConfiguration.readConfiguration();
				
				
				
				System.out.println("Configurable parameters list:\n");				
				gameConfiguration.parametersList();				
				do {
				System.out.println("\nWhat is the parameter you would like to change?");
				gameConfiguration.setKeyToSet(entry.nextInt());				
				}while (gameConfiguration.optionNumberCheck(gameConfiguration.getKeyToSet(), gameConfiguration.getConfigElements().size())==false);
				do {
				System.out.println("what is the value you want set on "+gameConfiguration.getConfigElements().get(gameConfiguration.getKeyToSet())+" parameter" );
				
				gameConfiguration.setValueToSet(entry.next());
				gameConfiguration.saveConfiguration();
				}while(gameConfiguration.getValueToSet()==null);
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			System.out.println("1: Replay");
			System.out.println("2: Quit");
			System.out.println("choice ?");
			choice = entry.nextInt();
		} while (choice == 1);

	}

}
