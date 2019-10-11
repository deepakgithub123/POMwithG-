package mapLayerSettings;

import genOps.GenMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MapOverview extends GenMethods {

	private String siteName = null;
	private String siteId = null;
	private String siteId1 = null;
	private int count = 0;
	
	private void valuesSiteNameSiteID() throws InterruptedException
	{
		count = getAvailableSites();
		String siteNameID = getRandomSiteNameID(count);
		//System.out.println(siteNameID);

		String[] parts = siteNameID.split("-");
		siteName = parts[0];
		siteId = parts[1];
	}
	
	@BeforeClass
	public void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
		valuesSiteNameSiteID();
	}

	@Test(priority=1)
	public void GIN25() throws InterruptedException //To verify the availability of map screen
	{
		waitForElementPresence(By.id("Header_0"), 15);
		Assert.assertTrue(dr.findElement(By.id("Header_0")).isDisplayed());	
		
		/*Thread.sleep(2000);
		dr.findElement(By.xpath("//a[@title='Zoom in']")).click();
		Thread.sleep(2000);
		dr.findElement(By.xpath("//a[@title='Zoom out']")).click();*/
	}

	@Test(priority=2)
	public void GIN26() throws InterruptedException //To verify the site's data on map
	{
		waitForElementPresence(searchBox, 10);
		sendKeysToElement(searchBox, siteName);
		Thread.sleep(2000);
		clickWhenReady(autoCompleteSuggestion, 5);
		Thread.sleep(2000);
		Assert.assertTrue(dr.findElement(By.id(siteId)).isDisplayed());
	}

	@Test(priority=3, dependsOnMethods = {"GIN26"})
	public void GIN27() throws InterruptedException //To verify the functionality of Search box on map window
	{
		dr.navigate().refresh();
		valuesSiteNameSiteID();
		sendKeysToElement(searchBox, siteName);
		Thread.sleep(2000);
		clickWhenReady(autoCompleteSuggestion, 5);
		Thread.sleep(2000);
		Assert.assertTrue(dr.findElement(By.id(siteId)).isDisplayed());
	}

	@Test(priority=4, dependsOnMethods = {"GIN25"})
	public void GIN28() throws InterruptedException //To verify the "Maximize" and "Restore Down" button on map window
	{
		clickWhenReady(mapWindowRestoreDown, 10);
		isElementPresent(mapWindowMax);
		Thread.sleep(5000);
		clickWhenReady(mapWindowMax, 10);
		isElementPresent(mapWindowRestoreDown);
		Thread.sleep(5000);
	}

	@Test(priority=5, dependsOnMethods = {"GIN25"})
	public void GIN29() throws InterruptedException//To verify the functionality of "Settings" Icon on map window
	{
		clickWhenReady(mapSettingsBtn, 5);
	
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 10);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		String[] tabs = new String[]{"General", "Layers", "Drive Test", "Benchmarking", "KPI", "KPI-D", "KPI-C", "Multi-Technology", "Capacity-Offender", "Monitoring", "Neighbor Listing"};

		List<WebElement> allElements = dr.findElements(setingsPopUpTabs);

		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xPathToSetingsPopUpTabs + "[" + i + "]"));

			if(linkElement.getText().equalsIgnoreCase("Capacity-Offender"))
			{
				clickWhenReady(nextTabScrollBtn, 5);
				clickWhenReady(nextTabScrollBtn, 5);
			}
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(tabs[j]),"Tab not found: "+ linkElement);
			//System.out.println(linkElement.getText() + " -- " + tabs[j]);
		}
		clickWhenReady(settingsPopUpCloseBtn, 5); //Close-POP-UP
	}

	@Test(priority=6, dependsOnMethods = {"GIN25"})
	public void GIN30() throws InterruptedException //To verify the "Minimize" and "Restore" button on map window
	{
		//Thread.sleep(10000);
		clickWhenReady(minRestoreMapWindow, 5);
		Thread.sleep(2000);
		sendKeysToElement(searchBox, siteName);
		Thread.sleep(2000);
		clickWhenReady(autoCompleteSuggestion, 5);
		
		Assert.assertFalse(isElementPresent(By.xpath("//*[@id='"+ siteId +"']")));
		clickWhenReady(minRestoreMapWindow, 5);
		Assert.assertTrue(isElementPresent(By.xpath("//*[@id='"+ siteId +"']")));
	}

	@Test(priority=16, dependsOnMethods = {"GIN50"})
	public void GIN31() throws InterruptedException //To verify the cross button on map window

	{
		clickWhenReady(mapWindowCrossBtn, 5);
		Thread.sleep(2000);
		Assert.assertFalse(dr.findElement(mapWindowHeader).isDisplayed());
	}
	
	@Test(priority=7, dependsOnMethods = {"GIN25"})
	public void GIN32() throws InterruptedException //To verify the Zoom in/Zoom out functionality on map window
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		String initialZoomLevel = getElementText(zoomLevelValue, 5);
		int initialZoomLevelNo = Integer.parseInt(initialZoomLevel);
		int ExpZoomLevelNo = initialZoomLevelNo + 1;

		clickWhenReady(zoomInBtn, 5);
		Thread.sleep(2000);

		String zoomLevelAfterZoomIn = getElementText(zoomLevelValue, 5);
		int intZoomLevelAfterZoomIn = Integer.parseInt(zoomLevelAfterZoomIn);
		Thread.sleep(2000);
		Assert.assertEquals(ExpZoomLevelNo, intZoomLevelAfterZoomIn);

		clickWhenReady(zoomOutBtn, 5);
		Thread.sleep(2000);
		String zoomLevelAfterZoomOut = getElementText(zoomLevelValue, 5);
		int intZoomLevelAfterZoomOut = Integer.parseInt(zoomLevelAfterZoomOut);
		Assert.assertEquals(intZoomLevelAfterZoomOut, initialZoomLevelNo);	
	}

	@Test(priority=8, dependsOnMethods = {"GIN29"})
	public void GIN33() //To verify functionality of Tools available on map
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		clickWhenReady(mapSettingsBtn, 10);
		waitForElementPresence(settingsPopUpTitle, 60);
		
		clickWhenReady(generalShowToolsCheckBox, 5);
		clickWhenReady(settingsPopUpCloseBtn, 5);
		
		String[] Tools = new String[]{"Draw a polyline", "Draw a polygon", "Draw a rectangle", "Draw a circle", "Draw a marker"};

		List<WebElement> allElements = dr.findElements(availableTools); 
		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xPathToTools + "[" + i + "]"));
			//System.out.println(linkElement.getAttribute("title"));
			int j = i-1;
			Assert.assertTrue(linkElement.getAttribute("title").equalsIgnoreCase(Tools[j]),"Tool not found: "+ linkElement);
			//System.out.println(linkElement.getAttribute("title") + " -- " + Tools[j]);
		}
	}

	@Test(priority=9, dependsOnMethods = {"GIN25"})
	public void GIN41() //To verify the functionality of Layer selector on map window
	{
		
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		waitForElementPresence(layerSelector, 60);

		hoverOverElement(layerSelector);
		
		/*WebElement layerControl = dr.findElement(layerSelector);
		Actions act1 = new Actions(dr); 
		Actions hoverOverLayer = act1.moveToElement(layerControl);
		hoverOverLayer.perform();*/

		List<WebElement> allElements = dr.findElements(xpathAvailableLayers); 

		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(linkElement.getText().equalsIgnoreCase("Multi-Technology")){
				//do nothing
			}
			else{
				linkElement.click();
			}

			String strlayerName = "";
			String strTemp1 = getElementText(layeNameBottomRightMap, 5);
			String strTemp2 = strTemp1.replaceFirst("Leaflet ","");
			strlayerName = strTemp2.replaceAll("\\| © Global Technology Associates, Layer: ", "");

			linkElement.click();

			try{
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(strlayerName),"Layer name not found: "+ linkElement.getText());
			//System.out.println(linkElement.getText() + " -- " + strlayerName);
			}
			catch(WebDriverException e){
				System.out.println("================Catch WebDriverException================");
	            System.out.println(e+"\n"+e.getMessage());
	            System.out.println("Layer Name not found -- " + strlayerName);
	        }
			
		}
	}
	
	@Test(priority=10)
	public void GIN42() throws InterruptedException //To verify the functionality of Map view selector on map window
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		WebDriverWait wait = new WebDriverWait(dr, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='map_0']/div[3]/div[2]/div[2]/a")));
				
		hoverOverElement(By.xpath("//*[@id='map_0']/div[3]/div[2]/div[2]/a"));

		List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='map_0']/div[3]/div[2]/div[2]/form/select[1]/option")); 
		
		for(int i=1; i <= allElements.size(); i++)
		{														    
			WebElement linkElement = dr.findElement(By.cssSelector("#map_0 > div.leaflet-control-container > div.leaflet-top.leaflet-right > div.leaflet-control-layers.leaflet-control.leaflet-control-layers-expanded > form > select.leaflet-control-layers-base > option:nth-child("+i+")"));
			linkElement.click();
			Thread.sleep(5000);
		}
	}

	@Test(priority=11, dependsOnMethods = {"GIN26"})
	public void GIN45() throws InterruptedException //To verify the functionality of Street View on map window
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		valuesSiteNameSiteID();
		sendKeysToElement(searchBox, siteName);
		Thread.sleep(2000);
		clickWhenReady(autoCompleteSuggestion, 5);
		
		waitForElementPresence(By.id(siteId), 60);
		
		Assert.assertTrue(dr.findElement(By.id(siteId)).isDisplayed());
		clickWhenReady(By.id(siteId), 5);
		Thread.sleep(2000);
		clickWhenReady(streeViewIcon, 5);
		Thread.sleep(2000);
		
		String idFinal = getSiteInfoDynamicID();
		
		String xpathStreetViewTitle= "//*[@id='"+ idFinal +"']/div/div[1]/div[1]";
		String streetViewTitleValue = getElementText(By.xpath(xpathStreetViewTitle), 5);
		String expTitle = "Street View: "+ siteName;
		Assert.assertEquals(streetViewTitleValue, expTitle);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[1]/div[2]"), 5); //close pop-up
		
		Thread.sleep(2000);
		
		rightClickonElement(By.id(siteId));
		
		WebElement streeViewContextMenuLink = dr.findElement(showInfoContextMenu);
		WebElement streeViewContextMenuLink1 = dr.findElement(streeViewContextMenu1);
		if(streeViewContextMenuLink.getText().equalsIgnoreCase("Show Information")){
			streeViewContextMenuLink.click();
		}
		else{
			streeViewContextMenuLink1.click();
		}
		Thread.sleep(7000);
		
		//clickWhenReady(streeViewContextMenu, 5);
		
		String idFinal1 = getSiteInfoDynamicID();
		
		String xpathStreetViewTitle1= "//*[@id='"+ idFinal1 +"']/div/div[1]/div[1]";
		String streetViewTitleValue1 = getElementText(By.xpath(xpathStreetViewTitle1), 5);
		Thread.sleep(2000);
		String expTitle1 = "Street View: "+ siteName; //SiteName is not appearing.;
		Assert.assertEquals(streetViewTitleValue1, expTitle1);
		clickWhenReady(By.xpath("//*[@id='"+ idFinal1 +"']/div/div[1]/div[2]"), 5); //close pop-up	
	}

	@Test(priority=12, dependsOnMethods = {"GIN26"})
	public void GIN46() throws InterruptedException //To verify the functionality of Bird Eye View on map window
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		valuesSiteNameSiteID();
		sendKeysToElement(searchBox, siteName);
		Thread.sleep(2000);
		clickWhenReady(autoCompleteSuggestion, 5);
		Thread.sleep(2000);
		Assert.assertTrue(dr.findElement(By.id(siteId)).isDisplayed());
		
		rightClickonElement(By.id(siteId));
		
		/*WebElement R1 = dr.findElement(By.id(siteID));
		Actions builder = new Actions(dr);
		builder.contextClick(R1).perform();*/
		
		clickWhenReady(birdEyeContextMenu, 5);
		Thread.sleep(2000);
		
		String idIni = "";
		String idFin = "";
		dr.findElement(By.id("windowsDrop")).click();
		WebElement link = dr.findElement(By.xpath("//*[@id='windowsDrop']/ul/li[2]"));
		String onclickAttribute = link.getAttribute("onclick");
		idIni = onclickAttribute.replaceFirst("mapCustom.bringToFront","");
		idFin = idIni.substring(2, idIni.length()-2);

		String xpathBirdViewTitle= "//*[@id='"+ idFin +"']/div/div[1]/div[1]";
		String birdViewTitleValue = getElementText(By.xpath(xpathBirdViewTitle), 5);
		
		String expTitle = "Bird's Eye View: "+ siteName; //SiteName is not appearing.
		Assert.assertEquals(birdViewTitleValue, expTitle);

		clickWhenReady(By.xpath("//*[@id='"+ idFin +"']/div/div[1]/div[2]/div"), 5);
	}
	
	@Test(priority=13, dependsOnMethods = {"GIN25"})
	public void GIN47() throws InterruptedException //To verify the functionality of 'Add to Terrain Profile' on map window
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		valuesSiteNameSiteID();
		searchSite(siteName, siteId);
		rightClickonElement(By.id(siteId));
		clickWhenReady(AddtoTerrainProfileContextMenu, 10);
		
		String siteNameID = getRandomSiteNameID(count);

		String[] parts = siteNameID.split("-");
		siteId1 = parts[1];

		rightClickonElement(By.id(siteId1));
		clickWhenReady(AddtoTerrainProfileContextMenu, 10);
		
		String idFinal = getSiteInfoDynamicID();
		
		String xPathTerrainProfile = "//*[@id='"+ idFinal +"']/div[1]/div[1]";
		String terrainProfileTitle = getElementText(By.xpath(xPathTerrainProfile), 5);
		String expTitle = "Terrain profile";
		Assert.assertEquals(terrainProfileTitle, expTitle);
		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div[1]/div[2]/div"), 5);
	
	}
	
	@Test(priority=14, dependsOnMethods = {"GIN25"})
	public void GIN48() throws InterruptedException //To verify the Legends available on the map window
       {
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		waitForElementPresence(layerSelector, 60);
		hoverOverElement(layerSelector);

		WebElement layerElement;
		List<WebElement> allElements = dr.findElements(xpathAvailableLayers); 
		System.out.println(allElements.size());
		for(int i=1; i <= allElements.size(); i++)
		{
			layerElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));
			if(layerElement.getText().equalsIgnoreCase("Multi-Technology"))
			{
				//do nothing
			}
			else if(layerElement.getText().equalsIgnoreCase("heatmap"))
			{
				//do nothing
			}
			else
			{
				layerElement.click();
			}

			Thread.sleep(2000);
			hoverOverElement(By.xpath("//*[@id='map_0']/div[3]/div[4]/div[1]/a"));
			waitForElementPresence(By.xpath("//*[@id='map-legend-0']/h3"), 30);
			
			String legendTitle = dr.findElement(By.xpath("//*[@id='map-legend-0']/h3")).getText();
			//System.out.println(legendTitle);
			Assert.assertTrue(layerElement.getText().equalsIgnoreCase(legendTitle),"Legend title not found: "+ layerElement);

			if(layerElement.getText().equalsIgnoreCase("heatmap"))
			{
				//do nothing
			}
			else
			{
				layerElement.click();
			}
		}

		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 60);
		Actions builder = new Actions(dr);
		builder.keyDown(Keys.CONTROL);

		for(int i=1; i <= allElements.size(); i++)
		{
			layerElement = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]"));

			if(layerElement.getText().equalsIgnoreCase("Multi-Technology"))
			{
				layerElement.click();
				layerElement.click();
			}
			else if(layerElement.getText().equalsIgnoreCase("heatmap"))
			{
				//do nothing
			}
			else
			{
				layerElement.click();
			}	
		}
		builder.keyUp(Keys.CONTROL);
		builder.perform(); 
       }
	
	@Test(priority=15, dependsOnMethods = {"GIN25"})
	public void GIN50() throws InterruptedException //To verify the functionality of "Layer Settings" Icon on map window
	{
		clickWhenReady(settingsBtnFooter, 10);
		waitForElementPresence(settingsPopUpTitle, 60);
		String SettingsTabTitle = getElementText(settingsPopUpTitle, 5);
		Assert.assertEquals(SettingsTabTitle, "Settings");

		String[] tabs = new String[]{"General", "Layers", "Drive Test", "Benchmarking", "KPI", "KPI-D", "KPI-C", "Multi-Technology", "Capacity-Offender", "Monitoring", "Neighbor Listing"};

		List<WebElement> allElements = dr.findElements(setingsPopUpTabs);

		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xPathToSetingsPopUpTabs + "[" + i + "]"));

			if(linkElement.getText().equalsIgnoreCase("Capacity-Offender"))
			{
				clickWhenReady(nextTabScrollBtn, 5);
				clickWhenReady(nextTabScrollBtn, 5);
			}

			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(tabs[j]),"Tab not found: "+ linkElement);
			//System.out.println(linkElement.getText() + " -- " + tabs[j]);
		}
		//clickWhenReady(settingsPopupCloseBtn, 5); //Close-POP-UP
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
