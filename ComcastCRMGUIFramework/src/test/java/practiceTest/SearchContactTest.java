package practiceTest;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepository.LoginPage;
import com.crm.generic.baseutility.BaseClass;

/**
 * test class for contact module
 * @author kavya
 */

public class SearchContactTest extends BaseClass
{
	/**
	 * Scenario:login()==>navigateContact++>createContact()==verify
	 */
	@Test
	public void searchContactTest()
	{
		/*Log in to app*/
		LoginPage lp = new LoginPage(driver);
		lp.LoginToapp("url", "username", "password");;
	}
}
