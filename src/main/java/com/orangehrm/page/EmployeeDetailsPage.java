package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmployeeDetailsPage extends BasePage {
	private final By employeeIdInputLocator = By.cssSelector("input[name='employeeId']");
	private final By employeeListLinkLocator = By.xpath("//a[text()='Employee List']");

	public EmployeeDetailsPage(WebDriver driver) {
		super.driver = driver; // Set the protected driver from BasePage
	}

	public String getEmployeeId() {
		return find(employeeIdInputLocator).getAttribute("value");
	}

	public EmployListPage navigateToEmployeeList() {
		click(employeeListLinkLocator);
		return new EmployListPage(driver);
	}
}