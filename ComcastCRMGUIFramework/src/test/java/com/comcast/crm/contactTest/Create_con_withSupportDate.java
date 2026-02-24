package com.comcast.crm.contactTest;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class Create_con_withSupportDate 
{
		@Test(groups="regressionTest")
		public void create_con_supportDate() throws Throwable
		{
		//public static void main(String[] args) throws Throwable {
			FileUtility flib = new FileUtility();
			ExcelUtility elib = new ExcelUtility();
			JavaUtility javaLib = new JavaUtility();
			
			String url = flib.getDataFromPropertiesFile("url");
			String browser = flib.getDataFromPropertiesFile("browser");
			String user = flib.getDataFromPropertiesFile("user");
			String password = flib.getDataFromPropertiesFile("password");
		
			
			String lastname = elib.getDataFromExcel("contact", 4, 2)+javaLib.getRandomNumber();
						
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

			String startDate = javaLib.getSystemDateYYYYDDMM();
			String endDate = javaLib.requireDateYYYDDMM(30);
		
			
		
			
			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
			driver.findElement(By.name("support_start_date")).sendKeys(startDate);
			driver.findElement(By.name("support_end_date")).clear();
			driver.findElement(By.name("support_end_date")).sendKeys(endDate);
			
			
			
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			Thread.sleep(2000);

					String actualdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
					if(startDate.equals(actualdate))
					{
						System.out.println(actualdate+" is verified");
					}
					else
					{
						System.out.println(actualdate+"is not verified");
					}
					
					String afterdate = driver.findElement(By.id("dtlview_Support End Date")).getText();
					if(endDate.equals(afterdate))
					{
						System.out.println(afterdate+" is verified");
					}
					else
					{
						System.out.println(afterdate+"is not verified");
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
