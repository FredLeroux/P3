package p3.Game;
/**
 * this class extend configuration
 * <li> The aim of the class is to initiate the configuration and set parameters and their default value .
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Configuration.P3Menu;
import p3.Exception.EntryException;

public class GameParameters extends P3Menu {
	/// Variables declaration
	private LinkedHashMap<String, String> gameParameters;
	private static final Logger PARAMETERS_LOGGER = LogManager.getLogger(GameParameters.class.getName());
	protected static boolean devMode;
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables Getter an Setter

	public LinkedHashMap<String, String> getGameParameters() {
		return this.gameParameters;
	}

	public void setGameParameters(LinkedHashMap<String, String> gameParameters) {
		this.gameParameters = gameParameters;
	}

	public static boolean isDevMode() {
		return devMode;
	}

	public static void setDevMode(boolean devMode) {
		GameParameters.devMode = devMode;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Method implementation
	/**
	 * This method set the parameters
	 * <li>their default value
	 * <li>set the parameterList
	 * <li>create a keyList
	 * <li>set default value
	 * <li>load config
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws EntryException
	 */
	public GameParameters() throws FileNotFoundException, IOException, EntryException {
		PARAMETERS_LOGGER.trace("****LAUNCH APPLICATION****");
		PARAMETERS_LOGGER.trace("Enter in method  GameParameters");
		this.gameParameters = new LinkedHashMap<>();
		this.gameParameters.put("Variant Version", "false");
		this.gameParameters.put("Digits Minimum Range", "0");
		this.gameParameters.put("Digits Maximum Range", "9");
		this.gameParameters.put("Element(s) Number Composing Secret Code", "4");
		this.gameParameters.put("Maximum Hit Attributed", "20");
		this.gameParameters.put("Defender Auto Mode", "false");
		setParamatersList(this.gameParameters);
		createParameterKeysList(this.gameParameters);
		setDefaultGamevalue(this.gameParameters);
		readConfiguration();
		PARAMETERS_LOGGER.trace("Out of method  GameParameters");

	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Class End
}
