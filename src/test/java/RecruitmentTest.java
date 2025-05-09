import base.BaseTest;
import com.orangehrm.page.RecruitmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecruitmentTest extends BaseTest {
	RecruitmentPage recruitmentPage;
	//This test cases might fail because the site has a weired button that disappears and appears in random
	@BeforeMethod
	public void login() {
		loginPage.login("Admin", "admin123");
		var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By recruitmentLocator = By.xpath("//span[normalize-space()='Recruitment']");

		// Wait and click cause this caused me headache
		wait.until(ExpectedConditions.visibilityOfElementLocated(recruitmentLocator)).click();
		recruitmentPage = new RecruitmentPage();
	}

	@Test
	public void validateFailDateInFuture() {
		recruitmentPage.searchQuery("", "", "", "", "2028-1-1", "", "", "", "");
		Assert.assertTrue(recruitmentPage.isDateErrorMsgDisplayed(), "Should throw an error when the from date hasn't came yet");
	}

	@Test
	public void validateThatEmptyQueryIsTheSameAsNoQuery() {
		int numberOfRowsBeforeClickingSearch = recruitmentPage.getNumberOfRecords();
		recruitmentPage.searchQuery("", "", "", "", "", "", "", "", "");
		int numberOfRowsAfterClickingSearch = recruitmentPage.getNumberOfRecords();
		Assert.assertEquals(numberOfRowsAfterClickingSearch,numberOfRowsBeforeClickingSearch,"The number should stay the same when there are no filters selected");
	}
	@Test
	public void validateDateFilters(){
	String fromDateStr = "2022-1-1" , toDateStr = "2024-1-1";
		recruitmentPage.searchQuery("", "", "", "",fromDateStr , toDateStr, "", "", "");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-d-M");
		boolean isDateValid = true;
		LocalDate fromDate = LocalDate.parse(fromDateStr, formatter);
		LocalDate toDate = LocalDate.parse(toDateStr, formatter);
		for(String date : recruitmentPage.getDateOfResultRows()){
			LocalDate currentDate = LocalDate.parse(date, formatter);
			if (currentDate.isBefore(fromDate) || currentDate.isAfter(toDate)) {
				isDateValid = false;
				break;
			}
		}
		Assert.assertTrue(isDateValid);
	}
	@Test
	public void validateAddCandidateWithGoodData(){
		final int numberOfRecBefore = recruitmentPage.getNumberOfRecords();
		recruitmentPage.addCandidate("Moaz","Hussam","Syam","moarth@north.com");
		final int numberOfRecAfter = recruitmentPage.getNumberOfRecords();
		Assert.assertEquals(numberOfRecBefore + 1 , numberOfRecAfter);
	}
}
