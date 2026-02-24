package practiceTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

import junit.framework.Assert;

@Listeners(com.comcast.crm.listenerutility.ListenersPractice.class)
public class InvoiceTest extends BaseClass
{
	//@Test(retryAnalyzer = com.comcast.crm.listenerutility.RetryListenerImp.class)
	@Test
	public void activatesim()
	{
		System.out.println("Excecute createInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
//	@Test
//	public void createInvoicewithcontactTest()
//	{
//		System.out.println("Excecute create invoice with contact Test");
//		System.out.println("Step-1");
//		System.out.println("Step-2");
//		System.out.println("Step-3");
//		System.out.println("Step-4");
//	}
}
