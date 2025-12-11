package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    // attribute tao file HTML report
    private static ExtentReports extent;
    // attribute tuong tac voi test va render
    private static ExtentTest test;


    // ham khoi tao
    public static ExtentReports getInstance() {
        if (extent == null) {
            // tao ten file voi format timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

            // tao duong dan luu file
            String reportPath = System.getProperty("user.dir") + "/" + ConfigReader.getReportPath() + "/Report_" + ".html";

            // tao extent spark reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // cau hinh report: title, report name, theme, ngay tao report,...
            // String reportTitle = ConfigReader.get
            sparkReporter.config().setDocumentTitle("Orange HRM test");
            sparkReporter.config().setReportName("Selenium test excution");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // ham tao test case moi
    public static ExtentTest crearteTest(String testName, String description) {
        test = getInstance().createTest(testName, description);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    // ham flush de ghi info testcase ra file
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
