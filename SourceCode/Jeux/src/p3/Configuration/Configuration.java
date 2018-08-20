package p3.Configuration;
/**
 * this class create and check a config.properties
 * Improvement axe extend this class to properties class.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import p3.Exception.EntryException;
import p3.Game.GameParameters;

abstract class Configuration {
	// Variables declaration
	private int keyToSet;
	private String configFile;
	private String valueToSet;
	private LinkedHashMap<Integer, String> configElements;
	private LinkedHashMap<String, String> paramaters;
	protected Properties properties;
	protected ArrayList<String> parametersTxtKeys;
	protected ArrayList<String> defaultValue = new ArrayList<>();
	protected String parameterKey = null;
	protected String parameterCurrentValue = null;
	protected String parameterDefaultValue = null;
	private static final Logger CONFIG_LOGGER = LogManager.getLogger(GameParameters.class.getName());

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables instantiation
	public Configuration() {
		this.keyToSet = 0;
		this.configFile = "config.properties";
		this.valueToSet = null;
		this.configElements = new LinkedHashMap<>();
		this.paramaters = new LinkedHashMap<>();
		this.properties = new Properties();
		this.parametersTxtKeys = new ArrayList<>();
		this.parameterCurrentValue = null;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Variables Getter an Setter
	public HashMap<String, String> getParamaters() {
		return paramaters;
	}

	public void setParamatersList(LinkedHashMap<String, String> paramaters) {
		this.paramaters = paramaters;
	}

	public void displayParameters() {
		this.paramaters.forEach((key, value) -> System.out.println(key + "=" + value.replaceAll(" ", "_")));
	}

	public void setParameterKey(int parametersTableIndice) {
		this.parameterKey = parametersTxtKeys.get(parametersTableIndice);
	}

	public void setParameterCurrentValue(int parametersTableIndice) {
		this.parameterCurrentValue = getProperty(this.parameterKey);
	}

	public void setParameterDefaultValueValue(int parametersTableIndice) {
		this.parameterDefaultValue = this.defaultValue.get(parametersTableIndice);
	}

	public String getParameterKey() {
		return parameterKey;
	}

	public String getParameterCurrentValue() {
		return parameterCurrentValue;
	}

	public String getParameterDefaultValue() {
		return parameterDefaultValue;
	}

	public int getKeyToSet() {
		return keyToSet;
	}

	public void setKeyToSet(String keyToSet) throws EntryException {
		int keyToSetint = stringToInteger(keyToSet);
		this.keyToSet = keyToSetint;
	}

	public String getValueToSet() {
		return valueToSet;
	}

	public void setValueToSet(String valueToSet) {
		this.valueToSet = valueToSet;
	}

	public void setDefaultGamevalue(LinkedHashMap<String, String> parameters) {
		parameters.forEach((key, value) -> this.defaultValue.add(value));
	}

	public String getProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}

	public HashMap<Integer, String> getConfigElements() {
		return configElements;

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Logger Method implementation
	public void traceMethodLogger(int i, String method) {
		if (i == 0)
			CONFIG_LOGGER.trace("Enter in method " + method);
		if (i == 1)
			CONFIG_LOGGER.trace("Out of method " + method);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Methods Implementation
	/**
	 * 
	 * @return an HashMap containing all element
	 */
	public HashMap<Integer, String> parametersList() {
		traceMethodLogger(0, "parametersList");
		ArrayList<String> keyList = new ArrayList<>();
		keyList.addAll(this.paramaters.keySet());
		for (int i = 0; i < this.paramaters.size(); i++)
			this.configElements.put(i + 1, keyList.get(i));
		traceMethodLogger(1, "parametersList");
		return this.configElements;
	}

	/**
	 * This method create a list of all parameter keys and replace all space by "_"
	 * 
	 * @param parameters
	 */
	public void createParameterKeysList(HashMap<String, String> parameters) {
		traceMethodLogger(0, "createParameterKeysLis");
		ArrayList<String> keyset = new ArrayList<>();
		keyset.addAll(parameters.keySet());
		keyset.forEach(string -> this.parametersTxtKeys.add(string.replaceAll(" ", "_")));
		traceMethodLogger(1, "createParameterKeysLis");
	}

	/**
	 * this method will check the presence and integrity of config.properties, if
	 * the file does not exist the method will create it, if it is damaged the
	 * method will repair it.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws EntryException
	 */
	public void readConfiguration() throws IOException, FileNotFoundException, EntryException {
		traceMethodLogger(0, "readConfiguration");
		boolean pass = true;
		InputStream input = null;
		InputStream input1 = null;
		try {
			input = new FileInputStream(this.configFile);
		} catch (FileNotFoundException fne) {
			pass = false;
		}
		try {
			if (pass == false)
				throw new EntryException(new FileNotFoundException());
		} catch (EntryException e0) {
			createtxt();
			storeDefaultValue(this.paramaters);
			displayParameters();
			input = new FileInputStream(configFile);
		}

		this.properties.load(input);

		input.close();

		try {

			boolean integrity = integrityCheck();
			if (integrity == false) {
				throw new EntryException("Compromised File Integrity", 5);
			}
		} catch (EntryException e1) {
			storeDefaultValue(this.paramaters);

			input1 = new FileInputStream(this.configFile);
			this.properties.load(input1);
			input1.close();
			traceMethodLogger(1, "readConfiguration");
		}

	}

	/**
	 * This method create the file config.properties
	 * 
	 * @throws IOException
	 */
	public void createtxt() throws IOException {
		traceMethodLogger(0, "createtxt");
		FileWriter fw;
		fw = new FileWriter(new File(this.configFile));
		fw.close();
		traceMethodLogger(1, "createtxt");
	}

	/**
	 * This method load in config.properties the default valaue
	 * 
	 * @param parameters
	 *            is an hashMap containing parameters and default value
	 * @throws FileNotFoundException
	 */
	public void storeDefaultValue(LinkedHashMap<String, String> parameters) throws FileNotFoundException {
		traceMethodLogger(0, "storeDefaultValue");
		PrintWriter table;
		table = new PrintWriter(new File(this.configFile));
		parameters.forEach((key, defaultvalue) -> table.println(key.replaceAll(" ", "_") + "=" + defaultvalue));
		table.close();
		traceMethodLogger(1, "storeDefaultValue");
	}

	/**
	 * 
	 * @return true if the file config.properties is not damaged
	 */
	public boolean integrityCheck() {
		traceMethodLogger(0, "integrityCheck");
		boolean pass = true;
		ArrayList<Object> integrity = new ArrayList<>();
		HashMap<Object, Object> propertiesTable = new HashMap<>();
		this.properties.forEach((key, value) -> propertiesTable.put(key, value));
		integrity.addAll(propertiesTable.keySet());

		if (integrity.size() != this.parametersTxtKeys.size())

			pass = false;
		else {
			int wrong = 0;
			for (int i = 0; i < this.parametersTxtKeys.size(); i++) {
				String toCheck = integrity.get(i).toString().replaceAll("=", "");
				if (!this.parametersTxtKeys.contains(toCheck)
						|| properties.getProperty(parametersTxtKeys.get(i)).isEmpty()) {
					wrong++;
				}
			}

			if (wrong > 0)
				pass = false;

		}
		traceMethodLogger(1, "integrityCheck");
		return pass;
	}

	/**
	 * this method will write configuration in config.properties
	 * 
	 * @param key
	 *            = parameter name
	 * @param value
	 *            = value
	 * @throws IOException
	 */
	public void writeConfiguration(String key, String value) throws IOException {
		traceMethodLogger(0, "writeConfiguration");
		OutputStream output = null;
		try {
			output = new FileOutputStream(configFile);
			properties.setProperty(key, value);
			properties.store(output, null);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		traceMethodLogger(1, "writeConfiguration");
	}

	/**
	 * This method will write and save in config.properties the new value of a
	 * parameter
	 * 
	 * @throws IOException
	 */
	public void saveConfiguration() throws IOException {
		traceMethodLogger(0, "saveConfiguration");
		String key = null;
		String value = null;
		key = configElements.get(this.keyToSet);
		value = this.valueToSet;
		if (value != null)
			writeConfiguration(key.replaceAll(" ", "_"), value);
		traceMethodLogger(1, "saveConfiguration");
	}

	/**
	 * Converter a string to a boolean value true or false
	 * 
	 * @param str
	 * @return true or false
	 */
	public boolean booleanConverter(String str) {
		traceMethodLogger(0, "booleanConverter");
		String itsTrue = "true";
		boolean bool = itsTrue.equals(str);
		traceMethodLogger(1, "booleanConverter");
		return bool;
	}

	/**
	 * Display the default values
	 */

	public void displayDefaultValue() {
		traceMethodLogger(0, "displayDefaultValue");
		this.paramaters.forEach((key, value) -> System.out
				.println("parameter : " + key.replaceAll(" ", "_") + " -> Default value= " + value));
		traceMethodLogger(1, "displayDefaultValue");
	}

	/**
	 * Display current set value
	 */
	public void displayCurrentValue() {
		traceMethodLogger(0, "displayCurrentValue");
		ArrayList<String> currentParameters = new ArrayList<>();
		for (int i = 0; i < this.paramaters.size(); i++) {
			setParameterKey(i);
			setParameterCurrentValue(i);
			currentParameters.add("parameter : " + this.parameterKey + " = " + this.parameterCurrentValue);
		}
		currentParameters.forEach(string -> System.out.println(string));
		traceMethodLogger(0, "displayCurrentValue");
	}

	/**
	 * Change a String into an Integer
	 * 
	 * @param str
	 *            is the string to transform in integer
	 * @return an integer
	 * @throws EntryException
	 */
	public int stringToInteger(String str) throws EntryException {
		traceMethodLogger(0, "stringToInteger");
		int integer = -1;
		boolean pass = entryIntegerCheck(str);
		if (pass == true)
			integer = Integer.parseInt(str);
		traceMethodLogger(1, "stringToInteger");
		return integer;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	// Check Methods Implementation
	/**
	 * 
	 * @param valueToSet
	 * @param minParameter
	 * @param maxParameter
	 * @return true if the value to set is within the range set
	 * @throws EntryException
	 */
	public boolean valueRangeCheck(String valueToSet, int minParameter, int maxParameter) throws EntryException {
		traceMethodLogger(0, "valueRangeCheck");
		boolean pass = true;
		int value = stringToInteger(valueToSet);
		if (value < minParameter || value > maxParameter)
			pass = false;
		traceMethodLogger(1, "valueRangeCheck");
		return pass;

	}

	/**
	 * 
	 * @param valueToSet
	 * @return true if the value to set is an integer
	 */
	public boolean valueIntegerCheck(String valueToSet) {
		traceMethodLogger(0, "valueIntegerCheck");
		boolean pass = true;
		boolean digitOnly = valueToSet.matches("[0-9]{" + valueToSet.length() + "}");
		if (digitOnly == false)
			pass = false;
		traceMethodLogger(1, "valueIntegerCheck");
		return pass;
	}

	/**
	 * 
	 * @param booleanValue
	 * @return true if the string booleanValue is equals to true or false
	 */
	public boolean valueBooleanCheck(String booleanValue) {
		traceMethodLogger(0, "valueBooleanCheck");
		boolean pass = false;
		boolean itsTrue = booleanValue.equalsIgnoreCase("true");
		boolean itsFalse = booleanValue.equalsIgnoreCase("false");
		if (itsTrue == true || itsFalse == true)
			pass = true;
		traceMethodLogger(1, "valueBooleanCheck");
		return pass;
	}

	/**
	 * 
	 * @param entry
	 * @return true if the entry is an integer
	 * @throws EntryException
	 */
	public boolean entryIntegerCheck(String entry) throws EntryException {
		traceMethodLogger(0, "entryIntegerCheck");
		boolean pass = true;

		boolean digitOnly = entry.matches("[0-9]{" + entry.length() + "}");
		try {
			if (digitOnly == false)
				throw new EntryException(entry, 2);
		} catch (EntryException e) {
			pass = false;
		}
		traceMethodLogger(1, "entryIntegerCheck");
		return pass;

	}

}
