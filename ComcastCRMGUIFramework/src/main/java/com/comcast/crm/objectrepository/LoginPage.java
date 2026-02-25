package com.comcast.crm.objectrepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author achut
 contains Login page elements & business lib like login()
 */
public class LoginPage extends WebDriverUtility
{
	WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//create a separate class
	//object creation
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name ="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement submitButton; //3:Object initialization


	@FindBy(name="org")
	private WebElement org;
	
	//4.Object Encapsulation
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	/**
	 * Log in to application based on user name password 
	 * @param url
	 * @param username
	 * @param password
	 */
	//BusinessLibrary
	public void LoginToapp(String url,String username,String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		submitButton.click();
	}
	
	
	
}
