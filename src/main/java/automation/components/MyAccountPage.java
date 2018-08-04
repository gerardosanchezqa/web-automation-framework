package automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyAccountPage extends BasePage {

    @FindBy(linkText = "MY WISHLIST")
    WebElement myWishListLink;

    @FindBy(css = ".nav-2")
    WebElement tvTabLink;

    @FindBy(css=".products-grid .item")
    List<WebElement> displayedTvs;

    public MyAccountPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(myWishListLink);
    }

    public TvPage clickTvTabLink(){
        clickElement(tvTabLink);
        return withPage().tvPage();
    }

    public MyWishlistPage clickMyWishlistLink(){
        clickElement(myWishListLink);
        return withPage().myWislistPage();
    }

}
