package practiceTest;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.comcast.crm.generic.fileutility.ExcelUtility;

public class getProductInfoTest 
{
	
	

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_5szpgfto9i_e&adgrpid=155259813593&hvpone=&hvptwo=&hvadid=674893540034&hvpos=&hvnetw=g&hvrand=18409370857598820051&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9062135&hvtargid=kwd-64107830&hydadcr=14452_2316413&gad_source=1");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER); 
		//System.out.println(driver.findElement(By.xpath("(//span[text()='iPhone 17 Pro 512 GB: 15.93 cm (6.3″) Display with Promotion up to 120Hz, A19 Pro Chip, Breakthrough Battery Life, Pro Fusion Camera System with Center Stage Front Camera; Cosmic Orange']//ancestor::div[@class='puisg-col-inner']//span)[17]")).getText());
		WebElement ele = driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../..//span[@class='a-price']"));
		System.out.println(ele.getText());
	}
	
	@DataProvider
	public Object[][]getData() throws EncryptedDocumentException, IOException
	{
		ExcelUtility eLib = new ExcelUtility();
		int rowcount = eLib.getRowcount("product1");
		Object[][]obj = new Object[rowcount][2];
		
		for(int i =0;i<=rowcount-1;i++)
		{
			obj[i][0] = eLib.getDataFromExcel("product1", i+1, 0);
			obj[i][1] = eLib.getDataFromExcel("product1", i+1, 1);	
		}
		
		
//		obj[0][0]="mobiles";
//		obj[0][1]="iPhone 17 Pro 512 GB: 15.93 cm (6.3″) Display with Promotion up to 120Hz, A19 Pro Chip, Breakthrough Battery Life, Pro Fusion Camera System with Center Stage Front Camera; Cosmic Orange";
//		
//		obj[1][0]="mobiles";
//		obj[1][1]="iPhone 17 Pro Max 256 GB: 17.42 cm (6.9″) Display with Promotion, A19 Pro Chip, Best Battery Life in Any iPhone Ever, Pro Fusion Camera System, Center Stage Front Camera; Deep Blue";
//		
//		obj[2][0]="mobiles";
//		obj[2][1]="iQOO Z10x 5G (Ultramarine, 8GB RAM, 256GB Storage) | 6500 mAh Large Capacity Battery | Dimensity 7300 Processor | Military-Grade Durability";
		return obj;
		
}
}
