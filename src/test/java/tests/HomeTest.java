package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

import java.util.List;

public class HomeTest extends BaseTest  {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPass();

    @Test
    public void testAccessHomeAfterLogin() throws Exception{
        LoginPage lgp = new LoginPage(driver);
        lgp.login(VALID_USERNAME, VALID_PASSWORD);

        Thread.sleep(3000);

        HomePage homePage= new HomePage(driver);
        String currentUrl= homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"));

        List<String> actualMenuItems = homePage.getSidebarMenuItem();

        List<String> expectedMenuItems= List.of(
                "Admin",
                "PIM",
                "Leave",
                "Time",
                "Recruitment",
                "My Info",
                "Performance",
                "Dashboard",
                "Directory",
                "Maintenance",
                "Claim",
                "Buzz"
        );
        for (String expectedMenu:expectedMenuItems){
            homePage.highlightMenuByName(expectedMenu, 500);
            Assert.assertTrue(actualMenuItems.contains(expectedMenu));
        }
    }
}
