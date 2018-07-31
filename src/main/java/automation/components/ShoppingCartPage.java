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
    @CacheLookup
    WebElement  mobileUpdateQuantityButton;

    @FindBy(css=".error-msg")
    @CacheLookup
    WebElement errorMessage;

    @FindBy(css=".btn-empty")
    @CacheLookup
    WebElement emptyCartLink;

    @FindBy(css=".cart-empty")
    @CacheLookup
    WebElement cartEmptyIndicator;

    public ShoppingCartPage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

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
