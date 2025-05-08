import base.BaseTest;
import com.orangehrm.page.BuzzPage;
import com.orangehrm.page.MaintenancePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaintenanceTest extends BaseTest {
    private MaintenancePage maintenancePage;

    @BeforeMethod
    public void goToMaintenance(){
        loginPage.login("Admin", "admin123");
        maintenancePage = new MaintenancePage();
        maintenancePage.navigateToMaintenance();
    }

    @BeforeMethod
    public void accessAdministrator(){
        maintenancePage.updateAccessPassword();
        maintenancePage.confirmAccess();
        maintenancePage.isReAuthenticateDone();
    }

    @Test
    public void purgeEmployeeRecordsTest(){
        maintenancePage.goToPurgeEmployeeRecords();
        maintenancePage.searchAboutPastEmployee("Ahmed");
        Assert.assertTrue(maintenancePage.isEmployeeNotFound());
    }

    @Test
    public void purgeCandidateRecordsTest(){
        maintenancePage.goToPurgeCandidateRecords();
        maintenancePage.searchAboutVacancyCandidate("Junior Account Assistant");
        Assert.assertTrue(maintenancePage.isCandidateFound());
    }


}
