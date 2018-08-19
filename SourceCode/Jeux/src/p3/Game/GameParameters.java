package p3.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

public class GameParameters extends Configuration {
	private LinkedHashMap<String, String> gameParameters;

	public GameParameters() throws FileNotFoundException, IOException, EntryException {
		// TODO create a method for table or not
		this.gameParameters = new LinkedHashMap<>();
		this.gameParameters.put("Variant Version", "false");
		this.gameParameters.put("Digits Minimum Range", "0");
		this.gameParameters.put("Digits Maximum Range", "9");
		this.gameParameters.put("Element(s) Number Composing Secret Code", "4");
		this.gameParameters.put("Maximum Hit Attributed", "20");
		this.gameParameters.put("Developper Mode", "false");
		this.gameParameters.put("Defender Auto Mode", "false");
		setParamatersList(this.gameParameters);
		createParameterKeysList(this.gameParameters);
		setDefaultGamevalue(this.gameParameters);
		readConfiguration();

	}

}
