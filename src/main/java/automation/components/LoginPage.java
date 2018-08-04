package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(css = ".new-users a")
    WebElement createAccountButton;

    @FindBy(id = "email")
    WebElement loginEmailField;

    @FindBy(id = "pass")
    WebElement loginPasswordField;

    @FindBy(id = "send2")
    WebElement loginButton;

    public LoginPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(createAccountButton);
    }

    public CreateAccountPage clickCreateAccountbutton() {
        clickElement(createAccountButton);
        return withPage().createAccountPage();
    }

    public MyAccountPage loginWith(String email, String password) {
        addEmail(email);
        addPassword(password);
        clickLoginButton();
        return withPage().myAccountPage();
    }

    public void addEmail(String email){
        clearFieldAndSendKeys(loginEmailField, email);
    }

    public void addPassword(String password){
        clearFieldAndSendKeys(loginPasswordField, password);
    }

    public void clickLoginButton(){
        clickElement(loginButton);
    }
}