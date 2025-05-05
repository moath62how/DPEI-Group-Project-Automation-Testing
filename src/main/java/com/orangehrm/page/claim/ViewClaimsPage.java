package com.orangehrm.page.claim;

import com.base.BasePage;
import org.openqa.selenium.By;

public class ViewClaimsPage extends BasePage {
	private final By eventNameDropDown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");
	private final By statusDropDown = By.cssSelector("#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div.oxd-table-filter > div.oxd-table-filter-area > form > div:nth-child(1) > div > div:nth-child(4) > div > div:nth-child(2) > div > div");
	private final By includeDropDown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/div/div[3]/div/div[2]/div/div/div[1]");
	private final By fromDateField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input");
	private final By toDateField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/div/div/input");
	private final By employeeNameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input");
	private final By referenceIdField = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input");
	private final By resetButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[3]/button[1]");
	private final By searchButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[2]/form/div[3]/button[2]");
	private final By searchResultNumber = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div[2]/div/span");
	private final By dateErrorMessage = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");

	public void searchQuery(String employeeName, String fromDate, String toDate, String referenceId, String include, String eventName, String status) {
		setHintField(employeeNameField, employeeName);
		set(fromDateField, fromDate);
		set(toDateField,toDate);
		set(referenceIdField,referenceId);
		setDropDown(eventNameDropDown,eventName);
		setDropDown(statusDropDown,status);
		setDropDown(includeDropDown,include);
		click(searchButton);

	}
	public int getNumberOfQueryResults(){
		String str  = find(searchResultNumber).getText();
		String numberStr = str.replaceAll("\\D+", "");
		return Integer.parseInt(numberStr);
	}
	public boolean isDateErrorDisplayed(){
		try {
			return find(dateErrorMessage).isDisplayed();
		} catch (Exception ignored) {
			return false;
		}
	}

}
