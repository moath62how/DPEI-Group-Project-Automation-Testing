package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DashboardPage {
    @FindBy(xpath = "//h6[text()='Quick Launch']")
    public WebElement quickLaunchTitle;
    @FindBy(xpath = "//div[@class='orangehrm-quick-launch']/div/div/div/p")
    public List<WebElement> quickLaunchButtonsText;
    @FindBy(xpath = "//div[@class='orangehrm-quick-launch']/div/div/div/button")
    public List<WebElement> quickLaunchButtons;
    @FindBy(xpath = "//h6[text()='Employee Distribution by Sub Unit']")
    public WebElement employeeDistributionTitle;
    @FindBy(xpath = "//h6[text()='Latest News']")
    public WebElement latestNewsTitle;
    @FindBy(xpath = "//h6[text()='Time at Work']")
    public WebElement timeAtWorkTitle;
    @FindBy(xpath = "//h6[text()='My Actions']")
    public WebElement myActionsTitle;
    @FindBy(xpath = "//h6[text()='Buzz Latest Posts']")
    public WebElement buzzLatestPostsTitle;
    @FindBy(xpath = "//h6[text()='Employees on Leave Today']")
    public WebElement employeesOnLeaveTodayTitle;
    private final WebDriver driver;
    private final WebDriverWait wait;

    // يمكنك إضافة المزيد من المحددات للعناصر الأخرى في لوحة المعلومات

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public boolean isQuickLaunchTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(quickLaunchTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر Quick Launch: " + e.getMessage());
            return false;
        }
    }

    public List<String> getQuickLaunchButtonLabels() {
        return quickLaunchButtonsText.stream().map(WebElement::getText).toList();
    }

    public void clickQuickLaunchButton(String buttonLabel) {
        for (int i = 0; i < quickLaunchButtonsText.size(); i++) {
            if (quickLaunchButtonsText.get(i).getText().equals(buttonLabel)) {
                quickLaunchButtons.get(i).click();
                break;
            }
        }
    }

    public boolean isEmployeeDistributionTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(employeeDistributionTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر Employee Distribution: " + e.getMessage());
            return false;
        }
    }

    public boolean isLatestNewsTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(latestNewsTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر Latest News: " + e.getMessage());
            return false;
        }
    }

    public boolean isTimeAtWorkTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(timeAtWorkTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر Time at Work: " + e.getMessage());
            return false;
        }
    }

    public boolean isMyActionsTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(myActionsTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر My Actions: " + e.getMessage());
            return false;
        }
    }

    public boolean isBuzzLatestPostsTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(buzzLatestPostsTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر Buzz Latest Posts: " + e.getMessage());
            return false;
        }
    }

    public boolean isEmployeesOnLeaveTodayTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(employeesOnLeaveTodayTitle)).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.err.println("لم يتم العثور على عنصر Employees on Leave Today: " + e.getMessage());
            return false;
        }
    }

    // يمكنك إضافة المزيد من الدوال للتفاعل مع عناصر لوحة المعلومات
}