import automation.components.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTestCase extends DataProviders{

    public WebDriver webDriver;
    public PagesFactory pagesFactory;
    HomePage homePage;
    MobilePage mobilePage;
    LoginPage loginPage;
    CreateAccountPage createAccountPage;
    MobileDetailsPage mobileDetailsPage;
    ShoppingCartPage shoppingCartPage;
    ProductComparationPage productComparationPage;
    MyAccountPage myAccountPage;
    MyWishlistPage myWishlistPage;
    WishlistSharingPage wishlistSharingPage;
    TvPage tvPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void before(){
        webDriver = new ChromeDriver();
        System.out.println(webDriver);
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        pagesFactory = new PagesFactory(webDriver);
    }

    public HomePage goToWebsite(String website) {
        webDriver.get(website);
        return new HomePage(webDriver, pagesFactory);
    }

    @AfterMethod
    public void after() {
        webDriver.quit();
    }

}