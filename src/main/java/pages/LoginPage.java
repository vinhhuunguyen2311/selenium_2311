package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    //define attribute
    //selenium => webdriver
    private WebDriver driver;
    //define cac element trong login page
    //form username
    private By usernameField = By.name("username");
    //form pw
    private By pwField = By.name("password");
    //button login
    private By loginBt = By.xpath("//button[@type='submit']");
    //error msg co tren web
    private By errorMSG = By.xpath("//div[@role='alert']");
    //error msg reuired cua username
    private By errorMSGUserName = By.xpath("//span[text()='Required']\n");
    //endpoint cua page
    private String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    //ham khoi tao
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //ham mo trang login
    public void openLoginPage() {
        driver.get(loginUrl);
    }

    //ham nhap usernam
    public void enterUsername(String username) {
        //b1: tim element input username tren web
        WebElement usernameElement = driver.findElement(usernameField);
        //b2: xoa du lieu cu tren form input(neu co)
        usernameElement.clear();
        //b3: nhap username vao form input
        usernameElement.sendKeys(username);
    }

    //ham nhap usernam
    public void enterPW(String pw) {
        //b1: tim element input pw tren web
        WebElement pwElement = driver.findElement(pwField);
        //b2: xoa du lieu cu tren form input(neu co)
        pwElement.clear();
        //b3: nhap username vao form input
        pwElement.sendKeys(pw);
    }

    //ham click bt login
    public void clickLoginBT() {
        //b1: tim element
        WebElement loginBTElement = driver.findElement(loginBt);
        //b2: click
        loginBTElement.click();
    }

    //ham login
    public void login(String username, String pw) {
        openLoginPage();
        enterUsername(username);
        enterPW(pw);
        clickLoginBT();
    }

    //ham verify
    public boolean isErrorDisplayed() {
        try {
            WebElement errorElement = driver.findElement(errorMSG);
            return errorElement.isDisplayed();
        } catch (Exception e) {
            // vao catch khi khong tim thay loi
            return false;
        }
    }

    //ham get endpointUrl
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    //ham verify required
    public boolean isRequiredDisplay(){
        try {
            WebElement requiredElement = driver.findElement(errorMSGUserName);
            return requiredElement.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
