package com.base;

import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
	protected static WebDriver driver;

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	protected WebElement find(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected List<WebElement> findElements(By locator){
	return driver.findElements(locator);
	}

	protected void set(By locator, String text) {
		var element = find(locator);
		element.click();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.clear();

		((JavascriptExecutor)driver).executeScript(
				"arguments[0].value = '';", element);


		try { Thread.sleep(200); } catch (Exception ignored) {}

		element.sendKeys(text);
	}

	protected void click(By locator) {
		find(locator).click();
	}

	public String getUrl() {
	return	driver.getCurrentUrl();
	}
	protected void setDropDown(By locator,String value){
		click(locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(".//*[text()='" + value + "']"))).click();
	}
	protected void setHintField(By locator,String value){
		set(locator,value);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath(".//*[text()='" + value + "']"))).click();
	}
}
