package layerSettings;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import genOps.GenMethods;

public class Kpi extends GenMethods{

	private String startDateVal = "";
	private String endDateVal = "";
	private String siteName = "";
	private String[] siteDataAfterKpi = null;
	private int thresholdValueNo = 0;
	private int[] thresholdRangeValues = null;
	private String[] colorArray = null;
	private Double[] popUpKpiSectorValArray = null;

	@BeforeClass
	private void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority=1, groups = "KPI", alwaysRun = true)
	private void GIN69() throws InterruptedException //To verify the functionality of fields on "KPI" tab
	{	
		clickWhenReady(mapSettingsBtn, 5);
		clickWhenReady(kpiTab, 5);

		Assert.assertEquals(getElementText(kpiTechLabel, 5), "Technology:");
		Assert.assertEquals(getElementText(kpiStartDateLabel, 5), "Date (Starting)");
		Assert.assertEquals(getElementText(kpiEndDateLabel, 5), "Date (Ending)");
		Assert.assertEquals(getElementText(kpiKpisLabel, 5), "KPIs:");

		int thresholdValueNo = 0;
		List<WebElement> availableTechnologies = dr.findElements(kpiTechField);
		for(int i=2; i <= availableTechnologies.size(); i++){
			WebElement techValue = dr.findElement(By.xpath(xpathtechFieldKpi + "["+ i +"]"));
			String techName = techValue.getText().trim();
			techValue.click();
			Thread.sleep(5000);

			String receivedStartEndDates = getAvailableDates();
			splitStartEndDate(receivedStartEndDates);
			sendKeysToElement(kpiStartDateField, startDateVal);
			sendKeysToElement(kpiEndDateField, endDateVal);

			String columnName = xr.getCellData("CalenderInputDate", "KPI Technology", i);
			if(columnName.equalsIgnoreCase(techName)){
				xr.setCellData("CalenderInputDate", "KPI Start Date", i, startDateVal);
				xr.setCellData("CalenderInputDate", "KPI End Date", i, endDateVal);
			}
			
			List<WebElement> availableKpis = dr.findElements(kpiKpisField);
			for(int j=1; j <= availableKpis.size(); j++){
				WebElement kpiValue = dr.findElement(By.xpath(xpathKpisFieldKpi + "["+ j +"]"));
				kpiValue.click();
				String expKpiValue = kpiValue.getText();

				if(kpiValue.getText().equalsIgnoreCase("Please select a technology.")){
					//do nothing
				}
				else{
					String thresholdValue = new Select(dr.findElement(kpiThresholdSelector)).getFirstSelectedOption().getText();
					thresholdValue = thresholdValue.replaceFirst("Thresholds: ","");
					thresholdValueNo = Integer.parseInt(thresholdValue);

					for(int k=3; k<=thresholdValueNo+2; k++){
						String actThresholdVal = getElementText(By.xpath("//*[@id='kpi_settings_0']//*[@id='ksc_dd_0']/tbody/tr["+ k +"]/td[4]/div"), 5);
						actThresholdVal = actThresholdVal.replace("_", " ");
						actThresholdVal = WordUtils.capitalize(actThresholdVal);
						Assert.assertEquals(actThresholdVal, expKpiValue);
						//System.out.println(actThresholdVal + " - " + expKpiValue);
					}
				}
			}
		}

		//List<WebElement> thresholdOptions = dr.findElements(By.xpath("//*[@id='kpi_threshold_num_0']/option"));
		for(int x=1; x <= thresholdValueNo; x++){
			int y = x - 1;
			if(x < thresholdValueNo){
				String randomNo = genData.generateRandomNumeric(3);
				sendKeysToElement(By.id("cr_v"+ y), randomNo);
				getElementText(By.id("cr_v_d_"+ x), 5);
				Assert.assertEquals(randomNo, getElementText(By.id("cr_v_d_"+ x), 5));
			}
			clickWhenReady(By.xpath("//*[@id='kpi_settings_kpi_crs_0']//*[@id='cr_c"+ y +"']"), 5);
			WebElement colorBox = dr.findElement(By.xpath("//div[contains(@class, 'colorpicker') and (contains(@style, 'display: block;'))]/div[5]/input"));
			colorBox.sendKeys(Keys.CONTROL + "a");
			colorBox.sendKeys(Keys.DELETE);
			colorBox.sendKeys(hexCodes[y]);
			clickWhenReady(By.xpath("//*[@id='kpi_settings_0']//*[@id='ksc_dd_0']/tbody/tr[3]/td[4]/div"), 5);
		}
		clickWhenReady(kpiSaveBtn, 5);
	}

	@Test(priority=2, groups = "KPI")
	private void GIN70() throws InterruptedException //To verify the functionality of "KPI" tab for technology 'CDMA1X'
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		clickWhenReady(kpiTab, 5);

		List<WebElement> availableTechnologies = dr.findElements(kpiTechField);
		for(int i=1; i <= availableTechnologies.size(); i++){
			WebElement techValue = dr.findElement(By.xpath(xpathtechFieldKpi + "["+ i +"]"));
			if(techValue.getText().equalsIgnoreCase("CDMA 1X")){
				techValue.click();
				break;
			}
		}

		String columnName = xr.getCellData("CalenderInputDate", "KPI Technology", 2);
		if(columnName.equalsIgnoreCase("CDMA 1X")){
			startDateVal = xr.getCellData("CalenderInputDate", "KPI Start Date", 2);
			endDateVal = xr.getCellData("CalenderInputDate", "KPI End Date", 2);
		}
		sendKeysToElement(kpiStartDateField, startDateVal);
		sendKeysToElement(kpiEndDateField, endDateVal);

		List<WebElement> availableKpis = dr.findElements(kpiKpisField);
		int availableKpiCount = availableKpis.size();
		int ranNum = genData.generateRandomNumericFromRange(availableKpiCount + 1, 1);
		clickWhenReady(By.xpath(xpathKpisFieldKpi + "["+ ranNum +"]"), 5);

		String thresholdValue = new Select(dr.findElement(kpiThresholdSelector)).getFirstSelectedOption().getText();
		thresholdValue = thresholdValue.replaceFirst("Thresholds: ","");
		int thresholdValueNo = Integer.parseInt(thresholdValue);
		setThresholdValueColor(thresholdValueNo);
		clickWhenReady(kpiSaveBtn, 5);

		hoverOverElement(layerSelector);
		List<WebElement> actAvailableLayers = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= actAvailableLayers.size(); i++){
			Boolean valueselected = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).isSelected();
			if(valueselected == true){
				String actSelectedLayerText = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).getText();
				Assert.assertEquals(actSelectedLayerText, "KPI");
				break;
			}
		}

		int count = getAvailableSitesAfterKpi();
		String strSiteNameIndex = getRandomSiteName(count);
		String[] siteIndexValues = strSiteNameIndex.split("\\.");
		siteName = siteIndexValues[siteIndexValues.length - 2];
		//String index = siteIndexValues[siteIndexValues.length - 1];

		int gSvfEleSize = getPopSectorColorAndKpiValues("color");
		searchSiteBringToViewClick(siteName, "markers-KPI");
		getPopSectorColorAndKpiValues("kpi");
		matchColorAccordingToRange(gSvfEleSize);
	}

	@Test(priority=3, groups = "KPI")
	private void GIN71() throws InterruptedException //To verify the functionality of "KPI" tab for technology 'CDMA Voice'
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		clickWhenReady(kpiTab, 5);

		List<WebElement> availableTechnologies = dr.findElements(kpiTechField);
		for(int i=1; i <= availableTechnologies.size(); i++){
			WebElement techValue = dr.findElement(By.xpath(xpathtechFieldKpi + "["+ i +"]"));
			if(techValue.getText().equalsIgnoreCase("CDMA Voice")){
				techValue.click();
				break;
			}
		}

		String columnName = xr.getCellData("CalenderInputDate", "KPI Technology", 3);
		if(columnName.equalsIgnoreCase("CDMA Voice")){
			startDateVal = xr.getCellData("CalenderInputDate", "KPI Start Date", 3);
			endDateVal = xr.getCellData("CalenderInputDate", "KPI End Date", 3);
		}
		sendKeysToElement(kpiStartDateField, startDateVal);
		sendKeysToElement(kpiEndDateField, endDateVal);

		List<WebElement> availableKpis = dr.findElements(kpiKpisField);
		int availableKpiCount = availableKpis.size();
		int ranNum = genData.generateRandomNumericFromRange(availableKpiCount + 1, 1);
		clickWhenReady(By.xpath(xpathKpisFieldKpi + "["+ ranNum +"]"), 5);

		String thresholdValue = new Select(dr.findElement(kpiThresholdSelector)).getFirstSelectedOption().getText();
		thresholdValue = thresholdValue.replaceFirst("Thresholds: ","");
		int thresholdValueNo = Integer.parseInt(thresholdValue);
		setThresholdValueColor(thresholdValueNo);

		clickWhenReady(kpiSaveBtn, 5);

		hoverOverElement(layerSelector);
		List<WebElement> actAvailableLayers = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= actAvailableLayers.size(); i++){
			Boolean valueselected = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).isSelected();
			if(valueselected == true){
				String actSelectedLayerText = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).getText();
				Assert.assertEquals(actSelectedLayerText, "KPI");
				break;
			}
		}

		int count = getAvailableSitesAfterKpi();
		String strSiteNameIndex = getRandomSiteName(count);
		String[] siteIndexValues = strSiteNameIndex.split("\\.");
		siteName = siteIndexValues[siteIndexValues.length - 2];
		//String index = siteIndexValues[siteIndexValues.length - 1];

		int gSvfEleSize = getPopSectorColorAndKpiValues("color");
		searchSiteBringToViewClick(siteName, "markers-KPI");
		getPopSectorColorAndKpiValues("kpi");
		matchColorAccordingToRange(gSvfEleSize);
	}

	@Test(priority=4, groups = "KPI")
	private void GIN72() throws InterruptedException //To verify the functionality of "KPI" tab for technology 'EVDO'
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		clickWhenReady(kpiTab, 5);

		List<WebElement> availableTechnologies = dr.findElements(kpiTechField);
		for(int i=1; i <= availableTechnologies.size(); i++){
			WebElement techValue = dr.findElement(By.xpath(xpathtechFieldKpi + "["+ i +"]"));
			if(techValue.getText().equalsIgnoreCase("EVDO")){
				techValue.click();
				break;
			}
		}

		String columnName = xr.getCellData("CalenderInputDate", "KPI Technology", 4);
		if(columnName.equalsIgnoreCase("EVDO")){
			startDateVal = xr.getCellData("CalenderInputDate", "KPI Start Date", 4);
			endDateVal = xr.getCellData("CalenderInputDate", "KPI End Date", 4);
		}
		sendKeysToElement(kpiStartDateField, startDateVal);
		sendKeysToElement(kpiEndDateField, endDateVal);

		List<WebElement> availableKpis = dr.findElements(kpiKpisField);
		int availableKpiCount = availableKpis.size();
		int ranNum = genData.generateRandomNumericFromRange(availableKpiCount + 1, 1);
		clickWhenReady(By.xpath(xpathKpisFieldKpi + "["+ ranNum +"]"), 5);

		String thresholdValue = new Select(dr.findElement(kpiThresholdSelector)).getFirstSelectedOption().getText();
		thresholdValue = thresholdValue.replaceFirst("Thresholds: ","");
		int thresholdValueNo = Integer.parseInt(thresholdValue);
		setThresholdValueColor(thresholdValueNo);

		clickWhenReady(kpiSaveBtn, 5);

		hoverOverElement(layerSelector);
		List<WebElement> actAvailableLayers = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= actAvailableLayers.size(); i++){
			Boolean valueselected = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).isSelected();
			if(valueselected == true){
				String actSelectedLayerText = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).getText();
				Assert.assertEquals(actSelectedLayerText, "KPI");
				break;
			}
		}

		int count = getAvailableSitesAfterKpi();
		String strSiteNameIndex = getRandomSiteName(count);
		String[] siteIndexValues = strSiteNameIndex.split("\\.");
		siteName = siteIndexValues[siteIndexValues.length - 2];
		//String index = siteIndexValues[siteIndexValues.length - 1];

		int gSvfEleSize = getPopSectorColorAndKpiValues("color");
		searchSiteBringToViewClick(siteName, "markers-KPI");
		getPopSectorColorAndKpiValues("kpi");
		matchColorAccordingToRange(gSvfEleSize);
	}

	@Test(priority=5, groups = "KPI")
	private void GIN73() throws InterruptedException //To verify the functionality of "KPI" tab for technology 'LTE'
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		clickWhenReady(kpiTab, 5);

		List<WebElement> availableTechnologies = dr.findElements(kpiTechField);
		for(int i=1; i <= availableTechnologies.size(); i++){
			WebElement techValue = dr.findElement(By.xpath(xpathtechFieldKpi + "["+ i +"]"));
			if(techValue.getText().equalsIgnoreCase("LTE")){
				techValue.click();
				break;
			}
		}

		String columnName = xr.getCellData("CalenderInputDate", "KPI Technology", 5);
		if(columnName.equalsIgnoreCase("LTE")){
			startDateVal = xr.getCellData("CalenderInputDate", "KPI Start Date", 5);
			endDateVal = xr.getCellData("CalenderInputDate", "KPI End Date", 5);
		}
		sendKeysToElement(kpiStartDateField, startDateVal);
		sendKeysToElement(kpiEndDateField, endDateVal);

		List<WebElement> availableKpis = dr.findElements(kpiKpisField);
		int availableKpiCount = availableKpis.size();
		int ranNum = genData.generateRandomNumericFromRange(availableKpiCount + 1, 1);
		clickWhenReady(By.xpath(xpathKpisFieldKpi + "["+ ranNum +"]"), 5);

		String thresholdValue = new Select(dr.findElement(kpiThresholdSelector)).getFirstSelectedOption().getText();
		thresholdValue = thresholdValue.replaceFirst("Thresholds: ","");
		int thresholdValueNo = Integer.parseInt(thresholdValue);
		setThresholdValueColor(thresholdValueNo);

		clickWhenReady(kpiSaveBtn, 5);

		hoverOverElement(layerSelector);
		List<WebElement> actAvailableLayers = dr.findElements(xpathAvailableLayers);
		for(int i=1; i <= actAvailableLayers.size(); i++){
			Boolean valueselected = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).isSelected();
			if(valueselected == true){
				String actSelectedLayerText = dr.findElement(By.xpath(strXpathToAvailableLayers + "[" + i + "]")).getText();
				Assert.assertEquals(actSelectedLayerText, "KPI");
				break;
			}
		}

		int count = getAvailableSitesAfterKpi();
		String strSiteNameIndex = getRandomSiteName(count);
		String[] siteIndexValues = strSiteNameIndex.split("\\.");
		siteName = siteIndexValues[siteIndexValues.length - 2];
		//String index = siteIndexValues[siteIndexValues.length - 1];

		int gSvfEleSize = getPopSectorColorAndKpiValues("color");
		searchSiteBringToViewClick(siteName, "markers-KPI");
		getPopSectorColorAndKpiValues("kpi");
		matchColorAccordingToRange(gSvfEleSize);
	}

	@Test(priority=6, groups = "KPI")
	private void GIN74() throws InterruptedException //To verify the threshold drop-down functionality in "KPI" tab
	{
		dr.navigate().refresh();
		clickWhenReady(mapSettingsBtn, 5);
		clickWhenReady(kpiTab, 5);

		List<WebElement> availableTechnologies = dr.findElements(kpiTechField);
		int availableTechCount = availableTechnologies.size();
		int ranNum = genData.generateRandomNumericFromRange(availableTechCount + 1, 1);
		clickWhenReady(By.xpath(xpathtechFieldKpi + "["+ ranNum +"]"), 5);

		List<WebElement> availableKpis = dr.findElements(kpiKpisField);
		int availableKpiCount = availableKpis.size();
		int ranNum1 = genData.generateRandomNumericFromRange(availableKpiCount + 1, 1);
		clickWhenReady(By.xpath(xpathKpisFieldKpi + "["+ ranNum1 +"]"), 5);

		List<WebElement> thresholdOptions = dr.findElements(By.xpath("//*[@id='kpi_threshold_num_0']/option"));
		for(int z=1; z <= thresholdOptions.size(); z++){
			WebElement thresholdValue = dr.findElement(By.xpath("//*[@id='kpi_threshold_num_0']/option["+ z +"]"));
			String thresholdText = thresholdValue.getText();
			thresholdValue.click();

			thresholdText = thresholdText.replaceFirst("Thresholds: ","");
			int thresholdCount = Integer.parseInt(thresholdText);	

			List<WebElement> noOfThresholdsAppearing = dr.findElements(By.xpath("//*[@id='ksc_dd_0']/tbody/tr"));	
			Assert.assertEquals(thresholdCount, noOfThresholdsAppearing.size()-2);
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
	
	private String getAvailableDates() //get active dates from date picker
	{
		String startEndDate = "";
		String startDateNo = "";
		String endDateNo = "";
		try{
			int activeDatesCount;
			clickWhenReady(kpiStartDateField, 5);

			List<WebElement> activeDates = dr.findElements(By.xpath("//*[@id='ui-datepicker-div']/table/tbody//a[@class='ui-state-default']"));
			activeDatesCount = activeDates.size();
			if(activeDatesCount<=8){
				int counter = 0;
				while(activeDatesCount<=8){
					counter++;
					clickWhenReady(By.cssSelector("#ui-datepicker-div > div > a.ui-datepicker-prev.ui-corner-all"), 5);
					List<WebElement> activeDates1 = dr.findElements(By.xpath("//*[@id='ui-datepicker-div']/table/tbody//a[@class='ui-state-default']"));
					activeDatesCount = activeDates1.size();
					if(counter == 12 || activeDatesCount > 8){
						startDateNo = activeDates1.get(1).getText();
						endDateNo = activeDates1.get(6).getText();
						break;
					}
				}  
			}
			else{
				startDateNo = activeDates.get(1).getText();
				endDateNo = activeDates.get(6).getText();
			}

			String month = getElementText(By.cssSelector("#ui-datepicker-div > div > div > span:nth-child(1)"), 5);
			String year = getElementText(By.cssSelector("#ui-datepicker-div > div > div > span:nth-child(2)"), 5);
			String monthNumber = "";

			for(int k=0; k<=monthsToNumber.length-1; k++){
				if(monthsToNumber[k][1].equalsIgnoreCase(month)){
					monthNumber = monthsToNumber[k][0];
					break;
				}						
			}

			String finalStartDate = year + "-" + monthNumber + "-" + startDateNo;
			String finalEndDate = year + "-" + monthNumber + "-" + endDateNo;
			//System.out.println(finalStartDate + "\n" + finalEndDate);

			startEndDate = finalStartDate + "." + finalEndDate;
		}
		catch(Exception e){
			System.out.println("No data:  \n" + e.getMessage());
		}
		return startEndDate;
	}

	private void splitStartEndDate(String strStartEndDate) //gives start and end date separately
	{
		String[] startEndArray = strStartEndDate.split("\\.");
		startDateVal = startEndArray[startEndArray.length - 2];
		endDateVal = startEndArray[startEndArray.length - 1];
	}

	private void setThresholdValueColor(int strThresholdNo) //sets the threshold random range values and color
	{
		thresholdValueNo = strThresholdNo;
		thresholdRangeValues = new int[thresholdValueNo-1];
		String randomNo = genData.generateRandomNumeric(3);
		for(int x=1; x <= thresholdValueNo; x++){
			int y = x - 1;
			if(x<thresholdValueNo){
				sendKeysToElement(By.id("cr_v"+ y), randomNo);
				thresholdRangeValues[y] = Integer.parseInt(randomNo);
				getElementText(By.id("cr_v_d_"+ x), 5);
				Assert.assertEquals(randomNo, getElementText(By.id("cr_v_d_"+ x), 5));
				int updatedNo = Integer.parseInt(randomNo) + 100;
				randomNo = Integer.toString(updatedNo);
			}
			clickWhenReady(By.xpath("//*[@id='kpi_settings_kpi_crs_0']//*[@id='cr_c"+ y +"']"), 5);
			WebElement colorBox = dr.findElement(By.xpath("//div[contains(@class, 'colorpicker') and (contains(@style, 'display: block;'))]/div[5]/input"));
			colorBox.sendKeys(Keys.CONTROL + "a");
			colorBox.sendKeys(Keys.DELETE);
			colorBox.sendKeys(hexCodes[y]);

			clickWhenReady(By.xpath("//*[@id='kpi_settings_kpi_crs_0']//*[@id='ksc_dd_0']/tbody/tr[3]/td[4]/div"), 5);
		}
	}

	private void matchColorAccordingToRange(int gSvgEleSize) //compares color & sectorKpi values according to the range defined
	{
		for(int q=1; q <= (gSvgEleSize/2); q++){
			String actColor = "";
			for(int w=0; w < (thresholdRangeValues.length); w++){
				if(w==0 && popUpKpiSectorValArray[q-1] < thresholdRangeValues[w]){
					actColor = colorArray[w];
					actColor = actColor.replace("#", "");
					Assert.assertEquals(hexCodes[w], actColor);
					break;
				}
				if(w < thresholdRangeValues.length && thresholdRangeValues[w] <= popUpKpiSectorValArray[q-1]){
					if(w+1 < thresholdRangeValues.length && popUpKpiSectorValArray[q-1] < thresholdRangeValues[w+1]){
						actColor = colorArray[w+1];
						actColor = actColor.replace("#", "");
						Assert.assertEquals(hexCodes[w+1], actColor);
						break;
					}	
				}
				if(w+1 == thresholdRangeValues.length && thresholdRangeValues[w] <= popUpKpiSectorValArray[q-1]){
					actColor = colorArray[w+1];
					actColor = actColor.replace("#", "");
					Assert.assertEquals(hexCodes[w+1], actColor);
					break;
				}
			}
		}
	}

	private int getPopSectorColorAndKpiValues(String token) //gets the sector kpi values per site into an array "popUpKpiSectorValArray"
	{
		int gSvgEleSize = 0;
		if(token.equalsIgnoreCase("color")){
			String cssPath = "#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div > div.markers-KPI > span > #s_" + siteName;
			WebElement svgElement = dr.findElement(By.cssSelector(cssPath));
			List<WebElement> gSvgElements = svgElement.findElements(By.cssSelector("g"));
			colorArray = new String[gSvgElements.size()/2];
			gSvgEleSize = gSvgElements.size();
			for(int z=1, y=0; z <= gSvgElements.size(); z+=2, y++){
				WebElement asdf = dr.findElement(By.cssSelector(cssPath+" > g:nth-child("+ z +") > path:nth-child(1)"));
				colorArray[y] = asdf.getAttribute("fill");
			}
		}

		if(token.equalsIgnoreCase("kpi")){
			String popUpSiteName = getElementText(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-popup-pane > div > div.leaflet-popup-content-wrapper > div > div > div > div:nth-child(1) > div:nth-child(1)"), 5);
			if(popUpSiteName.equalsIgnoreCase(siteName)){
				popUpKpiSectorValArray = new Double[gSvgEleSize/2];
				int count = (gSvgEleSize/2)+2;
				for(int i=3, j=1; i<=count; i++, j++){
					WebElement popUpKpiSectorValue = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-popup-pane > div > div.leaflet-popup-content-wrapper > div > div > div > div:nth-child(1) > div:nth-child("+ i +") > small > b"));
					String tempValue = popUpKpiSectorValue.getText().trim();
					String valueToBeRemoved = j + ": ";
					tempValue = tempValue.replace(valueToBeRemoved, "");
					popUpKpiSectorValArray[i-3] = Double.parseDouble(tempValue);
				}
			}
		}
		return gSvgEleSize;
	}

	private int getAvailableSitesAfterKpi() throws InterruptedException //get the all sites visible on the screen
	{
		String initialZoomLevel = getElementText(zoomLevelValue, 5);
		int initialZoomLevelNo = Integer.parseInt(initialZoomLevel);

		getToZoomLevel(initialZoomLevelNo);

		List<WebElement> availableSites = dr.findElements(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div"));
		if(availableSites.size() == 0)
		{
			mapPanning();
			Thread.sleep(20000);
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
				/*if(siteNameVal.equalsIgnoreCase("")){
					//System.out.println(i+": found nothing");
					//do nothing
				}
				else{*/
				siteDataAfterKpi[j]=(siteNameVal);
				/*}*/

			}

			/*List<String> list = new ArrayList<String>();
			for(String s : siteDataAfterKpi) {
				if(s != null && s.length() > 0) {
					list.add(s);
				}
			}
			siteDataAfterKpi = list.toArray(new String[list.size()]);*/

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
				/*if(siteNameVal.equalsIgnoreCase("") || siteNameVal.equalsIgnoreCase(null)){
					//System.out.println(i+": found nothing");
					//do nothing
				}
				else{*/
				siteDataAfterKpi[j]=(siteNameVal);
				/*}*/
			}

			/*List<String> list = new ArrayList<String>();
			for(String s : siteDataAfterKpi) {
				if(s != null && s.length() > 0) {
					list.add(s);
				}
			}
			siteDataAfterKpi = list.toArray(new String[list.size()]);*/

			/*for(int i=0; i < siteDataAfterKpi.length; i++){
				System.out.println(siteDataAfterKpi[i]);
			}*/
			siteCount1 = siteDataAfterKpi.length;
			return siteCount1;
		}
	}

	private String getRandomSiteName(int siteCount) throws InterruptedException //pick a random site from the collection
	{
		int randomRowNo, min; String ranSiteName;
		min = (siteCount/2)+1;
		randomRowNo = genData.generateRandomNumericFromRange(siteCount, min);
		ranSiteName = siteDataAfterKpi[randomRowNo] + "." + randomRowNo;

		if(ranSiteName.equalsIgnoreCase("")){
			int counter = 0;
			while(ranSiteName.equalsIgnoreCase("")){
				counter++;
				randomRowNo = genData.generateRandomNumericFromRange(siteCount, min);
				ranSiteName = siteDataAfterKpi[randomRowNo] + "." + randomRowNo;
				if(counter == 10){
					break;
				}
			}
		}
		return ranSiteName;
	}
}