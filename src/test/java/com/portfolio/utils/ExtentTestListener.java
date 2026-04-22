package com.portfolio.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();

    // ThreadLocal so parallel tests don't interfere with each other
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Runs when each test STARTS
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
            result.getMethod().getMethodName(),
            result.getMethod().getDescription()
        );
        extentTest.set(test);
        test.info("Test Started: " + result.getMethod().getMethodName());
    }

    // Runs when test PASSES
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS,
            "Test Passed  — " + result.getMethod().getMethodName()
        );
    }

    // Runs when test FAILS
    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL,
            "Test Failed  — " + result.getThrowable()
        );
    }

    // Runs when test is SKIPPED
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP,
            "Test Skipped — " + result.getMethod().getMethodName()
        );
    }

    // Runs after ALL tests finish — flushes report to HTML file
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public static void logInfo(String message) {
    extentTest.get().info(message);
}

public static void logPass(String message) {
    extentTest.get().pass(message);
}

public static void logFail(String message) {
    extentTest.get().fail(message);
}
}