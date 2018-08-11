package p3.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class GameParameters extends Configuration {
	private  LinkedHashMap <String,String> gameParameters;
	protected ArrayList<String> defaultValue =new ArrayList<>();
	
	
	
	public GameParameters() throws FileNotFoundException, IOException, EntryException {
	//TODO create a method for table	or not 
		gameParameters = new  LinkedHashMap<>();
		gameParameters.put("Variant Version","false");
		gameParameters.put("Digits Minimum Range","0");
		gameParameters.put("Digits Maximum Range","9");
		gameParameters.put("Element(s) Number Composing Secre Code","4");
		gameParameters.put("Maximum Hit Attributed","20");
		gameParameters.put("Developper Mode","false");
		gameParameters.put("Defender Auto Mode","false");
		setParamatersList(gameParameters);
		createParameterKeysList(gameParameters);		
		
	}
	
	public void setDefaultGamevalue() {		
		gameParameters.forEach((key,value)-> defaultValue.add(value));
		
	}
	

}
