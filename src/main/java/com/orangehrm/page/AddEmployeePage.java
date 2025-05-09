package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage extends BasePage {
	private final By firstNameInputLocator = By.name("firstName");
	private final By lastNameInputLocator = By.name("lastName");
	private final By saveButtonLocator = By.xpath("//button[text()=' Save ']");

	public AddEmployeePage(WebDriver driver) {
		super.driver = driver; // Set the protected driver from BasePage
	}

	public AddEmployeePage enterFirstName(String firstName) {
		set(firstNameInputLocator, firstName);
		return this; // Enable method chaining
	}

	public AddEmployeePage enterLastName(String lastName) {
		set(lastNameInputLocator, lastName);
		return this;
	}

	public void clickSaveButton() {
		click(saveButtonLocator);
	}
}