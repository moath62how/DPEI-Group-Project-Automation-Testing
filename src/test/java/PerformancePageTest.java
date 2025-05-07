
import com.orangehrm.page.PerformanceKpiPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class PerformancePageTest {
	WebDriver driver;
	PerformanceKpiPage kpiPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Login
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		// Navigate to KPI section
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview");

		kpiPage = new PerformanceKpiPage(driver);
	}

	@Test
	public void testAddKpiForJobTitle() throws InterruptedException {
		kpiPage.goToKpiPage();

		kpiPage.clickAddButton();
		Assert.assertTrue(kpiPage.isOnAddKpiPage(), "Not on Add KPI page");

		kpiPage.enterKpiName("adham");
		kpiPage.selectJobTitleAccountAssistant();
		kpiPage.clickSave();

		Thread.sleep(2000); // Wait for redirection
		Assert.assertTrue(kpiPage.isRedirectedToKpiListPage(), "Did not return to KPI list page");

		Assert.assertTrue(kpiPage.isKpiListed("adham", "Account Assistant"),
				"KPI 'adham' for 'Account Assistant' not found.");
	}

	@Test
	public void testSearchKpiByJobTitle() throws InterruptedException {
		kpiPage.goToKpiPage();
		Thread.sleep(2000); // Ensure page is loaded

		kpiPage.filterByJobTitle("Account Assistant");
		kpiPage.clickSearchButton();
		Thread.sleep(2000); // Wait for results to appear

		Assert.assertTrue(kpiPage.isKpiListed("adham", "Account Assistant"),
				"Filtered KPI search did not show 'adham' for 'Account Assistant'.");
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}