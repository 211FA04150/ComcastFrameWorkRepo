package com.comcast.crm.contactTest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.crm.generic.baseutility.BaseClass;

public class CreateContactTest extends BaseClass
{
		@Test(groups = "smokeTest")
		public void CreatecontactTest() throws Throwable {
		//public static void main(String[] args) throws Throwable {
			//create obj
			FileUtility flib = new FileUtility();
			ExcelUtility elib = new ExcelUtility();
			String url = flib.getDataFromPropertiesFile("url");
			String browser = flib.getDataFromPropertiesFile("browser");
			String user = flib.getDataFromPropertiesFile("user");
			String password = flib.getDataFromPropertiesFile("password");
			
			//genereate random number
			Random ram = new Random();
			int random = ram.nextInt(200);
			
			//read testscript data fromm excelfile
			String lastname = elib.getDataFromExcel("contact", 4,2 )+random;
						
			WebDriver driver = null;
			if(browser.equals("chrome"))
			{
				driver = new ChromeDriver();
			}
			else if(browser.equals("firefox"))
			{
				driver = new FirefoxDriver();
			}
			else if(browser.equals("edge"))
			{
				driver = new EdgeDriver();
			}
			else
			{
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get(url);
			driver.findElement(By.name("user_name")).sendKeys(user);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
			

			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			Thread.sleep(2000);

					String conlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
					if(conlastname.equals(lastname))
					{
						System.out.println(conlastname+" is created");
					}
					else
					{
						System.out.println(conlastname+"is created");
					}

//			WebElement ele  = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
			Thread.sleep(2000);
			
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			
			
			driver.quit();
	}
}
