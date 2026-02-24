package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizationpage
{
	WebDriver driver;
	public  Organizationpage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement create_new_org;
	
	@FindBy(name = "search_text")
	private WebElement searchEdt;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchdropdown; 
	
	@FindBy(name="submit")
	private WebElement searchbutton;
	
	public WebElement getSearchbutton() {
		return searchbutton;
	}



	public WebElement getSearchEdt() {
		return searchEdt;
	}

	

	public WebElement getSearchdropdown() {
		return searchdropdown;
	}

	

	public WebElement getCreate_new_org() {
		return create_new_org;
	}
	
}
