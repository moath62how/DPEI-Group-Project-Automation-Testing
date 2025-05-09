package com.orangehrm.page.claim;

import com.base.BasePage;
import org.openqa.selenium.By;

public class CreateClaimsPage extends BasePage {
	private final By eventField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div");
	private final By  currencyField= By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div");
	private final By submitButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
	private final By cancelButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[1]");

	public void cancelTheClaim(){
		click(cancelButton);
	}
	public void makeClaim(String event , String currency){
		setDropDown(eventField,event);
		setDropDown(currencyField,currency);
		click(submitButton);
	}
}
