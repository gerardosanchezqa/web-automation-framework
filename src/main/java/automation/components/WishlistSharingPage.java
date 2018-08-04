package automation.components;

import org.apache.bcel.verifier.PassVerifier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistSharingPage extends BasePage {

    @FindBy(css= "#form-validate button")
    WebElement submitShareWishlistButton;

    @FindBy(className= "validate-emails")
    WebElement emailAddressesField;

    @FindBy(id= "message")
    WebElement messageField;

    public WishlistSharingPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(submitShareWishlistButton);
    }

    public WishlistSharingPage addEmailAddresses(String email) {
        clearFieldAndSendKeys(emailAddressesField, email);
        return withPage().wishlistSharingPage();
    }

    public WishlistSharingPage addMessage(String message) {
        clearFieldAndSendKeys(messageField, message);
        return withPage().wishlistSharingPage();
    }

    public MyWishlistPage shareWishlist() {
        clickElement(submitShareWishlistButton);
        return withPage().myWislistPage();
    }
}
