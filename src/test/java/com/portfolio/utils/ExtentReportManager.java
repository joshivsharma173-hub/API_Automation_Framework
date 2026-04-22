package com.portfolio.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(
                "reports/ExtentReport.html"
            );

            // Report configuration
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("API Automation Report");
            spark.config().setReportName("RestAssured Portfolio - Test Results");
            spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // System info shown in report dashboard
            extent.setSystemInfo("Project", "RestAssured Portfolio");
            extent.setSystemInfo("Author", "Joshiv Kumar");
            extent.setSystemInfo("Environment", "GoRest API");
            extent.setSystemInfo("Base URL", ConfigReader.get("base.url"));
        }
        return extent;
    }
}