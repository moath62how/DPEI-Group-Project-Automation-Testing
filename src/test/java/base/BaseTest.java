package base;

import com.base.BasePage;
import com.orangehrm.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


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
