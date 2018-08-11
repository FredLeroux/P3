<<<<<<< HEAD
package p3.Game;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
//TODO JAVADOC
//TODO Clean and classified all methode
	//TODO methode to check integrity config.prop
	//TODO metyhode display defaukt value
	//TODO methode repaire config properties
	public static boolean codeCompareCheck = false;
	public static boolean hitCheck = false;
	public static Scanner entry = new Scanner(System.in);
	public static int gameChoice;
	public static int choice;
	public static Configuration configInitial;
	 
	
	public static void main(String[] args) throws EntryException, IOException {
		
		GameParameters gameConfiguration = new GameParameters();		
		gameConfiguration.readConfiguration();
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
				
				//moreLess.configurationCheck();
				//System.out.println("maxhit set at : " + moreLess.getMaxHit());
				//System.out.println("Select Version");
				//System.out.println("1: Basis");
				//System.out.println("2: Variante");
				//moreLess.setVersion(entry.nextInt());
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
						moreLess.entryCheckLength(moreLess.getOpEntry(), moreLess.getElementsNb());
						moreLess.entryIntegerCheck(moreLess.getOpEntry());
					} while (moreLess.getOpEntry() == null);
					moreLess.setOpProposal(moreLess.getOpEntry());
					moreLess.Comparison(moreLess.getCode(), moreLess.getOpProposal());
					moreLess.setHistoric(moreLess.getOpProposal(), moreLess.getHit());
					moreLess.setPreviousHit("Player previous proposition : ");
					if (!moreLess.getOpProposal().equals(moreLess.getCode())) {
						moreLess.setGameAnswer();
						System.out.println(moreLess.getGameAnswer());
					}
					codeCompareCheck = (!moreLess.getOpProposal().equals(moreLess.getCode()));
					hitCheck = moreLess.getHit() < moreLess.getMaxHit();
				} while (codeCompareCheck && hitCheck);
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
			
				//System.out.println("Select Version");
				//System.out.println("1: Basis");
				///System.out.println("2: Variante");
				//mLDefender.setVersion(entry.nextInt());
				mLDefender.setChallengerMode(2);
				do {
					System.out.println("set your code");
					mLDefender.setOpEntry(entry.next());
					mLDefender.entryCheckLength(mLDefender.getOpEntry(), mLDefender.getElementsNb());
					mLDefender.entryIntegerCheck(mLDefender.getOpEntry());
					mLDefender.entryDuplicateCheck(mLDefender.getOpEntry());
					mLDefender.entryIntegerRangeCheck(mLDefender.getOpEntry());
				} while (mLDefender.getOpEntry() == null);
				mLDefender.setOpCode(mLDefender.getOpEntry());

				do {
					mLDefender.setHit();
					mLDefender.SecretCodeResearch(mLDefender.getPcProposal());

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

					while (mLDefender.isCheatTentative() == true)
						;

				} while (!(mLDefender.getPcProposal().equals(mLDefender.getOpCode()))
						&& /*!(mLDefender.getHit() == mLDefender.getMaxHit()) && */mLDefender.isCheating() == false);

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
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				//mLDuel.setVariantVersion(entry.nextInt());();
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
							mLDuel.setChallengerMode(2);

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

				} while (!mLDuel.getOpProposal().equals(mLDuel.getCode()) && mLDuel.getHit() < mLDuel.getMaxHit()
						&& !(mLDuel.getPcProposal().equals(mLDuel.getOpCode()))
						&& !(mLDuel.getHit() == mLDuel.getMaxHit()) && mLDuel.isCheating() == false);

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
				gameConfiguration.optionNumberCheck(gameConfiguration.getKeyToSet(), gameConfiguration.getConfigElements().size());
				}while (gameConfiguration.getKeyToSet()==0);
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

