package layerSettings;

import java.util.ArrayList;
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

public class CapacityOffender extends GenMethods {
	private String[] siteDataAfterKpi = null;
	private String siteName = null;

	@BeforeClass
	public void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority=1)
	public void GIN88() throws InterruptedException
	{
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		Assert.assertEquals(getElementText(settingsPopUpTitle, 10), "Settings");
		clickWhenReady(capacityOffenderTab, 5);

		List<WebElement> availableTech = dr.findElements(By.xpath("//*[@id='capoff_settings_0']//*[@id='capoff_0']/option"));
		for(int t=1; t <= availableTech.size(); t++){
			WebElement techValue = dr.findElement(By.xpath("//*[@id='capoff_settings_0']//*[@id='capoff_0']/option["+ t +"]"));
			String strTechValue = null;
			if(t == 2){
				dr.navigate().refresh();
				clickWhenReady(mapSettingsBtn, 5);
				waitForElementPresence(settingsPopUpTitle, 60);
				Assert.assertEquals(getElementText(settingsPopUpTitle, 10), "Settings");
				clickWhenReady(capacityOffenderTab, 5);
				Thread.sleep(2000);
			}
			try{
				techValue.click();
				strTechValue = techValue.getText().trim();
			}
			catch(Exception e){
				WebElement techValue1 = dr.findElement(By.xpath("//*[@id='capoff_settings_0']//*[@id='capoff_0']/option["+ t +"]"));
				techValue1.click();
				strTechValue = techValue1.getText().trim();
			}

			String[][] hex = null;
			List<WebElement> allElements1 = dr.findElements(By.xpath("//*[@id='capoff_settings_colors_0']/table/tbody/tr"));
			for(int i=1;i<=allElements1.size();i++)
			{
				int j = i - 1;
				hex = new String[allElements1.size()][2];
				WebElement style  = dr.findElement(By.xpath("//*[@id='capoff_settings_colors_0']/table/tbody/tr["+i+"]/td[3]/div"));
				String styleStr = style.getAttribute("style");
				String[] reqString = styleStr.split(";");
				String[][] matrix = new String[reqString.length][]; 
				int k = 0;
				for (String row : reqString) {
					matrix[k++] = row.split("\\: ");
				}

				hex[j][0] = getElementText(By.cssSelector("#capoff_settings_colors_0 > table > tbody > tr:nth-child("+ i +") > td:nth-child(2)"), 5);

				for(int a=0; a<=matrix.length-1; a++){
					String str = matrix[a][0];
					if(str.trim().equalsIgnoreCase("background-color")){
						String rgb = matrix[a][1];
						rgb = rgb.replace("rgb(", "");
						rgb = rgb.replace(")", "");
						String[] abc = rgb.split(", ");
						int r = Integer.parseInt(abc[0]);
						int g = Integer.parseInt(abc[1]);
						int b = Integer.parseInt(abc[2]);

						hex[j][1] = String.format("#%02x%02x%02x", r, g, b);
						//System.out.println(i + ": " + hex[j][0] + " " + hex[j][1]);
					}
				}
			}	

			clickWhenReady(saveBtnCapOffender, 2);
			mapPanning();
			hoverOverElement(layerSelector);

			List<WebElement> allElements = dr.findElements(xpathAvailableLayers); //handled issue of multi-technology apeparing selected
			for(int i=1; i <= allElements.size(); i++){
				Boolean valueselected=dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).isSelected();
				if(valueselected==true){
					String act=dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).getText();
					if(act.equalsIgnoreCase("Multi-Technology")){
						clickWhenReady(By.xpath(strXpathToAvailableLayers + "[" + i + "]"), 2);
						break;
					}
				}
			}

			String strSiteNameIndex = valueSiteName();
			String[] siteIndexValues = strSiteNameIndex.split("\\.");
			siteName = siteIndexValues[siteIndexValues.length - 2];

			String cssPath = "#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div > div.markers-0-Capacity-Offender > span > #s_" + siteName;
			WebElement asdf = dr.findElement(By.cssSelector(cssPath+" > g:nth-child(1) > path:nth-child(1)"));
			String[] statusColor = new String[2];
			statusColor[0] = asdf.getAttribute("fill");

			searchSiteBringToViewClick(siteName, "markers-0-Capacity-Offender");

			WebElement showInfoLink = dr.findElement(showInfoContextMenu);
			if(showInfoLink.getText().equalsIgnoreCase("Show Information")){
				showInfoLink.click();
			}
			String idFinal= getSiteInfoDynamicID();
			By capacityStatusTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[6]");
			clickWhenReady(capacityStatusTabPath, 10);
			String strTechFieldPath = "//*[@id='"+ idFinal +"']//*[@id='co_options']//*[@id='_technology']/option";

			WebElement technology = dr.findElement(By.xpath(strTechFieldPath + "[" + t + "]"));
			if(technology.getText().trim().equalsIgnoreCase(strTechValue)){
				technology.click();
				statusColor[1] = getElementText(By.cssSelector("#"+idFinal+" > div > div:nth-child(2) > div.jqx-tabs-content.jqx-widget-content > div:nth-child(6) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(7) > div:nth-child(2) > div:nth-child(1)"), 10).toUpperCase();
			}
			if(technology.getText().trim().equalsIgnoreCase(strTechValue)){
				technology.click();
				statusColor[1] = getElementText(By.cssSelector("#"+idFinal+" > div > div:nth-child(2) > div:nth-child(9) > div:nth-child(6) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > div:nth-child(2)  > div:nth-child(1)"), 10).toUpperCase();
			}

			for(int q=0; q<=4 ; q++){
				if(statusColor[0].equalsIgnoreCase(hex[q][0])){
					Assert.assertEquals(statusColor[1], hex[q][1]);
				}
			}
		}
	}

	private String valueSiteName() throws InterruptedException
	{
		int count = getAvailableSitesAfterKpi();
		String strSiteName = getRandomSiteName(count);
		return strSiteName;
	}

	public int getAvailableSitesAfterKpi() throws InterruptedException //get the all sites visible on the screen
	{
		String initialZoomLevel = getElementText(zoomLevelValue, 5);
		int initialZoomLevelNo = Integer.parseInt(initialZoomLevel);
		getToZoomLevel(initialZoomLevelNo);

		List<WebElement> availableSites = dr.findElements(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div"));
		if(availableSites.size() == 0)
		{
			mapPanning();
			Thread.sleep(20000);
			//waitForElementPresence(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div[1]"), 20);
			List<WebElement> availableSitesAfterPanning;
			availableSitesAfterPanning = dr.findElements(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div"));
			if(availableSitesAfterPanning.size()==0){
				clickWhenReady(zoomInBtn, 5);
				Thread.sleep(2000);
				clickWhenReady(zoomOutBtn, 5);
				availableSitesAfterPanning = dr.findElements(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div"));
			}

			siteDataAfterKpi = new String[availableSitesAfterPanning.size()];

			for(int i=1; i <= availableSitesAfterPanning.size(); i++)
			{
				WebElement getSiteData = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ i +") > div > div"));
				String siteNameVal = getSiteData.getText();
				int j = i - 1;
				if(siteNameVal.equalsIgnoreCase("")){
					//System.out.println(i+": found nothing");
					//do nothing
				}
				else{
					siteDataAfterKpi[j]=(siteNameVal);
				}
			}

			List<String> list = new ArrayList<String>();
			for(String s : siteDataAfterKpi) {
				if(s != null && s.length() > 0) {
					list.add(s);
				}
			}
			siteDataAfterKpi = list.toArray(new String[list.size()]);

			/*for(int i=0; i < siteDataAfterKpi.length; i++){
				System.out.println(siteDataAfterKpi[i]);
			}*/
			siteCount1 = siteDataAfterKpi.length;
			return siteCount1;
		}
		else{
			siteDataAfterKpi = new String[availableSites.size()];
			for(int i=1; i <= availableSites.size(); i++)
			{
				WebElement getSiteData = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ i +") > div > div"));
				String siteNameVal = getSiteData.getText();
				int j = i - 1;
				if(siteNameVal.equalsIgnoreCase("") || siteNameVal.equalsIgnoreCase(null)){
					//System.out.println(i+": found nothing");
					//do nothing
				}
				else{
					siteDataAfterKpi[j]=(siteNameVal);
				}
			}

			List<String> list = new ArrayList<String>();
			for(String s : siteDataAfterKpi) {
				if(s != null && s.length() > 0) {
					list.add(s);
				}
			}
			siteDataAfterKpi = list.toArray(new String[list.size()]);

			/*for(int i=0; i < siteDataAfterKpi.length; i++){
				System.out.println(siteDataAfterKpi[i]);
			}*/
			siteCount1 = siteDataAfterKpi.length;
			return siteCount1;
		}
	}

	public String getRandomSiteName(int siteCount) throws InterruptedException //pick a random site from the collection
	{
		int randomRowNo, min; String ranSiteName;
		min = (siteCount/2)+1;
		randomRowNo = genData.generateRandomNumericFromRange(siteCount, min);
		ranSiteName = siteDataAfterKpi[randomRowNo] + "." + randomRowNo;
		return ranSiteName;
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
		//dr.quit();
	}
}
