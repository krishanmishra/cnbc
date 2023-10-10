package com.qa.cnbc.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.cnbc.pages.HomePage;
import com.qa.cnbc.pages.LoginPage;
import com.qa.cnbc.pages.ProfilePage;

public class BaseTest {

	public WebDriver driver;

	public LoginPage loginPage;
	public HomePage homePage;
	public ProfilePage profilePage;
	public String un;
	public String pwd;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	@BeforeTest
	public WebDriver setUp() throws Exception {

		// here we are checking if the value is coming from the maven command (mvn test)
		// if so then read that otherwise read that from data properties
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: Utilities.readPropertiesFile("browser");

		if (DriverType.Firefox.toString().toLowerCase().equals(browserName.toLowerCase())) {

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setAcceptInsecureCerts(true);
			tlDriver.set(new FirefoxDriver(firefoxOptions));

		} else if (DriverType.Chrome.toString().toLowerCase().equals(browserName.toLowerCase())) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.setAcceptInsecureCerts(true);
			tlDriver.set(new ChromeDriver(options));

		} else {
			throw new Exception("Please pass valid browser type value");
		}
		/* Delete cookies */
		getDriver().manage().deleteAllCookies();

		/* maximize the browser */
		getDriver().manage().window().maximize();

		getDriver().get(Utilities.readPropertiesFile("url"));

		// initialize the page

		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		profilePage = new ProfilePage(getDriver());

		// Read the username and password

		pwd = System.getProperty("password") != null ? System.getProperty("password")
				: Utilities.readPropertiesFile("password");
		un = System.getProperty("username") != null ? System.getProperty("username")
				: Utilities.readPropertiesFile("username");
		
		return getDriver();
	}


	/**
	 * get the thread local copy of driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}


	/*******************************************
	 * After Suite Quit the browser
	 ***********************/
	@AfterTest
	public void afterSuite() {
		//driver.close();
		getDriver().quit();

	}

	/*******************************************
	 * Driver type as Enum
	 ***********************/
	enum DriverType {
		Firefox, IE, Chrome
	}
	
	/*
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
