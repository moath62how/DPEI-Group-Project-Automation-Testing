package Jira;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class JiraListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String className = result.getTestClass().getName();
        Throwable throwable = result.getThrowable();

        String errorMessage = (throwable != null) ? throwable.getMessage() : "No error message";
        String stackTrace = (throwable != null) ? throwable.toString() : "";

        JiraBugReporter.createIssue(
                "Test Failed: " + className + "." + testName,
                "Error Message: " + errorMessage + "\n\nStack Trace:\n" + stackTrace
        );
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
