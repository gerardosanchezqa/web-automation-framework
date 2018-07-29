import org.testng.Assert;
import org.testng.annotations.Test;

public class BankManagerLoginTest extends BaseTestCase{

    @Test(priority = 0, dataProvider = "InvalidLoginDataFromExcel")
    public void unsuccessfulLogin(String userID, String password) {
        loginPage = goToWebsite("http://www.demo.guru99.com/V4/");
        Assert.assertEquals(loginPage.login(userID, password).getCurrentAlertText(), "User or Password is not valid");
        loginPage.closeCurrentAlert();
    }

    @Test(priority = 1, dataProvider = "ValidLoginDataFromExcel")
    public void successfulLogin(String userID, String password) {
        loginPage = goToWebsite("http://www.demo.guru99.com/V4/");
        managerHomePage = loginPage.login(userID, password);
        Assert.assertEquals(managerHomePage.getPageTitle(), "Guru99 Bank Manager HomePage");
    }
}