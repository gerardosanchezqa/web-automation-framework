import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class KeyboardAndMouseFunctionsTest extends BaseTestCase{

    @Test(dataProvider = "loginDataProvider")
    public void successfulLogin(String userID, String password) {
        loginPage = goToWebsite("http://www.demo.guru99.com/V4/");
        managerHomePage = loginPage.login(userID, password);
        Assert.assertEquals(managerHomePage.getPageTitle(), "Guru99 Bank Manager HomePage");
    }

}