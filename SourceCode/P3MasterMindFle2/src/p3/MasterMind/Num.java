package p3.MasterMind;

import java.util.ArrayList;

@SuppressWarnings("serial")
class Num extends ArrayList<Integer>{
	
	private final int nbDigits;
	private final StringBuilder secretCode;

	public Num() {
		this.nbDigits = 10;
		this.secretCode = new StringBuilder();
	}
	
	
	public Num sDigitsTable () {
		for (int i=0; i<nbDigits;i++)
			this.add(i);
		forEach(snum -> secretCode.append(snum));
		System.out.println(secretCode.toString());
		return this;
	}

}
