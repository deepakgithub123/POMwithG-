package layerSettings;

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

public class NeighborListing extends GenMethods {
	
	@BeforeClass
	public void InitializeSuite() throws InterruptedException
	{
		testBedSetup();
		login(validUsername, validPassword);
	}

	@Test(priority=1)
	public void GIN104() throws InterruptedException//To verify the functionality of "Neighbor Listing" tab on settings pop-up
	{
		int a[]= new int[4];
		clickWhenReady(mapSettingsBtn, 5);
		waitForElementPresence(settingsPopUpTitle, 60);
		Assert.assertEquals(getElementText(settingsPopUpTitle, 10), "Settings");
		clickWhenReady(nextTabScrollBtn, 5);
		Thread.sleep(2000);
		clickWhenReady(nextTabScrollBtn, 5);
		Thread.sleep(2000);
		clickWhenReady(nextTabScrollBtn, 5);
		Thread.sleep(2000);
		clickWhenReady(neighborListingTab, 5);

		for(int x=3; x<=7; x++){
			int y = x + 1;
			if(y<=7){
				String randomNo = genData.generateRandomNumeric(1);
				sendKeysToElement(By.xpath("//*[@id='nlsc_dd_0']/tbody/tr["+x+"]/td[6]/input"), randomNo);
				a[x-3] = Integer.parseInt(randomNo);
				getElementText(By.xpath("//*[@id='nlsc_dd_0']/tbody/tr["+y+"]/td[2]/div"), 5);
				Assert.assertEquals(randomNo, getElementText(By.xpath("//*[@id='nlsc_dd_0']/tbody/tr["+y+"]/td[2]/div"), 5));
			}

			clickWhenReady(By.xpath("//*[@id='nlsc_dd_0']/tbody/tr["+x+"]/td[1]/div"), 5);
			WebElement colorBox = dr.findElement(By.xpath("//div[contains(@class, 'colorpicker') and (contains(@style, 'display: block;'))]/div[5]/input"));
			colorBox.sendKeys(Keys.CONTROL + "a");
			colorBox.sendKeys(Keys.DELETE);
			colorBox.sendKeys("ff");
			clickWhenReady(By.xpath("//*[@id='nlsc_dd_0']/tbody/tr[3]/td[4]/div"), 5);			
		}
		clickWhenReady(By.xpath("//*[@id='nl_settings_save_button_0']"), 10);
		Thread.sleep(2000);
		hoverOverElement(By.xpath("//*[@id='map_0']/div[3]/div[4]/div[1]/a"));
		String strNeighborHeaderCss = "#map_0 > div.leaflet-control-container > div.leaflet-bottom.leaflet-right > div.legend.leaflet-control-layers.leaflet-control > div.leaflet-control-layers-list > div >";
		By neighborHeaderCSSpath = By.cssSelector(strNeighborHeaderCss + " h3:nth-child(3)"); 
		clickWhenReady(neighborHeaderCSSpath, 60);
		for(int i=1;i<5;i++)
		{
			int y=i+1;
			By value1 = By.cssSelector(strNeighborHeaderCss + " div:nth-child(4) > ul > li > span > table > tbody > tr:nth-child("+ i +") > td:nth-child(6)");
			By value2 = By.cssSelector(strNeighborHeaderCss + " div:nth-child(4) > ul > li > span > table > tbody> tr:nth-child("+ y +")> td:nth-child(2)>div");
			Assert.assertEquals(getElementText(value1, 10), getElementText(value2, 10));
			Assert.assertEquals(a[i-1], Integer.parseInt(getElementText(value2, 10)));
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
