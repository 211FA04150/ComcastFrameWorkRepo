package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage 
{
	WebDriver driver = null;
	public CreatingNewContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastname;
	
 
	
	@FindBy(xpath = "//img[@title='Select']")
	private WebElement selectOrg_icon;
	
	@FindBy(id="search_txt")
	private WebElement orgsearchfield;
	
	@FindBy(name="search")
	private WebElement searchButton;
	
	

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	
	@FindBy(name="support_start_date")
	public WebElement startDate;
	
	@FindBy(name = "support_end_date")
	public WebElement endDate;
	
	
	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getSelectOrg_icon() {
		return selectOrg_icon;
	}
	
	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getOrgsearchfield() {
		return orgsearchfield;
	}
	
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void createCon(String lastName)
	{
		lastname.sendKeys(lastName);
		
	}
	
	public void createCon(String lastName,String startdate,String enddate)
	{
		lastname.sendKeys(lastName);
		startDate.sendKeys(startdate);
		endDate.sendKeys(enddate);
		saveButton.click();	
	}
	public void senddata(String data)
	{
		orgsearchfield.sendKeys(data);
		searchButton.click();
		
	}
}
