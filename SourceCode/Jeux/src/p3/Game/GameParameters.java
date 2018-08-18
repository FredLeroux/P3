package p3.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameParameters extends Configuration {
	private LinkedHashMap<String, String> gameParameters;
	private static final Logger LOGGER = LogManager.getLogger(GameParameters.class.getName());

	public GameParameters() throws FileNotFoundException, IOException, EntryException {
		// TODO create a method for table or not
		gameParameters = new LinkedHashMap<>();
		gameParameters.put("Variant Version", "false");
		gameParameters.put("Digits Minimum Range", "0");
		gameParameters.put("Digits Maximum Range", "9");
		gameParameters.put("Element(s) Number Composing Secret Code", "4");
		gameParameters.put("Maximum Hit Attributed", "20");
		gameParameters.put("Developper Mode", "false");
		gameParameters.put("Defender Auto Mode", "false");
		setParamatersList(gameParameters);
		createParameterKeysList(gameParameters);
		setDefaultGamevalue(gameParameters);
		readConfiguration();

	}

}
