package automation.components;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

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

    public void selectElementFromDropdown(WebElement webElement, String optionToSelect){
        Select selectedDropdown = new Select(webElement);
        selectedDropdown.selectByVisibleText(optionToSelect);
    }

    boolean verifyIfListIsSortedAlphabetically(List<String> iterable) {
        String previous = ""; // empty string: guaranteed to be less than or equal to any other
        for (final String current: iterable) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

    public HomePage goToWebsite(String desiredWebsite){
        _webDriver.get(desiredWebsite);
        return new HomePage(getWebDriver(), _pagesFactory);
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

    protected void switchToCurrentWindow() {
        for (String currentWindow: getWebDriver().getWindowHandles()) {
            getWebDriver().switchTo().window(currentWindow);
        }
    }
}