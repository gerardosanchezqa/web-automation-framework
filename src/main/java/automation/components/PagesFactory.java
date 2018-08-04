package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PagesFactory {
    private WebDriver _webDriver;

    public PagesFactory(WebDriver driver){
        _webDriver = driver;
        PageFactory.initElements(_webDriver, this);
    }

    public HomePage homePage(){
        return new HomePage(getWebDriver(), this);
    }

    public MobilePage mobilePage(){
        return new MobilePage(getWebDriver(), this);
    }

    public MobileDetailsPage mobileDetailsPage(){
        return new MobileDetailsPage(getWebDriver(), this);
    }

    public ShoppingCartPage shoppingCartPage(){
        return new ShoppingCartPage(getWebDriver(), this);
    }

    public ProductComparationPage productComparationPage(){
        return new ProductComparationPage(getWebDriver(), this);
    }

    public WebDriver getWebDriver() {
        return _webDriver;
    }

    protected LoginPage loginPage() {
        return new LoginPage(getWebDriver(), this);
    }

    protected CreateAccountPage createAccountPage() {
        return new CreateAccountPage(getWebDriver(), this);
    }

    protected MyAccountPage myAccountPage() {
        return new MyAccountPage(getWebDriver(), this);
    }

    protected MyWishlistPage myWislistPage() {
        return new MyWishlistPage(getWebDriver(), this);
    }

    protected WishlistSharingPage wishlistSharingPage() {
        return new WishlistSharingPage(getWebDriver(), this);
    }

    protected TvPage tvPage() {
        return new TvPage(getWebDriver(), this);
    }
}
