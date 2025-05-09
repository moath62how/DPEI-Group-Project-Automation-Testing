
import base.BaseTest;
import com.orangehrm.page.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DashboardPageTest extends BaseTest {
	private static final List<String> EXPECTED_QUICK_LAUNCH_LABELS = List.of(
			"Assign Leave", "Leave List", "Timesheets", "Apply Leave", "My Leave", "My Timesheets"
	);
	private static final Map<String, String> QUICK_LAUNCH_NAVIGATION = Map.of(
			"Assign Leave", "/leave/assignLeave",
			"Leave List", "/leave/viewLeaveList",
			"Timesheets", "/time/viewEmployeeTimesheet",
			"Apply Leave", "/leave/applyLeave",
			"My Leave", "/leave/viewMyLeaveList",
			"My Timesheets", "/time/viewMyTimesheet"
	);

	@Test(priority = 1)
	public void testDashboardElementsVisibility() {
		DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("Quick Launch"), "Quick Launch title is not displayed.");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("Employee Distribution by Sub Unit"), "Employee Distribution title is not displayed.");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("Latest News"), "Latest News title is not displayed.");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("Time at Work"), "Time at Work title is not displayed.");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("My Actions"), "My Actions title is not displayed.");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("Buzz Latest Posts"), "Buzz Latest Posts title is not displayed.");
		Assert.assertTrue(dashboardPage.isSectionTitleDisplayed("Employees on Leave Today"), "Employees on Leave Today title is not displayed.");
	}

	@Test(priority = 2)
	public void testQuickLaunchButtonsLabels() {
		DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
		List<String> actualLabels = dashboardPage.getQuickLaunchButtonLabels();
		Assert.assertEquals(actualLabels, EXPECTED_QUICK_LAUNCH_LABELS, "Quick Launch button labels do not match.");
	}

	@Test(priority = 3)
	public void testQuickLaunchButtonsNavigation() {
		DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
		for (Map.Entry<String, String> entry : QUICK_LAUNCH_NAVIGATION.entrySet()) {
			String buttonLabel = entry.getKey();
			String expectedUrlFragment = entry.getValue();
			dashboardPage.clickQuickLaunchButton(buttonLabel);
			Assert.assertTrue(basePage.getUrl().contains(expectedUrlFragment),
					"Navigation to " + buttonLabel + " failed. Expected URL to contain: " + expectedUrlFragment);
			driver.navigate().back();
		}
	}
}