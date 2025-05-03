import base.BaseTest;
import com.orangehrm.page.DashboardPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {
	@Test
	public void testValidLogin(){
		DashboardPage dashboardPage = loginPage.login("Admin","admin123");
		assertEquals(dashboardPage.getUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
	}
	@Test
	public void testValidLoginWithLeadingSpace()  {
		loginPage.login("   Admin","admin123");
		assertTrue(loginPage.isAlertDisplayed());
	}
	@Test
	public void testInvalidLogin(){
		loginPage.login("test","test1230");
		assertTrue(loginPage.isAlertDisplayed());
	}
}
