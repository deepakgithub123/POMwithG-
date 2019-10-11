package layerSettings;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genOps.GenMethods;

public class General extends GenMethods {

	private String siteName = null;
	private String siteId = null;

	@BeforeClass
	private void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority = 1, groups = "General"/*, dependsOnMethods = {"GIN29"}*/)
	public void GIN51() //To verify the functionality of "Show Tools" check box
	{
		clickWhenReady(mapSettingsBtn, 10);
		waitForElementPresence(settingsPopUpTitle, 60);
		clickWhenReady(generalShowToolsCheckBox, 5);

		Assert.assertEquals(getElementText(generalShowToolsLabel, 5), "Show tools");

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

	@Test(priority = 2, groups = "General"/*, dependsOnMethods = {"GIN29"}*/)
	public void GIN52() throws InterruptedException //To verify the functionality of "Disable Clustering at Zoom" field
	{
		dr.navigate().refresh();
		Random rdmNo = new Random();
		int ranNumVal = rdmNo.nextInt(13 - 10) + 10;

		clickWhenReady(mapSettingsBtn, 5);

		Assert.assertEquals(getElementText(generalDisableClusteringLabel, 5), "Disable Clustering at Zoom:");

		clickWhenReady(generalDisableClusterDropdown, 5);

		List<WebElement> abc = dr.findElements(generalDisableClusterDropdownVal);

		for(int i=1; i <= abc.size(); i++){
			WebElement zxc = dr.findElement(By.xpath(xpathToDisableClusterDropdown + "[" + i + "]"));

			if(zxc.getText().equalsIgnoreCase(Integer.toString(ranNumVal))){
				zxc.click();
			}
		}

		clickWhenReady(settingsPopUpCloseBtn, 5);

		String currentZoomLevel = "";
		currentZoomLevel = getElementText(zoomLevelValue, 5);
		int currentZoomLevelNo = 0;
		currentZoomLevelNo = Integer.parseInt(currentZoomLevel);

		for(int z = currentZoomLevelNo; currentZoomLevelNo >= ranNumVal; z--){
			clickWhenReady(zoomOutBtn, 5);
			currentZoomLevel = getElementText(zoomLevelValue, 5);
			currentZoomLevelNo = Integer.parseInt(currentZoomLevel);
		}

		if(currentZoomLevelNo < ranNumVal){
			Thread.sleep(10000);
			List<WebElement> availableSite = dr.findElements(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div"));

			for(int j=2; j <=availableSite.size(); j++){
				WebElement clusterElement = dr.findElement(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div[" + j + "]/div/span"));

				String countInCluster = clusterElement.getText();
				if(Integer.parseInt(countInCluster) == (int)Integer.parseInt(countInCluster)){
					break;
				}
			}
		}
	}

	@Test(priority = 3, groups = "General"/*, dependsOnMethods = {"GIN29"}*/)
	public void GIN53() throws InterruptedException //To verify the functionality of "Show site labels" check box
	{
		valuesSiteNameSiteID();
		searchSiteOpenSiteInfo();

		String[] expSiteLabelArray = new String[3];
		String idFinal = getSiteInfoDynamicID();
		String xPathToCellID = "//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div[5]/div[1]/div";
		String cellIDLabel = getElementText(By.xpath(xPathToCellID), 5);
		String xPathToSwitch = "//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div[9]/div[1]/div";
		String switchLabel = getElementText(By.xpath(xPathToSwitch), 5);

		if(cellIDLabel.trim().equalsIgnoreCase("Cell"))
		{
			String xPathToCellIDValue = "//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div[5]/div[2]/div";
			expSiteLabelArray[0] = getElementText(By.xpath(xPathToCellIDValue), 5);
			if(switchLabel == "Switch")
			{
				String xPathToSwitchValue = "//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div[9]/div[2]/div";
				expSiteLabelArray[1] = getElementText(By.xpath(xPathToSwitchValue), 5);
			}
			expSiteLabelArray[2] = siteName + "_" + expSiteLabelArray[0];
			expSiteLabelArray[3] = siteName;
			System.out.println("expSiteLabelArray: \n" + expSiteLabelArray[0] +"\n"+ expSiteLabelArray[1] +"\n"+ expSiteLabelArray[2] +"\n"+ expSiteLabelArray[3]);
		}

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[1]/div[2]/div"), 10);

		String[] generalSiteLabelArray = {"cell_id", "switch_id", "site_cell_id", "site_name"};
		for(int i=0; i<=3; i++){
			clickWhenReady(mapSettingsBtn, 5);
			Thread.sleep(2000);
			clickWhenReady(By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/p[3]//input[@value='"+ generalSiteLabelArray[i] +"']"), 5);
			clickWhenReady(settingsPopUpCloseBtn, 10);
			String actualSiteLabel = getElementText(By.xpath("//*[@id='"+ siteId +"']"), 10);
			Assert.assertEquals(expSiteLabelArray[i].trim(), actualSiteLabel.trim());
		}

	}

	@Test(priority = 4, groups = "General"/*, dependsOnMethods = {"GIN29"}*/)
	public void GIN54() throws InterruptedException //To verify the functionality of "Show sector labels (PN/PCI)" field
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 10);
		String[] generalSectorLabelArray = {"pci", "e_tilt", "sector_pn", "pn"};
		for(int i=0; i<=3; i++){

			Thread.sleep(1000);
			clickWhenReady(By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/p[5]//input[@value='"+ generalSectorLabelArray[i] +"']"), 5);
			Thread.sleep(1000);
			//clickWhenReady(settingsPopUpCloseBtn, 10);
		}
	}

	@Test(priority = 5, groups = "General"/*, dependsOnMethods = {"GIN29"}*/)
	public void GIN55() //To verify the functionality of "Icon Sizes (Aspect ratio(w/h): Optimal: 1.6, Current: 1.6 " field
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);

		By xpathSizeHeading = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/p[6]");
		By xpathIconWidthLabel = By.xpath("//*[@for='icon-width-0']");
		By xpathIconHeightLabel = By.xpath("//*[@for='icon-height-0']");
		By xpathPixel1 = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/form/div[1]/label[2]");
		By xpathPixel2 = By.xpath("//*[@id='map_settings_tabs_0']/div[2]/div[1]/form/div[2]/label[2]");
		By xpathIconOpacityLabel = By.xpath("//*[@for='icon-opacity-0']");
		By xpathPlanetOpacity = By.xpath("//*[@for='planet-opacity-0']");

		String sizeHeading = getElementText(xpathSizeHeading, 5);
		String iconWidthLabel = getElementText(xpathIconWidthLabel, 5);
		String iconHeightLabel = getElementText(xpathIconHeightLabel, 5);
		String pixel1 = getElementText(xpathPixel1, 5);
		String pixel2 = getElementText(xpathPixel2, 5);
		String iconOpacityLabel = getElementText(xpathIconOpacityLabel, 5);
		String planetOpacityLabel = getElementText(xpathPlanetOpacity, 5);

		Assert.assertEquals(sizeHeading.trim(), "Icon Sizes (Aspect ratio(w/h): Optimal: 1.6, Current: 1.6 )");
		Assert.assertEquals(iconWidthLabel.trim(), "Icon Width");
		Assert.assertEquals(iconHeightLabel.trim(), "Icon Height");
		Assert.assertEquals(pixel1.trim(), "pixels");
		Assert.assertEquals(pixel2.trim(), "pixels");
		Assert.assertEquals(iconOpacityLabel.trim(), "Icon Opacity (0 - 1)");
		Assert.assertEquals(planetOpacityLabel.trim(), "Planet Opacity (0 - 1)");

		By iconWidthField = By.id("icon-width-0");
		By iconHeightField = By.id("icon-height-0");

		sendKeysToElement(iconWidthField, "160");
		sendKeysToElement(iconHeightField, "100");

		clickWhenReady(By.xpath("//*[@id='icon-opacity-0']/div[2]/div[2]"), 5);
		clickWhenReady(By.xpath("//*[@id='planet-opacity-0']/div[2]/div[2]"), 5);

		clickWhenReady(settingsPopUpCloseBtn, 10);
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

	private void searchSiteOpenSiteInfo() throws InterruptedException
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 30);

		sendKeysToElement(searchBox, siteName);

		List<WebElement> hh = dr.findElements(By.cssSelector("body > div.autocomplete-suggestions > div.autocomplete-suggestion"));
		//System.out.println(hh.size());

		for(int i=1; i<=hh.size(); i++){
			WebElement nn = dr.findElement(By.cssSelector("body > div.autocomplete-suggestions > div:nth-child("+ i +")"));
			if(nn.getText().contains(siteName)){
				nn.click();	
				break;
			}
		}

		Thread.sleep(4000);
		By siteIDVal = By.id(""+ siteId +"");
		//Assert.assertTrue(isElementPresent(siteIDVal));
		if(isElementPresent(siteIDVal)==false){
			mapPanning();
		}

		clickWhenReady(mapWindowHeader, 10);
		clickWhenReady(siteIDVal,10);
		clickWhenReady(moreInfoIcon, 10);
		Thread.sleep(7000);
	}

	private void valuesSiteNameSiteID() throws InterruptedException
	{
		int count = getAvailableSites();
		String siteNameID = getRandomSiteNameID(count);
		System.out.println(siteNameID);

		String[] parts = siteNameID.split("-");
		siteName = parts[0];
		siteId = parts[1];
	}
}
