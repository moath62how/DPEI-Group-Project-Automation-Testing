package com.orangehrm.page.MyInfo;

import com.base.BasePage;
import org.openqa.selenium.By;

public class PersonalDetailsPage extends BasePage {

	private final By myInfoTab = By.linkText("My Info");

	private final By employeeFirstName = By.name("firstName");
	private final By employeeMiddleName = By.name("middleName");
	private final By employeeLastName = By.name("lastName");
	private final By employeeId = By.cssSelector("div.orangehrm-edit-employee-content div:nth-child(1) div.oxd-grid-item:nth-child(2) input");
	private final By otherId = By.cssSelector("div.orangehrm-edit-employee-content div:nth-child(1) div.oxd-grid-item:nth-child(3) input");
	private final By driversLicense = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[2]/div[2]/div[1]/div[1]/div[2]/input[1]");
	private final By licenseExpiryDate = By.cssSelector("div.orangehrm-edit-employee-content div:nth-child(2) div.oxd-grid-item:nth-child(2) input");
	private final By nationalityDropdown = By.xpath("//label[text()='Nationality']/following::div[@class='oxd-select-text-input'][1]");

	// Custom Fields section locators
	private final By bloodTypeDropdown = By.xpath("//label[contains(text(),'Blood Type')]/following::div[@class='oxd-select-text-input'][1]");

	// Attachments section locators
	private final By addAttachmentButton = By.xpath("//button[normalize-space()='Add']");
	private final By attachmentFileInput = By.xpath("//input[@type='file']");
	private final By attachmentDescription = By.xpath("//textarea[@placeholder='Enter description']");
	private final By saveAttachmentButton = By.xpath("//button[normalize-space()='Save']");

	// Existing attachments table locators
	private final By attachmentTable = By.className("orangehrm-container");
	private final By firstAttachmentFileName = By.xpath("//div[@class='orangehrm-container']/div[2]/div[1]/div[1]");
	private final By firstAttachmentDeleteButton = By.xpath("//div[@class='orangehrm-container']/div[2]/div[1]//button[1]");

	private final By popUpAlert = By.xpath("//i[@class='oxd-icon bi-check2 oxd-toast-icon']");
	private final By savePersonalDetailsButton = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']");
	private final By requiredErrorMessage = By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");
	public PersonalDetailsPage() {
		this.setDriver(driver);
	}

	// Navigation method
	public void navigateToMyInfo() {
		click(myInfoTab);
	}

	// Personal Details methods
	public void updateEmployeeName(String firstName, String middleName, String lastName) {
		set(employeeFirstName, firstName);
		set(employeeMiddleName, middleName);
		set(employeeLastName, lastName);
	}



	public void updateDriverLicenseInfo(String licenseNumber, String expiryDate) {
		set(driversLicense, licenseNumber);
		set(licenseExpiryDate, expiryDate);
	}

	public void selectNationality(String nationality) {
		click(nationalityDropdown);
		By nationalityOption = By.xpath(String.format("//span[text()='%s']", nationality));
		click(nationalityOption);
	}

	public void selectBloodType(String bloodType) {
		click(bloodTypeDropdown);
		By bloodTypeOption = By.xpath(String.format("//span[text()='%s']", bloodType));
		click(bloodTypeOption);
	}
	public boolean isPopupAlertDisplayed(){
		try{
			return find(popUpAlert).isDisplayed();
		} catch (Exception ignored) {
			return false;
		}
	}
	public void addAttachment(String filePath, String description) {
		click(addAttachmentButton);
		find(attachmentFileInput).sendKeys(filePath);
		set(attachmentDescription, description);
		click(saveAttachmentButton);
	}

	public String getFirstAttachmentFileName() {
		return find(firstAttachmentFileName).getText();
	}
	public boolean isRequiredMsgDisplayed(){
		try{
			return find(requiredErrorMessage).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void deleteFirstAttachment() {
		click(firstAttachmentDeleteButton);
	}
	public void savePersonalDetailsData(){
		click(savePersonalDetailsButton);
	}


	public boolean isAttachmentPresent(String fileName) {
		try {
			By attachment = By.xpath(String.format("//div[contains(@class,'orangehrm-container')]//div[contains(text(),'%s')]", fileName));
			return find(attachment).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}