package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random random = new Random();
		int randomNumber = random.nextInt(1000000);
		return randomNumber;
	}
	public String getSystemDateYYYYDDMM()
	{
		Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
	}
	public String requireDateYYYDDMM(int days)
	{
		Date dateobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = sim.getCalendar();
		cal.setTime(dateobj);
		cal.add(Calendar.DAY_OF_MONTH,days);
		String reqDate = sim.format	(cal.getTime());
		return reqDate;
	}
}
