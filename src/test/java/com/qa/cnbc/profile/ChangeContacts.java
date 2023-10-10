package com.qa.cnbc.profile;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.cnbc.base.BaseTest;
import com.qa.cnbc.base.Utilities;

public class ChangeContacts extends BaseTest {

	@Test(description="user can update the contacts")
	public void testUpdateContact() throws Exception {
		String firstName="First" + Utilities.generateRandomString(3);
		String lastName="Last" + Utilities.generateRandomString(3);
	
		loginPage=loginPage.openSinginwindow();		
		homePage = loginPage.doLogin(un,pwd);
		profilePage = homePage.gotoProfileScreen();
		profilePage=profilePage.updateContact(firstName,lastName,un);
		Assert.assertEquals(profilePage.getSuccessProfile(), "Your changes have been saved.");
		
		loginPage=homePage.logout();	

	}
}
