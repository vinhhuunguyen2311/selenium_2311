package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    //ham khoi tao moi truong test
    @BeforeMethod
    public void setUp(){
        //b1: cau hinh chromeDriver
        WebDriverManager.chromedriver().setup();
        //b2: cau hinh cac tuy chon
        ChromeOptions options = new ChromeOptions();
        //mo chrome o che do full man hinh
        options.addArguments("--start-maximized");
        //b3: khoi tao chromedriver
        driver = new ChromeDriver(options);
        //b4: setting thoi gian doi 10s
        //neu chrome tao som hon 10s, chay cac buoc tiep theo, khi thi bao loi error
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    //ham clear moi truong test sau khi xong 1 testcase
    @AfterMethod
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }
}
