package com.orangehrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class PerformanceKpiPage {
	WebDriver driver;

	// Navigation and menu (updated)
	private By configureTab = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[1]");
	private By kpiMenuItem = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]");

	// Add KPI (updated)
	private By addButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
	private By kpiNameInput = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input");
	private By jobTitleDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]");
	private By accountAssistantOption = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div");  // Assuming this is 'Appium'
	private By saveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");

	// Table and validation (updated)
	private By kpiTableRows = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div");

	// Search functionality (updated)
	private By searchJobTitleDropdown = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/div[1]");
	private By searchJobTitleAccountAssistantOption = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div/div/div[2]/div/div/div[1]");
	private By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");

	// Constructor
	public PerformanceKpiPage(WebDriver driver) {
		this.driver = driver;
	}

	// --- Navigation ---
	public void goToKpiPage() throws InterruptedException {
		driver.findElement(configureTab).click();
		Thread.sleep(1000); // Wait for dropdown to open
		driver.findElement(kpiMenuItem).click();
	}

	// --- Add KPI ---
	public void clickAddButton() {
		driver.findElement(addButton).click();
	}

	public boolean isOnAddKpiPage() {
		return driver.getCurrentUrl().contains("/saveKpi");
	}

	public void enterKpiName(String name) {
		driver.findElement(kpiNameInput).sendKeys(name);
	}

	public void selectJobTitleAccountAssistant() throws InterruptedException {
		driver.findElement(jobTitleDropdown).click();
		Thread.sleep(500);
		driver.findElement(accountAssistantOption).click();
	}

	public void clickSave() {
		driver.findElement(saveButton).click();
	}

	public boolean isRedirectedToKpiListPage() {
		return driver.getCurrentUrl().contains("/searchKpi");
	}

	// --- Search KPI ---
	public void filterByJobTitle(String jobTitle) throws InterruptedException {
		driver.findElement(searchJobTitleDropdown).click();
		Thread.sleep(500);
		driver.findElement(searchJobTitleAccountAssistantOption).click();
	}

	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}

	// --- Verification ---
	public boolean isKpiListed(String kpiName, String jobTitle) {
		List<String> kpiRowTexts = driver.findElements(kpiTableRows)
				.stream()
				.map(row -> row.getText())
				.toList();
		return kpiRowTexts.stream().anyMatch(text -> text.contains(kpiName) && text.contains(jobTitle));
	}
}