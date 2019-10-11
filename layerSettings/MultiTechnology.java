package layerSettings;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genOps.GenMethods;

public class MultiTechnology extends GenMethods {

	@BeforeClass
	public void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}
	
	@Test(priority=1)
	public void Exp87() throws InterruptedException //To verify the functionality on "Multi-technology" tab on settings pop-up (default threshold values)
	{
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(multiTechnologyTab, 5);
		WebElement selectElm = dr.findElement(thresholdsMultiTechnology); 
		Select mySelect= new Select(selectElm);
		WebElement selectedOption = mySelect.getFirstSelectedOption();
		String str = selectedOption.getText(); 
		String str1 = str.substring(str.length() - 1);
		int intValue = Integer.parseInt(str1);
		List<WebElement> alpha = dr.findElements(By.xpath("//*[@id='mwssc_dd_0']/tbody/tr"));
		int noOfRows = alpha.size() -2;
		Assert.assertEquals(intValue, noOfRows);
	}
	
	@Test(priority=2)
	public void GIN87() throws InterruptedException //To verify the functionality on "Multi-technology" tab on settings pop-up
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(multiTechnologyTab, 5);
		
		List<WebElement> element = dr.findElements(By.xpath("//*[@id='mws_threshold_num_0']/option"));
		for(int i=1;i<=element.size();i++)
		{
			clickWhenReady(thresholdsMultiTechnology, 2);
			clickWhenReady(By.xpath("//*[@id='mws_threshold_num_0']/option["+i+"]"), 2);
			WebElement selectElm = dr.findElement(thresholdsMultiTechnology); 
			Select mySelect= new Select(selectElm);
			WebElement selectedOption = mySelect.getFirstSelectedOption();
			String str = selectedOption.getText(); 
			String str1 = str.substring(str.length() - 1);
			int intValue = Integer.parseInt(str1);
			List<WebElement> alpha = dr.findElements(By.xpath("//*[@id='mwssc_dd_0']/tbody/tr"));
			int noOfRows = alpha.size() -2;
			Assert.assertEquals(intValue, noOfRows);
		}
		
		clickWhenReady(saveButton, 2);
		hoverOverElement(layerSelector);
		List<WebElement> allElements = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements.size(); i++)
		{
			Boolean valueselected=dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).isSelected();
			if(valueselected==true)
			{
				String act=dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).getText();
				Assert.assertEquals(act, "Multi-Technology");
				break;
			}
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
