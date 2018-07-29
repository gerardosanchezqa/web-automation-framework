package automation.components;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class BasePage extends PagesFactory{

    private final PagesFactory _pagesFactory;
    private final WebDriver _webDriver;

    public BasePage(WebDriver _webDriver, PagesFactory pagesFactory){
        super(_webDriver);
        this._webDriver = getWebDriver();
        this._pagesFactory = pagesFactory;
    }

    public void clearFieldAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void clickElement(WebElement webElement){
        webElement.click();
    }

    public LoginPage goToWebsite(String desiredWebsite){
        _webDriver.get(desiredWebsite);
        return new LoginPage(getWebDriver(), _pagesFactory);
    }

    public String getCurrentAlertText(){
            try
            {
                _webDriver.switchTo().alert();
                return _webDriver.switchTo().alert().getText();
            }
            catch (NoAlertPresentException Ex)
            {
                return "No alert thrown";
            }
    }

    public void closeCurrentAlert(){
        try
        {
            _webDriver.switchTo().alert().accept();
        }
        catch (NoAlertPresentException Ex)
        {
        }
    }

    public String getPageTitle(){
        return _webDriver.getTitle();
    }
}
