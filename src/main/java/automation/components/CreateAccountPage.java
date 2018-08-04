package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage extends BasePage {

    @FindBy(id = "firstname")
    WebElement firstNameField;

    @FindBy(id = "middlename")
    WebElement middleNameField;

    @FindBy(id = "lastname")
    WebElement lastNameField;

    @FindBy(id = "email_address")
    WebElement emailAddressField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "confirmation")
    WebElement passwordConfirmationField;

    @FindBy(css = ".account-create button")
    WebElement registerAccountButton;

    public CreateAccountPage(WebDriver webDriver, PagesFactory pagesFactory) {
        super(webDriver, pagesFactory);
    }

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(firstNameField);
    }

    public MyAccountPage fillFormAndSubmit(String firstname, String middlename, String lastname, String email, String password, String repeatedPassword) {
        clearFieldAndSendKeys(firstNameField, firstname);
        clearFieldAndSendKeys(middleNameField, middlename);
        clearFieldAndSendKeys(lastNameField, lastname);
        clearFieldAndSendKeys(emailAddressField, ((int)(Math.random()*12345))+email);
        clearFieldAndSendKeys(passwordField, password);
        clearFieldAndSendKeys(passwordConfirmationField, repeatedPassword);
        clickElement(registerAccountButton);
        return withPage().myAccountPage();
    }
}
