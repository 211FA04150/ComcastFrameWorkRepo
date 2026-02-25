package com.comcast.crm.objectrepository;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOrganizationPage 
{
	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	ExcelUtility eLib = new ExcelUtility();
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdit;
	
	@FindBy(id="phone")
	private WebElement contactEdit;
	
	@FindBy(name="org")
	private WebElement org;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverUtility getwLib() {
		return wLib;
	}

	public ExcelUtility geteLib() {
		return eLib;
	}

	public WebElement getContactEdit() {
		return contactEdit;
	}

	

	public WebElement getIndustryEdit() {
		return industryEdit;
	}

	@FindBy(xpath="//textarea[@ name='ship_street']")
	private WebElement shippingEdit;
	
	@FindBy(name="accounttype")
	private WebElement industryEdit;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getShippingEdit() {
		return shippingEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void createOrg(String orgName)
	{
		orgNameEdit.sendKeys(orgName);
		saveButton.click();	
	}
	public void createOrg(String orgName,String shippingaddress)
	{
		orgNameEdit.sendKeys(orgName);
		shippingEdit.sendKeys(shippingaddress);
		saveButton.click();	
	}
	
	public void CreateorgType() throws EncryptedDocumentException, IOException
	{
		WebDriverUtility wLib = new WebDriverUtility();
		String type = eLib.getDataFromExcel("org", 1, 5);
		wLib.select(industryEdit, type);
	}
	
	public void createOrg(String orgName,String shippingaddress,String contact)
	{
		orgNameEdit.sendKeys(orgName);
		shippingEdit.sendKeys(shippingaddress);
		contactEdit.sendKeys(contact);
		saveButton.click();	
	}
	
	
}
