package com.edge.GFS.online;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.util.framework.CommonGFS;
import com.util.framework.ExcelFunctions;
import com.util.framework.RandomAction;
import com.util.framework.SendMailSSL;


public class TestGFSExecutor extends CommonGFS {

	static final int maxtry = 3;
	static int retry = 0;
	public static int rowIndex;
	public static String projectPath = System.getProperty("user.dir");
	public static String inputFile = "C:\\Users\\Edge\\Desktop\\ExportEngineInput.xlsx";
	// projectPath + "\\config\\ExportEngineInput.xlsx";
	public static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	public static String reportFile = "C:\\Users\\Edge\\Desktop\\Reports\\GFS_OG_report\\ExportSummary_Cheney_"+ new Date().toString().replace(":", "").replace(" ", "") + ".xlsx";
			// for Edge - "C:\\Users\\Edge\\Desktop\\Reports\\CheneyOG_report\\ExportSummary_Cheney_" + PageAction.getDate().toString().replace(" ", "_");
//			+ new Date().toString().replace(":", "").replace(" ", "") + ".xlsx";
	// projectPath+ "\\Output_Summary\\ExportSummary_Cheney_" + new
	// Date().toString().replace(":", "").replace(" ", "")+".xlsx";
	public static int acno;
	public static XSSFWorkbook exportworkbook;
	public static XSSFSheet inputsheet;
	public static int AcColStatus, AcColdetail;
	public static FileOutputStream out;
	public static int totalNoOfRows;
	public static String folderDate;
	public static String currList = "";
	public static String emailMessageExport = "";
	public static String path = System.getProperty("user.home")+"\\Downloads\\chromedriver_win32\\chromedriver.exe";
	public static String project = "GFS";

	private final static Logger logger = Logger.getLogger(TestGFSExecutor.class);

	@BeforeTest
	public void beforeData() throws Exception {
		// read excel data

		// get active accounts
		// launch browser

		// -- fail ->
		exportworkbook = ExcelFunctions.openFile(inputFile);
		logger.info("Test data read.");
		inputsheet = exportworkbook.getSheet(project);
		AcColStatus = ExcelFunctions.getColumnNumber("Export Status", inputsheet);
		AcColdetail = ExcelFunctions.getColumnNumber("Detailed Export Status", inputsheet);

		logger.info("Exiting before data.");
		// copy config file to report folder
		// ExcelFunctions.copySheet(exportworkbook, , );
	}

	@AfterTest
	public void closeResources() throws SQLException, IOException {
		logger.info("Closing the resources!");

		if (out != null) {
			logger.info("Closing file output stream object!");
			out.close();
		}
		if (driver != null) {
			logger.info("Closing the browser!");
			// TestCases.driver.close();
			driver.quit();
		}

		if (exportworkbook != null) {
			exportworkbook.close();
		}
	}

	@BeforeMethod
	public static void setUp() throws IOException {
		// to get the browser on which the UI test has to be performed.
		logger.info("***********StartTest*********");
		RandomAction.deleteFiles(System.getProperty("user.home")+"\\Downloads");
		driver = RandomAction.openBrowser("Chrome", path);
		logger.info("Invoked browser .. ");
	}

	@AfterMethod
	public static void writeExcel() throws IOException {
		logger.info("Running Excel write method!");
		out = new FileOutputStream(new File(reportFile));
		exportworkbook.write(out);
		acno++;
		driver.close();
	}

