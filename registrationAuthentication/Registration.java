package registrationAuthentication;


import genOps.GenMethods;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testData.GenerateData;


public class Registration extends GenMethods{

	private GenerateData genData;
	private String generatedValidUser = "";
	private String generatedValidEmail = "";
	
	@BeforeClass
	public void InitializeSuite()
	{
		testBedSetup();
		genData = new GenerateData();
	}	

	@Test(priority=1)
	public void GIN12() throws InterruptedException //To verify the functionality of Register button on Login screen
	{
		clickWhenReady(registerBtn, 5);
		Thread.sleep(2000);
		String bodyText = getElementText(signUpLabel, 5);
		Assert.assertTrue(bodyText.contains("Sign Up"));
	}

	@Test(priority=2)
	public void GIN13() //To verify the fields available on Register user screen
	{
		String actualLabelUsername = getElementText(regUserFieldLabel, 5);
		Assert.assertEquals(actualLabelUsername, "Your Username");
		String actualLabelEmail = getElementText(regEmailFieldLabel, 5);
		Assert.assertEquals(actualLabelEmail, "Your Email");
		sendKeysToElement(regUserField, "randomUsername");
		sendKeysToElement(regEmailField, "randomEmail");
	}

	@Test(priority=3)
	public void GIN15() //To verify the functionality of Submit button on Register user screen with invalid field values
	{
		sendKeysToElement(regUserField, genData.generateStringWithNotAllowedSplChars(10));
		sendKeysToElement(regEmailField, genData.generateInvalidEmail(20));
		clickWhenReady(signUpBtn, 5);
		String actualMsgTitle = getElementText(regMsgBoxTitle, 5);
		Assert.assertEquals(actualMsgTitle, "ACCOUNT FAILURE");
		String actualMsgText = getElementText(regMsgBoxText, 5);
		Assert.assertEquals(actualMsgText.trim(), "You did not enter a valid email address or that address is already in use.");
		clickWhenReady(regMsgBoxOkBtn, 5);
	}

	@Test(priority=4)
	public void GIN16() //To verify the functionality of Submit button on Register user screen with valid field values
	{
		String validUser = genData.generateRandomAlphaNumeric(10);
		String validEmail = genData.generateValidEmail(30);
		dr.navigate().to(url);
		clickWhenReady(registerBtn, 5);
		sendKeysToElement(regUserField, validUser);
		sendKeysToElement(regEmailField, validEmail);
		clickWhenReady(signUpBtn, 5);
		
		String actualMsgTitle = getElementText(regMsgBoxTitle, 5);
		Assert.assertEquals(actualMsgTitle, "Success");
		String actualMsgText = getElementText(regMsgBoxText, 5);
		Assert.assertEquals(actualMsgText.trim(), "Please check your email for password.");
		clickWhenReady(signUpSuccessOkBtn, 5);
		
		generatedValidUser = validUser;
		generatedValidEmail = validEmail;
	}
	
	@Test(dependsOnMethods = {"GIN16"})
	public void EXP1() //To verify the functionality of Submit button on Register user screen with existing user name value
	{
		String validUser = generatedValidUser;
		dr.navigate().to(url);
		clickWhenReady(registerBtn, 5);
		waitForElementPresence(regUserField, 10);
		sendKeysToElement(regUserField, validUser);
		sendKeysToElement(regEmailField, genData.generateValidEmail(30));
		clickWhenReady(signUpBtn, 5);
	
		String actualMsgTitle = getElementText(regMsgBoxTitle, 5);
		Assert.assertEquals(actualMsgTitle, "ACCOUNT FAILURE");
		String actualMsgText = getElementText(regMsgBoxText, 5);
		Assert.assertEquals(actualMsgText.trim(), "That username is not available.");
		clickWhenReady(regMsgBoxOkBtn, 5);
	}

	@Test(dependsOnMethods = {"GIN16"})
	public void EXP2() //To verify the functionality of Submit button on Register user screen with existing email value
	{
		String validEmail = generatedValidEmail;
		dr.navigate().to(url);
		clickWhenReady(registerBtn, 5);
		waitForElementPresence(regUserField, 10);
		sendKeysToElement(regUserField, genData.generateRandomAlphaNumeric(10));
		sendKeysToElement(regEmailField, validEmail);
		clickWhenReady(signUpBtn, 5);

		String actualMsgTitle = getElementText(regMsgBoxTitle, 5);
		Assert.assertEquals(actualMsgTitle, "ACCOUNT FAILURE");
		clickWhenReady(regMsgBoxOkBtn, 5);
	}
	
	@Test(dependsOnMethods = {"GIN16"})
	public void EXP3() throws InterruptedException //To verify the login of newly registered user with any password value
	{
		String validUser = generatedValidUser;
		dr.navigate().to(url);
		sendKeysToElement(usernameField, validUser);
		Thread.sleep(2000);
		sendKeysToElement(passwordField, genData.generateRandomAlphaNumeric(10));
		Thread.sleep(1000);
		clickWhenReady(loginBtn, 5);

		isElementPresent(loginFailureTitle);
		String actualMsgTitle = getElementText(loginFailureTitle, 5);
		Assert.assertEquals(actualMsgTitle, "LOGIN FAILURE");
	}
	
	@Test(priority=5)
	public void EXP4() //To verify the functionality of Cancel button
	{
		dr.navigate().to(url);
		clickWhenReady(registerBtn, 5);
		clickWhenReady(regCancelBtn, 5);
		waitForElementPresence(loginBtn, 5);
		Assert.assertTrue(dr.findElement(By.id("loginbutton")).isDisplayed());
	}
	
	@Test(priority=6)
	public void clearRegisterIssue() //to remove the register button issue
	{
		dr.navigate().to(url);
		clickWhenReady(registerBtn, 5);
		sendKeysToElement(regUserField, genData.generateRandomAlphaNumeric(10));
		sendKeysToElement(regEmailField, genData.generateValidEmail(30));
		clickWhenReady(signUpBtn, 5);
		
		String actualMsgTitle = getElementText(regMsgBoxTitle, 5);
		Assert.assertEquals(actualMsgTitle, "Success");
		String actualMsgText = getElementText(regMsgBoxText, 5);
		Assert.assertEquals(actualMsgText.trim(), "Please check your email for password.");
		clickWhenReady(signUpSuccessOkBtn, 5);
	}
	
	@AfterMethod
	public void scrShot(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus())
		{
			String testName = result.getName().toString().trim();
			String activeClass = getClass().getName().trim();
			captureScreenshot(dr, testName, activeClass);
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		dr.quit();
	}

}
