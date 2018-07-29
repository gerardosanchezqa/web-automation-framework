import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyMobileFunctionality extends BaseTestCase{

    @Test(priority = 0)
    public void VerifyMobileSortingByName() {
        homePage = goToWebsite("http://live.guru99.com/index.php/");
        Assert.assertEquals(homePage.getPageTitle(), "Home page");
        mobilePage = homePage.clickMobileLink();
        Assert.assertEquals(mobilePage.getPageTitle(), "Mobile");
        mobilePage.sortMobilesBy("Name");
        Assert.assertTrue(mobilePage.verifyIfMobileSortedByName());
    }
}