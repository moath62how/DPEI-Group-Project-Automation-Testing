package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeDetailsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By employeeIdElement = By.xpath("//div[@class='orangehrm-edit-employee-id']/div/div[@class='orangehrm-edit-employee-id-input']/div/input");
    private final By employeeListLink = By.xpath("//a[text()='Employee List']");

    public EmployeDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this);
    }

    public String getEmployeeId() {
        WebElement employeeIdWebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdElement));
        return employeeIdWebElement.getAttribute("value");
    }

    public void navigateToEmployeeList() {
        WebElement employeeListLinkElement = wait.until(ExpectedConditions.elementToBeClickable(employeeListLink));
        employeeListLinkElement.click();
    }
}