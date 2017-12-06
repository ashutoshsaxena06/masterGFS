package com.edge.GFS.online;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class CommonGFS {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	private final static Logger logger = Logger.getLogger(CommonGFS.class);


	public void LogOutGFSAccount(WebDriver driver) {

		try {
			driver.findElement(By.xpath("//a[@class='account-menu-toggle']")).click();

			driver.findElement(By.xpath("//li[@class='logout-option']")).click();

			WebElement lnk_Confirm_logout = wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//div/a[@class='confirm blue button ng-binding']"))));
			lnk_Confirm_logout.click();
		} catch (WebDriverException se) {
			logger.info("Logout failed");
			se.printStackTrace();
		} catch (RuntimeException re) {
			// TODO: handle exception
			logger.info("logout Failed");
		} catch (Exception e2) {
			logger.info("logout failed");
			e2.printStackTrace();
		}
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//button[@class='button gfsexperience-modal-close']")).click();
			logger.info("Alert handled");
			return true;
		} catch (NoAlertPresentException Ex) {
			logger.info("No alert found");
			return false;
		} catch (NoSuchElementException e) {
			logger.info("No alert found");
			return false;
		} catch (WebDriverException e) {
			logger.info("No alert found");
			return false;
		}

	}

	public void CheckExportOGStatus(WebDriver driver) {

		try {
			if (wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.xpath("//div[@class='dialogWindow smaller']"))))
					.isDisplayed()) {
				String ExportStatus = driver.findElement(By.xpath("//div[@class='dialogWindow smaller']/*/*/*/*/*"))
						.getText();

				logger.info(ExportStatus);

				// driver.findElement(By.xpath("//div[@class='dialogWindow
				// smaller'][contains(.,'OK')]")).click();

				/*
				 * if (ExportStatus.equalsIgnoreCase("Export Failed")) {
				 * logger.info("Order Guide Export failed");
				 * ErrRemedy.ErrScreenshotCapture(driver);
				 * ErrRemedy.ErrReportingMail(); }
				 */
			}
		} catch (InvalidSelectorException ine) {
			logger.info("Export dialog window not found invalid selector");
			// LogOutGFSAccount(driver);
			ine.printStackTrace();
		} catch (NoSuchElementException ne) {
			logger.info("Export dailog window not found");
			// LogOutGFSAccount(driver);
			ne.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("No Export pop up");
			// LogOutGFSAccount(driver);
			e.printStackTrace();

		}

	}

	public void DialogWinExportOG(WebDriver driver) {

		if (driver.findElement(By.className("dialogWindow")).isDisplayed()) {

			logger.info("Export list window is displayed.");

			driver.findElement(By.xpath("//span[contains(.,'Price')]")).click();

			driver.findElement(By.xpath("//button[contains(.,'Export')]")).click();

			CheckExportOGStatus(driver);
		}

		else {
			logger.info("Select data columns dialog window is not popped up");
		}
	}

	public boolean LoginGFS(WebDriver driver, String usernameGFS, String passwordGFS) {

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			driver.get("https://apps.gfs.com/experience/");
			// Wait For Page To Load

			// pass login credentials
			driver.findElement(By.xpath("//*[@id='j_username']")).sendKeys(usernameGFS);
			driver.findElement(By.xpath("//*[@id='j_password']")).sendKeys(passwordGFS);

			driver.findElement(By.xpath("//*[@id='submit']")).click();
			logger.info("Login Successful");
			
			return true;
		
	}

	public Boolean StepsToExport(WebDriver driver) throws InterruptedException {

		// check if Alert is present
		isAlertPresent(driver);

		String Current_URL = driver.getCurrentUrl();
		// Verify if login is sucess
		Assert.assertEquals("https://apps.gfs.com/experience/main/portal.html", Current_URL);
		logger.info("GFS - Home Page");

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

		Thread.sleep(3000);
		return true;

	}

	/*
	 * public void StepsToExportBottega(WebDriver driver) throws
	 * InterruptedException {
	 * 
	 * // Check if ALert is present isAlertPresent(driver);
	 * 
	 * String Current_URL = driver.getCurrentUrl(); // Verify if login is sucess
	 * Assert.assertEquals("https://apps.gfs.com/experience/main/portal.html",
	 * Current_URL); logger.info("GFS - Home Page"); // Wait for Online
	 * Ordering link visiblity wait = new WebDriverWait(driver, 30); WebElement
	 * lnk_OnlineOrdering = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//a[contains(.,'Online Ordering')]"))); // Online ordering link
	 * assertion Assert.assertEquals(true, lnk_OnlineOrdering.isDisplayed());
	 * lnk_OnlineOrdering.click();
	 * 
	 * isAlertPresent(driver);
	 * 
	 * Assert.assertEquals(
	 * "https://apps.gfs.com/doc/desktop/index.html#/account",driver.
	 * getCurrentUrl());
	 * 
	 * driver.findElement(By.xpath("//div[contains(text(),'100020166')]")).click
	 * (); // Click Order guide option wait = new WebDriverWait(driver, 50);
	 * WebElement lnk_OrderGuide = wait
	 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
	 * "//a[contains(.,'Order Guide')]/*"))); lnk_OrderGuide.click(); //
	 * driver.findElement(By.xpath("//a[contains(.,'Order //
	 * Guide')]/*")).click(); WebElement btn_downArrowOrderGuideSelection =
	 * wait.until(ExpectedConditions .visibilityOfElementLocated(By.xpath(
	 * ".//*[@id='productList']/div/a[@class='actionsButton']")));
	 * btn_downArrowOrderGuideSelection.click(); driver.findElement(By.xpath(
	 * "//a[contains(.,'Print/Export List')]")).click();
	 * DialogWinExportOG(driver); }
	 */

	public Boolean StepsToExport(WebDriver driver, String AcName) throws InterruptedException {

		isAlertPresent(driver);

		String Current_URL = driver.getCurrentUrl();
		// Verify if login is sucess
		Assert.assertEquals("https://apps.gfs.com/experience/main/portal.html", Current_URL);
		logger.info("GFS - Home Page");

		// Wait for Online Ordering link visiblitys
		wait = new WebDriverWait(driver, 30);
		WebElement lnk_OnlineOrdering = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Online Ordering')]")));

		// Online ordering link assertion
		Assert.assertEquals(true, lnk_OnlineOrdering.isDisplayed());

		lnk_OnlineOrdering.click();

		// check for Alert
		isAlertPresent(driver);

		// Check select account 1
		// checkAccountPage(driver, AcName);
		logger.info("The account name is : " + AcName);
		
		// Check for account page & select account
		Thread.sleep(5000);
		checkAccountPage(driver, AcName);

		/*
		 * Assert.assertEquals(
		 * "https://apps.gfs.com/doc/desktop/index.html#/account",driver.
		 * getCurrentUrl());
		 * 
		 * driver.findElement(By.xpath("//div[contains(text(),'100020166')]")).
		 * click();
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

		Thread.sleep(3000);
		return true;

	}

	private void checkAccountPage(WebDriver driver, String AcName) {

		String AccountPageUrl = "https://apps.gfs.com/doc/desktop/index.html#/account";
		if (AccountPageUrl.equalsIgnoreCase(driver.getCurrentUrl())) {

			selectAccount(driver, AcName);
			logger.info("Account name selected :-" + AcName);

		} else {

			logger.info("No account selection page appeared.");
		}
	}

	private void selectAccount(WebDriver driver, String AcName) {
		// TODO Auto-generated method stub
		// logger.info("The account name appear on system is
		// -"+driver.findElement(By.xpath("")).getText());
		driver.findElement(By.xpath("//span[contains(text(),'" + AcName + "')]")).click();
		logger.info(AcName + " is selected");
	}

	public void PreImportOrderStepsGFS(WebDriver driver) {
		driver.findElement(By.xpath("//a[contains(.,'Online Ordering')]")).click();
		logger.info("Online ordering clicked");

		// check if alert is present
		isAlertPresent(driver);
		logger.info("Alert found status :- " + isAlertPresent(driver));

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