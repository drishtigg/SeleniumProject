package com.container.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class ReadConfig {

	// object
	Properties properties;

	String path = "C:\\Users\\i-drishti_verdis\\Documents\\FunctionTest-Workspace\\Configuration\\config.properties";

	// constructor
	public ReadConfig() {

		try {// initailize object
			properties = new Properties();

			// create file data
			FileInputStream fis = new FileInputStream(path);

			properties.load(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBaseurl() {

		String Value = properties.getProperty("baseurl");

		if (Value != null)
			return Value;
		else
			throw new RuntimeException("url not specified in config file");

	}

	public String getBrowser() {

		String Value = properties.getProperty("browser");

		if (Value != null)
			return Value;
		else
			throw new RuntimeException("browser not specified in config file");

	}

}
