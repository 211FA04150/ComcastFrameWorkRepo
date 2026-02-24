package com.crm.comcast.orgtest;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganizationInfoPage;
import com.comcast.crm.objectrepository.Organizationpage;
import com.crm.generic.baseutility.BaseClass;

//@Listeners(com.comcast.crm.listenerutility.ListenerImplementation.class)
public class CreateOrgTest extends BaseClass
{
	@Test(groups="smokeTest")
	public void createOrg() throws EncryptedDocumentException, IOException
	{
		String data = elib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
		String shipping = elib.getDataFromExcel("org", 1, 3);
	
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
	}
	

	@Test(groups="regressionTest")
	public void create_org_withcontact() throws EncryptedDocumentException, IOException
	{
		
		String data = elib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
		String shipping = elib.getDataFromExcel("org", 1, 3);
		String industry = elib.getDataFromExcel("org", 1, 4);
		String type = elib.getDataFromExcel("org", 1, 5);
		String contact = elib.getDataFromExcel("org", 1, 6);
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		Organizationpage org = new Organizationpage(driver);
		org.getCreate_new_org().click();
		
		CreatingNewOrganizationPage cnp = new CreatingNewOrganizationPage(driver);
		cnp.createOrg(data, shipping, contact);
		
		String contact_val = driver.findElement(By.id("dtlview_Phone")).getText();
		if(contact_val.equals(contact))
		{
			System.out.println(contact+" is verified");
		}
		else
		{
			System.out.println(contact+"is not verified");
		}
	}
	
	@Test(groups="regressionTest")
	public void create_org_Industry() throws EncryptedDocumentException, IOException
	{
		String data = elib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
		String shipping = elib.getDataFromExcel("org", 1, 3);
		String type = elib.getDataFromExcel("org", 1, 5);
		
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		Organizationpage org = new Organizationpage(driver);
		org.getCreate_new_org().click();
		
		
		CreatingNewOrganizationPage neworg = new CreatingNewOrganizationPage(driver);
		neworg.CreateorgType();
		neworg.createOrg(data, shipping);
		
		
	
		
		
	}
	


	
}
