
import base.BaseTest;
import com.orangehrm.page.AddEmployeePage;
import com.orangehrm.page.DashboardPage;
import com.orangehrm.page.EmployeeDetailsPage;
import com.orangehrm.page.EmployListPage;
import com.orangehrm.page.PimPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PimPageTest extends BaseTest {
	private static final String PIM_URL_FRAGMENT = "/pim/viewEmployeeList";
	private static final String EMPLOYEE_FIRST_NAME = "Maryam";
	private static final String EMPLOYEE_LAST_NAME = "Naeem";

	@Test(priority = 1)
	public void testNavigateToPIM() {
		DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
		PimPage pimPage = new PimPage(driver);
		pimPage.navigateToPIM();
		Assert.assertTrue(basePage.getUrl().contains(PIM_URL_FRAGMENT), "Failed to navigate to PIM page.");
	}

	@Test(priority = 2)
	public void testAddEmployeeAndGetId() {
		DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
		PimPage pimPage = new PimPage(driver);
		pimPage.navigateToPIM();
		AddEmployeePage addEmployeePage = pimPage.clickAddButton();
		addEmployeePage.enterFirstName(EMPLOYEE_FIRST_NAME)
				.enterLastName(EMPLOYEE_LAST_NAME)
				.clickSaveButton();
		EmployeeDetailsPage employeeDetailsPage = new EmployeeDetailsPage(driver);
		String employeeId = employeeDetailsPage.getEmployeeId();
		Assert.assertNotNull(employeeId, "Failed to retrieve employee ID after adding employee.");
	}

	@Test(priority = 3, dependsOnMethods = "testAddEmployeeAndGetId")
	public void testSearchEmployeeByIdAndDelete() {
		DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
		PimPage pimPage = new PimPage(driver);
		pimPage.navigateToPIM();
		AddEmployeePage addEmployeePage = pimPage.clickAddButton();
		addEmployeePage.enterFirstName(EMPLOYEE_FIRST_NAME)
				.enterLastName(EMPLOYEE_LAST_NAME)
				.clickSaveButton();
		EmployeeDetailsPage employeeDetailsPage = new EmployeeDetailsPage(driver);
		String employeeId = employeeDetailsPage.getEmployeeId();
		EmployListPage employeeListPage = employeeDetailsPage.navigateToEmployeeList();
		employeeListPage.enterEmployeeIdForSearch(employeeId)
				.clickEmployeeSearchButton();
		Assert.assertTrue(employeeListPage.isEmployeeFound(employeeId), "Employee not found with ID: " + employeeId);
		employeeListPage.deleteEmployee(employeeId);
		Assert.assertTrue(employeeListPage.isDeletionSuccessful(), "Failed to delete employee with ID: " + employeeId);
	}
}