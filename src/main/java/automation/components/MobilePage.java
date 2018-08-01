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
    @CacheLookup
    WebElement mobileSortByDropdown;

    @FindBy(css=".products-grid .item")
    List<WebElement> displayedItems;

    @FindBy(css=".block-compare .block-title")
    @CacheLookup
    WebElement compareBlockTitle;

    @FindBy(id="compare-items")
    @CacheLookup
    WebElement compareBlockItems;

    @FindBy(css=".block-list button")
    @CacheLookup
    WebElement compareButton;


    public MobilePage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

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
                return new MobileDetailsPage(getWebDriver(), this);
            }
        }
        return new MobileDetailsPage(getWebDriver(), this);
    }

    public ShoppingCartPage addMobileToCart(String mobileName) {
        for (WebElement element : displayedItems){
            if(element.findElement(By.cssSelector(".product-name")).getText().equals(mobileName)){
                clickElement(element.findElement(By.cssSelector(".btn-cart")));
                return new ShoppingCartPage(getWebDriver(), this);
            }
        }
        return new ShoppingCartPage(getWebDriver(), this);
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
        return new ProductComparationPage(getWebDriver(), this);
    }
}
