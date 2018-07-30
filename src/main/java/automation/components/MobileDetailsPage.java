package automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class MobileDetailsPage extends BasePage{

    @FindBy(css=".sort-by select")
    @CacheLookup
    WebElement mobileSortByDropdown;

    @FindBy(css=".products-grid .item")
    @CacheLookup
    List<WebElement> displayedItems;

    public MobileDetailsPage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

    public String getMobilePrice() {
        return getWebDriver().findElement(By.cssSelector(".price-info")).getText();

    }
}
