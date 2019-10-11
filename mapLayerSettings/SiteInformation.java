package mapLayerSettings;

import genOps.GenMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testData.GenerateData;

public class SiteInformation extends GenMethods {
	private GenerateData genData;
	private int rowCount = 0;
	private String siteName = null;
	private String siteId = null;

	private void searchSiteOpenSiteInfo() throws InterruptedException
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 30);

		searchSite(siteName, siteId);
		clickWhenReady(By.id(siteId),10);
		clickWhenReady(siteInfoIcon, 10);
		Thread.sleep(5000);
	}
	
	private void valuesSiteNameSiteID() throws InterruptedException
	{
		int count = getAvailableSites();
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
	public void GIN105() throws InterruptedException //To verify the "Site Information" pop-up from 'More Site Info' icon
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		String xPathTositeInfoMenuBar = "//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li";
		String xPathTositeInfoTitle = "//*[@id='"+ idFinal +"']/div/div[1]/div[1]";

		By xPathTitle = By.xpath(xPathTositeInfoTitle);
		String siteInfoTitle = getElementText(xPathTitle, 5);
		Assert.assertTrue(siteInfoTitle.contains("Site information"));

		String[] tabs = new String[]{"Site", "Sectors", "CDMA CM", "LTE CM", "Neighbors", "Capacity Status", "PN/PCI Planning", "Virtual Neighbors"};

		List<WebElement> allElements = dr.findElements(By.xpath(xPathTositeInfoMenuBar)); 

		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xPathTositeInfoMenuBar+ "[" + i + "]"));
			linkElement.getText();
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(tabs[j]),"Tab not found: "+ linkElement);
			//System.out.println(linkElement.getText() + " -- " + tabs[j]);
		}

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[1]/div[2]"), 5);
		Assert.assertFalse(isElementPresent(xPathTitle));
	}

	@Test(dependsOnMethods = {"GIN105"})
	public void GIN106() throws InterruptedException //To verify the "Site Information" pop-up
	{
		dr.navigate().refresh();
		clickWhenReady(mapWindowMax, 5);

		searchSite(siteName, siteId);

		rightClickonElement(By.id(siteId));

		WebElement showInfoLink = dr.findElement(showInfoContextMenu);
		if(showInfoLink.getText().equalsIgnoreCase("Show Information")){
			showInfoLink.click();
		}
		Thread.sleep(7000);

		String idFinal = getSiteInfoDynamicID();

		String xPathTositeInfoMenuBar = "//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li";
		String xPathTositeInfoTitle = "//*[@id='"+ idFinal +"']/div/div[1]/div[1]";

		By xPathTitle = By.xpath(xPathTositeInfoTitle);
		String siteInfoTitle = getElementText(xPathTitle, 5);
		Assert.assertTrue(siteInfoTitle.contains("Site information"));

		String[] tabs = new String[]{"Site", "Sectors", "CDMA CM", "LTE CM", "Neighbors", "Capacity Status", "PN/PCI Planning", "Virtual Neighbors"};

		List<WebElement> allElements = dr.findElements(By.xpath(xPathTositeInfoMenuBar)); 

		for(int i=1; i <= allElements.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xPathTositeInfoMenuBar+ "[" + i + "]"));
			linkElement.getText();
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(tabs[j]),"Tab not found: "+ linkElement.getText());
			//System.out.println(linkElement.getText() + " -- " + tabs[j]);
		}

		//clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[1]/div[2]"), 5);
		//Assert.assertFalse(isElementPresent(xPathTitle));
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN107() throws InterruptedException //To verify the site data on 'Site' tab in site info pop-up
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		String xPathToCascadeValue = "//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div/div[2]/div/div[3]/div[2]/div/div[2]/div[2]/div";
		String siteNameInColumn2 = getElementText(By.xpath(xPathToCascadeValue), 5);
		Assert.assertTrue(siteNameInColumn2.contains(siteName));
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN108() throws InterruptedException //To verify the data on 'Sectors' tab in site info pop-up
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By sectorTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[2]");
		clickWhenReady(sectorTabPath, 10);

		//List<WebElement> allElements = dr.findElements(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div/div"));
		for(int i=1; i <= 5; i++)
		{
			By abcPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div/div[" + i + "]/div[2]/div");
			if(i>3){
				Assert.assertFalse(isElementPresent(abcPath));
			}
		}

		By nfChartBtnPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/span[5]/span");
		Assert.assertTrue(isElementPresent(nfChartBtnPath));

		int tempRowCount = 0;
		for(int i=1; i <= 5; i++)
		{
			By abcPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[2]/div[2]/div/div[3]/div[2]/div/div[" + i + "]/div[2]/div");
			try{
				WebElement rowSize = dr.findElement(abcPath);
				if(rowSize.getText()!=null){
					tempRowCount += 1;
				}
			}
			catch(NoSuchElementException e){
				e.getMessage();
			}
		}
		rowCount = tempRowCount;
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN110() throws InterruptedException //To verify the functionality on 'CDMA CM' tab
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By cdmaCMTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[3]");
		clickWhenReady(cdmaCMTabPath, 10);

		By cmTypeLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_cdma_options']/span[1]/label");
		String cmTypeLabel = getElementText(cmTypeLabelPath,2);
		Assert.assertEquals(cmTypeLabel, "CM Type");

		String stringCMTypeFieldPath = "//*[@id='"+ idFinal +"']//*[@id='cm_cdma_options']//*[@id='_dbs']";
		By cmTypeFieldPath = By.xpath(stringCMTypeFieldPath);
		Assert.assertTrue(isElementPresent(cmTypeFieldPath));

		String[] ExpectedCMTypeFieldVal = new String[]{"cmodeqp", "rrheqp", "cdhnl", "cdhfl", "cell2", "cell3g", "ceqface",
				"ceqface3g", "fci", "cdmahom_raw", "CDMA Neighbor List & HOM", "EVDO Neighbor List & HOM"};

		List<WebElement> cmTypeFieldValues = dr.findElements(By.xpath(stringCMTypeFieldPath + "/option"));

		for(int i=1; i <= cmTypeFieldValues.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(stringCMTypeFieldPath + "/option[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(ExpectedCMTypeFieldVal[j]),"This CM Type field value not found: "+ linkElement.getText());
			//System.out.println(linkElement.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By exportExcelBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_cdma_options']/span[2]/input");
		Assert.assertTrue(isElementPresent(exportExcelBtnPath));

		By dateLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_cdma_options']/span[3]/label");
		String dateLabelPathText = dr.findElement(dateLabelPath).getText();
		Assert.assertEquals(dateLabelPathText, "Date");

		By datePickerPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_cdma_options']//*[@id='ui-id-1']");
		Assert.assertTrue(isElementPresent(datePickerPath));

		By updateBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_cdma_options']/span[3]/span[2]/input");
		Assert.assertTrue(isElementPresent(updateBtnPath));

		By dateRowCellPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[3]/div[2]/div/div[3]/div[2]/div/div[1]/div[1]/div");
		String dateCell = getElementText(dateRowCellPath,2);
		Assert.assertEquals(dateCell, "date");

		sendKeysToElement(datePickerPath, dateValCdmaCM);
		clickWhenReady(updateBtnPath, 10);

		Thread.sleep(3000);

		By dateRowCellValuePath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[3]/div[2]/div/div[3]/div[2]/div/div[1]/div[2]/div");
		String dateRowCellValue = getElementText(dateRowCellValuePath,2);
		String trimmedDateValue = dateRowCellValue.substring(0, Math.min(dateRowCellValue.length(), 10));
		Assert.assertEquals(dateValCdmaCM, trimmedDateValue);

		clickWhenReady(exportExcelBtnPath, 10);
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN111() throws InterruptedException //To verify the functionality on 'LTE CM' tab
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By lteCMTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[4]");
		clickWhenReady(lteCMTabPath, 10);

		By cmTypeLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_lte_options']/span[1]/label");
		String cmTypeLabel = getElementText(cmTypeLabelPath,2);
		Assert.assertEquals(cmTypeLabel, "CM Type");

		String stringCMTypeFieldPath = "//*[@id='"+ idFinal +"']//*[@id='cm_lte_options']//*[@id='_dbs']";
		By cmTypeFieldPath = By.xpath(stringCMTypeFieldPath);
		Assert.assertTrue(isElementPresent(cmTypeFieldPath));

		String[] ExpectedCMTypeFieldVal = new String[]{"AccessBarring", "Cell", "DynamicCoverage", "FreqBand", "General", "HrpdNeighbor", "LteNeighbor",
				"Mimo", "RadioCacCell", "SysInfo", "ULPower", "LTE Neighbor List"};

		List<WebElement> cmTypeFieldValues = dr.findElements(By.xpath(stringCMTypeFieldPath + "/option"));

		for(int i=1; i <= cmTypeFieldValues.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(stringCMTypeFieldPath + "/option[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(ExpectedCMTypeFieldVal[j]),"This CM Type field value not found: "+ linkElement.getText());
			//System.out.println(linkElement.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By exportExcelBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_lte_options']/span[2]/input");
		Assert.assertTrue(isElementPresent(exportExcelBtnPath));

		By dateLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_lte_options']/span[3]/label");
		String dateLabelPathText = dr.findElement(dateLabelPath).getText();
		Assert.assertEquals(dateLabelPathText, "Date");

		By datePickerPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_lte_options']//*[@id='ui-id-2']");
		Assert.assertTrue(isElementPresent(datePickerPath));

		By updateBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='cm_lte_options']/span[3]/span[2]/input");
		Assert.assertTrue(isElementPresent(updateBtnPath));

		By dateRowCellPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[4]/div[2]/div/div[3]/div[2]/div/div[1]/div[1]/div");
		String dateCell = getElementText(dateRowCellPath,2);
		Assert.assertEquals(dateCell, "date");

		sendKeysToElement(datePickerPath, dateValCdmaCM);
		clickWhenReady(updateBtnPath, 10);

		Thread.sleep(3000);

		By dateRowCellValuePath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[4]/div[2]/div/div[3]/div[2]/div/div[1]/div[2]/div");
		String dateRowCellValue = getElementText(dateRowCellValuePath,2);
		String trimmedDateValue = dateRowCellValue.substring(0, Math.min(dateRowCellValue.length(), 10));
		Assert.assertEquals(dateValCdmaCM, trimmedDateValue);

		clickWhenReady(exportExcelBtnPath, 10);	
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN112() throws InterruptedException //To verify the functionality of "Neighbors" tab with CDMA technology
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By neighborsTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[5]");
		clickWhenReady(neighborsTabPath, 10);

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/label[1]");
		By distanceLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/label[2]");
		By bandLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[2]/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[2]/label[2]");

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector");
		Assert.assertEquals(getElementText(distanceLabelPath,2), "Distance:");
		Assert.assertEquals(getElementText(bandLabelPath,2), "Band:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Technology");

		String stringSectorFieldPath = "//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='_sectors']";
		By sectorFieldPath = By.xpath(stringSectorFieldPath);
		Assert.assertTrue(isElementPresent(sectorFieldPath));

		String[] ExpectedSectorFieldVal = new String[]{"1", "2", "3"};
		for(int i=1; i <= 3; i++)
		{
			WebElement sectorField = dr.findElement(By.xpath(stringSectorFieldPath + "/option[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(sectorField.getText().equalsIgnoreCase(ExpectedSectorFieldVal[j]),"This Sector field value not found: "+ sectorField.getText());
			//System.out.println(sectorField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By distanceFieldBox = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/span[2]/input']");
		String distVal = genData.generateRandomNumeric(1);
		sendKeysToElement(distanceFieldBox, distVal);

		String stringTechFieldPath = "//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='_technology']";
		By techFieldPath = By.xpath(stringTechFieldPath);
		Assert.assertTrue(isElementPresent(techFieldPath));

		String[] ExpectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		for(int i=1; i <= 3; i++)
		{
			WebElement techField = dr.findElement(By.xpath(stringTechFieldPath + "/option[" + i + "]"));
			int j = i-1;
			if(techField.getText()=="CDMA"){
				techField.click();
				Thread.sleep(2000);
			}
			Assert.assertTrue(techField.getText().equalsIgnoreCase(ExpectedTechFieldVal[j]),"This Technology value not found: "+ techField.getText());
			//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By displayOnMapBtn = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='showNeighbors']");
		Assert.assertTrue(isElementPresent(displayOnMapBtn));

		//Neighbors functionality is not working fine currently...Data grid not appearing...
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN113() throws InterruptedException //To verify the functionality of "Neighbors" tab with EVDO technology
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By neighborsTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[5]");
		clickWhenReady(neighborsTabPath, 10);

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/label[1]");
		By distanceLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/label[2]");
		By bandLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[2]/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[2]/label[2]");

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector");
		Assert.assertEquals(getElementText(distanceLabelPath,2), "Distance:");
		Assert.assertEquals(getElementText(bandLabelPath,2), "Band:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Technology");

		String stringSectorFieldPath = "//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='_sectors']";
		By sectorFieldPath = By.xpath(stringSectorFieldPath);
		Assert.assertTrue(isElementPresent(sectorFieldPath));

		String[] ExpectedSectorFieldVal = new String[]{"1", "2", "3"};
		for(int i=1; i <= 3; i++)
		{
			WebElement sectorField = dr.findElement(By.xpath(stringSectorFieldPath + "/option[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(sectorField.getText().equalsIgnoreCase(ExpectedSectorFieldVal[j]),"This Sector field value not found: "+ sectorField.getText());
			//System.out.println(sectorField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By distanceFieldBox = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/span[2]/input']");
		String distVal = genData.generateRandomNumeric(1);
		sendKeysToElement(distanceFieldBox, distVal);

		String stringTechFieldPath = "//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='_technology']";
		By techFieldPath = By.xpath(stringTechFieldPath);
		Assert.assertTrue(isElementPresent(techFieldPath));

		String[] ExpectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		for(int i=1; i <= 3; i++)
		{
			WebElement techField = dr.findElement(By.xpath(stringTechFieldPath + "/option[" + i + "]"));
			int j = i-1;
			if(techField.getText()=="EVDO"){
				techField.click();
				Thread.sleep(2000);
			}
			Assert.assertTrue(techField.getText().equalsIgnoreCase(ExpectedTechFieldVal[j]),"This Technology value not found: "+ techField.getText());
			//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By displayOnMapBtn = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='showNeighbors']");
		Assert.assertTrue(isElementPresent(displayOnMapBtn));

		//Neighbors functionality is not working fine currently...Data grid not appearing...
	}
	
	@Test(dependsOnMethods = {"GIN106"})
	public void GIN114() throws InterruptedException //To verify the functionality of "Neighbors" tab with LTE technology
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By neighborsTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[5]");
		clickWhenReady(neighborsTabPath, 10);

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/label[1]");
		By distanceLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/label[2]");
		By bandLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[2]/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[2]/label[2]");

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector");
		Assert.assertEquals(getElementText(distanceLabelPath,2), "Distance:");
		Assert.assertEquals(getElementText(bandLabelPath,2), "Band:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Technology");

		String stringSectorFieldPath = "//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='_sectors']";
		By sectorFieldPath = By.xpath(stringSectorFieldPath);
		Assert.assertTrue(isElementPresent(sectorFieldPath));

		String[] ExpectedSectorFieldVal = new String[]{"1", "2", "3"};
		for(int i=1; i <= 3; i++)
		{
			WebElement sectorField = dr.findElement(By.xpath(stringSectorFieldPath + "/option[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(sectorField.getText().equalsIgnoreCase(ExpectedSectorFieldVal[j]),"This Sector field value not found: "+ sectorField.getText());
			//System.out.println(sectorField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By distanceFieldBox = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']/span/div[1]/span[2]/input']");
		String distVal = genData.generateRandomNumeric(1);
		sendKeysToElement(distanceFieldBox, distVal);

		String stringTechFieldPath = "//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='_technology']";
		By techFieldPath = By.xpath(stringTechFieldPath);
		Assert.assertTrue(isElementPresent(techFieldPath));

		String[] ExpectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		for(int i=1; i <= 3; i++)
		{
			WebElement techField = dr.findElement(By.xpath(stringTechFieldPath + "/option[" + i + "]"));
			int j = i-1;
			if(techField.getText()=="LTE"){
				techField.click();
				Thread.sleep(2000);
			}
			Assert.assertTrue(techField.getText().equalsIgnoreCase(ExpectedTechFieldVal[j]),"This Technology value not found: "+ techField.getText());
			//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		By displayOnMapBtn = By.xpath("//*[@id='"+ idFinal +"']//*[@id='nl_options']//*[@id='showNeighbors']");
		Assert.assertTrue(isElementPresent(displayOnMapBtn));

		//Neighbors functionality is not working fine currently...Data grid not appearing...
	}
	
	@Test(dependsOnMethods = {"GIN106"})
	public void GIN115() throws InterruptedException //To verify the functionality on "Capacity Status" tab
	{
		searchSiteOpenSiteInfo();

		String idFinal = getSiteInfoDynamicID();

		By capacityStatusTabPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[6]");
		clickWhenReady(capacityStatusTabPath, 10);

		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='co_options']/span/label");
		Assert.assertEquals(getElementText(techLabelPath,2), "Technology");

		String stringTechFieldPath = "//*[@id='"+ idFinal +"']//*[@id='co_options']//*[@id='_technology']";
		By techFieldPath = By.xpath(stringTechFieldPath);
		Assert.assertTrue(isElementPresent(techFieldPath));

		By dateRowCellPath = By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[2]/div[6]/div[2]/div/div[3]/div[2]/div/div[1]/div[1]/div");
		String[] ExpectedTechFieldVal = new String[]{"LTE", "EVDO"};
		for(int i=1; i <= 2; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(stringTechFieldPath + "/option[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(ExpectedTechFieldVal[j]),"This Technology value not found: "+ linkElement.getText());
			linkElement.click();
			isElementPresent(dateRowCellPath);
			//System.out.println(linkElement.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN116() throws InterruptedException //To verify the fields available on PN/PCI planning
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/label[2]");
		By excludedLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[2]/label[1]");
		By intervalLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[2]/label[2]");
		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='getPnPci']");
		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/ul/li";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='_technology']/option";
		String xpathExcludedField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_userlist']";
		String xpathIntervalField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_interval']";
		By excludedFieldBox = By.xpath(xpathExcludedField);
		By intervalFieldBox = By.xpath(xpathIntervalField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[7]"), 10);

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Technology:");
		Assert.assertEquals(getElementText(excludedLabelPath,2), "Excluded:");
		Assert.assertEquals(getElementText(intervalLabelPath,2), "Interval:");
		isElementPresent(submitBtnPath);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/button"), 5);
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			isElementPresent(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			linkElement.click();
		}

		String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		clickWhenReady(By.xpath(xpathTechField), 5);
		for(int x=1; x <= 3; x++)
		{
			WebElement techField = dr.findElement(By.xpath(xpathTechField + "[" + x + "]"));
			int j = x-1;
			Assert.assertTrue(techField.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ techField.getText());
			techField.click();
			Thread.sleep(2000);
			//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		String xpathToSectorsTabsInside = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[3]/div/ul/li";
		String[] expectedTabsVal = new String[]{"Sector 1", "Sector 2", "Sector 3"};
		for(int y=1; y <= 3; y++)
		{
			WebElement sectorTab = dr.findElement(By.xpath(xpathToSectorsTabsInside + "[" + y + "]"));
			int j = y-1;
			Assert.assertTrue(sectorTab.getText().equalsIgnoreCase(expectedTabsVal[j]),"Sector tab not found: "+ sectorTab.getText());
			sectorTab.click();
			//System.out.println(sectorTab.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
		}

		sendKeysToElement(intervalFieldBox, "4");
		sendKeysToElement(excludedFieldBox, "4,16,32,98,154,224");

		clickWhenReady(submitBtnPath, 30);

		By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='row0pncpi_neighbours_grid_1']/div[1]/div");
		waitForElementPresence(dataGrid, 60);
		WebElement gridCell = dr.findElement(dataGrid);
		Assert.assertNotNull(gridCell.getText(), "Data grid is not appearing...PnPCI functionality is not working.");
	}

	@Test(dependsOnMethods = {"GIN116"})
	public void GIN117() throws InterruptedException //To verify the functionality of 'PN/PCI Planning' with CDMA technology
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='getPnPci']");
		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/ul/li";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='_technology']/option";
		String xpathExcludedField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_userlist']";
		String xpathIntervalField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_interval']";
		By excludedFieldBox = By.xpath(xpathExcludedField);
		By intervalFieldBox = By.xpath(xpathIntervalField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[7]"), 10);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/button"), 5);
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			isElementPresent(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			if(i!=1){
				linkElement.click();
			}

			String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
			clickWhenReady(By.xpath(xpathTechField), 5);
			for(int x=1; x <= 3; x++)
			{
				WebElement techField = dr.findElement(By.xpath(xpathTechField + "[" + x + "]"));
				int j = x-1;
				Assert.assertTrue(techField.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ techField.getText());
				if(techField.getText()=="CDMA"){
					techField.click();
					Thread.sleep(2000);
				}
				//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
			}

			String xpathToSectorsTabsInside = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[3]/div/ul/li";
			String[] expectedTabsVal = new String[]{"Sector 1", "Sector 2", "Sector 3"};
			for(int y=1; y <= 3; y++)
			{
				WebElement sectorTab = dr.findElement(By.xpath(xpathToSectorsTabsInside + "[" + y + "]"));
				int j = y-1;
				Assert.assertTrue(sectorTab.getText().equalsIgnoreCase(expectedTabsVal[j]),"Sector tab not found: "+ sectorTab.getText());
				sectorTab.click();
				//System.out.println(sectorTab.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
			}

			sendKeysToElement(intervalFieldBox, "4");
			sendKeysToElement(excludedFieldBox, "4,16,32,98,154,224");

			clickWhenReady(submitBtnPath, 5);

			By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='row0pncpi_neighbours_grid_1']/div[1]/div");
			waitForElementPresence(dataGrid, 60);
			WebElement gridCell = dr.findElement(dataGrid);
			Assert.assertNotNull(gridCell.getText(), "Data grid is not appearing...PnPCI functionality is not working.");
		}
	}

	@Test(dependsOnMethods = {"GIN116"})
	public void GIN118() throws InterruptedException //To verify the functionality of 'PN/PCI Planning' with EVDO technology
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='getPnPci']");
		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/ul/li";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='_technology']/option";
		String xpathExcludedField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_userlist']";
		String xpathIntervalField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_interval']";
		By excludedFieldBox = By.xpath(xpathExcludedField);
		By intervalFieldBox = By.xpath(xpathIntervalField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[7]"), 10);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/button"), 5);
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			isElementPresent(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			if(i!=1){
				linkElement.click();
			}

			String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
			clickWhenReady(By.xpath(xpathTechField), 5);
			for(int x=1; x <= 3; x++)
			{
				WebElement techField = dr.findElement(By.xpath(xpathTechField + "[" + x + "]"));
				int j = x-1;
				Assert.assertTrue(techField.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ techField.getText());
				if(techField.getText()=="EVDO"){
					techField.click();
					Thread.sleep(2000);
				}
				//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
			}

			String xpathToSectorsTabsInside = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[3]/div/ul/li";
			String[] expectedTabsVal = new String[]{"Sector 1", "Sector 2", "Sector 3"};
			for(int y=1; y <= 3; y++)
			{
				WebElement sectorTab = dr.findElement(By.xpath(xpathToSectorsTabsInside + "[" + y + "]"));
				int j = y-1;
				Assert.assertTrue(sectorTab.getText().equalsIgnoreCase(expectedTabsVal[j]),"Sector tab not found: "+ sectorTab.getText());
				sectorTab.click();
				//System.out.println(sectorTab.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
			}

			sendKeysToElement(intervalFieldBox, "4");
			sendKeysToElement(excludedFieldBox, "4,16,32,98,154,224");

			clickWhenReady(submitBtnPath, 5);

			By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='row0pncpi_neighbours_grid_1']/div[1]/div");
			waitForElementPresence(dataGrid, 60);
			WebElement gridCell = dr.findElement(dataGrid);
			Assert.assertNotNull(gridCell.getText(), "Data grid is not appearing...PnPCI functionality is not working.");
		}
	}

	@Test(dependsOnMethods = {"GIN116"})
	public void GIN119() throws InterruptedException //To verify the functionality of 'PN/PCI Planning' with LTE technology
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='getPnPci']");
		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/ul/li";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='_technology']/option";
		String xpathExcludedField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_userlist']";
		String xpathIntervalField = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='pnpci_interval']";
		By excludedFieldBox = By.xpath(xpathExcludedField);
		By intervalFieldBox = By.xpath(xpathIntervalField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[7]"), 10);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[1]/span[1]/div/button"), 5);
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			isElementPresent(By.xpath(xpathSectorField + "[" + i + "]/a/label/input"));
			if(i!=1){
				linkElement.click();
			}

			String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
			clickWhenReady(By.xpath(xpathTechField), 5);
			for(int x=1; x <= 3; x++)
			{
				WebElement techField = dr.findElement(By.xpath(xpathTechField + "[" + x + "]"));
				int j = x-1;
				Assert.assertTrue(techField.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ techField.getText());
				if(techField.getText()=="LTE"){
					techField.click();
					Thread.sleep(2000);
				}
				//System.out.println(techField.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
			}

			String xpathToSectorsTabsInside = "//*[@id='"+ idFinal +"']//*[@id='pnpci_options']/span/span/span[3]/div/ul/li";
			String[] expectedTabsVal = new String[]{"Sector 1", "Sector 2", "Sector 3"};
			for(int y=1; y <= 3; y++)
			{
				WebElement sectorTab = dr.findElement(By.xpath(xpathToSectorsTabsInside + "[" + y + "]"));
				int j = y-1;
				Assert.assertTrue(sectorTab.getText().equalsIgnoreCase(expectedTabsVal[j]),"Sector tab not found: "+ sectorTab.getText());
				sectorTab.click();
				//System.out.println(sectorTab.getText() + " -- " + ExpectedCMTypeFieldVal[j]);
			}

			sendKeysToElement(intervalFieldBox, "4");
			sendKeysToElement(excludedFieldBox, "4,16,32,98,154,224");

			clickWhenReady(submitBtnPath, 5);

			By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='pnpci_options']//*[@id='row0pncpi_neighbours_grid_1']/div[1]/div");
			waitForElementPresence(dataGrid, 60);
			WebElement gridCell = dr.findElement(dataGrid);
			Assert.assertNotNull(gridCell.getText(), "Data grid is not appearing...PnPCI functionality is not working.");
		}
	}

	@Test(dependsOnMethods = {"GIN106"})
	public void GIN120() throws InterruptedException //To verify the functionality of "Virtual Neighbors" tab with CDMA technology
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[2]");
		By distanceLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[3]");
		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='getVn']");

		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='_sectors']/option";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='_technology']/option";
		String xpathDistanceField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='vn_distance']";
		By excludedFieldBox = By.xpath(xpathDistanceField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[8]"), 10);

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Tech:");
		Assert.assertEquals(getElementText(distanceLabelPath,2), "Distance(miles):");
		isElementPresent(submitBtnPath);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);
		Thread.sleep(2000);

		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]"));
			linkElement.click();
			Thread.sleep(2000);
			//System.out.println(linkElement.getText());
		}

		String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathTechField + "[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ linkElement.getText());
			if(linkElement.getText()=="CDMA"){
				linkElement.click();
				Thread.sleep(2000);
			}
			//System.out.println(linkElement.getText() + " -- " + expectedTechFieldVal[j]);
		}

		String distanceValue = "3";
		sendKeysToElement(excludedFieldBox, distanceValue);
		clickWhenReady(submitBtnPath, 5);

		By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='row0vn-data']/div[2]/div");
		waitForElementPresence(dataGrid, 5);

		boolean flag = isElementPresent(dataGrid);
		if(flag==true){
			Thread.sleep(5000);
			Assert.assertTrue(isElementPresent(dataGrid),"Data grid ");
		}

		List<WebElement> totalRecordsPath = dr.findElements(By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='contenttablevn-data']/div"));

		boolean flag1 = true;
		int dist =Integer.parseInt(distanceValue);
		for(int i=1; i < totalRecordsPath.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='contenttablevn-data']//*[@id='row0vn-data']/div[7]/div"));
			int distValInGrid = Integer.parseInt(linkElement.getText());
			if(distValInGrid > dist){
				flag1 = false;
			}
			Assert.assertTrue(flag1, "Distance functionality is not working");
		}
	}

	public void GIN121() throws InterruptedException //To verify the functionality of "Virtual Neighbors" tab with EVDO technology
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[2]");
		By distanceLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[3]");
		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='getVn']");

		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='_sectors']/option";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='_technology']/option";
		String xpathDistanceField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='vn_distance']";
		By excludedFieldBox = By.xpath(xpathDistanceField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[8]"), 10);

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Tech:");
		Assert.assertEquals(getElementText(distanceLabelPath,2), "Distance(miles):");
		isElementPresent(submitBtnPath);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);
		Thread.sleep(2000);

		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]"));
			linkElement.click();
			Thread.sleep(2000);
			//System.out.println(linkElement.getText());
		}

		String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathTechField + "[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ linkElement.getText());
			if(linkElement.getText()=="EVDO"){
				linkElement.click();
				Thread.sleep(2000);
			}
			//System.out.println(linkElement.getText() + " -- " + expectedTechFieldVal[j]);
		}

		String distanceValue = "3";
		sendKeysToElement(excludedFieldBox, distanceValue);
		clickWhenReady(submitBtnPath, 5);

		By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='row0vn-data']/div[2]/div");
		waitForElementPresence(dataGrid, 5);

		boolean flag = isElementPresent(dataGrid);
		if(flag==true){
			Thread.sleep(5000);
			Assert.assertTrue(isElementPresent(dataGrid),"Data grid ");
		}

		List<WebElement> totalRecordsPath = dr.findElements(By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='contenttablevn-data']/div"));

		boolean flag1 = true;
		int dist =Integer.parseInt(distanceValue);
		for(int i=1; i < totalRecordsPath.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='contenttablevn-data']//*[@id='row0vn-data']/div[7]/div"));
			int distValInGrid = Integer.parseInt(linkElement.getText());
			if(distValInGrid > dist){
				flag1 = false;
			}
			Assert.assertTrue(flag1, "Distance functionality is not working");
		}
	}

	public void GIN122() throws InterruptedException //To verify the functionality of "Virtual Neighbors" tab with LTE technology
	{
		searchSiteOpenSiteInfo();
		String idFinal = getSiteInfoDynamicID();

		By sectorLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[1]");
		By techLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[2]");
		By distanceLabelPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']/span/span/span/label[3]");
		By submitBtnPath = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='getVn']");

		String xpathSectorField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='_sectors']/option";
		String xpathTechField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='_technology']/option";
		String xpathDistanceField = "//*[@id='"+ idFinal +"']//*[@id='vn_options']//*[@id='vn_distance']";
		By excludedFieldBox = By.xpath(xpathDistanceField);

		clickWhenReady(By.xpath("//*[@id='"+ idFinal +"']/div/div[2]/div[1]/ul/li[8]"), 10);

		Assert.assertEquals(getElementText(sectorLabelPath,2), "Sector:");
		Assert.assertEquals(getElementText(techLabelPath,2), "Tech:");
		Assert.assertEquals(getElementText(distanceLabelPath,2), "Distance(miles):");
		isElementPresent(submitBtnPath);

		List<WebElement> valCountInSectorField = dr.findElements(By.xpath(xpathSectorField));
		valCountInSectorField.size();
		Assert.assertEquals(valCountInSectorField.size(), rowCount);
		Thread.sleep(2000);

		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathSectorField + "[" + i + "]"));
			linkElement.click();
			Thread.sleep(2000);
			//System.out.println(linkElement.getText());
		}

		String[] expectedTechFieldVal = new String[]{"CDMA", "EVDO", "LTE"};
		for(int i=1; i <= 3; i++)
		{
			WebElement linkElement = dr.findElement(By.xpath(xpathTechField + "[" + i + "]"));
			int j = i-1;
			Assert.assertTrue(linkElement.getText().equalsIgnoreCase(expectedTechFieldVal[j]),"Value not found: "+ linkElement.getText());
			if(linkElement.getText()=="LTE"){
				linkElement.click();
				Thread.sleep(2000);
			}
			//System.out.println(linkElement.getText() + " -- " + expectedTechFieldVal[j]);
		}

		String distanceValue = "3";
		sendKeysToElement(excludedFieldBox, distanceValue);
		clickWhenReady(submitBtnPath, 5);

		By dataGrid = By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='row0vn-data']/div[2]/div");
		waitForElementPresence(dataGrid, 5);

		boolean flag = isElementPresent(dataGrid);
		if(flag==true){
			Thread.sleep(5000);
			Assert.assertTrue(isElementPresent(dataGrid),"Data grid ");
		}

		List<WebElement> totalRecordsPath = dr.findElements(By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='contenttablevn-data']/div"));

		boolean flag1 = true;
		int dist =Integer.parseInt(distanceValue);
		for(int i=1; i < totalRecordsPath.size(); i++)
		{
			WebElement linkElement = dr.findElement(By.xpath("//*[@id='"+ idFinal +"']//*[@id='vn-data']//*[@id='contenttablevn-data']//*[@id='row0vn-data']/div[7]/div"));
			int distValInGrid = Integer.parseInt(linkElement.getText());
			if(distValInGrid > dist){
				flag1 = false;
			}
			Assert.assertTrue(flag1, "Distance functionality is not working");
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
