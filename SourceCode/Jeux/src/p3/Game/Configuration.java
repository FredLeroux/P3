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
	protected  Properties properties= new Properties();
	protected ArrayList <String> parametersTxtKeys; 
	protected ArrayList<String> defaultValue =new ArrayList<>();
	protected String parameterKey = null;
	protected String parameterCurrentValue = null;
	protected String parameterDefaultValue = null;
	protected boolean valueCheckPass = true;
	
	
	public Configuration() {
		this.keyToSet = 0;
		this.configFile = "config.properties";
		this.valueToSet = null;
		this.configElements = new LinkedHashMap<>();
		this.paramaters = new LinkedHashMap<>();
		//this.properties 
		this.parametersTxtKeys= new ArrayList <>();
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
			this.properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		input.close();
	}

	public void createtxt(LinkedHashMap<String, String> parameters) throws IOException {
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
	public boolean booleanConverter(String str) {
		String itsTrue = "true";
		boolean bool = itsTrue.equals(str);
		return bool;
		}
		
		public String getProperty(String key) {
			String value = properties.getProperty(key);
			return value;
		}
		
		
		public void setDefaultGamevalue(LinkedHashMap <String,String> parameters) {		
			parameters.forEach((key,value)-> this.defaultValue.add(value));
			
		}
	
		
		public void setParameterKey(int parametersTableIndice) {			
			this.parameterKey =parametersTxtKeys.get(parametersTableIndice);
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
	this.paramaters.forEach((key,value)->System.out.println("parameter : "+key.replaceAll(" ", "_")+" -> Default value= "+value));
}

public void displayCurrentValue() {
	ArrayList <String> currentParameters = new ArrayList<>();
	for(int i= 0;i<this.paramaters.size();i++) {
		setParameterKey(i);
		setParameterCurrentValue(i);
		currentParameters.add("parameter : "+this.parameterKey+" -> Default value= "+this.parameterCurrentValue);		
	}
	currentParameters.forEach(string->System.out.println(string));
}
public int stringToInteger(String str) {
	int integer = Integer.parseInt(str);
	return integer;
}


public void valueRangeCheck(String valueToSet, int minParameter, int maxParameter) {	
	int value = stringToInteger(valueToSet);
	if (value < minParameter || value > maxParameter)
		
		this.valueCheckPass = false;
	else
		this.valueCheckPass = true;
		
}

public void valueIntegerCheck(String valueToSet) {
	boolean digitOnly = valueToSet.matches("[0-9]{" + valueToSet.length() + "}");
	if (digitOnly == false)
		this.valueCheckPass = false;
	else
		this.valueCheckPass = true;
}

public void valueBooleanCheck(String booleanValue) {
	boolean itsTrue = booleanValue.equalsIgnoreCase("true");
	boolean itsFalse = booleanValue.equalsIgnoreCase("false");
	if (itsTrue == true || itsFalse == true)
		this.valueCheckPass = true;
	else
		this.valueCheckPass = false;

}		
		
		
}
