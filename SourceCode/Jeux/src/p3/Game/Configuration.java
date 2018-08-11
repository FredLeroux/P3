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



public class Configuration {

	private int keyToSet;
	private String configFile;
	private String valueToSet;
	private LinkedHashMap<Integer, String> configElements;
	private LinkedHashMap<String, String> paramaters;
	protected Properties properties;
	protected ArrayList <String> parametersTxtKeys; 
	public Configuration() {
		this.keyToSet = 0;
		this.configFile = "config.properties";
		this.valueToSet = null;
		this.configElements = new LinkedHashMap<>();
		this.paramaters = new LinkedHashMap<>();
		this.properties = new Properties();
		this.parametersTxtKeys= new ArrayList <>();
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

	public void parametersList() {
		ArrayList<String> keyList = new ArrayList<>();
		keyList.addAll(this.paramaters.keySet());
		for (int i = 0; i < this.paramaters.size(); i++)
			this.configElements.put(i + 1, keyList.get(i));
		this.configElements.forEach((key, value) -> System.out.println(key + ":" + value));
	}
	public void createParameterKeysList(HashMap<String,String> parameters) {
		
		ArrayList <String> keyset = new ArrayList <>();
		keyset.addAll(parameters.keySet());
		keyset.forEach(string->this.parametersTxtKeys.add(string.replaceAll(" ", "_")));
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

		try {
			input = new FileInputStream(this.configFile);
		} catch (FileNotFoundException fne) {
			pass = false;
		} finally {
			try {
				if (pass == false)
					throw new EntryException(new FileNotFoundException());
			} catch (EntryException e) {
				createtxt(this.paramaters);
				displayParameters();
				input = new FileInputStream(configFile);
			}
		}
		try {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		input.close();
	}

	public void createtxt(HashMap<String, String> parameters) throws IOException {
		FileWriter fw;
		try {
			fw = new FileWriter(new File(this.configFile));
			PrintWriter table;
			table = new PrintWriter(new File(this.configFile));
			parameters.forEach((key, defaultvalue) -> table.println(key.replaceAll(" ", "_") + "=" + defaultvalue));
			table.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public void optionNumberCheck(int keyToSet, int optionsNumber) {
		boolean pass = true;
		try {
			if (keyToSet < 1 || keyToSet > optionsNumber)
				throw new EntryException(keyToSet, optionsNumber, 0);
		} catch (EntryException e) {
			pass = false;
		}
		if (pass == false)
			this.keyToSet = 0;
		else
			this.keyToSet = keyToSet;
	}

}
