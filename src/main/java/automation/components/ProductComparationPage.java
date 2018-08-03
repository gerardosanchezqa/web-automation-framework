package automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductComparationPage extends BasePage{

    @FindBy(css = ".page-title h1")
    WebElement pageTitle;

    @FindBy(css = ".product-shop-row.top")
    WebElement displayedItems;

    public ProductComparationPage(WebDriver webDriver, PagesFactory pagesFactory){
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(pageTitle);
    }

    public String getComparePageTitle(){
        switchToCurrentWindow();
        return pageTitle.getText();
    }

    public boolean isMobileDisplayedInComparePage(String mobileName) {
        return displayedItems.findElement(By.linkText(mobileName)).isDisplayed();
    }

    public MobilePage closeComparationPage() {
        getWebDriver().close();
        switchToCurrentWindow();
        return new MobilePage(getWebDriver(), this);
    }

}
