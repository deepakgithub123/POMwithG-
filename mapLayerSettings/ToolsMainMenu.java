package mapLayerSettings;

import genOps.GenMethods;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ToolsMainMenu extends GenMethods {
	
	@BeforeClass
	public void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority= 1)
	public void GIN123() throws InterruptedException //To verify the functionality of "New Map" functionality
	{
		dr.navigate().refresh();
		for(int i=1;i<=10;i++)
		{
		clickWhenReady(toolsLinkInMenuBar, 2);
		clickWhenReady(By.xpath("//*[@id='full-extent-btn']/div"), 10);
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent(By.xpath("//*[@id='Header_"+i+"']/div[1]/div[1]")));
		Thread.sleep(2000);
		}
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
