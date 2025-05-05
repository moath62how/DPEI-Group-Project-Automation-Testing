package claim;

import base.BaseTest;
import com.orangehrm.page.claim.ViewClaimsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ViewClaimTest extends BaseTest {
	ViewClaimsPage viewClaimsPage;
	@BeforeMethod
	public void login ()  {
		loginPage.login("Admin","admin123");
		var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By ClaimLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[11]/a");
		// Wait and click cause this caused me headache
		wait.until(ExpectedConditions.visibilityOfElementLocated(ClaimLocator)).click();
		System.out.println("Test");
		viewClaimsPage = new ViewClaimsPage();
	}
	@Test
	public void invalidSearchQuery()  {
		viewClaimsPage.searchQuery("","2022-1-1","2021-1-1","","Past Employees Only","Accommodation","Approved");
		Assert.assertTrue(viewClaimsPage.isDateErrorDisplayed());
	}
	@Test
	public void validateNumberOfRowsResults(){
		//We don't have an actual static number that we could depend on so we will check if they are bigger than 0  P.S:i hate this site
		Assert.assertTrue(viewClaimsPage.getNumberOfQueryResults() >= 1) ;
	}
}
