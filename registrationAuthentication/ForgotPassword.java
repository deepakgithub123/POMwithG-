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


public class ForgotPassword extends GenMethods {
	
	private GenerateData genData;

	@BeforeClass
	public void InitializeSuite()
	{
		testBedSetup();
		genData = new GenerateData();
	}
	
	@Test(priority=1)
	public void GIN8() //To verify the functionality of Forgot Password button with blank field
	{
		clickWhenReady(frgtPassBtn, 5);
		dr.findElements(By.xpath("//h1[contains(text(),'Forgot Password')]"));
		clickWhenReady(frgtPassSubmitBtn, 5);
		String actMsg = getElementText(frgtFailureMsg, 5);
		Assert.assertEquals(actMsg,"Failure");
	}
	
	@Test(priority=2)
	public void GIN9() //To verify the functionality of Forgot Password button with valid email id
	{
		dr.navigate().to(url);
		clickWhenReady(frgtPassBtn, 5);
		sendKeysToElement(frgtEmailField, registeredEmail);
		clickWhenReady(frgtPassSubmitBtn, 5);
		String actMsg = getElementText(frgtSuccessMsg, 5);
		Assert.assertEquals(actMsg.trim(), "Please check your email for password.");		
	}
	
	@Test(priority=3)
	public void GIN10() //To verify the functionality of Forgot Password button with invalid email id
	{
		dr.navigate().to(url);
		clickWhenReady(frgtPassBtn, 5);
		sendKeysToElement(frgtEmailField, genData.generateInvalidEmail(20));
		clickWhenReady(frgtPassSubmitBtn, 5);
		String actMsg = getElementText(frgtFailureMsg, 5);
		Assert.assertEquals(actMsg,"Failure");
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
