package registrationAuthentication;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genOps.GenMethods;
import testData.GenerateData;


public class Login extends GenMethods{

	private GenerateData genData;
	
	@BeforeClass
	public void InitializeSuite()
	{
		testBedSetup();
		genData = new GenerateData();
	}	

	@Test(priority=1)
	public void GIN1() //To verify the Login page
	{
		String expTitle="GINA 1.0 Login";
		String actualTitle = dr.getTitle();
		Assert.assertEquals(actualTitle, expTitle);
	}

	@Test(priority=2)
	public void GIN2() throws InterruptedException //To verify the Labels on Login Screen
	{
		String expLabelUser = "Username";
		String actualLabelUser = getElementText(loginUserFieldLabel, 5);
		Assert.assertEquals(actualLabelUser, expLabelUser);
		String expLabelPass="Password";
		String actualLabelPass = getElementText(loginPassFieldLabel, 5);
		Assert.assertEquals(actualLabelPass, expLabelPass);
		isElementPresent(loginBtn);
		isElementPresent(serviceBtn);
		isElementPresent(forgotBtn);
		isElementPresent(registerBtn);
	}

	@Test(priority=3)
	public void GIN4() //To verify the functionality of text box fields: Username, Password
	{	
		sendKeysToElement(usernameField, genData.generateRandomAlphaNumeric(10));
		sendKeysToElement(passwordField, genData.generateRandomAlphaNumeric(10));
	}

	@Test(priority=4)
	public void GIN6() throws InterruptedException //To verify the functionality of Login button with valid credentials
	{
		login(validUsername, validPassword);
		clickWhenReady(xpathToLogout, 10);
	}

	@Test(priority=5)
	public void GIN7() throws InterruptedException //To verify the functionality of Login button with invalid credentials
	{
		waitForElementPresence(usernameField, 20);
		sendKeysToElement(usernameField, genData.generateRandomAlphaNumeric(10));
		sendKeysToElement(passwordField, genData.generateRandomAlphaNumeric(10));
		clickWhenReady(loginBtn, 5);
		String expMessage = "      Invalid Login: Please check your username and/or password.       Both are case sensitive.";
		String actualMessage = getElementText(loginFailureMsg, 5);
		Assert.assertEquals(actualMessage, expMessage);
	}
	
	@Test(priority=6)
	public void GIN24() throws InterruptedException //To verify the functionality of "Service Desk" button on Login Page
	{
		dr.navigate().to(url);
		String expTitle="GINA 1.0 Login";
		String expTab2Title="Login - Service Desk";
		String actualTitle = dr.getTitle();
		Assert.assertEquals(actualTitle, expTitle);
		clickWhenReady(serviceBtn, 20);
		ArrayList<String> openedTabs = new ArrayList<String> (dr.getWindowHandles());
		dr.switchTo().window(openedTabs.get(1));
		String tab2title = dr.getTitle();
		Assert.assertEquals(tab2title, expTab2Title);
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
