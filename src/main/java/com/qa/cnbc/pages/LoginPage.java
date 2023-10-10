package com.qa.cnbc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.cnbc.base.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {

		super(driver);

	}

	private By emailId = By.xpath("//input[@name='email']");
	private By password = By.xpath("//input[@name='password']");
	private By loginBtn = By.xpath("//button[@name='signin']");
	private By signIn = By.linkText("SIGN IN");
	private By singupTitle = By.xpath("//div[@class='AuthForms-headerTitle']");
	private By messageConfirmation = By.xpath("//p[@class='SignedIn-messageConfirmation']");

	String un;
	String pwd;

	public void enterUsername(String username) {
		waitForElementVisible(emailId, locatortimeout);
		getElement(emailId).sendKeys(username);
	}

	public void enterPassword(String username) {
		waitForElementVisible(password, locatortimeout);
		getElement(password).sendKeys(username);
	}

	public void clickLoginButton() {
		waitForElementVisible(loginBtn, locatortimeout);
		getElement(loginBtn).click();
	}

	public void clickSignIn() {
		getElement(signIn).click();
	}

	public void messageConfirmation() {
		waitForElementVisible(messageConfirmation, locatortimeout);
		System.out.println("Message for Sing in: " + getElement(messageConfirmation).getText());
		waitForElementToBecomeInvisible(getElement(messageConfirmation));

	}

	public HomePage doLogin(String un,String pwd) throws Exception {		
		System.out.println("login creds are: " + un + " : " + pwd);
		waitForElementVisible(singupTitle, locatortimeout);
		enterUsername(un);
		enterPassword(pwd);
		clickLoginButton();

		waitForPageLoad(timeout);
		messageConfirmation();
		return new HomePage(driver);
	}

	public LoginPage openSinginwindow() {
		clickSignIn();
		switchToChildWindow();
		return new LoginPage(driver);
	}

}
