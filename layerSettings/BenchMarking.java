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
import testData.GenerateData;

public class BenchMarking extends GenMethods {
	GenerateData genData = new GenerateData();
	String benchmarkingNameVal = "";

	@BeforeClass
	private void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority=1)
	public void GIN66() throws InterruptedException //To verify the functionality of "Benchmarking" with EVDO technology
	{
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(benchMarkingTab, 5);
		
		Assert.assertEquals(getElementText(nameLabelBenchmarking, 1), "Name");
		Assert.assertEquals(getElementText(marketLabelBenchmarking, 1), "Market");
		Assert.assertEquals(getElementText(technologyLabelBenchmarking, 1), "Technology");
		Assert.assertEquals(getElementText(typeLabelBenchmarking, 1), "Type");
		Assert.assertEquals(getElementText(dateLabelBenchmarking, 1), "Date");
		Assert.assertEquals(getElementText(wirelessCarrierLabellBenchmarking, 1), "Wireless Carrier");
		Assert.assertEquals(getElementText(parameterLabelBenchmarking, 1), "Parameter");
		Assert.assertEquals(getElementText(autoThresholdsLabelBenchmarking, 1), "Auto Thresholds");
		Assert.assertEquals(getElementText(horizontalOffsetLabelBenchmarking, 1), "Horizontal Offset (points)");
		Assert.assertEquals(getElementText(verticalOffsetLabelBenchmarking, 1), "Vertical Offset (points)");

		benchmarkingNameVal = "Benchmarking"+ genData.generateRandomNumeric(5);
		sendKeysToElement(nameTextBoxBenchmarking, benchmarkingNameVal);

		//Market Field
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='bm_settings_markets_0']/ul/li"));
		clickWhenReady(marketFieldBenchmarking, 2);
		int ranNumber = genData.generateRandomNumericFromRange(allElements.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='bm_settings_markets_0']/ul/li["+ ranNumber +"]"), 5);

		//Technology Field
		List<WebElement> allElements2 = dr.findElements(By.xpath("//*[@id='bm_settings_technology_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='bm_settings_technology_0']/button"), 2);
		int ranNumber2 = genData.generateRandomNumericFromRange(allElements2.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='bm_settings_technology_0']/ul/li["+ ranNumber2 +"]"), 5);
		clickWhenReady(technologyFieldBenchmarking, 2);
		clickWhenReady(EVDOTechnologyBenchmarking, 5);

		//Type Field
		int typeFieldSize = 0;
		List<WebElement> allElements3 = dr.findElements(By.xpath("//*[@id='bm_settings_type_0']/ul/li"));
		if(allElements3.size() == 1){
			clickWhenReady(typeFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_type_0']/ul/li[2]"), 30);
			List<WebElement> allElements3a = dr.findElements(By.xpath("//*[@id='bm_settings_type_0']/ul/li"));
			typeFieldSize = allElements3a.size();
			int ranNumber3 = genData.generateRandomNumericFromRange(typeFieldSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_type_0']/ul/li["+ ranNumber3 +"]"), 5);
		}
		else{
			clickWhenReady(typeFieldBenchmarking, 2);
			int ranNumber3 = genData.generateRandomNumericFromRange(allElements3.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_type_0']/ul/li["+ ranNumber3 +"]"), 5);
		}

		//Date
		int typeDateSize = 0;
		List<WebElement> allElements4 = dr.findElements(By.xpath("//*[@id='bm_settings_dates_0']/ul/li"));
		if(allElements4.size() == 0){
			clickWhenReady(dateFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_dates_0']/ul/li[1]"), 30);
			List<WebElement> allElements4a = dr.findElements(By.xpath("//*[@id='bm_settings_dates_0']/ul/li"));
			typeDateSize = allElements4a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeDateSize + 1, 1);
			clickWhenReady(By.xpath("//*[@id='bm_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}
		else{
			clickWhenReady(dateFieldBenchmarking, 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 1);
			clickWhenReady(By.xpath("//*[@id='bm_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Wireless Carrier
		int typeWirelessCarrier = 0;
		List<WebElement> allElements5 = dr.findElements(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li"));
		if(allElements5.size() == 1){
			clickWhenReady(wirelessCarrierFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li[2]"), 30);
			List<WebElement> allElements5a = dr.findElements(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li"));
			typeWirelessCarrier = allElements5a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeWirelessCarrier + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li["+ ranNumber4 +"]"), 5);

		}
		else{
			clickWhenReady(wirelessCarrierFieldBenchmarking, 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Parameter
		int parameterSize = 0;
		List<WebElement> allElements6 = dr.findElements(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li"));
		if(allElements6.size() == 1){
			clickWhenReady(parameterFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li[2]"), 30);
			List<WebElement> allElements6a = dr.findElements(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li"));
			parameterSize = allElements6a.size();
			int ranNumber5 = genData.generateRandomNumericFromRange(parameterSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		else{
			clickWhenReady(parameterFieldBenchmarking, 2);
			int ranNumber5 = genData.generateRandomNumericFromRange(allElements5.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}

		//Type Horizontal Offset 
		sendKeysToElement(horizontalOffsetFieldBenchmarking, "10");
		//Type Vertical Offset 
		sendKeysToElement(verticalOffsetparameterFieldBenchmarking, "10");
		clickWhenReady(submitBtnBenchmarking, 5);

		Thread.sleep(30000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements7 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements7.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(benchmarkingNameVal))
			{
				Assert.assertEquals(linkElement.getText(), benchmarkingNameVal);
				break;
			}
		}
	}

	@Test(priority=2)
	public void GIN67() throws InterruptedException //To verify the functionality of "Benchmarking" with LTE technology
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(benchMarkingTab, 5);

		//verify labels
		Assert.assertEquals(getElementText(nameLabelBenchmarking, 1), "Name");
		Assert.assertEquals(getElementText(marketLabelBenchmarking, 1), "Market");
		Assert.assertEquals(getElementText(technologyLabelBenchmarking, 1), "Technology");
		Assert.assertEquals(getElementText(typeLabelBenchmarking, 1), "Type");
		Assert.assertEquals(getElementText(dateLabelBenchmarking, 1), "Date");
		Assert.assertEquals(getElementText(wirelessCarrierLabellBenchmarking, 1), "Wireless Carrier");
		Assert.assertEquals(getElementText(parameterLabelBenchmarking, 1), "Parameter");
		Assert.assertEquals(getElementText(autoThresholdsLabelBenchmarking, 1), "Auto Thresholds");
		Assert.assertEquals(getElementText(horizontalOffsetLabelBenchmarking, 1), "Horizontal Offset (points)");
		Assert.assertEquals(getElementText(verticalOffsetLabelBenchmarking, 1), "Vertical Offset (points)");

		benchmarkingNameVal = "Benchmarking"+ genData.generateRandomNumeric(5);
		sendKeysToElement(nameTextBoxBenchmarking, benchmarkingNameVal);

		//Market Field
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='bm_settings_markets_0']/ul/li"));
		clickWhenReady(marketFieldBenchmarking, 2);
		int ranNumber = genData.generateRandomNumericFromRange(allElements.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='bm_settings_markets_0']/ul/li["+ ranNumber +"]"), 5);

		//Technology Field
		List<WebElement> allElements2 = dr.findElements(By.xpath("//*[@id='bm_settings_technology_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='bm_settings_technology_0']/button"), 2);
		int ranNumber2 = genData.generateRandomNumericFromRange(allElements2.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='bm_settings_technology_0']/ul/li["+ ranNumber2 +"]"), 5);
		clickWhenReady(technologyFieldBenchmarking, 2);
		clickWhenReady(LTETechnologyBenchmarking, 5);

		//Type Field
		int typeFieldSize = 0;
		List<WebElement> allElements3 = dr.findElements(By.xpath("//*[@id='bm_settings_type_0']/ul/li"));
		if(allElements3.size() == 1){
			clickWhenReady(typeFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_type_0']/ul/li[2]"), 30);
			List<WebElement> allElements3a = dr.findElements(By.xpath("//*[@id='bm_settings_type_0']/ul/li"));
			typeFieldSize = allElements3a.size();
			int ranNumber3 = genData.generateRandomNumericFromRange(typeFieldSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_type_0']/ul/li["+ ranNumber3 +"]"), 5);
		}
		else{
			clickWhenReady(typeFieldBenchmarking, 2);
			int ranNumber3 = genData.generateRandomNumericFromRange(allElements3.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_type_0']/ul/li["+ ranNumber3 +"]"), 5);
		}

		//Date
		int typeDateSize = 0;
		List<WebElement> allElements4 = dr.findElements(By.xpath("//*[@id='bm_settings_dates_0']/ul/li"));
		if(allElements4.size() == 0){
			clickWhenReady(dateFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_dates_0']/ul/li[1]"), 30);
			List<WebElement> allElements4a = dr.findElements(By.xpath("//*[@id='bm_settings_dates_0']/ul/li"));
			typeDateSize = allElements4a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeDateSize + 1, 1);
			clickWhenReady(By.xpath("//*[@id='bm_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}
		else{
			clickWhenReady(dateFieldBenchmarking, 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 1);
			clickWhenReady(By.xpath("//*[@id='bm_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Wireless Carrier
		int typeWirelessCarrier = 0;
		List<WebElement> allElements5 = dr.findElements(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li"));
		if(allElements5.size() == 1){
			clickWhenReady(wirelessCarrierFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li[2]"), 30);
			List<WebElement> allElements5a = dr.findElements(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li"));
			typeWirelessCarrier = allElements5a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeWirelessCarrier + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li["+ ranNumber4 +"]"), 5);

		}
		else{
			clickWhenReady(wirelessCarrierFieldBenchmarking, 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Parameter
		int parameterSize = 0;
		List<WebElement> allElements6 = dr.findElements(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li"));
		if(allElements6.size() == 1){
			clickWhenReady(parameterFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li[2]"), 30);
			List<WebElement> allElements6a = dr.findElements(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li"));
			parameterSize = allElements6a.size();
			int ranNumber5 = genData.generateRandomNumericFromRange(parameterSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		else{
			clickWhenReady(parameterFieldBenchmarking, 2);
			int ranNumber5 = genData.generateRandomNumericFromRange(allElements5.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}

		//Type Horizontal Offset 
		sendKeysToElement(horizontalOffsetFieldBenchmarking, "10");
		//Type Vertical Offset 
		sendKeysToElement(verticalOffsetparameterFieldBenchmarking, "10");
		clickWhenReady(submitBtnBenchmarking, 5);

		Thread.sleep(30000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements7 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements7.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(benchmarkingNameVal))
			{
				Assert.assertEquals(linkElement.getText(), benchmarkingNameVal);
				break;
			}
		}
	}

	@Test(priority=3)
	public void GIN68() throws InterruptedException //To verify the functionality of "Benchmarking" with Voice technology
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");
		clickWhenReady(benchMarkingTab, 5);

		//Verify Labels
		Assert.assertEquals(getElementText(nameLabelBenchmarking, 1), "Name");
		Assert.assertEquals(getElementText(marketLabelBenchmarking, 1), "Market");
		Assert.assertEquals(getElementText(technologyLabelBenchmarking, 1), "Technology");
		Assert.assertEquals(getElementText(typeLabelBenchmarking, 1), "Type");
		Assert.assertEquals(getElementText(dateLabelBenchmarking, 1), "Date");
		Assert.assertEquals(getElementText(wirelessCarrierLabellBenchmarking, 1), "Wireless Carrier");
		Assert.assertEquals(getElementText(parameterLabelBenchmarking, 1), "Parameter");
		Assert.assertEquals(getElementText(autoThresholdsLabelBenchmarking, 1), "Auto Thresholds");
		Assert.assertEquals(getElementText(horizontalOffsetLabelBenchmarking, 1), "Horizontal Offset (points)");
		Assert.assertEquals(getElementText(verticalOffsetLabelBenchmarking, 1), "Vertical Offset (points)");

		benchmarkingNameVal = "Benchmarking"+ genData.generateRandomNumeric(5);
		sendKeysToElement(nameTextBoxBenchmarking, benchmarkingNameVal);

		//Market Field
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='bm_settings_markets_0']/ul/li"));
		clickWhenReady(marketFieldBenchmarking, 2);
		int ranNumber = genData.generateRandomNumericFromRange(allElements.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='bm_settings_markets_0']/ul/li["+ ranNumber +"]"), 5);

		//Technology Field
		List<WebElement> allElements2 = dr.findElements(By.xpath("//*[@id='bm_settings_technology_0']/ul/li"));
		clickWhenReady(By.xpath("//*[@id='bm_settings_technology_0']/button"), 2);
		int ranNumber2 = genData.generateRandomNumericFromRange(allElements2.size()+1, 1);
		clickWhenReady(By.xpath("//*[@id='bm_settings_technology_0']/ul/li["+ ranNumber2 +"]"), 5);
		clickWhenReady(technologyFieldBenchmarking, 2);
		clickWhenReady(voiceTechnologyBenchmarking, 5);

		//Type Field
		int typeFieldSize = 0;
		List<WebElement> allElements3 = dr.findElements(By.xpath("//*[@id='bm_settings_type_0']/ul/li"));
		if(allElements3.size() == 1){
			clickWhenReady(typeFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_type_0']/ul/li[2]"), 30);
			List<WebElement> allElements3a = dr.findElements(By.xpath("//*[@id='bm_settings_type_0']/ul/li"));
			typeFieldSize = allElements3a.size();
			int ranNumber3 = genData.generateRandomNumericFromRange(typeFieldSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_type_0']/ul/li["+ ranNumber3 +"]"), 5);
		}
		else{
			clickWhenReady(typeFieldBenchmarking, 2);
			int ranNumber3 = genData.generateRandomNumericFromRange(allElements3.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_type_0']/ul/li["+ ranNumber3 +"]"), 5);
		}

		//Date
		int typeDateSize = 0;
		List<WebElement> allElements4 = dr.findElements(By.xpath("//*[@id='bm_settings_dates_0']/ul/li"));
		if(allElements4.size() == 0){
			clickWhenReady(dateFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_dates_0']/ul/li[1]"), 30);
			List<WebElement> allElements4a = dr.findElements(By.xpath("//*[@id='bm_settings_dates_0']/ul/li"));
			typeDateSize = allElements4a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeDateSize + 1, 1);
			clickWhenReady(By.xpath("//*[@id='bm_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}
		else{
			clickWhenReady(dateFieldBenchmarking, 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 1);
			clickWhenReady(By.xpath("//*[@id='bm_settings_dates_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Wireless Carrier
		int typeWirelessCarrier = 0;
		List<WebElement> allElements5 = dr.findElements(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li"));
		if(allElements5.size() == 1){
			clickWhenReady(wirelessCarrierFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li[2]"), 30);
			List<WebElement> allElements5a = dr.findElements(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li"));
			typeWirelessCarrier = allElements5a.size();
			int ranNumber4 = genData.generateRandomNumericFromRange(typeWirelessCarrier + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li["+ ranNumber4 +"]"), 5);

		}
		else{
			clickWhenReady(wirelessCarrierFieldBenchmarking, 2);
			int ranNumber4 = genData.generateRandomNumericFromRange(allElements4.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_wireless_0']/ul/li["+ ranNumber4 +"]"), 5);
		}

		//Parameter
		int parameterSize = 0;
		List<WebElement> allElements6 = dr.findElements(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li"));
		if(allElements6.size() == 1){
			clickWhenReady(parameterFieldBenchmarking, 2);
			waitForElementPresence(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li[2]"), 30);
			List<WebElement> allElements6a = dr.findElements(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li"));
			parameterSize = allElements6a.size();
			int ranNumber5 = genData.generateRandomNumericFromRange(parameterSize + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}
		else{
			clickWhenReady(parameterFieldBenchmarking, 2);
			int ranNumber5 = genData.generateRandomNumericFromRange(allElements5.size() + 1, 2);
			clickWhenReady(By.xpath("//*[@id='bm_settings_parameters_0']/ul/li["+ ranNumber5 +"]"), 10);
		}

		//Type Horizontal Offset 
		sendKeysToElement(horizontalOffsetFieldBenchmarking, "10");
		//Type Vertical Offset 
		sendKeysToElement(verticalOffsetparameterFieldBenchmarking, "10");
		clickWhenReady(submitBtnBenchmarking, 5);

		Thread.sleep(30000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements7 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements7.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(benchmarkingNameVal))
			{
				Assert.assertEquals(linkElement.getText(), benchmarkingNameVal);
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
