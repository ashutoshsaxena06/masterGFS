package com.util.framework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheneyNew {

	private static WebDriverWait wait;
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub


		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Edge\\git\\masterGFS\\src\\main\\java\\resources\\chromedriver.exe");
		// RandomAction.setDownloadFilePath();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		BrowserAction.ClearBrowserCache(driver);
		
		//Launch URL
		driver.get("http://cheneybrothers.foodorderentry.com/");
		// Frame 
		isFramePresent(driver);
		
		driver.switchTo().frame("Index");
		driver.findElement(By.xpath("//input[@name='accesscode']")).sendKeys("010060008173");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("G8173");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//isFramePresent(driver);
	
		// Veify login 
		//Assert.assertEquals(driver.findElement(By.xpath("//td[1]/*/*[contains(.,'Customer Snapshot')]")).getText(), "Customer Snapshot");
		System.out.println("Home Page displayed - "+driver.findElement(By.xpath("//td[@class='A2wUnchange']")).isDisplayed());
		
		isFramePresent(driver);
		
		WebElement lnk_OrderGuides = driver.findElement(By.xpath("//div/a/*[@id='divOGText']"));
		lnk_OrderGuides.click();
		

		//input[@name='accesscode']
		//input[@name='password']
		//input[@type='submit']

	}
	
	public static boolean isFramePresent(WebDriver driver) throws InterruptedException {
		//
		// driver.findElement(By.xpath("//html/body/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[1]/td/input")).click();
		Thread.sleep(5000);
		// List to get & store frame
		List<WebElement> ele = driver.findElements(By.tagName("frame"));
		System.out.println("Number of frames in a page :" + ele.size()); // ele.size
																			// -
																			// size
																			// of
																			// frame
																			// list

		if (ele.size() == 0) {
			System.out.println("No frames on this page");
			return false; // No frames
		} else {
			System.out.println("Frames present on this page, Below are the details -");

			for (WebElement el : ele) {
				// Returns the Id of a frame
				System.out.println("Frame Id :" + el.getAttribute("id"));
				// Returns the Name of a frame.
				System.out.println("Frame name :" + el.getAttribute("name"));
			}
			return true; // frames present
		}

	}

}
