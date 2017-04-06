package com.edge.GFS.online;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.framework.ErrRemedy;
import junit.framework.Assert;

public class CommonGFS {

	private WebDriver driver;
	private WebDriverWait wait;

	public void LogOutGFSAccount(WebDriver driver) {

		try {
			driver.findElement(By.xpath("//a[@class='account-menu-toggle']")).click();

			driver.findElement(By.xpath("//li[@class='logout-option']")).click();

			WebElement lnk_Confirm_logout= wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/a[@class='confirm blue button ng-binding']"))));
			lnk_Confirm_logout.click();
		} catch (WebDriverException se){
			System.out.println("Logout failed");
			se.printStackTrace();
		}
		catch (RuntimeException re) {
			// TODO: handle exception
			System.out.println("logout Failed");
		}
		catch (Exception e2) {
			System.out.println("logout failed");
			e2.printStackTrace();
		}
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//button[@class='button gfsexperience-modal-close']")).click();
			System.out.println("Alert handled");
			return true;
		} catch (NoAlertPresentException Ex) {
			System.out.println("No alert found");
			return false;
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;

		}

	}

	public void CheckExportOGStatus(WebDriver driver) {

		try {
			if (driver.findElement(By.className("dialogWindow smaller")).isDisplayed()) {
				String ExportStatus = driver.findElement(By.xpath("//div[@class='dialogWindow smaller']")).getText();

				System.out.println(ExportStatus);

			//	driver.findElement(By.xpath("//div[@class='dialogWindow smaller'][contains(.,'OK')]")).click();
				
/*				if (ExportStatus.equalsIgnoreCase("Export Failed")) {

					System.out.println("Order Guide Export failed");
					ErrRemedy.ErrScreenshotCapture(driver);
					ErrRemedy.ErrReportingMail();
				}*/
			}
		} 
		catch (InvalidSelectorException ine) {
			System.out.println("Export dialog window not found invalid selector");
			// LogOutGFSAccount(driver);
			ine.printStackTrace();
		} 
		catch (NoSuchElementException ne) {
			System.out.println("Export dailog window not found");
			// LogOutGFSAccount(driver);
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("No Export pop up");
			// LogOutGFSAccount(driver);
			e.printStackTrace();

		}

	}

	public void DialogWinExportOG(WebDriver driver) {

		if (driver.findElement(By.className("dialogWindow")).isDisplayed()) {

			System.out.println("Export list window is displayed.");

			driver.findElement(By.xpath("//span[contains(.,'Price')]")).click();

			driver.findElement(By.xpath("//button[contains(.,'Export')]")).click();

			CheckExportOGStatus(driver);
		}

		else {
			System.out.println("Select data columns dialog window is not popped up");
		}
	}

	public boolean LoginGFS(WebDriver driver, String usernameGFS, String passwordGFS) {

		try {

			//driver.manage().window().maximize();

			driver.get("https://apps.gfs.com/experience/");
			// Wait For Page To Load

			// pass login credentials
			driver.findElement(By.xpath("//*[@id='j_username']")).sendKeys(usernameGFS);
			driver.findElement(By.xpath("//*[@id='j_password']")).sendKeys(passwordGFS);

			driver.findElement(By.xpath("//*[@id='submit']")).click();
			System.out.println("Login Successful");

			return true;

		}

		catch (NoSuchElementException Ne) {
			// TODO: handle exception
			System.out.println("No such element \n" + Ne.getMessage());
			return false;
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Program failed.\n" + e.getMessage());
			e.printStackTrace();

			return false;
		}

	}

	public void StepsToExport(WebDriver driver) throws InterruptedException {
		
		// check if Alert is present
		isAlertPresent(driver);
		
		String Current_URL = driver.getCurrentUrl();
		// Verify if login is sucess
		Assert.assertEquals("https://apps.gfs.com/experience/main/portal.html", Current_URL);
		System.out.println("GFS - Home Page");

		// Wait for Online Ordering link visiblity
		wait = new WebDriverWait(driver, 30);
		WebElement lnk_OnlineOrdering = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Online Ordering')]")));

		// Online ordering link assertion
		Assert.assertEquals(true, lnk_OnlineOrdering.isDisplayed());

		lnk_OnlineOrdering.click();

		String Home_URL = "https://apps.gfs.com/doc/desktop/index.html#/home_page";

		Assert.assertEquals("https://apps.gfs.com/doc/desktop/index.html#/home_page", Home_URL);

		isAlertPresent(driver);

		// Click Order guide option
		wait = new WebDriverWait(driver, 50);
		WebElement lnk_OrderGuide = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Order Guide')]/*")));
		lnk_OrderGuide.click();

		// driver.findElement(By.xpath("//a[contains(.,'Order
		// Guide')]/*")).click();

		WebElement btn_downArrowOrderGuideSelection = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@id='productList']/div/a[@class='actionsButton']")));
		btn_downArrowOrderGuideSelection.click();

		driver.findElement(By.xpath("//a[contains(.,'Print/Export List')]")).click();
		DialogWinExportOG(driver);

	}


	/*public void StepsToExportBottega(WebDriver driver) throws InterruptedException {
		
		// Check if ALert is present
		isAlertPresent(driver);
		
		String Current_URL = driver.getCurrentUrl();
		// Verify if login is sucess
		Assert.assertEquals("https://apps.gfs.com/experience/main/portal.html", Current_URL);
		System.out.println("GFS - Home Page");

		// Wait for Online Ordering link visiblity
		wait = new WebDriverWait(driver, 30);
		WebElement lnk_OnlineOrdering = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Online Ordering')]")));

		// Online ordering link assertion
		Assert.assertEquals(true, lnk_OnlineOrdering.isDisplayed());

		lnk_OnlineOrdering.click();
		
		isAlertPresent(driver);
		
		Assert.assertEquals("https://apps.gfs.com/doc/desktop/index.html#/account",driver.getCurrentUrl());
		
		driver.findElement(By.xpath("//div[contains(text(),'100020166')]")).click();

		// Click Order guide option
		wait = new WebDriverWait(driver, 50);
		WebElement lnk_OrderGuide = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Order Guide')]/*")));
		lnk_OrderGuide.click();

		// driver.findElement(By.xpath("//a[contains(.,'Order
		// Guide')]/*")).click();

		WebElement btn_downArrowOrderGuideSelection = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@id='productList']/div/a[@class='actionsButton']")));
		btn_downArrowOrderGuideSelection.click();

		driver.findElement(By.xpath("//a[contains(.,'Print/Export List')]")).click();
		DialogWinExportOG(driver);

	}*/
	
	public void StepsToExportAcSelect(WebDriver driver, String AcName) throws InterruptedException {
		
		isAlertPresent(driver);
		//Check select account 1 
		//checkAccountPage(driver, AcName);
		System.out.println("The account name is : "+AcName);
		
		String Current_URL = driver.getCurrentUrl();
		// Verify if login is sucess
		Assert.assertEquals("https://apps.gfs.com/experience/main/portal.html", Current_URL);
		System.out.println("GFS - Home Page");

		// Wait for Online Ordering link visiblitys
		wait = new WebDriverWait(driver, 30);
		WebElement lnk_OnlineOrdering = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Online Ordering')]")));

		// Online ordering link assertion
		Assert.assertEquals(true, lnk_OnlineOrdering.isDisplayed());

		lnk_OnlineOrdering.click();
		
		// check for Alert
		isAlertPresent(driver);
		
		//Check for account page & select account
		Thread.sleep(5000);
		checkAccountPage(driver, AcName);

	/*	
		Assert.assertEquals("https://apps.gfs.com/doc/desktop/index.html#/account",driver.getCurrentUrl());
		
		driver.findElement(By.xpath("//div[contains(text(),'100020166')]")).click();
*/
		// Click Order guide option
		Thread.sleep(2000);
		wait = new WebDriverWait(driver, 50);
		WebElement lnk_OrderGuide = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Order Guide')]/*")));
		lnk_OrderGuide.click();

		// driver.findElement(By.xpath("//a[contains(.,'Order
		// Guide')]/*")).click();

		WebElement btn_downArrowOrderGuideSelection = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//*[@id='productList']/div/a[@class='actionsButton']")));
		btn_downArrowOrderGuideSelection.click();

		driver.findElement(By.xpath("//a[contains(.,'Print/Export List')]")).click();
		DialogWinExportOG(driver);

	}
	

	private void checkAccountPage(WebDriver driver, String AcName) {
		// TODO Auto-generated method stub
		String AccountPageUrl = "https://apps.gfs.com/doc/desktop/index.html#/account";
		if (AccountPageUrl.equalsIgnoreCase(driver.getCurrentUrl())) {
			
			selectAccount(driver,AcName);
			System.out.println("Account name selected :-" +AcName);
			
		} else {
			
			System.out.println("No account selection page appeared.");
		}
	}

	private void selectAccount(WebDriver driver, String AcName) {
		// TODO Auto-generated method stub
		//System.out.println("The account name appear on system is -"+driver.findElement(By.xpath("")).getText());
		driver.findElement(By.xpath("//div[contains(text(),'"+ AcName +"')]")).click();
		System.out.println(AcName+ " is selected");
	}

	public void PreImportOrderStepsGFS(WebDriver driver) {
		driver.findElement(By.xpath("//a[contains(.,'Online Ordering')]")).click();
		System.out.println("Online ordering clicked");

		// check if alert is present
		isAlertPresent(driver);
		System.out.println("Alert found status :- " + isAlertPresent(driver));

		// clicking on 10 key link
		driver.findElement(By.xpath("//a/div[contains(.,'10 key/import')]")).click();
		// driver.findElement(By.xpath("//a[contains(@focus-parent-id,'16')]")).click();

		driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/div/div[3]/a[4]")).click();

		driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/div/div[2]/a[3]")).click();

		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/div/div[1]/a"))
				.click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/a[2]")).click();
		// click on choose file
		driver.findElement(By.xpath("//input[@id='importFileField']")).click();
	}

	public void PostImportOrderStepsGFS(WebDriver driver) {

		driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/div/div[3]/a[4]")).click();

		driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/div/div[2]/a[3]")).click();

		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/div/div[1]/a"))
				.click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[6]/a[2]")).click();
		// click on choose file
		driver.findElement(By.xpath("//input[@id='importFileField']")).click();
	}

}
