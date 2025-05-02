package com.base;


import org.openqa.selenium.*;

public class BasePage {
	protected WebDriver driver;

	public WebElement find(By locator){
		return driver.findElement(locator);
	}

}
