package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.generic.baseutility.BaseClass;

public class ListenerImplementation implements ITestListener,ISuiteListener
{
	public void onStart(ISuite suite)
	{
		System.out.println("Report configuration");
	}
	
	public void onFinish(ISuite suite)
	{
		System.out.println("Report Backup");
	}
	
	public void onTestStart(ITestResult result)
	{
		System.out.println("======="+result.getMethod().getMethodName()+"===start====");
	}
	
	public void onTestSucess(ITestResult result)
	{
		System.out.println("======="+result.getMethod().getMethodName()+"=====End====");
	}
	
	public void onTestFailure(ITestResult result)
	{
		String testname = result.getMethod().getMethodName();
		WebDriver driver=BaseClass.sdriver;
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String time = new Date().toString().replace(" ","_").replace(":", "_");
		File dest = new File("./screenshot/"+testname+"+"+time+".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		
	}
	
}