=======
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
					moreLess.setHitCount();
					do {

						moreLess.getHistoric();
						moreLess.AskOpProposition(moreLess.getCode(), "Your proposition : ");
						moreLess.setOpcodeEntry(entry.next());

						moreLess.entryCheckLength(moreLess.getOpcodeEntry(), moreLess.getElementsNb());

						moreLess.entryIntegerCheck(moreLess.getOpcodeEntry());
					} while (moreLess.getOpcodeEntry() == null);
					if (moreLess.getOpcodeEntry().equals(moreLess.getCode())
							|| (moreLess.getHitCount() == moreLess.getMaxHit())) {
						moreLess.comparison(moreLess.getCode(), moreLess.getOpcodeEntry());
						moreLess.Summary(moreLess.getOpcodeEntry(), moreLess.getCode(), moreLess.isChallengerMode());
						moreLess.setHistoric(moreLess.getOpcodeEntry(), moreLess.answerToString(),
								moreLess.getHitCount());
						moreLess.getHistoric();
						break;
					}
					moreLess.setGameAnswer(moreLess.isChallengerMode());
					System.out.println(moreLess.getGameAnswer());
					moreLess.comparison(moreLess.getCode(), moreLess.getOpcodeEntry());
					moreLess.setHistoric(moreLess.getOpcodeEntry(), moreLess.answerToString(), moreLess.getHitCount());
					codeCompareCheck = (!moreLess.getOpcodeEntry().equals(moreLess.getCode()));
					hintCheck = moreLess.getHitCount() < moreLess.getMaxHit();
				} while (codeCompareCheck && hintCheck);
			}
			// --------------------------------------------------------------------------------------------------------------
		/*	if (gameChoice == 2) {
				Mastermind masterMind = new Mastermind();
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				masterMind.setVersion(entry.nextInt());
				masterMind.setChallengerMode(1);
				masterMind.setCode(masterMind.getMinRange(), masterMind.getMaxRange(), masterMind.getElementsNb());
				do {
					masterMind.AskOpProposition(masterMind.getCode(), "Your proposition : ");
					masterMind.setOpcodeEntry(entry.next());
					// masterMind.setGameAnswer(masterMind.getMaxHit(),
					// masterMind.isChallengerMode());
					System.out.println(masterMind.getGameAnswer());
					masterMind.comparison(masterMind.getCode(), masterMind.getOpcodeEntry());
					if (!(masterMind.getOpcodeEntry().equals(masterMind.getCode()))) {
						// masterMind.setHistoric(masterMind.getHit(), masterMind.isChallengerMode());
						masterMind.getHistoric();
					}
					codeCompareCheck = (!masterMind.getOpcodeEntry().equals(masterMind.getCode()));
					// hintCheck = masterMind.getHit() < masterMind.getMaxHit();
				} while (codeCompareCheck && hintCheck);
			}*/
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
					mLDefender.setOpcodeEntry(entry.next());
					mLDefender.entryCheckLength(mLDefender.getOpcodeEntry(), mLDefender.getElementsNb());
					mLDefender.entryIntegerCheck(mLDefender.getOpcodeEntry());
					mLDefender.entryDuplicateCheck(mLDefender.getOpcodeEntry());
				} while (mLDefender.getOpcodeEntry() == null);
				mLDefender.setOpCode(mLDefender.getOpcodeEntry());

				do {
					mLDefender.setHitCount();
					mLDefender.MorelessSecretCodeResearch(mLDefender.getMaxRange(), mLDefender.getMinRange(),
							mLDefender.getElementsNb(), mLDefender.getPcCode());
					do {
						do {

							if (mLDefender.isAutoMode() == false) {
								mLDefender.AskOpClues(mLDefender.getOpCode(), mLDefender.getPcCode(),
										"Enter your clues on this proposition : ");
								mLDefender.setOpcodeEntry(entry.next());

								mLDefender.entryCheckLength(mLDefender.getOpcodeEntry(), mLDefender.getElementsNb());
								mLDefender.entryContentsCheck(mLDefender.getOpcodeEntry(), "+-=");
							} else {
								mLDefender.comparison(mLDefender.getOpCode(), mLDefender.getPcCode());
								mLDefender.setOpcodeEntry(mLDefender.answerToString());
							}
						} while (mLDefender.getOpcodeEntry() == null);
						mLDefender.setMLClues(mLDefender.getOpcodeEntry());
						String previousMLClue = mLDefender.getOpcodeEntry();
						mLDefender.cheatStop(mLDefender.getOpCode(), mLDefender.getPcCode(), previousMLClue);
						if (mLDefender.isCheating() == true) {
							mLDefender.Summary(mLDefender.getPcCode(), mLDefender.getOpCode(),
									mLDefender.isChallengerMode());
							mLDefender.setHistoric(mLDefender.getPcCode(), mLDefender.answerToString(),
									mLDefender.getHitCount());
							mLDefender.getHistoric();
							break;
						}
						if (mLDefender.getPcCode().equals(mLDefender.getOpCode())
								|| (mLDefender.getHitCount() == mLDefender.getMaxHit())) {
							mLDefender.Summary(mLDefender.getPcCode(), mLDefender.getOpCode(),
									mLDefender.isChallengerMode());
							mLDefender.setHistoric(mLDefender.getPcCode(), mLDefender.answerToString(),
									mLDefender.getHitCount());
							mLDefender.getHistoric();

							break;
						}
						mLDefender.setGameAnswer(mLDefender.isChallengerMode());
						if (mLDefender.isAutoMode() == false) {
							System.out.println(mLDefender.getGameAnswer());
							if (mLDefender.getmLClues() == null) {
								mLDefender.setHistoric(mLDefender.getPcCode(), previousMLClue,
										mLDefender.getHitCount());
								mLDefender.setHitCount();
							} else
								mLDefender.setHistoric(mLDefender.getPcCode(), mLDefender.getmLClues(),
										mLDefender.getHitCount());
							System.out.println("Previous proposition(s) historic : ");
							mLDefender.getHistoric();
						} else
							mLDefender.setHistoric(mLDefender.getPcCode(), mLDefender.getmLClues(),
									mLDefender.getHitCount());
					} while (mLDefender.getmLClues() == null);

				} while (!mLDefender.getOpCode().equals(mLDefender.getPcCode()) && mLDefender.isCheating() == false);

			}
			// ------------------------------------------------------------------------------------------------------------------------------------------------------

		/*	if (gameChoice == 4) {
				Mastermind mastermindDfndr = new Mastermind();
				System.out.println("Select Version");
				System.out.println("1: Basis");
				System.out.println("2: Variante");
				mastermindDfndr.setVersion(entry.nextInt());
				mastermindDfndr.setChallengerMode(2);
				mastermindDfndr.setCodeList(mastermindDfndr.getElementsNb());
				do {
					System.out.println("set your code");
					mastermindDfndr.setOpcodeEntry(entry.next());
				} while (mastermindDfndr.getOpcodeEntry() == null);
				do {
					mastermindDfndr.pcProposition(mastermindDfndr.getCodeList());
					if (mastermindDfndr.getOpCode().equals(mastermindDfndr.getPcEntry())
							|| ((mastermindDfndr.getHit() + 1) == mastermindDfndr.getMaxHit())) {
						mastermindDfndr.setGameAnswer(mastermindDfndr.getMaxHit(), mastermindDfndr.isChallengerMode());
						mastermindDfndr.Summary(mastermindDfndr.getGameAnswer(), mastermindDfndr.isChallengerMode());
						break;
					}
					if (mastermindDfndr.isAutoMode() == false) {
						do {
							mastermindDfndr.AskOpClues(mastermindDfndr.getOpcodeEntry(), mastermindDfndr.getPcEntry(),
									"Rigth Placed Element(s) number  :");
							mastermindDfndr.setClueRightPlaced(entry.nextInt());
						} while (mastermindDfndr.getClueRightPlaced() < 0);
						do {
							mastermindDfndr.AskOpClues(mastermindDfndr.getOpcodeEntry(), mastermindDfndr.getPcEntry(),
									" Present Element(s) number :");
							mastermindDfndr.setClueElmentPresent(entry.nextInt());
						} while (mastermindDfndr.getClueElmentPresent() < 0);
					} else
						mastermindDfndr.autocompare();
					mastermindDfndr.secretCodeResearch(mastermindDfndr.getCodeList()); //
					mastermindDfndr.setGameAnswer(mastermindDfndr.getMaxHit(), mastermindDfndr.isChallengerMode());
					if (mastermindDfndr.isAutoMode() == false)
						System.out.println(mastermindDfndr.getGameAnswer());
					mastermindDfndr.setHistoric(mastermindDfndr.getHitCount(), mastermindDfndr.isChallengerMode());
				} while (!mastermindDfndr.getOpcodeEntry().equals(mastermindDfndr.getPcEntry()));
			}*/

			// --------------------------------------------------------------------------------------------------------------------------------------------------------
		/*	if (gameChoice == 5) {
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
					mLDuelPC.setOpcodeEntry(entry.next());
				} while (mLDuelPC.getOpcodeEntry() == null);

				System.out.println("The game begin with your first search try on  PC secret code ");

				 boucle test  do {

					// -----------------------------player

					do {
						System.out.println("Challenger player");
						mLDuelPlayer.getHistoric();
						mLDuelPlayer.AskOpProposition(mLDuelPlayer.getCode(), "Your proposition : ");
						mLDuelPlayer.setOpcodeEntry(entry.next());
					} while (mLDuelPlayer.getOpcodeEntry() == null);
					// mLDuelPlayer.setGameAnswer(mLDuelPlayer.getMaxHit(),
					// mLDuelPlayer.isChallengerMode());
					System.out.println(mLDuelPlayer.getGameAnswer());
					mLDuelPlayer.comparison(mLDuelPlayer.getCode(), mLDuelPlayer.getOpcodeEntry());
					// mLDuelPlayer.setHistoric(mLDuelPlayer.getHit(),
					// mLDuelPlayer.isChallengerMode());

					// --------------------------pc
					do {
						System.out.println("Challenger PC");
						// ----------------------duel pc set pc entry
						// mLDuelPC.secretCodeResearch();
						// ----------------------duel pc set clue
						if (mLDuelPC.isAutoMode() == false) {
							mLDuelPC.getHistoric();
							mLDuelPC.AskOpClues(mLDuelPC.getOpcodeEntry(), mLDuelPC.getPcCode(),
									"Enter your clues on this proposition : ");
							// mLDuelPC.setMLClues(entry.next());
						} // else
							// mLDuelPC.autocompare();
					} while (mLDuelPC.getmLClues() == null);
					mLDuelPC.setGameAnswer(mLDuelPC.isChallengerMode());
					if (mLDuelPC.isAutoMode() == false)
						System.out.println(mLDuelPC.getGameAnswer());
					// mLDuelPC.setHistoric(mLDuelPC.getHit(), mLDuelPC.isChallengerMode());

					/* boucle test } while (!mLDuelPC.getPcCode().equals(mLDuelPC.getOpcodeEntry()));*/

			//}

			// -----------------------------------------------------------------------------------------------------------------------------------------------------
			System.out.println("1: Replay");
			System.out.println("2: Quit");
			System.out.println("choice ?");
			choice = entry.nextInt();
		} while (choice == 1);

	}

>>>>>>> parent of 376506d... Moreless full end
}