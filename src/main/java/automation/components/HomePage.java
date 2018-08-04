package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(css = ".nav-1")
    WebElement mobileTabLink;

    @FindBy(linkText = "MY ACCOUNT")
    WebElement myAccountLink;

    public HomePage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(mobileTabLink);
    }

    public MobilePage clickMobileLink() {
        clickElement(mobileTabLink);
        return withPage().mobilePage();
    }

    public LoginPage clickMyAccountLink() {
        clickElement(myAccountLink);
        return withPage().loginPage();
    }
}