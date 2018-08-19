package p3.Game;

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

abstract class Configuration {

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

	public HashMap<String, String> getParamaters() {
		return paramaters;
	}

	public void setParamatersList(LinkedHashMap<String, String> paramaters) {
		this.paramaters = paramaters;
	}

	public void displayParameters() {
		this.paramaters.forEach((key, value) -> System.out.println(key + "=" + value.replaceAll(" ", "_")));
	}

	public HashMap<Integer, String> getConfigElements() {
		return configElements;
	}

	public HashMap<Integer, String> parametersList() {
		ArrayList<String> keyList = new ArrayList<>();
		keyList.addAll(this.paramaters.keySet());
		for (int i = 0; i < this.paramaters.size(); i++)
			this.configElements.put(i + 1, keyList.get(i));
		return this.configElements;
	}

	public void createParameterKeysList(HashMap<String, String> parameters) {

		ArrayList<String> keyset = new ArrayList<>();
		keyset.addAll(parameters.keySet());
		keyset.forEach(string -> this.parametersTxtKeys.add(string.replaceAll(" ", "_")));
	}

	public int getKeyToSet() {
		return keyToSet;
	}

	public void setKeyToSet(int keyToSet) {
		this.keyToSet = keyToSet;
	}

	public String getValueToSet() {
		return valueToSet;
	}

	public void setValueToSet(String valueToSet) {
		this.valueToSet = valueToSet;
	}

	public void readConfiguration() throws IOException, FileNotFoundException, EntryException {
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

		try {
			this.properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		input.close();

		try {

			boolean integrity = integrityCheck();
			if (integrity == false) {
				throw new EntryException("Compromised File Integrity", 5);
			}
		} catch (EntryException e1) {
			storeDefaultValue(this.paramaters);
			try {
				input1 = new FileInputStream(this.configFile);
				this.properties.load(input1);
			} catch (IOException e) {
				e.printStackTrace();
				input1.close();
			}
		}

	}

	public void createtxt() throws IOException {
		FileWriter fw;
		try {
			fw = new FileWriter(new File(this.configFile));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void storeDefaultValue(LinkedHashMap<String, String> parameters) throws FileNotFoundException {
		PrintWriter table;
		try {
			table = new PrintWriter(new File(this.configFile));
			parameters.forEach((key, defaultvalue) -> table.println(key.replaceAll(" ", "_") + "=" + defaultvalue));
			table.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean integrityCheck() {
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
		return pass;
	}

	public void writeConfiguration(String key, String value) {
		OutputStream output = null;
		try {
			output = new FileOutputStream(configFile);
			properties.setProperty(key, value);
			properties.store(output, null);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveConfiguration() {
		String key = null;
		String value = null;
		key = configElements.get(this.keyToSet);
		value = this.valueToSet;
		if (value != null)
			writeConfiguration(key.replaceAll(" ", "_"), value);
	}

	public boolean optionNumberCheck(int keyToSet, int optionsNumber) {
		boolean pass = true;
		try {
			if (keyToSet < 1 || keyToSet > optionsNumber)
				throw new EntryException(keyToSet, optionsNumber, 0);
		} catch (EntryException e) {
			pass = false;
		}
		return pass;
	}

	public boolean booleanConverter(String str) {
		String itsTrue = "true";
		boolean bool = itsTrue.equals(str);
		return bool;
	}

	public String getProperty(String key) {
		String value = properties.getProperty(key);
		return value;
	}

	public void setDefaultGamevalue(LinkedHashMap<String, String> parameters) {
		parameters.forEach((key, value) -> this.defaultValue.add(value));

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

	public void displayDefaultValue() {
		this.paramaters.forEach((key, value) -> System.out
				.println("parameter : " + key.replaceAll(" ", "_") + " -> Default value= " + value));
	}

	public void displayCurrentValue() {
		ArrayList<String> currentParameters = new ArrayList<>();
		for (int i = 0; i < this.paramaters.size(); i++) {
			setParameterKey(i);
			setParameterCurrentValue(i);
			currentParameters.add("parameter : " + this.parameterKey + " = " + this.parameterCurrentValue);
		}
		currentParameters.forEach(string -> System.out.println(string));
	}

	public int stringToInteger(String str) {
		int integer = Integer.parseInt(str);
		return integer;
	}

	public boolean valueRangeCheck(String valueToSet, int minParameter, int maxParameter) {
		boolean pass = true;
		int value = stringToInteger(valueToSet);
		if (value < minParameter || value > maxParameter)
			pass = false;
		return pass;

	}

	public boolean valueIntegerCheck(String valueToSet) {
		boolean pass = true;
		boolean digitOnly = valueToSet.matches("[0-9]{" + valueToSet.length() + "}");
		if (digitOnly == false)
			pass = false;
		return pass;
	}

	public boolean valueBooleanCheck(String booleanValue) {
		boolean pass = false;
		boolean itsTrue = booleanValue.equalsIgnoreCase("true");
		boolean itsFalse = booleanValue.equalsIgnoreCase("false");
		if (itsTrue == true || itsFalse == true)
			pass = true;
		return pass;
	}

}
