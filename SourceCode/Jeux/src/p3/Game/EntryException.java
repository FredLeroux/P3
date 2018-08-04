package p3.Game;

import java.util.ArrayList;

public class EntryException extends Exception {
	

	public EntryException() {
		System.out.println("\nENTRY EXCEPTION\n");
	}
	
	public EntryException(String str, int i) {	
		if (i==0)
			System.out.println("\nENTRY LENGTH FORMAT EXCEPTION\n\nThis ENTRY : " + str + "\nIs not composed of the elements number set \nYour ENTRY was set as null, please retry.\n");
		else if (i==1)
			System.out.println("\nENTRY CLUE(S)ERROR EXCEPTION\nIt seem's that you've made a mistake " + "\nThe correct clue is : "
					+ str + "\nYour clue(s) were set as null, please retry.\n" );
		else if (i==2)
			System.out.println("\nENTRY NUMBER FORMAT EXCEPTION\nThis entry : " + str+" is not fully an integer\nYour ENTRY was set as null, please retry.\n" );
		else if (i==3)
			System.out.println("\nENTRY DUPLICATE FORMAT EXCEPTION\nThis entry : " + str+" contains duplicate which are not allowed in basis version \nYour ENTRY was set as null, please retry.\n" );
		else if (i==4)
			System.out.println("\nENTRY RANGE EXCEPTION\nThis entry : " + str+" is not within the set range \nYour ENTRY was set as null, please retry.\n" );
	}
	
	public EntryException(String str, String contains) {
		System.out.println("\nENTRY CONTENTS FORMAT EXCEPTION\n\nThis ENTRY : " + str + "\nIs not only composed of " + contains+"\nYour ENTRY was set as null, please retry.\n");
	}
	
	public EntryException(int cheatCount) {
		ArrayList<String> number = new ArrayList <>();
		number.add("First");
		number.add("Second");
				
			System.out.println(number.get(cheatCount-1)+" Cheating tentative detection or entry error, the third will be the last\n" );
		
	}


	}
	

