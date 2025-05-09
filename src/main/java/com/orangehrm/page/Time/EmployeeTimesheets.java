package com.orangehrm.page.Time;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

public class EmployeeTimesheets extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private String employeeN = "Admin  Admin123";

    public EmployeeTimesheets(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By employeeNameBy = By.xpath("//input[@data-v-75e744cd and @placeholder='Type for hints...']");
    private By viewButtonBy = By.xpath("//button[contains(@class, 'oxd-button') and text()=' View ']");
    private By timePageBy = By.xpath("//span[contains(@class, 'oxd-text oxd-text--span oxd-main-menu-item--name') and text()='Time']");
    private By timesheetsTabBy = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Timesheets')]");
    private By myTimesheetsLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='My Timesheets']");
    private By attendanceTabBy = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Attendance')]");
    private By myRecordsLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='My Records']");
    private By punchInOutLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Punch In/Out']");
    private By employeeRecordsLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Employee Records']");
    private By configurationLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Configuration']");
    private By reportsTabBy = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Reports')]");
    private By projectReportsLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Project Reports']");
    private By employeeReportsLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Employee Reports']");
    private By attendanceSummaryLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Attendance Summary']");
    private By projectInfoTabBy = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Project Info')]");
    private By customersLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Customers']");
    private By projectsLinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Projects']");

    // Generic method to click elements
    private EmployeeTimesheets clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        return this;
    }

    // Methods to interact with elements
    public EmployeeTimesheets clickTimePage() {
        return clickElement(timePageBy);
    }

    public EmployeeTimesheets enterEmployeeName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameBy));
        driver.findElement(employeeNameBy).sendKeys(employeeN);
        return this;
    }

    public EmployeeTimesheets clickViewButton() {
        return clickElement(viewButtonBy);
    }

    public EmployeeTimesheets clickTimesheetsTab() {
        return clickElement(timesheetsTabBy);
    }

    public EmployeeTimesheets clickMyTimesheetsLink() {
        clickTimesheetsTab();
        return clickElement(myTimesheetsLinkBy);
    }

    public EmployeeTimesheets clickAttendanceTab() {
        return clickElement(attendanceTabBy);
    }

    public EmployeeTimesheets clickMyRecordsLink() {
        clickAttendanceTab();
        return clickElement(myRecordsLinkBy);
    }

    public EmployeeTimesheets clickPunchInOutLink() {
        clickAttendanceTab();
        return clickElement(punchInOutLinkBy);
    }

    public EmployeeTimesheets clickEmployeeRecordsLink() {
        clickAttendanceTab();
        return clickElement(employeeRecordsLinkBy);
    }

    public EmployeeTimesheets clickConfigurationLink() {
        clickAttendanceTab();
        return clickElement(configurationLinkBy);
    }

    public EmployeeTimesheets clickReportsTab() {
        return clickElement(reportsTabBy);
    }

    public EmployeeTimesheets clickProjectReportsLink() {
        clickReportsTab();
        return clickElement(projectReportsLinkBy);
    }

    public EmployeeTimesheets clickEmployeeReportsLink() {
        clickReportsTab();
        return clickElement(employeeReportsLinkBy);
    }

    public EmployeeTimesheets clickAttendanceSummaryLink() {
        clickReportsTab();
        return clickElement(attendanceSummaryLinkBy);
    }

    public EmployeeTimesheets clickProjectInfoTab() {
        return clickElement(projectInfoTabBy);
    }

    public EmployeeTimesheets clickCustomersLink() {
        clickProjectInfoTab();
        return clickElement(customersLinkBy);
    }

    public EmployeeTimesheets clickProjectsLink() {
        clickProjectInfoTab();
        return clickElement(projectsLinkBy);
    }
}
