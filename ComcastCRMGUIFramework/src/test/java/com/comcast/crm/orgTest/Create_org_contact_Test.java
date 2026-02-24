package com.comcast.crm.orgTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class Create_org_contact_Test 
{
	@Test(groups="regressionTest")
	public void Create_org_contact() throws Throwable
	{
	//public static void main(String[] args) throws Throwable {
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility javaLib = new JavaUtility();
	String url =fLib.getDataFromPropertiesFile("url");
	String browser = fLib.getDataFromPropertiesFile("browser");
	String user = fLib.getDataFromPropertiesFile("user");
	String password = fLib.getDataFromPropertiesFile("password");

	

	String data = eLib.getDataFromExcel("org", 1, 2)+javaLib.getRandomNumber();
	String shipping = eLib.getDataFromExcel("org", 1, 3);
	String industry = eLib.getDataFromExcel("org", 1, 4);
	String type = eLib.getDataFromExcel("org", 1, 5);
	String contact = eLib.getDataFromExcel("org", 1, 6);
	
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
	
	driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(data);
	
	WebElement webele = driver.findElement(By.name("industry"));
	Select sel1 = new Select(webele);
	sel1.selectByVisibleText(industry);
	
	WebElement webele1 = driver.findElement(By.name("accounttype"));
	Select sel2 = new Select(webele1);
	sel2.selectByVisibleText(type);
	
	driver.findElement(By.id("phone")).sendKeys(contact);
	driver.findElement(By.xpath("//textarea[@ name='ship_street']")).sendKeys(shipping);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	Thread.sleep(2000);
			//verify the industries and type info
			String contact_val = driver.findElement(By.id("dtlview_Phone")).getText();
			if(contact_val.equals(contact))
			{
				System.out.println(contact+" is verified");
			}
			else
			{
				System.out.println(contact+"is not verified");
			}

//	WebElement ele  = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='themes/softed/images/user.PNG']")));
	Thread.sleep(2000);
	
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	
	
	driver.quit();
}
}
