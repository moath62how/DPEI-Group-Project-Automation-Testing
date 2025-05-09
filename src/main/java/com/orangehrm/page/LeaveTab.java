package com.orangehrm.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

public class LeaveTab extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LeaveTab(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By leaveTabBy = By.xpath("//span[contains(@class, 'oxd-main-menu-item--name') and text()='Leave']");
    private By applylinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='Apply']");
    private By myLeavelinkBy = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='My Leave']");
    private By entitlementsTab = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Entitlements')]");
    private By addEntitlementsLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Add Entitlements']");
    private By employeeEntitlementsLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Employee Entitlements']");
    private By myEntitlementsLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='My Entitlements']");
    private By reportsTab = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Reports')]");
    private By leaveEntitlementsUsageReportLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Leave Entitlements and Usage Report']");
    private By myLeaveEntitlementsUsageReportLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='My Leave Entitlements and Usage Report']");
    private By configureTab = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'Configure')]");
    private By leavePeriodLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Leave Period']");
    private By leaveTypesLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Leave Types']");
    private By workWeekLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Work Week']");
    private By holidaysLink = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and text()='Holidays']");
    private By moreTab = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(text(), 'More')]");
    private By leaveListTab = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and contains(text(), 'Leave List')]");
    private By assignLeaveTab = By.xpath("//a[contains(@class, 'oxd-topbar-body-nav-tab-link') and contains(text(), 'Assign Leave')]");

    private LeaveTab clickElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        return this;
    }

    public LeaveTab clickLeaveTab() {
        return clickElement(leaveTabBy);
    }

    public LeaveTab clickApplyLink() {
        return clickElement(applylinkBy);
    }

    public LeaveTab clickMyLeaveLink() {
        return clickElement(myLeavelinkBy);
    }

    public LeaveTab clickEntitlementsTab() {
        return clickElement(entitlementsTab);
    }

    public LeaveTab clickAddEntitlementsLink() {
        clickEntitlementsTab();
        return clickElement(addEntitlementsLink);
    }

    public LeaveTab clickEmployeeEntitlementsLink() {
        clickEntitlementsTab();
        return clickElement(employeeEntitlementsLink);
    }

    public LeaveTab clickMyEntitlementsLink() {
        clickEntitlementsTab();
        return clickElement(myEntitlementsLink);
    }

    public LeaveTab clickReportsTab() {
        return clickElement(reportsTab);
    }

    public LeaveTab clickLeaveEntitlementsUsageReportLink() {
        clickReportsTab();
        return clickElement(leaveEntitlementsUsageReportLink);
    }

    public LeaveTab clickMyLeaveEntitlementsUsageReportLink() {
        clickReportsTab();
        return clickElement(myLeaveEntitlementsUsageReportLink);
    }

    public LeaveTab clickConfigureTab() {
        return clickElement(configureTab);
    }

    public LeaveTab clickLeavePeriodLink() {
        clickConfigureTab();
        return clickElement(leavePeriodLink);
    }

    public LeaveTab clickLeaveTypesLink() {
        clickConfigureTab();
        return clickElement(leaveTypesLink);
    }

    public LeaveTab clickWorkWeekLink() {
        clickConfigureTab();
        return clickElement(workWeekLink);
    }

    public LeaveTab clickHolidaysLink() {
        clickConfigureTab();
        return clickElement(holidaysLink);
    }

    public LeaveTab clickLeaveListTab() {
        return clickElement(leaveListTab);
    }

    public LeaveTab clickAssignLeaveTab() {
        return clickElement(assignLeaveTab);
    }
}