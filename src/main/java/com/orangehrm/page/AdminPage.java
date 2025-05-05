package com.orangehrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
	WebDriver driver;

	// Locators
	private By addButton = By.cssSelector(".oxd-button.oxd-button--medium.oxd-button--secondary");
	private By roleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
	private By adminOption = By.xpath("//div[@role='option']//span[text()='Admin']");
	private By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
	private By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
	private By enabledOption = By.xpath("//div[@role='option']//span[text()='Enabled']");
	private By usernameInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By passwordInput = By.xpath("(//input[@type='password'])[1]");
	private By confirmPasswordInput = By.xpath("(//input[@type='password'])[2]");
	private By saveButton = By.xpath("//button[@type='submit']");
	private By userTable = By.cssSelector(".oxd-table-row");
	private By searchUsernameInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[1]");
	private By searchUserRoleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
	private By searchUserRoleESSOption = By.xpath("//div[@role='option']//span[text()='ESS']");
	private By searchEmployeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
	private By searchStatusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
	private By searchStatusEnabledOption = By.xpath("//div[@role='option']//span[text()='Enabled']");
	private By searchButton = By.xpath("//button[@type='submit' and .=' Search ']");

	// Constructor
	public AdminPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAddButton() {
		driver.findElement(addButton).click();
	}

	public boolean isOnAddUserPage() {
		return driver.getCurrentUrl().contains("/saveSystemUser");
	}

	public void selectUserRoleAdmin() {
		driver.findElement(roleDropdown).click();
		driver.findElement(adminOption).click();
	}

	public void enterEmployeeName(String name) {
		driver.findElement(employeeNameInput).sendKeys(name);
		// Optional: wait and press ENTER if a dropdown shows up.
	}

	public void selectStatusEnabled() {
		driver.findElement(statusDropdown).click();
		driver.findElement(enabledOption).click();
	}

	public void enterUsername(String username) {
		driver.findElement(usernameInput).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(confirmPasswordInput).sendKeys(password);
	}

	public void clickSave() {
		driver.findElement(saveButton).click();
	}

	public boolean isBackOnUserListPage() {
		return driver.getCurrentUrl().contains("/viewSystemUsers");
	}

	public boolean isUserInTable(String username, String role, String empName, String status) {
		String xpath = String.format(
				"//div[@class='oxd-table-row']//div[text()='%s']/following-sibling::div[text()='%s']/following-sibling::div[text()='%s']/following-sibling::div[text()='%s']",
				username, role, empName, status
		);
		return driver.findElements(By.xpath(xpath)).size() > 0;
	}
	// --- Actions for search ---
	public void enterSearchUsername(String username) {
		driver.findElement(searchUsernameInput).clear();
		driver.findElement(searchUsernameInput).sendKeys(username);
	}

	public void selectSearchUserRoleESS() {
		driver.findElement(searchUserRoleDropdown).click();
		driver.findElement(searchUserRoleESSOption).click();
	}

	public void enterSearchEmployeeName(String empName) {
		driver.findElement(searchEmployeeNameInput).clear();
		driver.findElement(searchEmployeeNameInput).sendKeys(empName);
	}

	public void selectSearchStatusEnabled() {
		driver.findElement(searchStatusDropdown).click();
		driver.findElement(searchStatusEnabledOption).click();
	}

	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}

	public boolean isUserSearchResultDisplayed(String username, String role, String empName, String status) {
		String xpath = String.format(
				"//div[@class='oxd-table-row']//div[text()='%s']/following-sibling::div[text()='%s']/following-sibling::div[text()='%s']/following-sibling::div[text()='%s']",
				username, role, empName, status
		);
		return driver.findElements(By.xpath(xpath)).size() > 0;
	}

}