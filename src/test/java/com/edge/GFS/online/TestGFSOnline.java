package com.edge.GFS.online;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.util.framework.RandomAction;
import com.util.framework.SendMailSSL;

public class TestGFSOnline extends CommonGFS {

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
		RandomAction.deleteFiles("C:\\Users\\Edge\\Downloads");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Edge\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver(options);
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

		System.out.println("1, Espositos_GFS ");

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

		System.out.println("2, Liguoris_GFS ");

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

		System.out.println("3 , Gilberts_GFS ");

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

		System.out.println("4 , OldKeyLime_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "amyholt", "oklh1889"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Old Key Lime House");

	}

//	@Test(priority = 5)
//	public void SportsGrillSunset_GFS() throws InterruptedException {
//
//		System.out.println("5 , SportsGrillSunset_GFS ");
//
//		// check if login is success
//		Assert.assertTrue(LoginGFS(driver, "Sgsunset", "sports1"));
//		// check the flow to export
//		StepsToExport(driver);
//		// rename downloadeds
//		// String CurrentPath = RandomAction.setdownloadDir();
//		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
//		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Sunset");
//	}

	@Test(priority = 6)
	public void SportsGrillKendall_GFS() throws InterruptedException {

		System.out.println("6 , SportsGrillKendall_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "sgkendall", "sports1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Kendall");
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Pembroke Pines");
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Palmetto");
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill South Miami");
		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Sunset");
	}

//	@Test(priority = 7)
//	public void SportsGrillPembroke_GFS() throws InterruptedException {
//
//		System.out.println("7 , SportsGrillPembroke_GFS ");
//
//		// check if login is success
//		Assert.assertTrue(LoginGFS(driver, "sgpembroke", "sports1"));
//		// check the flow to export
//		StepsToExport(driver);
//		// rename downloadeds
//		// String CurrentPath = RandomAction.setdownloadDir();
//		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
//		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Pembroke Pines");
//	}
//
//	@Test(priority = 8)
//	public void SportsGrillPalmetto_GFS() throws InterruptedException {
//
//		System.out.println("8 , SportsGrillPalmetto_GFS ");
//
//		// check if login is success
//		Assert.assertTrue(LoginGFS(driver, "sgpalmetto", "sports1"));
//		// check the flow to export
//		StepsToExport(driver);
//		// rename downloadeds
//		// String CurrentPath = RandomAction.setdownloadDir();
//		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
//		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill Palmetto");
//
//	}
//
//	@Test(priority = 9)
//	public void SportsGrillSouthMiami_GFS() throws InterruptedException {
//
//		System.out.println("9 , SportsGrillSouthMiami_GFS ");
//
//		// check if login is success
//		Assert.assertTrue(LoginGFS(driver, "sgsouthmiami", "sports1"));
//		// check the flow to export
//		StepsToExport(driver);
//		// rename downloadeds
//		// String CurrentPath = RandomAction.setdownloadDir();
//		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
//		SendMailSSL.sendMailAction("GFS - Offline GP", "Sports Grill South Miami");
//	}

	@Test(priority = 10)
	public void Talavera_GFS() throws InterruptedException {

		System.out.println("10 , Talavera_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "talavera001", "talavera002"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Talavera Restaurant");

	}

	// @Test(priority = 11)
	// public void BigPink_GFS() throws InterruptedException {
	//
	// System.out.println("11 , BigPink_GFS ");
	//
	// // check if login is success
	// Assert.assertTrue(LoginGFS(driver, "bigpink", "123456"));
	// // check the flow to export
	// StepsToExport(driver);
	// // rename downloadeds
	// // String CurrentPath = RandomAction.setdownloadDir();
	// // File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
	// SendMailSSL.sendMailAction("GFS - Offline GP", "Big Pink");
	//
	// }

	@Test(priority = 12)
	public void Hamiltons_GFS() throws InterruptedException {

		System.out.println("12 , Hamiltons_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "ghamilton", "Leevia@"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("Gordon - Hybrid", "Hamiltons Catering");
	}

	@Test(priority = 13)
	public void ConchRep_GFS() throws InterruptedException {

		System.out.println("13 , ConchRep_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "nbrummette", "pantera13"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "The Conch Republic");

	}

	@Test(priority = 14)
	public void Woodfield_GFS() throws InterruptedException {

		System.out.println("14 , Woodfield_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "mcdonald", "3650club"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("Gordon - Hybrid", "Woodfield Country Club");
	}

	// @Test(priority = 15)
	// public void Strebs_GFS() throws InterruptedException {
	//
	// System.out.println("15 , Strebs_GFS ");
	//
	// // check if login is success
	// Assert.assertTrue(LoginGFS(driver, "richw0", "235610"));
	// // check the flow to export
	// StepsToExport(driver);
	// // rename downloadeds
	// // String CurrentPath = RandomAction.setdownloadDir();
	// // File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
	// SendMailSSL.sendMailAction("GFS - Offline GP", "Strebs Restaurant");
	// }

	@Test(priority = 16)
	public void Prime112_GFS() throws InterruptedException {

		System.out.println("16 , Prime112_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "bigpink", "123456"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloads
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Prime 112");

	}

	@Test(priority = 17)
	public void TrumpIntl_GFS() throws InterruptedException {

		System.out.println("17 , TrumpIntl_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "rvigo", "Aux123456"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloads
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Trump International Beach Resort");

	}

	@Test(priority = 18)
	public void ChefDavid_GFS() throws InterruptedException {

		System.out.println("20, ChefDavid_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "dschwadron", "dschwadron1"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Chef David Cuisine");

	}

	@Test(priority = 19)
	public void Jaguar_GFS() throws InterruptedException {

		System.out.println("21, Jaguar_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "jaguar001", "Jaguar002"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Jaguar");

	}

	// #select AC Test cases

//	@Test(priority = 20)
//	public void Agliolio_GFS() throws InterruptedException {
//
//		System.out.println("18, Agliolio_GFS ");
//		// check if login is success
//		Assert.assertTrue(LoginGFS(driver, "agliolio", "4flowers"));
//		// check the flow to export
//		StepsToExportAcSelect(driver, "AGLIOLIO-BOYNTON BEACH");
//		// rename downloadeds
//		// String CurrentPath = RandomAction.setdownloadDir();
//		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
//		SendMailSSL.sendMailAction("GFS - Offline GP", "Agliolio Boynton Italian Bistro & Bar");
//
//	}

	@Test(priority = 21)
	public void AgliolioWellington_GFS() throws InterruptedException {

		System.out.println("19, AgliolioWellington_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "agliolio", "4flowers"));
		// check the flow to export
		StepsToExportAcSelect(driver, "AGLIOLIO-WELLINGTON");
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Agliolio Wellington Fresh Pasta & Wine");

	}

	@Test(priority = 22)
	public void TownKitchen_GFS() throws InterruptedException {

		System.out.println("22, TownKitchen_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "townkitchen", "kitchen1"));
		// check the flow to export
		StepsToExportAcSelect(driver, "TOWN KITCHEN");
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Town Kitchen & Bar");
	}

	@Test(priority = 23)
	public void HouseTK_GFS() throws InterruptedException {

		System.out.println("23, HouseTK_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "townkitchen", "kitchen1"));
		// check the flow to export
		StepsToExportAcSelect(driver, "HOUSE KITCHEN & BAR");
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "House T&K");

	}


	@Test(priority = 24)
	public void Hallandale_GFS() throws InterruptedException {

		System.out.println("24, Hallandale_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "dawnwight", "Flashback1"));
		// check the flow to export
		StepsToExportAcSelect(driver, "FLASHBACK DINER");
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Flashback Diner Hallandale");

	}
	
	@Test(priority = 25)
	public void Davie_GFS() throws InterruptedException {

		System.out.println("26, Davie_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "dawnwight", "Flashback1"));
		// check the flow to export
		StepsToExportAcSelect(driver, "FLASHBACK DINER & COFFEE HOUSE");
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Flashback Diner Davie");

	}
	
	
	
	@Test(priority = 26)
	public void BocaRaton_GFS() throws InterruptedException {

		System.out.println("26, BocaRaton_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "dawnwight", "Flashback1"));
		// check the flow to export
		StepsToExportAcSelect(driver, "FLASHBACK DINER - BOCA RATON");
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Flashback Diner Boca Raton");

	}
	
	@Test(priority = 27)
	public void OrangeBlossom_GFS() throws InterruptedException {

		System.out.println("26, BocaRaton_GFS ");

		// check if login is success
		Assert.assertTrue(LoginGFS(driver, "SDCook", "OBHills123"));
		// check the flow to export
		StepsToExport(driver);
		// rename downloadeds
		// String CurrentPath = RandomAction.setdownloadDir();
		// File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
		SendMailSSL.sendMailAction("GFS - Offline GP", "Orange Blossom Hills");

	}
	
	// @Test(priority = 24)
	// public void Bottega_GFS() throws InterruptedException {
	// System.out.println("24, Bottega_GFS ");
	// // check if login is success
	// Assert.assertTrue(LoginGFS(driver, "rprieto", "100020166"));
	// // check the flow to export
	// StepsToExportAcSelect(driver, "BOTTEGA EXPRESS");
	// // rename downloadeds
	// // String CurrentPath = RandomAction.setdownloadDir();
	// // File GFS_OG = RandomAction.getLatestFilefromDir(CurrentPath);
	// SendMailSSL.sendMailAction("Gordon Food Service", "Bottega Express");
	//
	// }

}