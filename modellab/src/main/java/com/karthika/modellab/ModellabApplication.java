package com.karthika.modellab;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModellabApplication {

	// static Select select;
	// static WebDriver driver;

	public static void main(String[] args) {
		SpringApplication.run(ModellabApplication.class, args);


		// driver=new ChromeDriver();
		// driver.get("https://www.moneycontrol.com");
		// driver.findElement(By.xpath("/html/body/div[3]/header/div[1]/div[1]/div/div/div[2]/div/div/form/input[5]")).sendKeys("Reliance Industries"+Keys.ENTER);
		// //driver.findElement(By.xpath("/html/body/header/div[1]/div[3]/nav/div/ul/li[10]/a"));
		// driver.get("https://www.moneycontrol.com/mf/sipcalculator.php");
		// select = new Select(driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[2]/div[2]/form/select")));
		// select.selectByVisibleText("Axis Mutual Fund");
	    // select = new Select(driver.findElement( By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[2]/div[4]/form/select")));
		// select.selectByVisibleText("Axis Arbitrage Fund");
		// driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[2]/form/div[2]/input")).sendKeys("100000");
        // driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[2]/form/div[6]/div[2]/input")).sendKeys("2021-08-02");
		// driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[2]/form/div[6]/div[4]/input")).sendKeys("2023-08-17");
        // driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/div[2]/div[2]/form/div[8]/input")).click();
	}

}
