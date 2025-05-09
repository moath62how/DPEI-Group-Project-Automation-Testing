package claim;

import base.BaseTest;
import com.orangehrm.page.claim.CreateClaimsPage;
import com.orangehrm.page.claim.ViewClaimsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class CreateClaimTest extends BaseTest {
	CreateClaimsPage createClaimsPage;
	@BeforeMethod
	public void login ()  {
		ViewClaimsPage viewClaimsPage;
		loginPage.login("Admin","admin123");
		var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By ClaimLocator = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[11]/a/span");
		// Wait and click cause this caused me headache
		wait.until(ExpectedConditions.visibilityOfElementLocated(ClaimLocator)).click();
		viewClaimsPage = new ViewClaimsPage();
		createClaimsPage = viewClaimsPage.navigateToCrateAClaim();
	}
	@Test
	public void checkValidPath(){
		createClaimsPage.makeClaim("Accommodation","Egyptian Pound");

		Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("https://opensource-demo.orangehrmlive.com/web/index.php/claim/submitClaim/id/"));
	}
	@Test
	public void tryCancelButton(){
		createClaimsPage.cancelTheClaim();
		Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).equalsIgnoreCase("https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewClaim"));
	}

}
