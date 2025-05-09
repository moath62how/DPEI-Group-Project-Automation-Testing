package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PimPage extends BasePage {
	private final By pimMenuLocator = By.xpath("//span[text()='PIM']/parent::a");
	private final By addButtonLocator = By.xpath("//button[text()=' Add ']");

	public PimPage(WebDriver driver) {
		super.driver = driver; // Set the protected driver from BasePage
	}

	public PimPage navigateToPIM() {
		click(pimMenuLocator);
		return this;
	}

	public AddEmployeePage clickAddButton() {
		click(addButtonLocator);
		return new AddEmployeePage(driver);
	}
}