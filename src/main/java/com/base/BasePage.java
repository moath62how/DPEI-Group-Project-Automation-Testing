package com.base;

import java.time.Instant;
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected List<WebElement> findElements(By locator){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return driver.findElements(locator);
	}

	protected void set(By locator, String text) {
		WebElement element = find(locator);
		element.click();
		try {
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
			element.clear();

			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);

			Thread.sleep(200);
			element = find(locator);
			element.sendKeys(text);
		} catch (StaleElementReferenceException e) {
			element = find(locator);
			element.sendKeys(text);
		} catch (Exception ignored) {
		}
	}

	protected void click(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
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

	public void uploadFile(By fileInputLocator, String imagePath) {
		WebElement fileInput = find(fileInputLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", fileInput);
		fileInput.sendKeys(imagePath);
	}
	public void waitUntilTextIsNot(By locator, String baseText){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(driver1 -> {
			String text = driver1.findElement(locator).getText();
			return !text.equals(baseText);
		});
	}
	public boolean isNewTabOpenedAfterAction(By locator){
		Set<String> originalTabs = driver.getWindowHandles();
		click(locator);
		boolean tabOpened = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			tabOpened = wait.until(d -> d.getWindowHandles().size() > originalTabs.size());
		} catch (TimeoutException e) {
			tabOpened = false;
		}
		return tabOpened;
	}
}