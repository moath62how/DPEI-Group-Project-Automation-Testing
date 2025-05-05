
import com.orangehrm.page.AdminPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class AdminPageTest {
	WebDriver driver;
	AdminPage adminPage;

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

		// Navigate to Admin page
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");

		adminPage = new AdminPage(driver);
	}

	@Test
	public void testAddNewAdminUser() throws InterruptedException {
		adminPage.clickAddButton();
		Assert.assertTrue(adminPage.isOnAddUserPage(), "Not on Add User page");

		adminPage.selectUserRoleAdmin();
		adminPage.enterEmployeeName("Ranga Akunuri");
		Thread.sleep(1000);  // Add explicit wait if autocomplete is needed

		adminPage.selectStatusEnabled();
		adminPage.enterUsername("adham");
		adminPage.enterPassword("adham123");
		adminPage.clickSave();

		Thread.sleep(3000);  // Wait for save and redirect
		Assert.assertTrue(adminPage.isBackOnUserListPage(), "Did not return to user list page");

		// Final Assertion: Check if user exists in the table
		boolean isUserAdded = adminPage.isUserInTable("adham", "Admin", "Ranga Akunuri", "Enabled");
		Assert.assertTrue(isUserAdded, "User 'adham' was not found in the table");
	}
	@Test
	public void testSearchForESSUser() throws InterruptedException {
		// Search for user "amars"
		adminPage.enterSearchUsername("amars");

		adminPage.selectSearchUserRoleESS();
		adminPage.enterSearchEmployeeName("amar HR");
		Thread.sleep(1000); // Optional: for autocomplete

		adminPage.selectSearchStatusEnabled();
		adminPage.clickSearchButton();

		Thread.sleep(2000); // Wait for table to update

		// Validate result row exists
		boolean isResultPresent = adminPage.isUserSearchResultDisplayed("amars", "ESS", "amar HR", "Enabled");
		Assert.assertTrue(isResultPresent, "Search result for user 'amars' with role 'ESS' not found.");
	}


	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}