package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployListPage {

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")
    public WebElement employeeIdSearchField;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    public WebElement employeeSearchButton;
    @FindBy(xpath = "//button[contains(@class, 'oxd-button--label-danger')]")
    public WebElement confirmDeleteButton;
    @FindBy(xpath = "//*[contains(text(), 'Successfully Deleted')]")
    public WebElement successMessage;
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String employeeRowXPath = "//div[@class='oxd-table-cell oxd-padding-cell'][1]//div[text()='%s']/ancestor::div[@class='oxd-table-row oxd-table-row--with-border']";
    private final String deleteButtonXPath = employeeRowXPath + "//div[@class='oxd-table-cell oxd-padding-cell'][9]//button[contains(@class, 'bi-trash')]";

    public EmployListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterEmployeeIdForSearch(String employeeId) {
        wait.until(ExpectedConditions.visibilityOf(employeeIdSearchField)).sendKeys(employeeId);
    }

    public void clickEmployeeSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeSearchButton)).click();
    }

    public WebElement findEmployeeRow(String employeeId) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(employeeRowXPath, employeeId))));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isEmployeeFound(String employeeId) {
        return findEmployeeRow(employeeId) != null;
    }

    public void deleteEmployee(String employeeId) {
        WebElement deleteButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(deleteButtonXPath, employeeId))));
        deleteButtonElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(String.format(employeeRowXPath, employeeId)))); // Wait for the row to disappear
    }

    public boolean isDeletionSuccessful() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
