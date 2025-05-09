
package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;
    public WebDriverWait waitLonger;

    @BeforeClass
    public void setupBase() {
    }

    @AfterClass
    public void tearDownBase() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("حدث خطأ أثناء إغلاق برنامج التشغيل: " + e.getMessage());
            } finally {
                driver = null;
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File destinationFile = new File("screenshots", result.getName() + "_" + timestamp + ".png");

            try {
                FileUtils.forceMkdirParent(destinationFile);
                FileUtils.copyFile(sourceFile, destinationFile);
                System.out.println("تم التقاط لقطة شاشة عند الفشل: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (sourceFile != null) {
                    FileUtils.deleteQuietly(sourceFile);
                }
            }
        }
    }
}