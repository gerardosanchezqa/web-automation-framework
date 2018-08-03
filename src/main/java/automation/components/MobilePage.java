package automation.components;

import org.apache.xerces.xs.StringList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MobilePage extends BasePage{

    @FindBy(css=".sort-by select")
    WebElement mobileSortByDropdown;

    @FindBy(css=".products-grid .item")
    List<WebElement> displayedItems;

    @FindBy(css=".block-compare .block-title")
    WebElement compareBlockTitle;

    @FindBy(id="compare-items")
    WebElement compareBlockItems;

    @FindBy(css=".block-list button")
    WebElement compareButton;


    public MobilePage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(mobileSortByDropdown);
    }

    public void sortMobilesBy(String optionToSelect) {
        selectElementFromDropdown(mobileSortByDropdown, optionToSelect);
    }

    public boolean verifyIfMobileSortedByName(){
        List<String> listWithMobileNames = new ArrayList<String>();
        for (WebElement element : displayedItems){
            listWithMobileNames.add(element.findElement(By.cssSelector(".product-name")).getText());
        }
        return verifyIfListIsSortedAlphabetically(listWithMobileNames);
    }

    public String getMobilePriceFromName(String mobileName) {
        for (WebElement element : displayedItems){
            if(element.findElement(By.cssSelector(".product-name")).getText().equals(mobileName)){
                return element.findElement(By.cssSelector(".price")).getText();
            }
        }
        return "No mobile with that name was found";
    }

    public MobileDetailsPage goToMobileDetails(String mobileName) {
        for (WebElement element : displayedItems){
            if(element.findElement(By.cssSelector(".product-name")).getText().equals(mobileName)){
                clickElement(element.findElement(By.cssSelector(".product-name")));
                return withPage().mobileDetailsPage();
            }
        }
        return withPage().mobileDetailsPage();
    }

    public ShoppingCartPage addMobileToCart(String mobileName) {
        for (WebElement element : displayedItems){
            if(element.findElement(By.cssSelector(".product-name")).getText().equals(mobileName)){
                clickElement(element.findElement(By.cssSelector(".btn-cart")));
                return withPage().shoppingCartPage();
            }
        }
        return withPage().shoppingCartPage();
    }

    public void clickAddToCompare(String mobileName) {
        for (WebElement element : displayedItems){
            if(element.findElement(By.cssSelector(".product-name")).getText().equals(mobileName)){
                clickElement(element.findElement(By.cssSelector(".link-compare")));
                break;
            }
        }
    }

    public boolean isCompareBlockDisplayed() {
        return compareBlockTitle.isDisplayed();
    }

    public boolean isMobileDisplayedInCompareBlock(String mobileName) {
        return compareBlockItems.findElement(By.linkText(mobileName)).isDisplayed();
    }

    public ProductComparationPage clickCompareButton() {
        clickElement(compareButton);
        return withPage().productComparationPage();
    }
}
