package com.qa.cnbc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;

public class Utilities {

	/**
	 * 
	 * @param lettersNum
	 * @return
	 */
	public static String generateRandomString(int lettersNum) {
		String finalString = "";

		int numberOfLetters = 25;
		long randomNumber;
		for (int i = 0; i < lettersNum; i++) {
			char letter = 97;
			randomNumber = Math.round(Math.random() * numberOfLetters);
			letter += randomNumber;
			finalString += String.valueOf(letter);
		}
		return finalString;
	}

	/**
	 * 
	 * @param lettersNum
	 * @return
	 */
	public static int generateRandomIntegers() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 1000 + r.nextInt(1000));

	}

	/*******************************************
	 * Read the Properties file
	 ***********************/
	public static String readPropertiesFile(String key) throws Exception {
		String value = "";
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\qa\\cnbc\\resources\\testdata.properties");
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (FileNotFoundException ex) {
			System.out.println("Failed to read from application.properties file.");
			throw ex;
		}
		if (value == null)
			throw new Exception("Key not found in properties file");
		return value;
	}

	/*******************************************
	 * Read the Properties file
	 * 
	 * @param <FileOutputStream>
	 ***********************/
	public static String setPropertiesFile(String key, String value) throws Exception {
		try {
			Properties prop = new Properties();

			// Load the existing properties from the file
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\qa\\cnbc\\resources\\testdata.properties");
			prop.load(fis);

			// Modify or add new properties
			prop.setProperty(key, value);

			FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\qa\\cnbc\\resources\\testdata.properties");

			// Save the updated properties back to the file
			prop.store(fout, null);
			System.out.println("Properties updated successfully.");

		} catch (FileNotFoundException ex) {
			System.out.println("Failed to read from application.properties file.");
			throw ex;
		}
		if (value == null)
			throw new Exception("Key not found in properties file");
		return value;
	}

}
