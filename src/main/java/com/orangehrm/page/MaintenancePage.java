package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;

public class MaintenancePage extends BasePage {
    private final By maintenanceTab = By.xpath("//span[normalize-space()='Maintenance']");
    private final By accessPassword = By.xpath("//input[@name='password']");
    String password = "admin123";
    private final By confirmButton = By.xpath("//button[@type='submit']");
    private final By cancelAccessButton = By.xpath("//button[@type='button']");

    private final By purgeRecords = By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']");
    private final By employeeRecords = By.xpath("//a[normalize-space()='Employee Records']");
    private final By candidateRecords = By.xpath("//a[normalize-space()='Candidate Records']");

    private final By searchTextarea = By.xpath("//input[@placeholder='Type for hints...']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By purgeAllButton = By.xpath("//button[normalize-space()='Purge All']");

    private final By validNotify = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");

    private final By accessRecords = By.xpath("//a[@class='oxd-topbar-body-nav-tab-item']");
    private final By downloadButton = By.xpath("//button[normalize-space()='Download']");

    public void navigateToMaintenance() {
        click(maintenanceTab);
    }

    public void updateAccessPassword(){
        set(accessPassword, password);
    }
    public void confirmAccess(){
        click(confirmButton);
    }
    public void cancelAccess(){
        click(cancelAccessButton);
    }

    public void isReAuthenticateDone(){
        click(purgeRecords);
    }

    public void goToPurgeEmployeeRecords(){
        click(purgeRecords);
        click(employeeRecords);
    }

    public void searchAboutPastEmployee(String pastEmployeeName){
        set(searchTextarea, pastEmployeeName);
        click(searchButton);
    }

    public boolean isEmployeeNotFound(){
        return find(validNotify).getText().contains("Invalid");
    }

    public void goToPurgeCandidateRecords(){
        click(purgeRecords);
        click(candidateRecords);
    }

    public void searchAboutVacancyCandidate(String candidate){
        set(searchTextarea, candidate);
        click(searchButton);
    }

    public boolean isCandidateFound(){
        return find(purgeAllButton).isDisplayed();
    }

}
