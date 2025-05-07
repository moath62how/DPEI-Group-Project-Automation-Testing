package com.orangehrm.page;

import com.base.BasePage;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class RecruitmentPage extends BasePage {
	private final By jobTitleField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div");
	private final By vacancyField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div");
	private final By hiringManagerField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div");
	private final By statusField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div");
	private final By candidateNameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[1]/div/div[2]/div/div/input");
	private final By keywordsField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[2]/div/div[2]/input");
	private final By dateOfAppFieldFrom = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[3]/div/div[2]/div/div/input");
	private final By dateOfAppFieldTo = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[4]/div/div[2]/div/div/input");
	private final By dateErrorMsg = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/div/div[4]/div/span");
	private final By methodOfAppField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/div/div/div/div[2]/div/div");
	private final By searchButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[4]/button[2]");
	private final By resetButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[4]/button[1]");
	private final By rowsDateOfApp = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[5]/div");
	private final By numberOfRecordsSpan = By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
	private  final By addNewCandidatesBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button/i");
	private final By firstNameInputField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input");
	private final By lastNameInputField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input");
	private final By middleNameInputField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[2]/div[2]/input");
	private final By emailInputField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input");
	private final By saveBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[8]/button[2]");
	private final By recruitmentBtn = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[5]/a");
	public void searchQuery(String jobTitle, String vacancy, String status, String candidateName, String dateOfAppFrom, String dateOfAppTo, String hiringManager, String methodOfApp, String keywords) {
		setDropDown(jobTitleField, jobTitle);
		setDropDown(vacancyField, vacancy);
		setDropDown(hiringManagerField, hiringManager);
		setDropDown(statusField, status);
		setHintField(candidateNameField, candidateName);
		set(dateOfAppFieldFrom, dateOfAppFrom);
		set(dateOfAppFieldTo, dateOfAppTo);
		setDropDown(methodOfAppField, methodOfApp);
		set(keywordsField, keywords);
		click(searchButton);
	}

	public ArrayList<String> getDateOfResultRows() {
		ArrayList<String> datesOfApp = new ArrayList<>();
		for (var e : findElements(rowsDateOfApp)) {
			datesOfApp.add(e.getText());
		}
		return datesOfApp;
	}

	public void addCandidate(String firstName, String middleName, String lastName, String email) {
		click(addNewCandidatesBtn);
			set(firstNameInputField,firstName);
			set(middleNameInputField,middleName);
			set(lastNameInputField,lastName);
			set(emailInputField,email);
			click(saveBtn);
			click(recruitmentBtn);
	}

	public int getNumberOfRecords() {

		String str = find(numberOfRecordsSpan).getText();
		if (str.equalsIgnoreCase("No Records Found")) {
			return 0;
		}
		String numberStr = str.replaceAll("\\D+", "");
		return Integer.parseInt(numberStr);
	}

	public boolean isDateErrorMsgDisplayed() {
		try {
			return find(dateErrorMsg).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
