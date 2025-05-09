package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DashboardPage extends BasePage {
	private static final Logger LOGGER = Logger.getLogger(DashboardPage.class.getName());

	private final By quickLaunchTitleLocator = By.xpath("//h6[text()='Quick Launch']");
	private final By quickLaunchButtonsTextLocator = By.xpath("//div[@class='orangehrm-quick-launch']/div/div/div/p");
	private final By quickLaunchButtonsLocator = By.xpath("//div[@class='orangehrm-quick-launch']/div/div/div/button");
	private final By employeeDistributionTitleLocator = By.xpath("//h6[text()='Employee Distribution by Sub Unit']");
	private final By latestNewsTitleLocator = By.xpath("//h6[text()='Latest News']");
	private final By timeAtWorkTitleLocator = By.xpath("//h6[text()='Time at Work']");
	private final By myActionsTitleLocator = By.xpath("//h6[text()='My Actions']");
	private final By buzzLatestPostsTitleLocator = By.xpath("//h6[text()='Buzz Latest Posts']");
	private final By employeesOnLeaveTodayTitleLocator = By.xpath("//h6[text()='Employees on Leave Today']");

	public DashboardPage(WebDriver driver) {
		super.driver = driver; // Set the protected driver from BasePage
	}

	public boolean isSectionTitleDisplayed(String sectionName) {
		By locator = switch (sectionName) {
			case "Quick Launch" -> quickLaunchTitleLocator;
			case "Employee Distribution by Sub Unit" -> employeeDistributionTitleLocator;
			case "Latest News" -> latestNewsTitleLocator;
			case "Time at Work" -> timeAtWorkTitleLocator;
			case "My Actions" -> myActionsTitleLocator;
			case "Buzz Latest Posts" -> buzzLatestPostsTitleLocator;
			case "Employees on Leave Today" -> employeesOnLeaveTodayTitleLocator;
			default -> throw new IllegalArgumentException("Unknown section: " + sectionName);
		};
		try {
			return find(locator).isDisplayed();
		} catch (Exception e) {
			LOGGER.warning("Section title not displayed: " + sectionName + ". Error: " + e.getMessage());
			return false;
		}
	}

	public List<String> getQuickLaunchButtonLabels() {
		return findElements(quickLaunchButtonsTextLocator)
				.stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	public DashboardPage clickQuickLaunchButton(String buttonLabel) {
		List<WebElement> buttonsText = findElements(quickLaunchButtonsTextLocator);
		List<WebElement> buttons = findElements(quickLaunchButtonsLocator);
		for (int i = 0; i < buttonsText.size(); i++) {
			if (buttonsText.get(i).getText().equals(buttonLabel)) {
				click(By.xpath(String.format("(//div[@class='orangehrm-quick-launch']/div/div/div/button)[%d]", i + 1)));
				break;
			}
		}
		return this;
	}
}