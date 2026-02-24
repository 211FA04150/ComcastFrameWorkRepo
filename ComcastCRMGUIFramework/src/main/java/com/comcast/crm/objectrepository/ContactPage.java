package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage
{
	WebDriver driver ;
	public ContactPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createnewcontactbutton;
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	public WebElement getCreatenewcontactbutton() {
		return createnewcontactbutton;
	}

	
	
}
