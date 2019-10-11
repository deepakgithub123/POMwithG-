package genOps;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.GenerateData;

public class GenMethods extends ElementsByLocator
{
	public static WebDriver dr;
	public String[][] siteData = null;
	public int siteCount, siteCount1;
	private String fileSeperator = System.getProperty("file.separator");
	public GenerateData genData = new GenerateData();

	public String chromeDriverPath = System.getProperty("user.dir")+"/src/browsers/chromedriver";

	public Xls_Reader xr = new Xls_Reader(System.getProperty("user.dir")+"/src/testData/TestData.xlsx");
	public String url = xr.getCellData("Login", "ProjectUrl", 2);
	public String validUsername = xr.getCellData("Login", "Username", 2);
	public String validPassword = xr.getCellData("Login", "Password", 2);
	public String validUsername1 = xr.getCellData("Login", "Username", 3);
	public String validPassword1 = xr.getCellData("Login", "Password", 3);
	public String registeredEmail = xr.getCellData("ForgotPassword", "Email Address", 2);
	public String dateValCdmaCM = xr.getCellData("CalenderInputDate", "CDMA CM", 2); //need to make dynamic
	public String dateValLteCM = xr.getCellData("CalenderInputDate", "LTE CM", 2); //need to make dynamic
	
	public String[] hexCodes = {"ff6700", "ff0066", "9acd32", "ff00cc", "2a09bf", "0f0114", "006666"};
	public String[][] monthsToNumber = { {"01", "January"} , { "02", "February"} , { "03", "March"} , { "04", "April"} , { "05", "May"} , { "06", "June"} , 
			{ "07", "July"} , { "08", "August"} , { "09", "September"} , { "10", "October"} , { "11", "November"} , { "12", "December"} };

