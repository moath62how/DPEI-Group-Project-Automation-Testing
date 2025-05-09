package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class PimPageTest extends BaseTest {

    private final String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String pimUrlContains = "/pim/viewEmployeeList";
    private final String username = "Admin";
    private final String password = "admin123";
    private final String addedEmployeeFirstName = "مريم ";
    private final String addedEmployeeLastName = "نعيم ";
    private String employeeId;

    private LoginPage loginPage;
    private PimPage pimPage;
    private AddEmployeePage addEmployeePage;
    private EmployListPage employeeListPage;
    private EmployeDetailsPage employeeDetailsPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitLonger = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        pimPage = new PimPage(driver);
        addEmployeePage = new AddEmployeePage(driver);
        employeeListPage = new EmployListPage(driver);
        employeeDetailsPage = new EmployeDetailsPage(driver);
        driver.get(loginUrl);
        loginPage.login(username, password);
    }

    @BeforeMethod
    public void navigateToPIM() {
        pimPage.navigateToPIM();
        Assert.assertTrue(driver.getCurrentUrl().contains(pimUrlContains), "فشل التنقل إلى صفحة PIM");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("حدث خطأ أثناء إغلاق برنامج التشغيل: " + e.getMessage());
            } finally {
                driver = null;
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
    }

    @Test
    public void testAddSearchAndDeleteEmployeeById() {
        testAddEmployeeAndGetId();
        testSearchEmployeeByIdAndDelete();
    }

    public void testAddEmployeeAndGetId() {
        pimPage.clickAddButton();
        addEmployeePage.enterFirstName(addedEmployeeFirstName);
        addEmployeePage.enterLastName(addedEmployeeLastName);
        addEmployeePage.clickSaveButton();
        employeeId = employeeDetailsPage.getEmployeeId();
        employeeDetailsPage.navigateToEmployeeList();
    }

    public void testSearchEmployeeByIdAndDelete() {
        employeeListPage.enterEmployeeIdForSearch(employeeId);
        employeeListPage.clickEmployeeSearchButton();
        Assert.assertTrue(employeeListPage.isEmployeeFound(employeeId), "لم يتم العثور على الموظف بالـ ID: " + employeeId);
        employeeListPage.deleteEmployee(employeeId);
        Assert.assertTrue(employeeListPage.isDeletionSuccessful(), "فشل حذف الموظف ذو الـ ID: " + employeeId);
    }
}