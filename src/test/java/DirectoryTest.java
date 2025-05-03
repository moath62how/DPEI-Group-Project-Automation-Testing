import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DirectoryTest extends BaseTest {

    WebDriver driver;
    @BeforeTest
    public void loginToSystem() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @BeforeClass
    public void setup() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory");
    }

    @Test
    public void testDropdownSelection() {
        WebElement jobDropdown = driver.findElement(By.xpath("//label[text()='Job Title']/following::div[1]"));
        jobDropdown.click();
        WebElement option = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Software Engineer']"));
        option.click();

        WebElement selectedValue = driver.findElement(By.xpath("//label[text()='Job Title']/following::div[1]//div[@class='oxd-select-text-input']"));
        String actualText = selectedValue.getText();
        Assert.assertEquals(actualText, "Software Engineer", "Selected value doesn't match!");
        System.out.println("Assertion Passed: Software Engineer was selected successfully.");

        WebElement locationDropdown = driver.findElement(By.xpath("//label[text()='Location']/following::div[1]"));
        locationDropdown.click();
        WebElement locationOption = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Canadian Regional HQ']"));
        locationOption.click();

        WebElement employeeNameField = driver.findElement(By.xpath("//label[text()='Employee Name']/following::input[1]"));
        employeeNameField.sendKeys("Sara");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='Sara  Tencrady']")));
        firstSuggestion.click();

        employeeNameField.sendKeys(Keys.ARROW_DOWN);
        employeeNameField.sendKeys(Keys.ENTER);

        WebElement searchButton = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
        searchButton.click();

        WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean resultDisplayed = false;
        boolean noRecordsDisplayed = false;

        try {
            WebElement resultCard = wt.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='orangehrm-directory-card']")));
            resultDisplayed = resultCard.isDisplayed();
        } catch (Exception e) {
            try {
                WebElement noRecords = wt.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='No Records Found']")));
                noRecordsDisplayed = noRecords.isDisplayed();
            } catch (Exception ignored) {}
        }

        Assert.assertTrue(resultDisplayed || noRecordsDisplayed, "Neither results nor 'No Records Found' message is displayed");
        System.out.println("Search completed, either results or 'No Records Found' message is displayed.");

    }

    @Test
    public void testResetButtonFunctionality() {
        WebElement jobDropdown = driver.findElement(By.xpath("//label[text()='Job Title']/following::div[1]"));
        jobDropdown.click();
        WebElement jobOption = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Software Engineer']"));
        jobOption.click();

        WebElement locationDropdown = driver.findElement(By.xpath("//label[text()='Location']/following::div[1]"));
        locationDropdown.click();
        WebElement locationOption = driver.findElement(By.xpath("//div[@role='listbox']//span[text()='Canadian Regional HQ']"));
        locationOption.click();

        WebElement employeeNameField = driver.findElement(By.xpath("//label[text()='Employee Name']/following::input[1]"));
        employeeNameField.sendKeys("Sara");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstSuggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='Sara  Tencrady']")));
        firstSuggestion.click();

        employeeNameField.sendKeys(Keys.ARROW_DOWN);
        employeeNameField.sendKeys(Keys.ENTER);

        WebElement resetButton = driver.findElement(By.xpath("//button[normalize-space()='Reset']"));
        resetButton.click();

        WebElement selectedJob = driver.findElement(By.xpath("//label[text()='Job Title']/following::div[1]//div[@class='oxd-select-text-input']"));
        Assert.assertEquals(selectedJob.getText(), "-- Select --");

        WebElement selectedLocation = driver.findElement(By.xpath("//label[text()='Location']/following::div[1]//div[@class='oxd-select-text-input']"));
        Assert.assertEquals(selectedLocation.getText(), "-- Select --");

        Assert.assertEquals(employeeNameField.getAttribute("value"), "");

        System.out.println("Reset button works as expected and all fields were cleared.");
    }
}