package automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class MobileDetailsPage extends BasePage{

    @FindBy(css=".breadcrumbs .product")
    WebElement selectedProduct;

    @FindBy(css=".products-grid .item")
    List<WebElement> displayedItems;

    public MobileDetailsPage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

    @Override
    protected BooleanCondition readyCondition() {
    return Conditions.elementPresent(selectedProduct);
    }

    public String getMobilePrice() {
        return getWebDriver().findElement(By.cssSelector(".price-info")).getText();

    }
}
