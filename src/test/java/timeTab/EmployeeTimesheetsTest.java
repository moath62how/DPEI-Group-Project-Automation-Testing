package timeTab;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.page.DashboardPage;
import com.orangehrm.page.Time.EmployeeTimesheets;

import base.BaseTest;

public class EmployeeTimesheetsTest extends BaseTest {
    private EmployeeTimesheets employeeTimesheets;

    @BeforeMethod
    public void setUp() {
        employeeTimesheets = new EmployeeTimesheets(driver);
        DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
    }

    @Test
    public void testAllActions() {
        // Test clicking the Time page
        employeeTimesheets.clickTimePage();
        Assert.assertTrue(driver.getCurrentUrl().contains("Time"), "Failed to navigate to Time page.");

        // Test entering employee name
        employeeTimesheets.enterEmployeeName();
        String enteredName = driver.findElement(By.xpath("//input[@data-v-75e744cd and @placeholder='Type for hints...']")).getAttribute("value");
        Assert.assertEquals(enteredName, "Admin  Admin123", "Employee name was not entered correctly.");

        // Test the Timesheets tab
        employeeTimesheets.clickMyTimesheetsLink();

        // Test the Attendance tab
        employeeTimesheets.clickMyRecordsLink();
        employeeTimesheets.clickPunchInOutLink();
        employeeTimesheets.clickEmployeeRecordsLink();
        employeeTimesheets.clickConfigurationLink();
        
        // Test the Reports tab
        employeeTimesheets.clickProjectReportsLink();
        employeeTimesheets.clickEmployeeReportsLink();
        employeeTimesheets.clickAttendanceSummaryLink();

        // Test the Project Info tab
        employeeTimesheets.clickCustomersLink();
        employeeTimesheets.clickProjectsLink();

           }
}