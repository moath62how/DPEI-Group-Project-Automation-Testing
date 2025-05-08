import base.BaseTest;
import com.orangehrm.page.BuzzPage;
import com.orangehrm.page.MaintenancePage;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaintenanceTest extends BaseTest {
    private MaintenancePage maintenancePage;

    @BeforeMethod
    public void a_goToMaintenance(){
        loginPage.login("Admin", "admin123");
        maintenancePage = new MaintenancePage();
        maintenancePage.navigateToMaintenance();
    }

    @BeforeMethod
    public void b_accessAdministrator(){
        maintenancePage.updateAccessPassword();
        maintenancePage.confirmAccess();
    }

    String inValidEmployeeName = "Ahmed";
    String validEmployeeName = "Ranga  Akunuri";
    String validCandidate = "Senior Support Specialist";
    String inValidCandidate = "AAAA";

    @Test
    public void purgeEmployeeRecordsTest(){
        maintenancePage.goToPurgeEmployeeRecords();
        maintenancePage.searchAboutPastEmployee(inValidEmployeeName);
        Assert.assertTrue(maintenancePage.isEmployeeNotFound());
    }

    @Test
    public void purgeCandidateRecordsTest(){
        maintenancePage.goToPurgeCandidateRecords();
        maintenancePage.searchAboutVacancyCandidate(validCandidate);
        Assert.assertTrue(maintenancePage.isSearchDone());
    }

    /*@Test
    public void purgeAllCandidatesRecordsTest(){
        maintenancePage.goToPurgeCandidateRecords();
        maintenancePage.searchAboutVacancyCandidate(validCandidate);
        Assert.assertTrue(maintenancePage.isSearchDone());
        maintenancePage.purgeCandidates();
        maintenancePage.confirmPurgeCandidates();
        Assert.assertTrue(maintenancePage.isPurgeAllDone());
    }*/

    @Test
    public void downloadEmployeePesonalDataTest(){
        maintenancePage.goToAccessRecords();
        maintenancePage.searchAboutEmployee(validEmployeeName);
        Assert.assertTrue(maintenancePage.isPersonalDataDownloaded());
    }
}
