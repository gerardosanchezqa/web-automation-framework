import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyMobileFunctionality extends BaseTestCase {

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

    String randomlyGeneratedEmail;
    String passwordForTherandomlyGeneratedEmail;
    int tvPrice;

    @Test(priority = 4, dataProvider = "ValidRegistrationData", groups = "WishlistAndPurchase")
    public void verifyCreateAccountAndShareWishlist(String firstname, String middlename, String lastname, String email, String password, String repeatedPassword) {
        String tvToUse = "LG LCD";
        randomlyGeneratedEmail = ((int) (Math.random() * 12345)) + email;
        passwordForTherandomlyGeneratedEmail = password;
        homePage = goToWebsite(websiteToVisit);
        loginPage = homePage.clickMyAccountLink();
        createAccountPage = loginPage.clickCreateAccountbutton();
        myAccountPage = createAccountPage.fillFormAndSubmit(firstname, middlename, lastname, randomlyGeneratedEmail, password, repeatedPassword);
        tvPage = myAccountPage.clickTvTabLink();
        tvPrice = tvPage.getTvPrice(tvToUse);
        myWishlistPage = tvPage.addTvToWishlist(tvToUse);
        wishlistSharingPage = myWishlistPage.clickShareWishlistButton();
        myWishlistPage = wishlistSharingPage.addEmailAddresses(email).addMessage("Sharing my wishlist with you!").shareWishlist();
        Assert.assertEquals(myWishlistPage.getSuccessMessageText(), "Your Wishlist has been shared.");
        myWishlistPage.logout();
    }

    int shippingPrice;
    String orderNumber;

    @Test(priority = 5, dataProvider = "ShippingInformation", dependsOnGroups = "WishlistAndPurchase")
    public void verifyUserAbleToPurchase(String country, String state, String zipcode, String address, String city, String telephone) {
        homePage = goToWebsite(websiteToVisit);
        loginPage = homePage.clickMyAccountLink();
        myAccountPage = loginPage.loginWith(randomlyGeneratedEmail, passwordForTherandomlyGeneratedEmail);
        myWishlistPage = myAccountPage.clickMyWishlistLink();
        shoppingCartPage = myWishlistPage.clickAddToCartButton();
        checkoutPage = shoppingCartPage.clickProceedToCheckoutButton();
        checkoutPage.addShippingInformation(country, state, zipcode, address, city, telephone).clickShippingInformationContinueButton();
        shippingPrice = checkoutPage.getShippingPrice();
        checkoutPage.clickShippingMethodContinueButton();
        checkoutPage.selectCheckAndMoneyRadioButton();
        checkoutPage.clickPaymentInformationContinueButton();
        Assert.assertEquals(checkoutPage.getTotal(), tvPrice + shippingPrice);
        checkoutPage.clickPlaceOrderButton();
        orderNumber = checkoutPage.getOrderNumber();
        System.out.println("Final order number: " + orderNumber);
    }
}