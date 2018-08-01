import org.testng.Assert;
        import org.testng.annotations.Test;

public class VerifyMobileFunctionality extends BaseTestCase{

    String websiteToVisit = "http://live.guru99.com/index.php/";

    @Test(priority = 0)
    public void VerifyMobileSortingByName() {
        homePage = goToWebsite(websiteToVisit);
        Assert.assertEquals(homePage.getPageTitle(), "Home page");
        mobilePage = homePage.clickMobileLink();
        Assert.assertEquals(mobilePage.getPageTitle(), "Mobile");
        mobilePage.sortMobilesBy("Name");
        Assert.assertTrue(mobilePage.verifyIfMobileSortedByName());
    }

    @Test(priority = 1)
    public void verifyThatMobilePricesAreMatching() {
        String mobileDeviceToTest = "SONY XPERIA";
        homePage = goToWebsite(websiteToVisit);
        mobilePage = homePage.clickMobileLink();
        String initialMobilePrice = mobilePage.getMobilePriceFromName(mobileDeviceToTest);
        mobileDetailsPage = mobilePage.goToMobileDetails(mobileDeviceToTest);
        Assert.assertEquals(mobileDetailsPage.getMobilePrice(), initialMobilePrice);
    }

    @Test(priority = 2)
    public void verifyAddingAnInvalidAmountOfAProduct() {
        String mobileDeviceToTest = "SONY XPERIA";
        homePage = goToWebsite(websiteToVisit);
        mobilePage = homePage.clickMobileLink();
        shoppingCartPage = mobilePage.addMobileToCart(mobileDeviceToTest);
        shoppingCartPage.updateQuantityAndUpdate("1000");
        Assert.assertEquals(shoppingCartPage.getProductQuantity(), "1000");
        Assert.assertEquals(shoppingCartPage.getErrorMessageText(), "Some of the products cannot be ordered in requested quantity.");
        shoppingCartPage.clickEmptyCartLink();
        Assert.assertTrue(shoppingCartPage.isShoppingCartEmpty());
    }

    @Test(priority = 3)
    public void verifyCompareProducts() {
        String mobileDeviceToTest1 = "SONY XPERIA", mobileDeviceToTest2 = "IPHONE";
        homePage = goToWebsite(websiteToVisit);
        mobilePage = homePage.clickMobileLink();
        mobilePage.clickAddToCompare(mobileDeviceToTest1);
        mobilePage.clickAddToCompare(mobileDeviceToTest2);
        Assert.assertTrue(mobilePage.isCompareBlockDisplayed());
        Assert.assertTrue(mobilePage.isMobileDisplayedInCompareBlock(mobileDeviceToTest1));
        Assert.assertTrue(mobilePage.isMobileDisplayedInCompareBlock(mobileDeviceToTest2));
        productComparationPage = mobilePage.clickCompareButton();
        Assert.assertEquals(productComparationPage.getComparePageTitle(), "COMPARE PRODUCTS");
        Assert.assertTrue(productComparationPage.isMobileDisplayedInComparePage(mobileDeviceToTest1));
        Assert.assertTrue(productComparationPage.isMobileDisplayedInComparePage(mobileDeviceToTest2));
        mobilePage = productComparationPage.closeComparationPage();
        Assert.assertEquals(mobilePage.getPageTitle(), "Mobile");
    }
}