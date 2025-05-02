package com.base;


import org.openqa.selenium.*;

public class BasePage {
	protected WebDriver driver;
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	public WebElement find(By locator){
		return driver.findElement(locator);
	}
	protected void set(By locator, String text) {
		find(locator).clear();
		find(locator).sendKeys(text);
	}

	protected void click(By locator) {
		find(locator).click();
	}
}
