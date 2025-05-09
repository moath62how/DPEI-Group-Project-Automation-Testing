import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.page.DashboardPage;
import com.orangehrm.page.LeaveTab;

import base.BaseTest;

public class LeaveTabTest extends BaseTest {
    private LeaveTab leaveTab;

    @BeforeMethod
    public void setUp() {
        leaveTab = new LeaveTab(driver);
        DashboardPage dashboardPage = loginPage.login("Admin", "admin123");
    }

    @Test
    public void testLeaveTabMethods() {

        leaveTab.clickLeaveTab()
                .clickApplyLink()
                .clickMyLeaveLink()
                .clickAddEntitlementsLink()
                .clickEmployeeEntitlementsLink()
                .clickMyEntitlementsLink()
                .clickLeaveEntitlementsUsageReportLink()
                .clickMyLeaveEntitlementsUsageReportLink()
                .clickLeavePeriodLink()
                .clickLeaveTypesLink()
                .clickWorkWeekLink()
                .clickHolidaysLink();
    }

}