package com.framework.commonUtils;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonGFS {

    private final static Logger logger = Logger.getLogger(CommonGFS.class);
    private static final List<String> OG_Columns = Arrays.asList("Item Number", "Brand", "Description", "Inner Pack", "Pack", "Price");
    private static final List<String> non_OG_Columns = Arrays.asList("Last Order Date", "Group Headers");
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String fileFormat = "CSV";
    public static String unSelectedColumnNames ="//div[@class='unselectedColumns']/descendant::span";
    public static String unSelectedColumnAdd ="//div[contains(@class,'unselectedColumns')]/descendant::span[contains(text(),'columnName')]/following-sibling::a";

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
            Thread.sleep(90000);
            logger.info("Alert handled");
            return true;
        } catch (NoAlertPresentException Ex) {
            logger.info("No alert found");
            return false;
        } catch (NoSuchElementException e) {
            logger.info("No alert found");
            return false;
        } catch (Exception e) {
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

    public void selectFileType() {
        WebElement ele_FileType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='CSV']/following-sibling::span[@class='radio']")));
        ele_FileType.click();
    }

    public void customExportWindow() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        selectFileType();
        chooseFileColumns();
        clickDownload();
    }

    private void clickDownload() {
        WebElement ele_FileType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Download']")));
        ele_FileType.click();
    }

    private void chooseFileColumns() {
        List<WebElement> unSelected = driver.findElements(By.xpath(unSelectedColumnNames));
        List<String> columnsToSelect = new ArrayList<>();

        for (WebElement we : unSelected) {
            logger.info("unselected column - " + we.getText());
            columnsToSelect.add(we.getText());
        }
        for ( String s : columnsToSelect ) {
            driver.findElement(By.xpath(unSelectedColumnAdd.replace("columnName", s))).click();
        }

        List<WebElement> selectedColumns = driver.findElements(By.xpath("//div[contains(@class,'selectedColumns hasOverflow')]/descendant::span[@class='labels columnName ng-binding']"));
        for (WebElement we : unSelected) {
            logger.info("selected columns " + we.getText());
        }

//        for (String s : non_OG_Columns) {
//            //uncheck not required
//            logger.info(driver.findElement(By.xpath("//*[text()='" + s + "']/following-sibling::input")).getAttribute("class").contains("ng-empty") ? s + "- unchecked" : s + "- unchecked element - " + CheckUncheckElement(s));
//        }
//        for (String s : OG_Columns) {
//            //check required
//            logger.info(driver.findElement(By.xpath("//*[text()='" + s + "']/following-sibling::input")).getAttribute("class").contains("ng-not-empty") ? s + "- checked" : s + "- checked element - " + CheckUncheckElement(s));
//        }
    }

    private boolean CheckUncheckElement(String s) {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + s + "']/following-sibling::span[@class='checkmark']"))).click();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Deprecated
    public void DialogWinExportOG(WebDriver driver) {

        if (driver.findElement(By.className("dialogWindow")).isDisplayed()) {

            logger.info("Export list window is displayed.");

            driver.findElement(By.xpath("//span[contains(.,'Price')]")).click();

            driver.findElement(By.xpath("//button[contains(.,'Export')]")).click();

            CheckExportOGStatus(driver);
        } else {
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
        boolean flag = checkLoginStatus(driver);
        return flag;

    }

    public Boolean checkLoginStatus(WebDriver driver) {
        String Current_URL = driver.getCurrentUrl();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Verify if login is sucess
        if ("https://apps.gfs.com/experience/main/portal.html".equalsIgnoreCase(Current_URL)) {
            logger.info("Login Successful");
            logger.info("GFS - Home Page");
            return true;
        } else {
            return false;
        }
    }


    private void onlineOrdering(WebDriver driver) {
        try {
            Thread.sleep(3000);
            WebElement lnk_OnlineOrdering = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Online Ordering')]")));
            // Online ordering link assertion
            Assert.assertEquals(true, lnk_OnlineOrdering.isDisplayed());
            lnk_OnlineOrdering.click();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Launching URL");
            driver.get("https://apps.gfs.com/experience/portal/dispatch.do?applicationName=doc");

        }
    }


    public Boolean StepsToExport(WebDriver driver, String AcName) throws InterruptedException {

        isAlertPresent(driver);

        // check for Alert
//        isAlertPresent(driver);
        // ORDER GUIDE PAGE
        driver.get("https://apps.gfs.com/doc/desktop/index.html#/account");

        // Check select account 1
        // checkAccountPage(driver, AcName);
        if (AcName != null && !AcName.isEmpty()) {
            logger.info("The account name is : " + AcName);
            // Check for account page & select account
            Thread.sleep(5000);
            checkAccountPage(driver, AcName);
        }
        // Click Order guide option
        Thread.sleep(5000);
        OrderGuide(driver);
        Thread.sleep(5000);
        WebElement btn_downArrowOrderGuideSelection = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(".//*[@id='productList']/div/a[@class='actionsButton']")));
        btn_downArrowOrderGuideSelection.click();

        WebElement btn_customExport = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.linkText("Custom Export")));
        btn_customExport.click();

        customExportWindow();
        Thread.sleep(3000);
        return true;
    }

    private void OrderGuide(WebDriver driver) {
        try {
            wait = new WebDriverWait(driver, 20);
            WebElement lnk_OrderGuide = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Order Guide')]/*")));
            lnk_OrderGuide.click();
        } catch (Exception e) {
            driver.get("https://apps.gfs.com/doc/desktop/index.html#/order_guide");
            e.printStackTrace();
        }
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
        driver.findElement(
                By.xpath("//div[@class='product-table-cell accountNumCol ng-binding'][contains(.,'" + AcName + "')]"))
                .click();
        logger.info(AcName + " is selected");
    }

}