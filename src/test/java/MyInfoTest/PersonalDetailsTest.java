package MyInfoTest;

import base.BaseTest;
import com.orangehrm.page.MyInfo.PersonalDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.time.Duration;
import org.testng.annotations.*;

public class PersonalDetailsTest extends BaseTest {
	private PersonalDetailsPage personalDetailsPage;
	@BeforeMethod
	public void login ()  {
		loginPage.login("Admin","admin123");
		var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By myInfoLocator = By.xpath("//span[normalize-space()='My Info']");

		// Wait and click cause this caused me headache
		wait.until(ExpectedConditions.visibilityOfElementLocated(myInfoLocator)).click();
		personalDetailsPage = new PersonalDetailsPage();
	}
	@Test
	public void testEmptyLastNameValidation(){
		personalDetailsPage.updateEmployeeName("face","sad","");
		personalDetailsPage.savePersonalDetailsData();
		Assert.assertTrue(personalDetailsPage.isRequiredMsgDisplayed());
	}
	@Test
	public void testInvalidLicenseData()  {
		personalDetailsPage.updateDriverLicenseInfo("NOT_EVEN_A_NUMBER","2022-0-0");
		personalDetailsPage.savePersonalDetailsData();
		Assert.assertFalse(personalDetailsPage.isPopupAlertDisplayed());
	}

}
