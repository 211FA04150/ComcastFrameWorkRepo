package com.comcast.crm.contactTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Create_contact_org_test 
{
	@Test(groups="regressionTest")
	public void create_contact_org() throws Throwable
	{
	//public static void main(String[] args) throws Throwable {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility javaLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
	FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
	Properties prop = new Properties();
	prop.load(fis);
	String url = flib.getDataFromPropertiesFile("url");
	String browser = flib.getDataFromPropertiesFile("browser");
	String user =flib.getDataFromPropertiesFile("user");
	String password = flib.getDataFromPropertiesFile("password");
	

	
	String orgname = elib.getDataFromExcel("org", 1, 2)+javaLib.getRandomNumber();
	
	String shipping = elib.getDataFromExcel("org", 1, 3);
	String lastname = elib.getDataFromExcel("contact", 1, 2)+javaLib.getRandomNumber();
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
	driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgname);
	
	
	
	driver.findElement(By.xpath("//textarea[@ name='ship_street']")).sendKeys(shipping);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//verify Header message expected result
			String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(orgname))
			{
				System.out.println(orgname+" is Verified");
			}
			else
			{
				System.out.println(orgname+"is  Failed");
			}	
			
			String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgname.trim().equals(orgname))
			{
				System.out.println(orgname+" is created sucessfully");
			}
			else
			{
				System.out.println(orgname+"is not created");
			}
			
			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			String parent = driver.getWindowHandle();
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastname);
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			//Switch to child window
			wLib.switchNewTabOnURL(driver, "module=Accounts");
			
			driver.findElement(By.name("search_text")).sendKeys(orgname);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
			
			wLib.switchNewTabOnTitle(driver, "Contacts&actions");
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			String headerInfo1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//			System.out.println(headerInfo1);
//			System.out.println(lastname);
			if(headerInfo1.contains(lastname))
			{
				System.out.println(lastname+" is Verified");
			}
			else
			{
				System.out.println(lastname+"is  Failed");
			}
			
			//verify Header orgName info expectedResult
			String actOrgname1 = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgname1.trim().equals(orgname))
			{
				System.out.println(orgname+" is created sucessfully");
			}
			else
			{
				System.out.println(orgname+"is not created");
			}
			Thread.sleep(2000);
			driver.quit();
	}
		
}

