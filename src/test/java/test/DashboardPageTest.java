package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DashboardPageTest {

    WebDriver driver;
    WebDriverWait wait;
    String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    String username = "Admin";
    String password = "admin123";
    DashboardPage dashboardPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(loginUrl);

    }

    @BeforeMethod
    public void loginAndNavigateToDashboard() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Quick Launch']")));
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1)
    public void testDashboardElementsVisibility() {
        Assert.assertTrue(dashboardPage.isQuickLaunchTitleDisplayed(), "عنوان Quick Launch غير معروض.");
        Assert.assertTrue(dashboardPage.isEmployeeDistributionTitleDisplayed(), "عنوان Employee Distribution غير معروض.");
        Assert.assertTrue(dashboardPage.isLatestNewsTitleDisplayed(), "عنوان Latest News غير معروض.");
        Assert.assertTrue(dashboardPage.isTimeAtWorkTitleDisplayed(), "عنوان Time at Work غير معروض.");
        Assert.assertTrue(dashboardPage.isMyActionsTitleDisplayed(), "عنوان My Actions غير معروض.");
        Assert.assertTrue(dashboardPage.isBuzzLatestPostsTitleDisplayed(), "عنوان Buzz Latest Posts غير معروض.");
        Assert.assertTrue(dashboardPage.isEmployeesOnLeaveTodayTitleDisplayed(), "عنوان Employees on Leave Today غير معروض.");
        System.out.println("تم التحقق من ظهور العناصر الأساسية في لوحة المعلومات.");
    }

    @Test(priority = 2)
    public void testQuickLaunchButtonsLabels() {
        List<String> expectedLabels = List.of("Assign Leave", "Leave List", "Timesheets", "Apply Leave", "My Leave", "My Timesheets");
        List<String> actualLabels = dashboardPage.getQuickLaunchButtonLabels();
        Assert.assertEquals(actualLabels, expectedLabels, "أسماء أزرار Quick Launch غير متطابقة.");
        System.out.println("تم التحقق من أسماء أزرار Quick Launch.");
    }

    @Test(priority = 3)
    public void testQuickLaunchButtonsNavigation() {
        dashboardPage.clickQuickLaunchButton("Assign Leave");
        Assert.assertTrue(driver.getCurrentUrl().contains("/leave/assignLeave"), "لم يتم الانتقال إلى صفحة Assign Leave.");
        driver.navigate().back();
        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickQuickLaunchButton("Leave List");
        Assert.assertTrue(driver.getCurrentUrl().contains("/leave/viewLeaveList"), "لم يتم الانتقال إلى صفحة Leave List.");
        driver.navigate().back();
        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickQuickLaunchButton("Timesheets");
        Assert.assertTrue(driver.getCurrentUrl().contains("/time/viewEmployeeTimesheet"), "لم يتم الانتقال إلى صفحة Timesheets.");
        driver.navigate().back();
        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickQuickLaunchButton("Apply Leave");
        Assert.assertTrue(driver.getCurrentUrl().contains("/leave/applyLeave"), "لم يتم الانتقال إلى صفحة Apply Leave.");
        driver.navigate().back();
        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickQuickLaunchButton("My Leave");
        Assert.assertTrue(driver.getCurrentUrl().contains("/leave/viewMyLeaveList"), "لم يتم الانتقال إلى صفحة My Leave.");
        driver.navigate().back();
        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickQuickLaunchButton("My Timesheets");
        Assert.assertTrue(driver.getCurrentUrl().contains("/time/viewMyTimesheet"), "لم يتم الانتقال إلى صفحة My Timesheets.");
        System.out.println("تم التحقق من عمل أزرار Quick Launch.");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
