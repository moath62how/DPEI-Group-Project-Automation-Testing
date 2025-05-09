package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class EmployListPage extends BasePage {
	private static final Logger LOGGER = Logger.getLogger(EmployListPage.class.getName());

	private final By employeeIdSearchFieldLocator = By.xpath("//form//input[@placeholder='Type for hints...']");
	private final By employeeSearchButtonLocator = By.xpath("//form//button[@type='submit']");
	private final By confirmDeleteButtonLocator = By.xpath("//button[contains(@class, 'oxd-button--label-danger')]");
	private final By successMessageLocator = By.xpath("//*[contains(text(), 'Successfully Deleted')]");
	private final String employeeRowXPath = "//div[@class='oxd-table-row oxd-table-row--with-border']//div[text()='%s']";
	private final String deleteButtonXPath = employeeRowXPath + "/ancestor::div[@class='oxd-table-row oxd-table-row--with-border']//button[contains(@class, 'bi-trash')]";

	public EmployListPage(WebDriver driver) {
		super.driver = driver; // Set the protected driver from BasePage
	}

	public EmployListPage enterEmployeeIdForSearch(String employeeId) {
		set(employeeIdSearchFieldLocator, employeeId);
		return this;
	}

	public EmployListPage clickEmployeeSearchButton() {
		click(employeeSearchButtonLocator);
		return this;
	}

	public boolean isEmployeeFound(String employeeId) {
		try {
			return find(By.xpath(String.format(employeeRowXPath, employeeId))).isDisplayed();
		} catch (Exception e) {
			LOGGER.warning("Employee row not found for ID: " + employeeId + ". Error: " + e.getMessage());
			return false;
		}
	}

	public EmployListPage deleteEmployee(String employeeId) {
		click(By.xpath(String.format(deleteButtonXPath, employeeId)));
		click(confirmDeleteButtonLocator);
		waitUntilTextIsNot(successMessageLocator, ""); // Wait for success message
		return this;
	}

	public boolean isDeletionSuccessful() {
		try {
			return find(successMessageLocator).isDisplayed();
		} catch (Exception e) {
			LOGGER.warning("Success message not displayed. Error: " + e.getMessage());
			return false;
		}
	}
}