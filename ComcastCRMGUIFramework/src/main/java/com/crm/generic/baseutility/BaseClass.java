package com.crm.generic.baseutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;

public class BaseClass 
{
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	public FileUtility fLib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS()
	{
		System.out.println("connect to db");
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(/*groups = {"smokeTest","regressionTest"}*/)
	public void congifBC() throws Throwable
	{
		System.out.println("Launch a browser");
		String Browser = fLib.getDataFromPropertiesFile("browser");
		
		if(Browser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(Browser.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		sdriver = driver;
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable
	{
		System.out.println("login");
		String url = fLib.getDataFromPropertiesFile("url");
		String username = fLib.getDataFromPropertiesFile("user");
		String password = fLib.getDataFromPropertiesFile("password");
		LoginPage login = new LoginPage(driver);
		login.LoginToapp(url, username,password);
	}
	
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("logout");
		HomePage homepage = new HomePage(driver);
		homepage.logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void congifAC()
	{
		System.out.println("Close the browser");
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS()
	{
		System.out.println("close db,Report backup");
	}
	
	
}
