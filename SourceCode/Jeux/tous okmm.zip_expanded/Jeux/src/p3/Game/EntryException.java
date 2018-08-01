package p3.Game;




public class EntryException extends Exception {
	

	public EntryException() {
		System.out.println("\nENTRY EXCEPTION\n");
	}
	/**
	
	 * 
	 * Exception to raise if secret code length filled is nor equals to elements number set.
	 * 
	 * @param str
	 *            is the string to test
	 * @param i
	 *            is the elements number criterion that the string must met
	 * @throws 
	 *  		  "Entry Format Exception"
	 * 
	 */
	public EntryException(String str, int i) {	
		if (i==0)
			System.out.println("\nENTRY FORMAT EXCEPTION\n\nYour Proposition : " + str + "\nIs not composed of " + i
					+ " elements\n");
		if (i==1)
			System.out.println("\nENTRY ERROR EXCEPTION\nIt seem's that you've made a mistake " + "\nThe correct clue is : "
					+ str + "\nYour clues were set as null, please retry.\n" );
	}
/**
	
	 * Exception to raised if duplicate are not allowed.
	 * 
	 * @param str
	 *            is the string to test
	 * @param i
	 *            is the elements number criterion that the string must met
	 * @param version 
	 * 				is the version which raised the exception           
	 * @throws 
	 *  		  "Entry Format Exception"
	 * 
	 */
	public EntryException(String str, int i,String version) {		
			System.out.println("\nENTRY FORMAT EXCEPTION\n\nYour Proposition : " + str + "\nIs not composed of " + i
					+ " elements\nIn " + version +" no duplicate allowed.\n");

	}
	
}
