
package com.edge.GFS.online;

import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.util.framework.BrowserAction;
import com.util.framework.SendMailSSL;

public class TestGFSOnline extends CommonGFS {

	private WebDriver driver;

	@BeforeClass
	public void setup() {
		System.out.println("*************GFS************");

	}

	@AfterClass
	public void AfterClass() {
		System.out.println("************End***********");
	}

	@BeforeMethod
	public void beforeTest() throws InterruptedException {
		System.out.println("***********StartTest*********");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Edge\\git\\masterGFS\\src\\main\\java\\resources\\chromedriver.exe");
		// RandomAction.setDownloadFilePath();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		BrowserAction.ClearBrowserCache(driver);
	}

	@AfterMethod
	public void pretearDown() {
		if (driver != null) {
			driver.quit();
			System.out.println("*******EndTest*********");

		}
	}

	@Test(priority = 1)
	public void Espositos_GFS() throws InterruptedException, MessagingException {
		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "jlay11", "pizza123"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Espositos Pizza");
	}

	@Test(priority = 2)
	public void Liguoris_GFS() throws InterruptedException {
		Assert.assertTrue(LoginGFS(driver, "jlay11", "pizza123"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		// -- send big pink files to Liguoris
		SendMailSSL.sendMailAction("GFS - Offline GP", "Liguori's Fired Up");
	}

	@Test(priority = 3)
	public void Gilberts_GFS() throws InterruptedException {
		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "rlebrun", "Gilberts1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Gilberts Resort");
	}

	@Test(priority = 4)
	public void OldKeyLime_GFS() throws InterruptedException {
		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "amyholt", "oklh1889"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Old Key Lime House");

	}

	@Test(priority = 5)
	public void SportsGrillSunset_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "Sgsunset", "sports1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Sunset");
	}

	@Test(priority = 6)
	public void SportsGrillKendall_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "sgkendall", "sports1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Kendall");

	}

	@Test(priority = 7)
	public void SportsGrillPembroke_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "sgpembroke", "sports1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Pembroke Pines");
	}

	@Test(priority = 8)
	public void SportsGrillPalmetto_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "sgpalmetto", "sports1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Palmetto");

	}

	@Test(priority = 9)
	public void SportsGrillSouthMiami_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "sgsouthmiami", "sports1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill South Miami");
	}

	@Test(priority = 10)
	public void Talavera_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "talavera001", "talavera002"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Talavera Restaurant");

	}

	@Test(priority = 11)
	public void TownKitchen_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "townkitchen", "kitchen1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Town Kitchen & Bar");
	}

	@Test(priority = 12)
	public void BigPink_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "bigpink", "123456"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Big Pink");

	}

	@Test(priority = 13)
	public void Hamiltons_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "ghamilton", "Leevia@"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("Gordon - Hybrid", "Hamiltons Catering");
	}

	@Test(priority = 14)
	public void ConchRep_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "nbrummette", "pantera13"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "The Conch Republic");

	}

	@Test(priority = 15)
	public void Woodfield_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "mcdonald", "3650club"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("Gordon - Hybrid", "Woodfield Country Club");
	}

	@Test(priority = 16)
	public void Bottega_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "rprieto", "100020166"));
		// check the flow to export
		StepsToExportBottega(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("Gordon Food Service", "Bottega Express");

	}

	@Test(priority = 17)
	public void Strebs_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "richw0", "235610"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Strebs Restaurant");
	}

	@Test(priority = 18)
	public void Prime112_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "bigpink", "123456"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Prime 112");

	}
	
	@Test(priority = 19)
	public void TrumpIntl_GFS() throws InterruptedException {

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "bigpink", "123456"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Trump International Beach Resort");

	}
	

}
/*
 * @priority(name = "Authentication") public Object[][] loginData() { Object[][]
 * arrayObject = ExcelUtils.readXLSXFile("D:/sampledoc.xls","Sheet1"); return
 * arrayObject; }
 */