package automation.components;

import com.sun.istack.internal.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BasePage extends PagesFactory{

    private PagesFactory _pagesFactory;
    private WebDriver _webDriver;
    protected WebDriverWait _wait;

    public BasePage(WebDriver webDriver, PagesFactory pagesFactory){
        this(webDriver, pagesFactory, 5);
    }

    private static final BooleanCondition IS_DOCUMENT_READY = new BooleanCondition() {
        @Override
        public String describeFailure(){
            return "Document is not ready";
        }

        @Nullable
        @Override
        public Boolean apply(@Nullable WebDriver input){
            return "complete".equals(((JavascriptExecutor) input).executeScript("return document.readyState;"));
        }
    };

    protected BasePage(WebDriver webDriver, PagesFactory pagesFactory, int readyConditionWait) {
        super(webDriver);
        this._webDriver = webDriver;
        this._pagesFactory = pagesFactory;
        this._wait = new WebDriverWait(webDriver, readyConditionWait);
        if(readyConditionWait > 0) {
            waitFor(IS_DOCUMENT_READY, "Waiting for page to be ready failed", readyConditionWait);
        } else {
            waitFor(IS_DOCUMENT_READY, "Waiting for page to be ready failed");
        }
        waitFor(readyCondition(), "Initialization failed", 5);
    }

    protected void waitFor(BooleanCondition condition, String contextDescription) {
        waitFor(condition, contextDescription, 20);
    }


    protected void waitFor(BooleanCondition condition, String contextDescription, int timeOutInSeconds) {
        waitFor(condition, contextDescription, timeOutInSeconds, WebDriverWait.DEFAULT_SLEEP_TIMEOUT);
    }

    protected void waitFor(BooleanCondition condition, String contextDescription, int timeOutInSeconds, long sleepDurationInMillis) {
        try {
            maybeDisableImplicitWait(condition);
            boolean waitComplete = false;
            long startTime = System.currentTimeMillis();
            while (!waitComplete) {
                try {
                    waitComplete = new WebDriverWait(_webDriver, timeOutInSeconds, sleepDurationInMillis).until(condition);
                } catch (TimeoutException e) {
                    throw e;
                } catch (WebDriverException e) {
                    // The web driver may throw an exception if we tried to evaluate something between page loads. So
                    // catch the exception and try again.
                    if (System.currentTimeMillis() - startTime > timeOutInSeconds * 1000) {
                        throw e;
                    }
                }
            }
        } finally {
            maybeEnableImplicitWait(condition);
        }
    }

    private void maybeEnableImplicitWait(BooleanCondition condition) {
        if (!condition.shouldImplicitlyWait()) {
            implicitlyWait(10);
        }
    }

    private void maybeDisableImplicitWait(BooleanCondition condition) {
        if (!condition.shouldImplicitlyWait()) {
            implicitlyWait(0);
        }
    }

    private void implicitlyWait(int durationInSeconds) {
        _webDriver.manage().timeouts().implicitlyWait(durationInSeconds, TimeUnit.SECONDS);
    }

    public void waitForElementToShow(WebElement webElement){
        _wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void clearFieldAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void clickElement(WebElement webElement){
        webElement.click();
    }

    public String getText(WebElement webElement){
        return webElement.getText();
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

    public PagesFactory withPage() {
        return _pagesFactory;
    }

    protected abstract BooleanCondition readyCondition();
}