package automation.components;

import org.apache.xerces.xs.StringList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage{

    @FindBy(css=".product-cart-actions .qty")
    WebElement mobileUpdateQuantityInput;

    @FindBy(css=".product-cart-actions .btn-update")
    WebElement  mobileUpdateQuantityButton;

    @FindBy(css=".error-msg")
    WebElement errorMessage;

    @FindBy(css=".btn-empty")
    WebElement emptyCartLink;

    @FindBy(css=".cart-empty")
    WebElement cartEmptyIndicator;

    public ShoppingCartPage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(mobileUpdateQuantityInput);
    }


    public void updateQuantityAndUpdate(String quantity){
        clearFieldAndSendKeys(mobileUpdateQuantityInput, quantity);
        clickElement(mobileUpdateQuantityButton);
    }

    public String getProductQuantity() {
        return mobileUpdateQuantityInput.getAttribute("value");
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public void clickEmptyCartLink(){
        clickElement(emptyCartLink);
    }

    public boolean isShoppingCartEmpty() {
        return cartEmptyIndicator.isDisplayed();
    }

}
