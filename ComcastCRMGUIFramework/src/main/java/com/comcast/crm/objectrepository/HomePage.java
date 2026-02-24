package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver ;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contact;
	
	@FindBy(linkText = "Campaigns")
	private WebElement Campaignslink;
	
	@FindBy(linkText = "More")
	private WebElement morelink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signout;
	
	public WebElement getSignout() {
		return adminImg;
	}

	public void setSignout(WebElement signout) {
		this.adminImg = signout;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContact() {
		return contact;
	}

	public WebElement getCampaignslink() {
		return Campaignslink;
	}

	public WebElement getMorelink() {
		return morelink;
	}
	
	public void navigateToCampaignPage()
	{
		Actions action = new Actions(driver);
		action.moveToElement(morelink).perform();
		Campaignslink.click();
	}
	
	public void logout()
	{
		Actions action = new Actions(driver);
		action.moveToElement(adminImg).perform();
		signout.click();
		
	}
	
}