	@DataProvider(name = "testData")
	public static Object[][] testData() throws IOException {
		logger.info("Inside Dataprovider. Creating the Object Array to store test data inputs.");
		Object[][] td = null;
		try {
			// Get TestCase sheet data
			int totalNoOfCols = inputsheet.getRow(inputsheet.getFirstRowNum()).getPhysicalNumberOfCells();
			totalNoOfRows = inputsheet.getLastRowNum();
			logger.info(totalNoOfRows + " Accounts and Columns are: " + totalNoOfCols);
			td = new String[totalNoOfRows][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows; i++) {
				for (int j = 0; j < totalNoOfCols; j++) {
					td[i - 1][j] = ExcelFunctions.getCellValue(inputsheet, i, j);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Test Cases captured in the Object Array. Exiting dataprovider.");
		return td;
	}

	@Test(dataProvider = "testData")
	public void Export_Mail_OG(
			String active, 
			String accountID, 
			String purveyor, 
			String restaurant_name,
			String username, 
			String password, 
			String accountname, 
			String exportstatus, 
			String detailedstatus) {
		Boolean result;
		logger.info("Inside OG Export : Started exporting OG for different accounts");
		XSSFCell cell1, cell2;
		TestGFSExecutor.rowIndex = Math.floorMod(TestGFSExecutor.acno, TestGFSExecutor.totalNoOfRows) + 1;

		logger.info("Test Case test #" + TestGFSExecutor.rowIndex);
		cell1 = TestGFSExecutor.exportworkbook.getSheet(project).getRow(TestGFSExecutor.rowIndex)
				.createCell(TestGFSExecutor.AcColStatus);
		cell1.setCellValue("");
		cell2 = TestGFSExecutor.exportworkbook.getSheet(project).getRow(TestGFSExecutor.rowIndex)
				.createCell(TestGFSExecutor.AcColdetail);
		cell2.setCellValue("");
		// if((cell1=TestGFSExecutor.exportworkbook.getSheet(project).getRow(TestGFSExecutor.rowIndex).getCell(TestGFSExecutor.AcColStatus))==null){
		// cell1 =
		// TestGFSExecutor.exportworkbook.getSheet(project).getRow(TestGFSExecutor.rowIndex).createCell(TestGFSExecutor.AcColStatus);
		// cell1.setCellValue("");
		// }
		// if((cell2=TestGFSExecutor.exportworkbook.getSheet(project).getRow(TestGFSExecutor.rowIndex).getCell(TestGFSExecutor.AcColdetail))==null){
		// cell2 =
		// TestGFSExecutor.exportworkbook.getSheet(project).getRow(TestGFSExecutor.rowIndex).createCell(TestGFSExecutor.AcColdetail);
		// cell2.setCellValue("");
		// }
		exportstatus = cell1.getStringCellValue();
		detailedstatus = cell2.getStringCellValue();

		try {
			if (active.equalsIgnoreCase("Yes")) {
				// if list is not empty
				logger.info(restaurant_name + " for purveryor " + purveyor + " is Active !!");
				Assert.assertEquals(true, LoginGFS(driver, username.trim(), password.trim()));
				if (accountname != null && !accountname.isEmpty()) {
					result = StepsToExport(driver, accountname);
				} else { // default OG
					result = StepsToExport(driver);
				}
				if (result.equals(true)) {
					emailMessageExport = "Pass";
					exportstatus = "Pass";
					detailedstatus = "OG exported succesfully";
				} else {
					emailMessageExport = "Failed";
					exportstatus = "Failed";
					detailedstatus = "OG export Failed";
				}
				Thread.sleep(8000);
				SendMailSSL.sendMailActionCsvDE(purveyor.trim(), restaurant_name.trim());
			} else {
				logger.info(restaurant_name + " for purveryor " + purveyor + " is not Active !!");
				exportstatus = "Not Active";
			}
			cell1.setCellValue(exportstatus);
			cell2.setCellValue(detailedstatus);

			logger.info("Exiting test method");

		} catch (Exception e) {
			e.printStackTrace();
			exportstatus = "Failed";
			detailedstatus = "Some technical issue ocurred during export";
			cell1.setCellValue(exportstatus);
			cell2.setCellValue(detailedstatus);
			logger.info("Technical issue occured during export for restaurant - "+restaurant_name);
		}
		logger.info(emailMessageExport.trim());
	}

	////////////////////////////////////////////////
	@AfterClass
	public static void sendMail() {
		try {
			String emailMsg = "Daily " + project + " OG Export Status: " + RandomAction.getDate();
			SendMailSSL.sendReport(emailMsg, reportFile);
			logger.info("Email Sent with Attachment");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////////

}
