package layerSettings;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genOps.GenMethods;

public class Layers extends GenMethods {
	String layerNameVal = "";
	
	@BeforeClass
	private void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}
	
	@Test(priority = 1, groups = "Layers", alwaysRun = true)
	public void GIN56() throws InterruptedException //To verify the functionality of "Add Custom Layer" button
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Verify header
		clickWhenReady(layersTab, 5);
		clickWhenReady(addCustomLayerBtn, 5);
		String actHeader = getElementText(addCustomLayerheader, 1);
		Assert.assertEquals(actHeader, "Add Custom csv File");

		//Verify Labels
		String actlabel1= getElementText(layerNameLabel, 1);
		Assert.assertEquals(actlabel1, "Layer Name:");

		String actlabel2= getElementText(idColumnLabel, 1);
		Assert.assertEquals(actlabel2, "Id Column:");

		String actlabel3= getElementText(pointDataLabel, 1);
		Assert.assertEquals(actlabel3, "Point Data:");

		String actlabel4= getElementText(publicLayerLabel, 1);
		Assert.assertEquals(actlabel4, "Public Layer:");

		String actlabel5= getElementText(fileInputLabel, 1);
		Assert.assertEquals(actlabel5, "File input:");
		//System.out.println(dr.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/div[2]")).getText());

		layerNameVal = "TestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(layerNameTextBox, layerNameVal);
		sendKeysToElement(idColumnTextBox, "cascade_id");
		//clickWhenReady(pointDataCheckBox, 2);
		//clickWhenReady(publicLayerCheckBox, 2);

		String file=System.getProperty("user.dir")+"\\src\\testData\\agoop2.csv";
		dr.findElement(fileInputUploadfield).sendKeys(file);
		waitForElementPresence(By.xpath("//*[@id='csvtable_0']/div/div[2]/div/div/div[1]/table/thead/tr/th[1]/div/span"), 60);
		clickWhenReady(saveBtn, 2);

		Thread.sleep(10000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(layerNameVal))
			{
				Assert.assertEquals(linkElement.getText(), layerNameVal);
				break;
			}
		}
	}

	@Test(priority = 2, groups = "Layers")
	public void GIN57() throws InterruptedException //To verify the functionality of "Add Custom Layer" with "Public Layer" check-box button checked
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Verify header
		clickWhenReady(layersTab, 5);
		clickWhenReady(addCustomLayerBtn, 5);
		String actHeader = getElementText(addCustomLayerheader, 1);
		Assert.assertEquals(actHeader, "Add Custom csv File");

		//Verify Labels
		String actlabel1= getElementText(layerNameLabel, 1);
		Assert.assertEquals(actlabel1, "Layer Name:");

		String actlabel2= getElementText(idColumnLabel, 1);
		Assert.assertEquals(actlabel2, "Id Column:");

		String actlabel3= getElementText(pointDataLabel, 1);
		Assert.assertEquals(actlabel3, "Point Data:");

		String actlabel4= getElementText(publicLayerLabel, 1);
		Assert.assertEquals(actlabel4, "Public Layer:");

		String actlabel5= getElementText(fileInputLabel, 1);
		Assert.assertEquals(actlabel5, "File input:");
		//System.out.println(dr.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/div[2]")).getText());

		layerNameVal = "TestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(layerNameTextBox, layerNameVal);
		sendKeysToElement(idColumnTextBox, "cascade_id");
		clickWhenReady(pointDataCheckBox, 2);
		clickWhenReady(publicLayerCheckBox, 2);

		String file=System.getProperty("user.dir")+"\\src\\testData\\agoop2.csv";
		dr.findElement(fileInputUploadfield).sendKeys(file);
		waitForElementPresence(By.xpath("//*[@id='csvtable_0']/div/div[2]/div/div/div[1]/table/thead/tr/th[1]/div/span"), 60);
		clickWhenReady(saveBtn, 2);

		//waitForElementPresence(layerSelector, 300);
		Thread.sleep(200000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(layerNameVal))
			{
				Assert.assertEquals(linkElement.getText(), layerNameVal);
				break;
			}
		}

		clickWhenReady(xpathToLogout, 2);
		login(validUsername1, validPassword1);
		hoverOverElement(layerSelector);
		List<WebElement> allElements1 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements1.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(layerNameVal))
			{
				Assert.assertEquals(linkElement.getText(), layerNameVal);
				break;
			}
		}
	}

	@Test(priority = 3, groups = "Layers")
	public void GIN58() throws InterruptedException //To verify the functionality of "Add Polygon Layer" button
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Verify header
		clickWhenReady(layersTab, 5);
		clickWhenReady(addPolygonLayerBtn, 5);
		String actHeader = getElementText(addPolygonLayerheader, 1);
		Assert.assertEquals(actHeader, "Add Polygon csv File");

		//Verify Labels
		String actlabel1= getElementText(polygonlayerNameLabel, 1);
		Assert.assertEquals(actlabel1, "Layer Name:");

		String actlabel2= getElementText(geomColumnLabel, 1);
		Assert.assertEquals(actlabel2, "Geom Column:");

		String actlabel4= getElementText(polygonPublicLayerLabel, 1);
		Assert.assertEquals(actlabel4, "Public Layer:");

		String actlabel5= getElementText(polygonFileInputLabel, 1);
		Assert.assertEquals(actlabel5, "File input:");
		//System.out.println(dr.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/div[2]")).getText());

		layerNameVal = "TestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(polygonlayerNameTextBox, layerNameVal);
		sendKeysToElement(geomColumnTextBox, "geojson");
		//clickWhenReady(polygonPublicLayerCheckBox, 2);

		String file=System.getProperty("user.dir")+"\\src\\testData\\geo.csv";
		dr.findElement(polygonFileInputUploadfield).sendKeys(file);
		waitForElementPresence(By.xpath("//*[@id='polytable_0']/div[1]/div[2]/div/div/div[1]/table/thead/tr/th[1]/div/span"), 60);
		clickWhenReady(polygonSaveBtn, 2);

		//waitForElementPresence(layerSelector, 300);
		Thread.sleep(5000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(layerNameVal))
			{
				Assert.assertEquals(linkElement.getText(), layerNameVal);
				break;
			}
		}
	}

	@Test(priority = 4, groups = "Layers")
	public void EXP58() throws InterruptedException //To verify the functionality of "Add Polygon Layer" button with "Public Layer" check-box button checked
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Verify header
		clickWhenReady(layersTab, 5);
		clickWhenReady(addPolygonLayerBtn, 5);
		String actHeader = getElementText(addPolygonLayerheader, 1);
		Assert.assertEquals(actHeader, "Add Polygon csv File");

		//Verify Labels
		String actlabel1= getElementText(polygonlayerNameLabel, 1);
		Assert.assertEquals(actlabel1, "Layer Name:");

		String actlabel2= getElementText(geomColumnLabel, 1);
		Assert.assertEquals(actlabel2, "Geom Column:");

		String actlabel4= getElementText(polygonPublicLayerLabel, 1);
		Assert.assertEquals(actlabel4, "Public Layer:");

		String actlabel5= getElementText(polygonFileInputLabel, 1);
		Assert.assertEquals(actlabel5, "File input:");
		//System.out.println(dr.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div/div[1]/div[2]")).getText());

		layerNameVal = "TestLayer"+ genData.generateRandomNumeric(5);
		sendKeysToElement(polygonlayerNameTextBox, layerNameVal);
		sendKeysToElement(geomColumnTextBox, "geojson");
		clickWhenReady(polygonPublicLayerCheckBox, 2);

		String file=System.getProperty("user.dir")+"\\src\\testData\\geo.csv";
		dr.findElement(polygonFileInputUploadfield).sendKeys(file);
		waitForElementPresence(By.xpath("//*[@id='polytable_0']/div[1]/div[2]/div/div/div[1]/table/thead/tr/th[1]/div/span"), 60);
		clickWhenReady(polygonSaveBtn, 2);

		//waitForElementPresence(layerSelector, 300);
		Thread.sleep(5000);
		hoverOverElement(layerSelector);
		List<WebElement> allElements = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(layerNameVal))
			{
				Assert.assertEquals(linkElement.getText(), layerNameVal);
				break;
			}
		}
		clickWhenReady(xpathToLogout, 2);
		login(validUsername, validPassword);
		hoverOverElement(layerSelector);
		List<WebElement> allElements1 = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= allElements1.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase(layerNameVal))
			{
				Assert.assertEquals(linkElement.getText(), layerNameVal);
				break;
			}
		}
	}

	@Test(priority = 5, groups = "Layers")
	public void GIN59() throws InterruptedException //To verify the layer listing on 'Layers' tab
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		clickWhenReady(layersTab, 5);
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='layer_list_0']/div")); 
		//System.out.println(allElements.size());
		for(int i=1; i <= allElements.size(); i++)
		{
			dr.findElement(By.xpath("//*[@id='layer_list_0']/div["+ i +"]/input")).click();
			/*String aa = dr.findElement(By.xpath("//*[@id='layer_list_0']/div["+ i +"]/span")).getText();
			System.out.println(aa);*/
		}

		clickWhenReady(settingsPopUpCloseBtn, 5);
		waitForElementPresence(layerSelector, 60);
		hoverOverElement(layerSelector);

		List<WebElement> allElements1 = dr.findElements(xpathAvailableLayers);
		Assert.assertEquals(allElements1.size(), 0);
	}

	@Test(priority = 6, groups = "Layers")
	public void GIN60() throws InterruptedException //To verify the functionality of "delete" button on 'Layers' tab
	{
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		clickWhenReady(layersTab, 5);
		//dr.findElement(By.xpath("//*[@,id='layer_list_0']/div[1]/button/span")).click();
		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='layer_list_0']/div"));
		for(int i=1; i <= allElements.size(); i++)
		{
			String aa = dr.findElement(By.xpath("//*[@id='layer_list_0']/div["+ i +"]/span")).getText();
			if(aa.trim().equalsIgnoreCase(layerNameVal))//layerNameVal-->GIN58
			{
				Assert.assertTrue(aa.trim().equalsIgnoreCase(layerNameVal));
				dr.findElement(By.xpath("//*[@id='layer_list_0']/div["+ i +"]/button/span")).click();
				Thread.sleep(3000);
				dr.findElement(By.xpath("//*[@id='modal-0-"+layerNameVal+"']/div/div/div[3]/button[2]")).click();
				Thread.sleep(3000);
				dr.findElement(By.xpath("//*[@id='layer_list_0']/div["+ i +"]/button/span")).click();
				Thread.sleep(3000);
				dr.findElement(By.xpath("//*[@id='modal-0-"+layerNameVal+"']/div/div/div[3]/button[1]")).click();
				break;
			}
		}
	}

	@Test(priority = 7, groups = "Layers")
	public void GIN61() throws InterruptedException //To verify the functionality of "Close" button on "Add Custom csv File" pop-up
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Click on Close button	
		clickWhenReady(layersTab, 5);
		clickWhenReady(addCustomLayerBtn, 5);
		String actHeader = getElementText(addCustomLayerheader, 1);
		Assert.assertEquals(actHeader, "Add Custom csv File");
		clickWhenReady(closeBtn, 2);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Click on Cross button
		String actHeader1 = getElementText(addCustomLayerheader, 1);
		Assert.assertEquals(actHeader1, "Add Custom csv File");
		clickWhenReady(crossBtn, 2);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Close by ESC button
		String actHeader2 = getElementText(addCustomLayerheader, 1);
		Assert.assertEquals(actHeader2, "Add Custom csv File");
		dr.findElement(By.xpath("/html/body/div[6]")).sendKeys(Keys.ESCAPE);
		Assert.assertEquals(SettingsTabTitle, "Settings");
	}

	@Test(priority = 8, groups = "Layers")
	public void GIN62() throws InterruptedException //To verify the functionality of "Close" button on "Add Polygon csv File" pop-up
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Click on Close button	
		clickWhenReady(layersTab, 5);
		clickWhenReady(addPolygonLayerBtn, 5);
		String actHeader = getElementText(addPolygonLayerheader, 1);
		Assert.assertEquals(actHeader, "Add Polygon csv File");
		clickWhenReady(polygonCloseBtn, 2);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Click on Cross button
		String actHeader1 = getElementText(addPolygonLayerheader, 1);
		Assert.assertEquals(actHeader1, "Add Polygon csv File");
		clickWhenReady(polygonCrossBtn, 2);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		//Close by ESC button		
		String actHeader2 = getElementText(addPolygonLayerheader, 1);
		Assert.assertEquals(actHeader2, "Add Polygon csv File");
		dr.findElement(By.xpath("/html/body/div[7]")).sendKeys(Keys.ESCAPE);
		Assert.assertEquals(SettingsTabTitle, "Settings");
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
