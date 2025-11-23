package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    //static final: tao hang so, khong thay doi duoc, dung chung cho cac testcase
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "admin123";

    private static final String INVALID_USERNAME = "wrongusername";
    private static final String INVALID_PASSWORD = "wrongpassword";

    //viet testcase
    @Test
    public void testLoginSuccess(){
        //b1: tao doi tuong login page
        LoginPage lgp = new LoginPage(driver);
        //b2: goi ham login de thuc hien cac step login
        lgp.login(VALID_USERNAME,VALID_PASSWORD);
        //b3: doi cho server kiem tra thong tin user
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //b4: kiem tra ket qua voi expect
        //c1: kiem tra endpoit url: neu endpoit moi khac endpoint login=> pass
        String currentUrl = lgp.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"));
    }
    @Test
    public void testLoginWithWrongUser(){
        LoginPage lgp = new LoginPage(driver);
        lgp.login(INVALID_USERNAME, VALID_PASSWORD);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //c1: dung url
        String currentUrl = lgp.getCurrentUrl();
        //Assert.assertTrue(currentUrl.contains("/login"));
        Assert.assertFalse(currentUrl.contains("dashboard"));
        //c2: dung ham isErrorDisplay()
        boolean hasError = lgp.isErrorDisplayed();
        Assert.assertTrue(hasError);
    }
    @Test
    public void testLoginWithWrongPW(){
        LoginPage lgp = new LoginPage(driver);
        lgp.login(VALID_USERNAME, INVALID_PASSWORD);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean hasError = lgp.isErrorDisplayed();
        Assert.assertTrue(hasError);
    }
    @Test
    public void testLoginWithoutUsername(){
        LoginPage lgp = new LoginPage(driver);
        lgp.login("", VALID_PASSWORD);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertFalse(lgp.getCurrentUrl().contains("dashboard"));
        boolean hasErrorRequired = lgp.isRequiredDisplay();
    }
}
