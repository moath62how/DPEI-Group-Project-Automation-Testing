package base;

import Jira.JiraBugReporter;
import com.base.BasePage;
import com.orangehrm.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
	private static final String ORANGE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	protected BasePage basePage;
	protected LoginPage loginPage;
	protected WebDriver driver;

	@BeforeMethod
	public void loadWebsite() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(ORANGE_URL);
		//this page uses some sort of frontend framework so we need to wait to not screw anything
		Thread.sleep(1500);
		basePage = new BasePage();
		basePage.setDriver(driver);
		loginPage = new LoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
