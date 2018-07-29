package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(className = "nav-1")
    @CacheLookup
    WebElement mobileLink;

    public HomePage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    public MobilePage clickMobileLink(){
        clickElement(mobileLink);
        return new MobilePage(getWebDriver(), this);
    }


    private void writeEmail(WebElement webElement, String email) {
        clearFieldAndSendKeys(webElement, email);
    }

    private void writePassword(WebElement webElement, String password) {
        clearFieldAndSendKeys(webElement, password);
    }

}