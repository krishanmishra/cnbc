package com.qa.cnbc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.cnbc.base.BasePage;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}

	
	private By myAccount=By.xpath("//button[@class='SignInMenu-accountMenu']");
	private By logout=By.xpath("//a[normalize-space()='SIGN OUT']");
	private By profile=By.cssSelector("a[title='profile']");
	
	//a[@title='profile']

	//button[@class='SignInMenu-accountMenu']
	//a[@title='profile']

	
	public void clickOnMyAccount() {
		waitForPageLoad(timeout);		
		waitForElementVisible(myAccount,locatortimeout);
		waitForElementClickable(myAccount,locatortimeout);
		getElement(myAccount).click();
	}
	
	public void clickOnProfileLink() {
		waitForElementVisible(profile,locatortimeout);
		doActionsClick(profile);
	}
	
	public void clickOnLogoutLink() {
		waitForElementVisible(logout,locatortimeout);
		doActionsClick(logout);
		waitForPageLoad(timeout);
	}
	
	
	public ProfilePage gotoProfileScreen() {
		waitForPageLoad(timeout);
		clickOnMyAccount();
		clickOnProfileLink();
		waitForPageLoad(timeout);
		return new ProfilePage(driver);
		
	}

	public LoginPage logout() {
		browerScrollUp();	
		clickOnMyAccount();		
		clickOnLogoutLink();
		waitForPageLoad(timeout);
		switchToChildWindow();
		// close the child browser window
	      driver.close();
		//driver.switchTo().defaultContent();
		return new LoginPage(driver);
	}
	
	
}
