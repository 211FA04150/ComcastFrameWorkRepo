package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver,WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void switchNewTabOnURL(WebDriver driver,String partialURL)
	{
		Set<String> child = driver.getWindowHandles();
		Iterator<String> itr = child.iterator();
		while(itr.hasNext())
		{
			String winId = itr.next();
			driver.switchTo().window(winId);
			
			String currurl = driver.getCurrentUrl();
			if(currurl.contains(partialURL))
			{
				break;
			}
		}
	}
	public void switchNewTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> child = driver.getWindowHandles();
		Iterator<String> itr = child.iterator();
		while(itr.hasNext())
		{
			String winId = itr.next();
			driver.switchTo().window(winId);
			
			String currurl = driver.getCurrentUrl();
			if(currurl.contains(partialTitle))
			{
				break;
			}
		}
	}
	public void switchFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchFrame(WebDriver driver,String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	public void switchFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	public void switchToAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement element,int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void select(WebElement element,String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void mouseOnElement(WebDriver driver,WebElement ele)
	{
		Actions action = new Actions(driver);
		action.doubleClick(ele).perform();
	}
	public void confAlert(WebDriver driver)
	{
		Alert ale = driver.switchTo().alert();
		ale.accept();
	}
	public void scrollByElement(WebDriver driver,WebElement ele)
	{
		Actions action = new Actions(driver);
		action.scrollToElement(ele);
	}
}
