package automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TvPage extends BasePage {

    @FindBy(className= "sort-by")
    WebElement tvSortBy;

    @FindBy(css = ".nav-2")
    WebElement tvTabLink;

    @FindBy(css=".products-grid .item")
    List<WebElement> displayedTvs;

    public TvPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(tvSortBy);
    }

    public TvPage clickTvTabLink(){
        clickElement(tvTabLink);
        return withPage().tvPage();
    }

    public MyWishlistPage addTvToWishlist(String tvName) {
        for (WebElement element : displayedTvs){
            if(element.findElement(By.cssSelector(".product-name")).getText().equals(tvName)){
                clickElement(element.findElement(By.cssSelector(".link-wishlist")));
                return withPage().myWislistPage();
            }
        }
        return withPage().myWislistPage();
    }

}
