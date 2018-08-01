package p3.Game;

import java.util.ArrayList;

public class Mastermind extends Game {

	private int nbRightPlaced;
	private int nbPresent;
	private int clueRightPlaced;
	private int clueElmentPresent;
	private ArrayList<String> codeList;

	public Mastermind() {
		this.nbRightPlaced = 0;
		this.nbPresent = 0;
		this.clueRightPlaced = 0;
		this.clueElmentPresent = 0;
		this.codeList = new ArrayList<>();
		historic.clear();// historic clear parceque sinon si on joue au challenger puis au defenseur l
							// historic n est pas reinitialiser
	}

	public int getNbRightPlaced() {
		return nbRightPlaced;
	}

	public int getNbPresent() {
		return nbPresent;
	}

	public int getClueRightPlaced() {
		return clueRightPlaced;
	}

	public void setClueRightPlaced(int clueRightPlaced) {
		boolean pass = true;
		try {
			comparison(getPcEntry(), getOpCode(), getElementsNb());
			if (nbRightPlaced != clueRightPlaced)
				throw new EntryException();
		} catch (EntryException e) {
			System.out.println("Attention it seem's that you've made a mistake "
					+ "\nThe correct clue is nb right placed = " + nbRightPlaced + "\n");
			pass = false;
		}
		if (pass == false) {
			this.clueRightPlaced = -1;

		} else
			this.clueRightPlaced = clueRightPlaced;
	}

	public int getClueElmentPresent() {
		return clueElmentPresent;
	}

	public void setClueElmentPresent(int clueElmentPresent) {
		boolean pass = true;
		try {
			comparison(getPcEntry(), getOpCode());
			if (nbPresent != clueElmentPresent)
				throw new EntryException();
		} catch (EntryException e) {
			System.out.println("Attention it seem's that you've made a mistake "
					+ "\nThe correct clue is and nb persent = " + nbPresent + "\n");

			pass = false;
		}
		if (pass == false) {
			this.clueElmentPresent = -1;

		} else
			this.clueElmentPresent = clueElmentPresent;
	}

	/**
	 * @return a String ArrayList
	 */
	public ArrayList<String> getCodeList() {
		return codeList;
	}

	/**
	 * create an array list containing all the secret code possibility function of
	 * the number element, with or without code composed of duplicate. ( i.e if
	 * duplicate are allowed code list will contain this kind of secret code :
	 * 1122,1111,25446....)
	 * 
	 * @param nbElements
	 *            is an integer which indicated the number of elements composing the
	 *            secret code
	 */

	public void setCodeList(int nbElements) {
		ArrayList<String> codeList = new ArrayList<>();
		if (codeList.isEmpty()) {
			String code = null;
			int codeListElmt = 0;
			for (int i = 0; i < Math.pow(10, nbElements); i++) {
				code = String.format("%0" + nbElements + "d", codeListElmt++);
				if (basisVer == true) {
					avoidDuplicate(code, nbElements);
					if (duplicate == true)
						continue;
					else
						codeList.add(code);
				} else
					codeList.add(code);
			}
		}

		this.codeList = codeList;
	}

	/**
	 * 
	 * @param codeList
	 *            is a string list
	 * 
	 * 
	 * @throws exception
	 *             IllegalArgumentException in case of a codeList with a size at 0 .
	 */

	public void pcProposition(ArrayList<String> codeList) {
		try {
			if (codeList.size() == 0)
				throw new EntryException();
		} catch (EntryException e) {
			System.out.println("!System Error!  \nMastermind defender mode need a code list initiated");

		}
		this.pcCode=codeList.get(getRandom(0, (codeList.size() - 1)));
		// causse a codeliste size at 0 is an empty codelist and not a codelits with 1
		// elment so to avoid bug -1 //to be clear a codelist with 1 string have a size
		// of
		// 1 so random can be 1 but with a size of 1 the first element is at the entry
		// zero so if random is 1 we will ask for codelist.get(1) but doesn't exist the
		// first place is 0 so whith -1 we will never have issue again

	}

	
	@Override
	public void comparison(String codeToCompare, String secretCode) {
		this.nbRightPlaced = 0;
		this.nbPresent = 0;
		int elementsNb = secretCode.length();
		ArrayList<Character> codeToCompareTbl = new ArrayList<Character>();
		ArrayList<Character> secretCodeTbl = new ArrayList<Character>();

		for (int i = 0; i < elementsNb; i++) {
			secretCodeTbl.add(secretCode.charAt(i));
			codeToCompareTbl.add(codeToCompare.charAt(i));
			if (secretCode.charAt(i) == codeToCompare.charAt(i)) {
				this.nbRightPlaced++;
			}
		}
		codeToCompareTbl.retainAll(secretCodeTbl);
		this.nbPresent = Math.abs(nbRightPlaced -codeToCompareTbl.size() );
	}

	@Override
	public void setHistoric(String code, String answer, int getHit) {
		
		int nbRigthPlaced =0;
		int nbPresent = 0;
		if (this.challengerMode == true) {
			nbRigthPlaced =this.nbRightPlaced;
			nbPresent = this.nbPresent;}
		else {			
			nbRigthPlaced =this.clueRightPlaced;
			nbPresent = this.clueElmentPresent;}
		

		historic.put(getHit, " : " + code + " // clues are " + nbRigthPlaced + " element(s) rigth placed, "
				+ nbPresent + " element(s) present");

	}

	public void secretCodeResearch(ArrayList<String> codeList) {
		
		String secretCodePossible = null;
		String codeToCompare = getPcCode();
		for (int i = 0; i < codeList.size(); i++) {
			secretCodePossible = codeList.get(i);// secret code eszt un des code de la liste car concraitement
													// on
			// compare un code à un code dela liste donc il faut garder le
			// meme sens pour etre plus claire on etablie les clue en
			// faisant pcentry versus opcode don on dois faire pcentry
			// versus un des code dela liste qui contient le opcode
			comparison(codeToCompare,secretCodePossible);
			if (!(clueRightPlaced == nbRightPlaced && clueElmentPresent == nbPresent)) {
				codeList.remove(secretCodePossible);

			} else
				continue;
		}

		codeList.remove(getPcCode());
		

	}
	@Override
	public void Summary (String code, String codeToCompare, boolean challengerMode) {
			
			String comment =null;
			
			clueRightPlaced = nbRightPlaced;
			clueElmentPresent = nbPresent;
			;
			
			if (code.equals(codeToCompare)) {
				
				comment ="Pc managed to find your secret code which was :" +code;}
			else {
				code = getOpCode();				
				comment = "Pc did not find you secret code which was : " +code;}
			ArrayList<String> summary = new ArrayList <>();
			summary.add(getGameAnswer);
			summary.add(comment);
			summary.add("Summary");		
			summary.forEach(elmt ->System.out.println(elmt));
			getHistoric();
		}

}