package com.qa.cnbc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.cnbc.base.BasePage;

public class ProfilePage extends BasePage {

	public ProfilePage(WebDriver driver) {
		super(driver);

	}

	private By username = By.id("firstname");
	private By lastname = By.id("lastname");
	private By email = By.xpath("//input[@name='email']");
	private By saveChanges = By.xpath("//button[@type='submit']");
	private By submit = By.xpath("//input[@type='submit']");
	private By oldpassword = By.xpath("//input[@name='oldPassword']");
	private By newPasswords = By.xpath("//input[@name='newPassword']");
	private By newPasswordConfirmation = By.xpath("//input[@name='newPasswordConfirmation']");
	private By successMessage = By.xpath("//div[@class='ChangePassword-successMessage']");
	private By errorMessage = By.xpath("//div[@class='ChangePassword-apiError']");
	private By errorMsg1 = By.xpath("//p[normalize-space()='New password and confirmation need to match']");

	private By successProfile = By.xpath("//div[@class='ProfileForm-successMessage']");

	public void oldPassword(String password) {

		waitForElementVisible(oldpassword, locatortimeout);
		getElement(oldpassword).sendKeys(password);
	}

	public void newPassword(String password) {

		getElement(newPasswords).sendKeys(password);
	}

	public void newConfirmationPassword(String password) {

		getElement(newPasswordConfirmation).sendKeys(password);
	}

	public void submitbutton() {

		getElement(submit).click();
	}

	public String resetPassword(String pwd, String resetPassword, String confirmPwd) throws Exception {

		oldPassword(pwd);
		newPassword(resetPassword);
		newConfirmationPassword(confirmPwd);
		submitbutton();
		System.out.println(resetPassword);
		return resetPassword;
	}

	public String getSuccessProfile() {
		waitForElementVisible(successProfile, locatortimeout);
		return getElement(successProfile).getText();
	}

	public String getSuccessMessage() throws Exception {

		waitForElementVisible(successMessage, locatortimeout);
		return getElement(successMessage).getText();
	}

	public String getValidationMessage() throws Exception {

		waitForElementVisible(errorMessage, locatortimeout);
		return getElement(errorMessage).getText();
	}

	public String getValidationMessages() throws Exception {

		waitForElementVisible(errorMsg1, locatortimeout);
		return getElement(errorMsg1).getText();
	}

	public ProfilePage updateContact(String first, String lastName, String un) {

		enterFirstName(first);
		enterLastName(lastName);
		enterEmail(un);
		clickOnSaveChanges();
		return new ProfilePage(driver);
	}

	public void clickOnSaveChanges() {

		waitForElementVisible(saveChanges, locatortimeout);
		getElement(saveChanges).click();
	}

	public void enterEmail(String un) {

		waitForElementVisible(email, locatortimeout);
		inputText(email, un);
	}

	public void enterLastName(String lastName2) {

		waitForElementVisible(lastname, locatortimeout);
		inputText(lastname, lastName2);

	}

	public void enterFirstName(String first) {
		waitForElementVisible(username, locatortimeout);
		inputText(username, first);

	}

}
