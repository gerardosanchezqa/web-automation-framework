package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(name = "uid")
    @CacheLookup
    WebElement emailInput;

    @FindBy(name = "password")
    @CacheLookup
    WebElement passwordInput;

    @FindBy(name = "btnLogin")
    @CacheLookup
    WebElement loginButton;

    public LoginPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    public ManagerHomePage login(String email, String password){
        writeEmail(emailInput, email);
        writePassword(passwordInput, password);
        submitLogin(loginButton);
        return new ManagerHomePage(getWebDriver(), this);
    }

    private void writeEmail(WebElement webElement, String email) {
        clearFieldAndSendKeys(webElement, email);
    }


    private void writePassword(WebElement webElement, String password) {
        clearFieldAndSendKeys(webElement, password);
    }

    private ManagerHomePage submitLogin(WebElement webElement) {
        clickElement(webElement);
        return new ManagerHomePage(getWebDriver(), this);
    }
}