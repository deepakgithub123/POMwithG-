package layerSettings;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genOps.GenMethods;

public class DriveTest extends GenMethods {
	
	String driveTestNameVal = "";

	@BeforeClass
	private void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority=1)
	public void GIN63() throws InterruptedException //To verify the functionality of "Drive Test" with EVDO technology
	{
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(driveTestTab, 5);

		//Verify Labels
		String actlabel1= getElementText(nameLabel, 1);
		Assert.assertEquals(actlabel1, "Name");

		String actlabel2= getElementText(marketLabel, 1);
		Assert.assertEquals(actlabel2, "Market");

		String actlabel3= getElementText(technologyLabel, 1);
		Assert.assertEquals(actlabel3, "Technology");

		String actlabel4= getElementText(typeLabel, 1);
		Assert.assertEquals(actlabel4, "Type");

		String actlabel5= getElementText(dateLabel, 1);
		Assert.assertEquals(actlabel5, "Date");

		String actlabel6= getElementText(parameterLabel, 1);
		Assert.assertEquals(actlabel6, "Parameter");

		String actlabel7= getElementText(autoThresholdsLabel, 1);
		Assert.assertEquals(actlabel7, "Auto Thresholds");

		String actlabel8= getElementText(horizontalOffsetLabel, 1);
		Assert.assertEquals(actlabel8, "Horizontal Offset (points)");

		String actlabel9= getElementText(verticalOffsetLabel, 1);
		Assert.assertEquals(actlabel9, "Vertical Offset (points)");

		driveTestNameVal = "DriveTestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(nameTextBox, driveTestNameVal);

		//Market Field
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='dt_settings_markets_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='dt_settings_markets_0']/button"), 2);
		int ranNumber = genData.generateRandomNumericFromRange(allElements.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='dt_settings_markets_0']/ul/li["+ ranNumber +"]"), 5);

		//Technology Field
		/*List<WebElement> allElements2 = dr.findElements(By.xpath("//*[@id='dt_settings_technology_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/button"), 2);
		int ranNumber2 = genData.generateRandomNumericFromRange(allElements2.size()+1);
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/ul/li["+ ranNumber2 +"]"), 5);*/
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/button"), 2);
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/ul/li[1]"), 5);
		//Thread.sleep(2000);

		//Type Field
		int typeFieldSize = 0;
		List<WebElement> allElements3 = dr.findElements(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li"));
		if(allElements3.size() == 1){
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li[2]"), 30);
			List<WebElement> allElements3a = dr.findElements(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li"));
			typeFieldSize = allElements3a.size();
			int ranNumber3 = genData.generateRandomNumericFromRange(typeFieldSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li["+ ranNumber3 +"]"), 5);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/button"), 2);
			int ranNumber3 = genData.generateRandomNumericFromRange(allElements3.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li["+ ranNumber3 +"]"), 5);
		}

		//Date
		int typeDateSize = 0;
		List<WebElement> allElements4 = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
		if(allElements4.size() == 0){
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_dates_0']/ul/li[1]"), 30);
			List<WebElement> allElements4a = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
			typeDateSize = allElements4a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeDateSize + 1, 1);
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/button"), 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 1);
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Parameter
		int parameterSize = 0;
		List<WebElement> allElements5 = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
		if(allElements5.size() == 1){
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li[2]"), 30);
			List<WebElement> allElements5a = dr.findElements(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li"));
			parameterSize = allElements5a.size();
			int ranNumber5 = genData.generateRandomNumericFromRange(parameterSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/button"), 2);
			int ranNumber5 = genData.generateRandomNumericFromRange(allElements5.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}

		//Type Horizontal Offset 
		sendKeysToElement(By.xpath("//*[@id='dt_settings_offset_hor_0']"), "10");
		//Type Vertical Offset 
		sendKeysToElement(By.xpath("//*[@id='dt_settings_offset_ver_0']"), "10");

		clickWhenReady(By.xpath("//*[@id='dt_settings_form_0']/div[2]/div[4]/div/button"), 5);

		Thread.sleep(50000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements6 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements6.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(driveTestNameVal))
			{
				Assert.assertEquals(linkElement.getText(), driveTestNameVal);
				break;
			}
		}
	}
	
	@Test(priority=2)
	public void GIN64() throws InterruptedException //To verify the functionality of "Drive Test" with LTE technology
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(driveTestTab, 5);

		//Verify Labels
		String actlabel1= getElementText(nameLabel, 1);
		Assert.assertEquals(actlabel1, "Name");

		String actlabel2= getElementText(marketLabel, 1);
		Assert.assertEquals(actlabel2, "Market");

		String actlabel3= getElementText(technologyLabel, 1);
		Assert.assertEquals(actlabel3, "Technology");

		String actlabel4= getElementText(typeLabel, 1);
		Assert.assertEquals(actlabel4, "Type");

		String actlabel5= getElementText(dateLabel, 1);
		Assert.assertEquals(actlabel5, "Date");

		String actlabel6= getElementText(parameterLabel, 1);
		Assert.assertEquals(actlabel6, "Parameter");

		String actlabel7= getElementText(autoThresholdsLabel, 1);
		Assert.assertEquals(actlabel7, "Auto Thresholds");

		String actlabel8= getElementText(horizontalOffsetLabel, 1);
		Assert.assertEquals(actlabel8, "Horizontal Offset (points)");

		String actlabel9= getElementText(verticalOffsetLabel, 1);
		Assert.assertEquals(actlabel9, "Vertical Offset (points)");

		driveTestNameVal = "DriveTestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(nameTextBox, driveTestNameVal);

		//Market Field
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='dt_settings_markets_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='dt_settings_markets_0']/button"), 2);
		int ranNumber = genData.generateRandomNumericFromRange(allElements.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='dt_settings_markets_0']/ul/li["+ ranNumber +"]"), 5);

		//Technology Field
		/*List<WebElement> allElements2 = dr.findElements(By.xpath("//*[@id='dt_settings_technology_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/button"), 2);
		int ranNumber2 = genData.generateRandomNumericFromRange(allElements2.size()+1);
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/ul/li["+ ranNumber2 +"]"), 5);*/
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/button"), 2);
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/ul/li[2]"), 5);
		Thread.sleep(2000);
		//Type Field
		int typeFieldSize = 0;
		List<WebElement> allElements3 = dr.findElements(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li"));
		if(allElements3.size() == 1){
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li[2]"), 30);
			List<WebElement> allElements3a = dr.findElements(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li"));
			typeFieldSize = allElements3a.size();
			int ranNumber3 = genData.generateRandomNumericFromRange(typeFieldSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li["+ ranNumber3 +"]"), 5);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/button"), 2);
			int ranNumber3 = genData.generateRandomNumericFromRange(allElements3.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li["+ ranNumber3 +"]"), 5);
		}


		//Date
		int typeDateSize = 0;
		List<WebElement> allElements4 = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
		if(allElements4.size() == 0){
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_dates_0']/ul/li[1]"), 30);
			List<WebElement> allElements4a = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
			typeDateSize = allElements4a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeDateSize + 1, 1);
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/button"), 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 1);
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Parameter
		int parameterSize = 0;
		List<WebElement> allElements5 = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
		if(allElements5.size() == 1){
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li[2]"), 30);
			List<WebElement> allElements5a = dr.findElements(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li"));
			parameterSize = allElements5a.size();
			int ranNumber5 = genData.generateRandomNumericFromRange(parameterSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/button"), 2);
			int ranNumber5 = genData.generateRandomNumericFromRange(allElements5.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		//Type Horizontal Offset 
		sendKeysToElement(By.xpath("//*[@id='dt_settings_offset_hor_0']"), "10");
		//Type Vertical Offset 
		sendKeysToElement(By.xpath("//*[@id='dt_settings_offset_ver_0']"), "10");

		clickWhenReady(By.xpath("//*[@id='dt_settings_form_0']/div[2]/div[4]/div/button"), 5);

		Thread.sleep(10000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements6 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements6.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(driveTestNameVal))
			{
				Assert.assertEquals(linkElement.getText(), driveTestNameVal);
				break;
			}
		}
	}
	
	@Test(priority=3)
	public void GIN65() throws InterruptedException //To verify the functionality of "Drive Test" with Voice technology
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(driveTestTab, 5);

		//Verify Labels
		String actlabel1= getElementText(nameLabel, 1);
		Assert.assertEquals(actlabel1, "Name");

		String actlabel2= getElementText(marketLabel, 1);
		Assert.assertEquals(actlabel2, "Market");

		String actlabel3= getElementText(technologyLabel, 1);
		Assert.assertEquals(actlabel3, "Technology");

		String actlabel4= getElementText(typeLabel, 1);
		Assert.assertEquals(actlabel4, "Type");

		String actlabel5= getElementText(dateLabel, 1);
		Assert.assertEquals(actlabel5, "Date");

		String actlabel6= getElementText(parameterLabel, 1);
		Assert.assertEquals(actlabel6, "Parameter");

		String actlabel7= getElementText(autoThresholdsLabel, 1);
		Assert.assertEquals(actlabel7, "Auto Thresholds");

		String actlabel8= getElementText(horizontalOffsetLabel, 1);
		Assert.assertEquals(actlabel8, "Horizontal Offset (points)");

		String actlabel9= getElementText(verticalOffsetLabel, 1);
		Assert.assertEquals(actlabel9, "Vertical Offset (points)");

		driveTestNameVal = "DriveTestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(nameTextBox, driveTestNameVal);

		//Market Field
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='dt_settings_markets_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='dt_settings_markets_0']/button"), 2);
		int ranNumber = genData.generateRandomNumericFromRange(allElements.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='dt_settings_markets_0']/ul/li["+ ranNumber +"]"), 5);

		//Technology Field
		/*List<WebElement> allElements2 = dr.findElements(By.xpath("//*[@id='dt_settings_technology_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/button"), 2);
		int ranNumber2 = genData.generateRandomNumericFromRange(allElements2.size()+1);
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/ul/li["+ ranNumber2 +"]"), 5);*/
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/button"), 2);
		clickWhenReady(By.xpath("//*[@id='dt_settings_technology_0']/ul/li[3]"), 5);
		Thread.sleep(2000);
		//Type Field
		int typeFieldSize = 0;
		List<WebElement> allElements3 = dr.findElements(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li"));
		if(allElements3.size() == 1){
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li[2]"), 30);
			List<WebElement> allElements3a = dr.findElements(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li"));
			typeFieldSize = allElements3a.size();
			int ranNumber3 = genData.generateRandomNumericFromRange(typeFieldSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li["+ ranNumber3 +"]"), 5);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/button"), 2);
			int ranNumber3 = genData.generateRandomNumericFromRange(allElements3.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_drive_test_types_0']/ul/li["+ ranNumber3 +"]"), 5);
		}

		//Date
		int typeDateSize = 0;
		List<WebElement> allElements4 = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
		if(allElements4.size() == 0){
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_dates_0']/ul/li[1]"), 30);
			List<WebElement> allElements4a = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
			typeDateSize = allElements4a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeDateSize + 1, 1);
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/button"), 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 1);
			clickWhenReady(By.xpath("//*[@id='dt_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Parameter
		int parameterSize = 0;
		List<WebElement> allElements5 = dr.findElements(By.xpath("//*[@id='dt_settings_dates_0']/ul/li"));
		if(allElements5.size() == 1){
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/button"), 2);
			waitForElementPresence(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li[2]"), 30);
			List<WebElement> allElements5a = dr.findElements(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li"));
			parameterSize = allElements5a.size();
			int ranNumber5 = genData.generateRandomNumericFromRange(parameterSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		else{
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/button"), 2);
			int ranNumber5 = genData.generateRandomNumericFromRange(allElements5.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='dt_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		//Type Horizontal Offset 
		sendKeysToElement(By.xpath("//*[@id='dt_settings_offset_hor_0']"), "10");
		//Type Vertical Offset 
		sendKeysToElement(By.xpath("//*[@id='dt_settings_offset_ver_0']"), "10");

		clickWhenReady(By.xpath("//*[@id='dt_settings_form_0']/div[2]/div[4]/div/button"), 5);

		Thread.sleep(10000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements6 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements6.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(driveTestNameVal))
			{
				Assert.assertEquals(linkElement.getText(), driveTestNameVal);
				break;
			}
		}
	}
	
	@AfterMethod
	private void scrShot(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus())
		{
			String testName = result.getName().toString().trim();
			String activeClass = getClass().getName().trim();
			captureScreenshot(dr, testName, activeClass);
		}
	}
	
	@AfterClass
	private void tearDown()
	{
		dr.quit();
	}
}
