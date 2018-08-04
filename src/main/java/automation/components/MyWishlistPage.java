package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyWishlistPage extends BasePage {

    @FindBy(className= "my-wishlist")
    WebElement myWishlistTitle;

    @FindBy(className= "btn-share")
    WebElement shareWishlistButton;

    @FindBy(className= "success-msg")
    WebElement myWishlistsuccessMessage;

    public MyWishlistPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(myWishlistTitle);
    }

    public WishlistSharingPage clickShareWishlistButton(){
        clickElement(shareWishlistButton);
        return withPage().wishlistSharingPage();
    }

    public String getSuccessMessageText(){
        return getText(myWishlistsuccessMessage);
    }

}
