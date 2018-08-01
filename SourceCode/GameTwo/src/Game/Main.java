package Game;

public class Main {

	public static void main(String[] args) {

		System.out.println("!!!!!!!!!!!!!!!GAME V2 with Code class called in main!!!!!!!!!!!!!!! ");
		MoreLess moreLess = new MoreLess();
		Code code = new Code(moreLess.getMaxRange(), moreLess.getElementsNb());
		moreLess.setSecretcode(code.toString());

		do {
			System.out.println("\nSecret code is " + moreLess.getSecretcode());
			moreLess.setOpEntry();
			moreLess.check(moreLess.getMaxHint());
			moreLess.comparison(moreLess.getSecretcode(), moreLess.getOpEntry());
			moreLess.setMorelessAnswer();
			moreLess.getMorelessAnswer();
			if (!(moreLess.getOpEntry().equals(moreLess.getSecretcode()))) {
				moreLess.setHistoric(moreLess.getHint(),
						" : " + moreLess.getOpEntry() + " // clues are " + moreLess.answerToString());
				moreLess.getHistoric();
			}
		} while ((!moreLess.getOpEntry().equals(moreLess.getSecretcode())) && moreLess.getHint() < moreLess.getMaxHint());

		System.out.println("youpala youpi");

	}

}
//moreLess.getHint()+1,
//" : " + moreLess.getOpEntry() + " // clues are " + moreLess.answerToString())