	public void testBedSetup() //open URL after browser setup
	{
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);	
		dr = new ChromeDriver();
		dr.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		dr.navigate().to(url);
		dr.manage().window().maximize();
	}

	public void login(String strUsername, String strPassword) throws InterruptedException //login user with passed username, password
	{
		sendKeysToElement(usernameField, strUsername);
		Thread.sleep(2000);
		sendKeysToElement(passwordField, strPassword);
		Thread.sleep(1000);
		clickWhenReady(loginBtn, 5);
		//waitForElementPresence(mapWindowHeader,20);
		clickWhenReady(mapWindowMax, 60);
	}

	public void rightClickonElement(By locator) //performs right click on the element
	{
		WebElement R1 = dr.findElement(locator);
		Actions builder = new Actions(dr);
		builder.contextClick(R1).perform();
	}

	public void hoverOverElement(By locator) //hover over the element
	{
		WebElement layerControl = dr.findElement(locator);
		Actions act1 = new Actions(dr); 
		Actions hoverOverLayer = act1.moveToElement(layerControl);
		hoverOverLayer.perform();
	}

	public String getSiteInfoDynamicID() //returns the site info pop-up dynamic id
	{
		String idIni = "";
		String idFin = "";
		clickWhenReady(windowsMenu, 5);
		WebElement link = dr.findElement(By.xpath("//*[@id='windowsDrop']/ul/li[2]"));
		String onclickAttribute = link.getAttribute("onclick");
		idIni = onclickAttribute.replaceFirst("mapCustom.bringToFront","");
		idFin = idIni.substring(2, idIni.length()-2);
		clickWhenReady(windowsMenu, 5);
		return idFin;
	}

	public boolean isElementPresent(By locator) //check if element present
	{
		try{
			dr.findElement(locator);
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}

	public void searchSite(String strSiteName, String strSiteID) throws InterruptedException //search a site using site name
	{
		sendKeysToElement(searchBox, strSiteName);
		Thread.sleep(2000);

		List<WebElement> noOfSuggestions = dr.findElements(By.cssSelector("body > div.autocomplete-suggestions > div.autocomplete-suggestion"));
		//System.out.println(noOfSuggestions.size());

		for(int i=1; i <= noOfSuggestions.size(); i++){
			WebElement requiredSuggestionVal = dr.findElement(By.cssSelector("body > div.autocomplete-suggestions > div:nth-child("+ i +")"));
			if(requiredSuggestionVal.getText().contains(strSiteName)){
				requiredSuggestionVal.click();
				break;
			}
		}
		//clickWhenReady(autoCompleteSuggestion, 5);
		Thread.sleep(4000);
		By siteIDVal = By.id(strSiteID);
		if(isElementPresent(siteIDVal)==false){
			mapPanning();
		}
	}

	public void clickWhenReady(By locator, int timeout) //click on a element when its ready
	{
		WebDriverWait wait = new WebDriverWait(dr, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public String getElementText(By locator, int timeout) //returns element text
	{
		String elementValue = "";
		WebDriverWait wait = new WebDriverWait(dr, timeout);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		elementValue = element.getText();
		return elementValue;
	}

	public void waitForElementPresence(By locator, int timeout) //wait for the presence of the element
	{
		WebDriverWait wait = new WebDriverWait(dr, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void sendKeysToElement(By locator, String strValue) //send keys to the element
	{
		WebElement element = dr.findElement(locator);
		element.clear();
		element.sendKeys(strValue);
	}

	public void mapPanning()
	{
		WebElement map = dr.findElement(By.xpath("//*[@id='mapWindow_0']//*[@id='map_0']/div[1]"));
		Actions builder = new Actions(dr);
		builder.keyDown(Keys.CONTROL).clickAndHold(map).moveByOffset(799,401).release().keyUp(Keys.CONTROL).build().perform();
	}

	public int getAvailableSites() throws InterruptedException //get the all sites visible on the screen
	{
		Thread.sleep(20);
		String initialZoomLevel = getElementText(zoomLevelValue, 5);
		int initialZoomLevelNo = Integer.parseInt(initialZoomLevel);

		if(initialZoomLevelNo > 12)
		{
			for(int i = initialZoomLevelNo; i > 11; i--){
				clickWhenReady(zoomOutBtn, 5);
				Thread.sleep(2000);
			}
		}
		else{
			for(int i = initialZoomLevelNo; i < 11; i++){
				clickWhenReady(zoomInBtn, 5);
				Thread.sleep(2000);
			}	
		}

		List<WebElement> availableSites = dr.findElements(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div"));
		if(availableSites.size() == 0)
		{
			mapPanning();
			Thread.sleep(20000);
			//waitForElementPresence(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div[10]"), 20);
			List<WebElement> availableSitesAfterPanning = dr.findElements(By.xpath("//*[@id='map_0']/div[2]/div[2]/div[3]/div"));
			siteData = new String[availableSitesAfterPanning.size()][2];

			for(int i=1; i <= availableSitesAfterPanning.size(); i++)
			{
				WebElement getSiteData = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ i +") > div > div"));
				String siteNameVal = getSiteData.getAttribute("data-id");;
				String siteIDVal = getSiteData.getAttribute("id");
				//System.out.println(i + " " + siteNameVal + " " + siteIDVal);

				int j = i - 1;
				siteData[j][0]=(siteNameVal);
				siteData[j][1]=(siteIDVal);
			}

			/*for(int i=0; i < availableSitesAfterPanning.size(); i++){
				System.out.println(siteData[i][0] + " "+ siteData[i][1]);
			}*/

			siteCount = siteData.length;
			return siteCount;
		}
		else{
			siteData = new String[availableSites.size()][2];
			for(int i=1; i <= availableSites.size(); i++)
			{
				WebElement getSiteData = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ i +") > div > div"));
				String siteNameVal = getSiteData.getAttribute("data-id");;
				String siteIDVal = getSiteData.getAttribute("id");
				//System.out.println(i + " " + siteNameVal + " " + siteIDVal);

				int j = i - 1;
				siteData[j][0]=(siteNameVal);
				siteData[j][1]=(siteIDVal);
			}

			/*for(int i=0; i < availableSites.size(); i++){
				System.out.println(siteData[i][0] + " "+ siteData[i][1]);
			}*/

			siteCount = siteData.length;
			return siteCount;
		}
	}

	public String getRandomSiteNameID(int siteCount) throws InterruptedException //pick a random site from the collection
	{
		/*if(randomRowNo == 0){*/
		int randomRowNo = genData.generateRandomNumericFromRange(siteCount, 1);
		/*}*/
		String siteNameFromArray = siteData[randomRowNo][0];
		String siteIDFromArray = siteData[randomRowNo][1];
		String SiteNameSiteID = siteNameFromArray + "-" + siteIDFromArray;
		return SiteNameSiteID;
	}

	public void captureScreenshot(WebDriver driver, String strTestName, String strClassName) //capture screenshot
	{
		DateFormat dateFormat = new SimpleDateFormat(" dMMM HH-mm", Locale.ENGLISH);
		Date date = new Date();

		String className = splitClassNameFromString(strClassName);
		String screenShotName = strTestName + dateFormat.format(date) + ".png";

		try {
			File folderPath = new File("Screenshots" + fileSeperator + "Failures");
			if (!folderPath.exists()) {
				//System.out.println("File created " + folderPath);
				folderPath.mkdir();
			}
			File screenshotFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(folderPath + fileSeperator + className + fileSeperator + strTestName, screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);

			System.out.println("Screenshot captured...");
		} 
		catch (Exception e)
		{
			System.out.println("Exception while taking screenshot " + e.getMessage());
		} 
	}

	public String splitClassNameFromString(String strClassName) {
		String[] reqClassname = strClassName.split("\\.");
		int i = reqClassname.length - 1;
		//System.out.println("Required Test Name : " + reqClassname[i]);
		return reqClassname[i];
	}

	public void getToZoomLevel(int strInitialZoomLevelNo) throws InterruptedException //get to the desired zoom level
	{
		if(strInitialZoomLevelNo > 12)
		{
			for(int i = strInitialZoomLevelNo; i > 11; i--){
				clickWhenReady(zoomOutBtn, 5);
				Thread.sleep(2000);
			}
		}
		else{
			for(int i = strInitialZoomLevelNo; i < 11; i++){
				clickWhenReady(zoomInBtn, 5);
				Thread.sleep(2000);
			}	
		}
	}
	
	public void searchSiteBringToViewClick(String siteName, String settingTabClassName) throws InterruptedException //search site > if not visible > bring to view > click on site
	{
		//int divCount = Integer.parseInt(index)+1;
		sendKeysToElement(searchBox, siteName);
		Thread.sleep(2000);

		List<WebElement> noOfSuggestions = dr.findElements(By.cssSelector("body > div.autocomplete-suggestions > div.autocomplete-suggestion"));
		for(int i=1; i <= noOfSuggestions.size(); i++){
			WebElement requiredSuggestionVal = dr.findElement(By.cssSelector("body > div.autocomplete-suggestions > div:nth-child("+ i +")"));
			if(requiredSuggestionVal.getText().contains(siteName)){
				requiredSuggestionVal.click();
				Thread.sleep(5000);
				break;
			}
		}

		List<WebElement> sitesAfterSearch = dr.findElements(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div"));
		for(int t=1; t<=sitesAfterSearch.size(); t++){
			boolean flag = false;
			WebElement searchedSite = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ t +") > div."+ settingTabClassName +" > div.site_name"));
			if(searchedSite.getText().trim().equalsIgnoreCase(siteName)){
				mapPanning();
				try{
					rightClickonElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ t +") > div."+ settingTabClassName +" > div.site_name"));
					break;
				}
				catch(Exception e){
					//do nothing
				}
			}
			else if(t == sitesAfterSearch.size()){
				((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView();", searchedSite);
				mapPanning();
				List<WebElement> sitesAfterSearch1 = dr.findElements(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div"));

				for(int y=1; y<=sitesAfterSearch1.size(); y++){
					By siteLocator = By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ y +") > div."+ settingTabClassName +" > div.site_name");
					WebElement searchedSite1 = dr.findElement(siteLocator);
					if(searchedSite1.getText().trim().equalsIgnoreCase(siteName)){
						try{
							rightClickonElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ t +") > div."+ settingTabClassName +" > div.site_name"));
							break;
						}
						catch(Exception e){
							//do nothing
						}
					}
				}
				flag = true;
			}
			if(flag==true){
				((JavascriptExecutor) dr).executeScript("arguments[0].scrollIntoView();", searchedSite);
				mapPanning();
				List<WebElement> sitesAfterSearch2 = dr.findElements(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div"));
				for(int y=1; y<=sitesAfterSearch2.size(); y++){
					WebElement searchedSite2 = dr.findElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ y +") > div."+ settingTabClassName +" > div.site_name"));
					if(searchedSite2.getText().trim().equalsIgnoreCase(siteName)){
						try{
							rightClickonElement(By.cssSelector("#map_0 > div.leaflet-map-pane > div.leaflet-objects-pane > div.leaflet-marker-pane > div:nth-child("+ t +") > div."+ settingTabClassName +" > div.site_name"));
							break;
						}
						catch(Exception e){
							//do nothing
						}
					}
				}
			}
		}
	}

}
