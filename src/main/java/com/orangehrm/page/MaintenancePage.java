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
    private final By dropdownSearch = By.cssSelector(".oxd-autocomplete-dropdown.--positon-bottom");
    String baseTextDropdown = "Searching....";
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By tableTitleBar = By.xpath("//div[@role='rowgroup']//div[2]");
    private final By recordsFoundStatus = By.xpath("//span[@class='oxd-text oxd-text--span']");

    private final By purgeAllButton = By.xpath("//button[normalize-space()='Purge All']");
    private final By confirmPurgeAllButton = By.xpath("//button[normalize-space()='Yes, Purge']");
    private final By cancelPurgeAllButton = By.xpath("//button[normalize-space()='No, Cancel']");
    private final By toastText = By.cssSelector(".oxd-toast-content.oxd-toast-content--success']");
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
    }

    public boolean isEmployeeNotFound(){
        waitUntilTextIsNot(dropdownSearch, baseTextDropdown);
        return find(dropdownSearch).getText().contains("No Records Found");
    }

    public void goToPurgeCandidateRecords(){
        click(purgeRecords);
        click(candidateRecords);
    }

    public void searchAboutVacancyCandidate(String candidate){
        set(searchTextarea, candidate);
        waitUntilTextIsNot(dropdownSearch, baseTextDropdown);
        findElements(dropdownSearch).getFirst().click();
        click(searchButton);
    }

    public boolean isSearchDone(){
        return find(tableTitleBar).isDisplayed();
    }

    public void purgeCandidates(){
        click(purgeAllButton);
    }
    public void confirmPurgeCandidates(){
        click(confirmPurgeAllButton);
    }
    public void cancelPurgeCandidates(){
        click(cancelPurgeAllButton);
    }
    public boolean isPurgeAllDone() {
        return find(toastText).getText().contains("Success");
    }

    public void goToAccessRecords(){
        click(accessRecords);
    }
    public void searchAboutEmployee(String employeeName){
        set(searchTextarea, employeeName);
        waitUntilTextIsNot(dropdownSearch, baseTextDropdown);
        findElements(dropdownSearch).getFirst().click();
        click(searchButton);
    }
    public boolean isPersonalDataDownloaded(){
        return isNewTabOpenedAfterAction(downloadButton);
    }
}