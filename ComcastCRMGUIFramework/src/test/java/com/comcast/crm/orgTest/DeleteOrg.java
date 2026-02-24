package com.comcast.crm.orgTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganizationInfoPage;
import com.comcast.crm.objectrepository.Organizationpage;
public class DeleteOrg
{
	public static void main(String[] args) throws Throwable 
	{
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility javaLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility(  );
		String url = fLib.getDataFromPropertiesFile("url");
		String browser = fLib.getDataFromPropertiesFile("browser");
		String user = fLib.getDataFromPropertiesFile("user");
		String password = fLib.getDataFromPropertiesFile("password");
	
		
		
		String data = eLib.getDataFromExcel("org", 10, 2)+javaLib.getRandomNumber();
		
		String shipping = eLib.getDataFromExcel("org", 1, 3);
		
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
		
		LoginPage lp = new LoginPage(driver);
		
		lp.LoginToapp(url,user, password);
		
		
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		Organizationpage org = new Organizationpage(driver);
		org.getCreate_new_org().click();
		
		
		CreatingNewOrganizationPage neworg = new CreatingNewOrganizationPage(driver);
		neworg.getOrgNameEdit().sendKeys(data);
		neworg.getShippingEdit().sendKeys(shipping);
		neworg.getSaveButton().click();
		
		//verify Header message expected result
		OrganizationInfoPage oip =new OrganizationInfoPage(driver);
				String headerInfo = oip.getHeaderMsg().getText();
				if(headerInfo.contains(data))
				{
					System.out.println(data+" is Verified");
				}
				else
				{
					System.out.println(data+"is  Failed");
				}
				//verify Header orgName info expectedResult
				String actOrgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
				if(actOrgname.equals(data))
				{
					System.out.println(data+" is created sucessfully");
				}
				else
				{
					System.out.println(data+"is not created");
				}
				
				//go back to orgpage
				home.getOrgLink().click();
				org.getSearchEdt().sendKeys(data);
				wLib.select(org.getSearchdropdown(),"Organization Name");
				org.getSearchbutton().click();
				//in dynamic element select and delete org
				driver.findElement(By.xpath("//a[text()='"+data+"']/../../td[8]/a[text()='del']")).click();
				Thread.sleep(2000);
				wLib.confAlert(driver);
		
		home.logout();
		driver.quit();
	}
}


