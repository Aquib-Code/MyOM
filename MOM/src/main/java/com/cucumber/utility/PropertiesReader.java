package com.cucumber.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader extends LoggerClass{
	/**
	 * Utility class to perform data read operations for test & config data
	 */

	private static Properties configProp;


	public static Properties loadProperties(String fileName) {
		configProp = new Properties();
		try {
			configProp.load(new FileInputStream(new File(System.getProperty("ResourcesBaseFolder") +System.getProperty("EnvironmentProps") + fileName + ".properties")));
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return configProp;
	}

	
}
