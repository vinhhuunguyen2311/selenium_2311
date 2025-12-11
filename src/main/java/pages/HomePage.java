package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private final By sidebarMenuItems = By.cssSelector(".oxd-main-menu-item-wrapper a.oxd-main-menu-item");
    private final By sidebarMenuNames = By.cssSelector(".oxd-main-menu-item-wrapper span.oxd-main-menu-item--name");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // ham lay danh sach cac menu trong item sidebar
    public List<String> getSidebarMenuItem() {
        // get list menu tu HTML
        List<WebElement> menuItems = driver.findElements(sidebarMenuNames);
        List<String> menuNames = new ArrayList<>();

        for (WebElement menuElement : menuItems) {
            // trim: xoa cac page du
            String text = menuElement.getText().trim();
            menuNames.add(text);
        }
        return menuNames;
    }

    // highlight item tren html
    // import code jvscrip vao html thong qua selenium
    public void highlightMenuByName(String menuName, long delay) {
        // findElements: return list element
        List<WebElement> menuElements = driver.findElements(sidebarMenuNames);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // highlight
        // duyet tung menu trong list menu
        // luu style cu cua menu de sau khi highlight thi tra ve nhu cu
        // highlight vaf cho khoang 1s
        // tra ve style ban dau

        for (WebElement menuElement : menuElements) {
            String text = menuElement.getText().trim();
            if (text.equals(menuName)) {
                String originalStyle = (String) js.executeScript(
                        "return arguments[0].getAttribute('style');", menuElement
                );
                js.executeScript(
                        "arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='3fb26eb';", menuElement
                );
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                js.executeScript(
                        "arguments[0].setAttribute('style', arguments[1]);", menuElement, originalStyle
                );
                break;
            }
        }
    }
}
