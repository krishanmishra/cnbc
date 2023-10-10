package com.qa.cnbc.profile;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.cnbc.base.BaseTest;
import com.qa.cnbc.base.Utilities;

public class ChangePassword extends BaseTest {

	@Test(priority = 1, enabled =true,description = "verify the user can reset the password ")
	public void testChangePassword() throws Exception {

		String resetPassword = "K" + Utilities.generateRandomString(3) + "@123";

		loginPage = loginPage.openSinginwindow();
		homePage = loginPage.doLogin(un, pwd);
		profilePage = homePage.gotoProfileScreen();
		resetPassword = profilePage.resetPassword(pwd, resetPassword,resetPassword);

		// write the new password into properties file
		Utilities.setPropertiesFile("password", resetPassword);
		
		//verify Success message after reset the password

		Assert.assertEquals(profilePage.getSuccessMessage(),
				"You have successfully reset your password. A confirmation email has been sent to kkmjssate@gmail.com.");
		loginPage = homePage.logout();

	}

	@Test(priority = 2,enabled=true, description = "Validation message when Old password is not valid. Please try again")
	public void verifyValidationMessage() throws Exception {

		String resetPassword = "K" + Utilities.generateRandomString(3) + "@123";

		loginPage = loginPage.openSinginwindow();
		homePage = loginPage.doLogin(un, pwd);
		profilePage = homePage.gotoProfileScreen();

		pwd = System.getProperty("password") != null ? System.getProperty("password") : "test@123";
		resetPassword = profilePage.resetPassword(pwd, resetPassword,resetPassword);

		// Verify the validation message
		Assert.assertEquals(profilePage.getValidationMessage(), "Old password is not valid. Please try again");
		loginPage = homePage.logout();

	}
	
	@Test(priority = 3, description = "Validation message when new and confirm password mismatched")
	public void verifyMismachedConfirmedPassword() throws Exception {

		String resetPassword = "K" + Utilities.generateRandomString(3) + "@123";

		loginPage = loginPage.openSinginwindow();
		
		homePage = loginPage.doLogin(un, pwd);
		profilePage = homePage.gotoProfileScreen();

		pwd = System.getProperty("password") != null ? System.getProperty("password") : "test@123";
		resetPassword = profilePage.resetPassword(pwd, resetPassword,"test@1234");

		// Verify the validation message
		Assert.assertEquals(profilePage.getValidationMessages(), "New password and confirmation need to match");
		loginPage = homePage.logout();

	}

}
