package com.comcast.contacttest;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.CreatingNewContactPage;
import com.comcast.crm.objectrepository.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.Organizationpage;
import com.crm.generic.baseutility.BaseClass;

import junit.framework.Assert;

/**
 * @author achut
 */
public class CreateContactTest extends BaseClass
{
	
	@Test(groups="smokeTest")
	public void createContact() throws EncryptedDocumentException, IOException
	{
		System.out.println("create contact");
		String lastname = elib.getDataFromExcel("contact", 4,2 )+jLib.getRandomNumber();
	
		
		HomePage home = new HomePage(driver);
		home.getContact().click();
		
		ContactPage cpage = new ContactPage(driver);
		cpage.getCreatenewcontactbutton().click();
		
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createCon(lastname);
		ccp.getSaveButton().click();
		
		String actHeader = cpage.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastname);
		Assert.assertEquals(status, true);
		
		String actlastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actlastName, lastname);
		soft.assertAll();
		
		
	}
	
	@Test(groups="regressionTest")
	public void createContactWithsupportDate() throws EncryptedDocumentException, IOException, InterruptedException
	{

		String lastname = elib.getDataFromExcel("contact", 4, 2)+jLib.getRandomNumber();
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.requireDateYYYDDMM(30);
		
		HomePage hp = new HomePage(driver);
		hp.getContact().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreatenewcontactbutton().click();
		
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.createCon(lastname, startDate, endDate);
		Thread.sleep(2000);
		//cnp.getSaveButton().click();
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

	}
	
	@Test(groups="regressionTest")
	public void createContactwithorgTest()throws Throwable
	{
		
		String data = elib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
		String shipping = elib.getDataFromExcel("org", 1, 3);
		
		
		HomePage home = new HomePage(driver);
		home.getOrgLink().click();

		Organizationpage org = new Organizationpage(driver);
		org.getCreate_new_org().click();
		
		
		CreatingNewOrganizationPage neworg = new CreatingNewOrganizationPage(driver);
		neworg.createOrg(data, shipping);
		
		
		Thread.sleep(2000);
		home.getContact().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreatenewcontactbutton().click();
		
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		
		String lastname = elib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNumber();
		cnp.createCon(lastname);
		Thread.sleep(2000);
		cnp.getSelectOrg_icon().click();
		
		Thread.sleep(2000);
		String parent = driver.getWindowHandle();
		
		
		wLib.switchNewTabOnURL(driver,"module=Accounts");
		
		cnp.getOrgsearchfield().sendKeys(data);
		cnp.getSearchButton().click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='"+data+"']")).click();
		driver.switchTo().window(parent);
		WebElement savebutt = cnp.getSaveButton();
		wLib.scrollByElement(driver, savebutt);
		cnp.getSaveButton().click();
		//cnp.CreateorgType();
		//cnp.getSaveButton().click();
		
//				String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//				if(headerInfo.contains(orgname))
//				{
//					System.out.println(orgname+" is Verified");
//				}
//				else
//				{
//					System.out.println(orgname+"is  Failed");
//				}	
//				
				String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actOrgname.trim().equals(lastname))
				{
					System.out.println(lastname+"is created sucessfully");
				}
				else
				{
					System.out.println(lastname+"is not created");
				}
	}

	}