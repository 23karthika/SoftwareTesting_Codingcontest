package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class AppTest {
    private WebDriver driver;
    private XSSFWorkbook wb;
    public ExtentReports report;
    private Actions action;
    Logger logger= Logger.getLogger(getClass());

    @BeforeTest
    public void beforeTestSetup()throws Exception {
        driver = new ChromeDriver();

        //action
        action =new Actions(driver);
        //excel
        String xlpath = "C:\\Users\\madhu\\OneDrive\\Pictures\\Desktop\\selenium\\selenium_cc2\\src\\excel\\excel_cc.xlsx";
        FileInputStream file = new FileInputStream(xlpath);
        wb = new XSSFWorkbook(file);

        //report
        report = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\madhu\\OneDrive\\Pictures\\Desktop\\selenium\\selenium_cc2\\src\\report\\Report.html");
        report.attachReporter(spark);
    }

    //testcase1
    @Test
    public void bookSearch() throws IOException {

        String searchText = wb.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
        ExtentTest test = report.createTest("TestCase 1");
        driver.get("https://www.barnesandnoble.com/");

        
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[1]/a")).click();
        
        driver.findElement(By.linkText("Books")).click();
        
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]"))
        .sendKeys(searchText);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/span/button")).click();
        
        String ExptName = "chetan bhagat";
        String OriginalName = driver.findElement(By.xpath("//*[@id=\"searchGrid\"]/div/section[1]/section[1]/div/div[1]/div[1]/h1/span")).getText();
        
        if(ExptName.equals(OriginalName)){
            System.out.println("String Matches");
            test.log(Status.PASS, "Match Passed");
            
        }
        else{
            test.log(Status.FAIL, "Match Failed");
        }
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./Screenshot/shot1.png";
        FileUtils.copyFile(screen, new File(path));
    }
    //testcase2
    @Test(dependsOnMethods="bookSearch")
    public void addToCart()throws Exception{
        
        ExtentTest test2 = report.createTest("TestCase 2");

        driver.get("https://www.barnesandnoble.com/");
        
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]")))
        .perform();
        
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();


         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"resolve_1\"]"))).click();
        driver.findElement(By.xpath("//*[@id=\"globalSubBanner\"]/div/a/div/div/div")).click();
        driver.findElement(By.xpath("//*[@id=\"prodInfoContainer\"]/div[3]/form[1]/input[11]")).submit();

        String ExptName = "Item Successfully Added To Your Cart";
        String OriginalName = driver.findElement(By.xpath("//*[@id=\"add-to-bag-main\"]/div[1]")).getText();
        
        if(ExptName.equals(OriginalName)){
            System.out.println("String Matches");
            test2.log(Status.PASS, "Match Passed");
        }
        else{
            test2.log(Status.FAIL, "Match Failed");
        }


    }
//testcase3
    @Test(dependsOnMethods = "addToCart")
    public void verifyingPopup()throws Exception{
        driver.get("https://www.barnesandnoble.com/");
        
        driver.findElement(By.xpath("//*[@id=\"footer\"]/div/dd/div/div/div[1]/div/a[5]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"rewards-modal-link\"]")).click();
        Thread.sleep(2000);

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./Screenshot/shot2.png";
        FileUtils.copyFile(screen, new File(path));


    }
    @AfterTest
    public void closeSetup(){
        report.flush();
    }
}
