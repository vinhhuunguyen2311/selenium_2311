package utils;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest extentTest;

    // ham khoi tao extent report truoc khi chay tat ca cac test
    @BeforeSuite
    public void setUpSuite() {
        ExtentReportManager.getInstance();
    }

    @AfterSuite
    public void tearDowmSuite() {
        ExtentReportManager.flushReport();
    }

    // ham khoi tao moi truong test
    @BeforeMethod
    public void setUp(ITestResult result) {
        // tao test case trong report
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = "test case " + testName;
        }
        extentTest = ExtentReportManager.crearteTest(testName, testDescription);

        // b1: cau hinh chromeDriver
        WebDriverManager.chromedriver().setup();
        // b2: cau hinh cac tuy chon
        ChromeOptions options = new ChromeOptions();
        // mo chrome o che do full man hinh
        options.addArguments("--start-maximized");
        // b3: khoi tao chromedriver
        driver = new ChromeDriver(options);
        // b4: setting thoi gian doi 10s
        // neu chrome tao som hon 10s, chay cac buoc tiep theo, khi thi bao loi error
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // ham clear moi truong test sau khi xong 1 testcase
    @AfterMethod
    public void tearDown(ITestResult result) {
        // kiem tra ket qua va log vao report
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test case passed: " + result.getMethod().getMethodName());
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test case failed: " + result.getMethod().getMethodName());
            if (result.getThrowable() != null) {
                extentTest.fail(result.getThrowable());
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
