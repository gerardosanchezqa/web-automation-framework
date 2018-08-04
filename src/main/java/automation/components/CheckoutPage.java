package automation.components;

import com.thoughtworks.selenium.webdriven.commands.Check;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    @FindBy(css ="#opc-billing")
    WebElement billingInformation;


    @FindBy(css ="#billing-buttons-container button")
    WebElement shippingInformationContinueButton;

    @FindBy(css ="#shipping-method-buttons-container button")
    WebElement shippingMethodContinueButton;

    @FindBy(css ="#payment-buttons-container button")
    WebElement paymentInformationContinueButton;

    @FindBy(css = "#review-buttons-container button")
    WebElement placeOrderButton;

    @FindBy(css=".sp-methods .price")
    WebElement shippingPrice;

    @FindBy(xpath= "//*[@id=\"billing:street1\"]")
    WebElement addressField;

    @FindBy(xpath= "//*[@id=\"billing:city\"]")
    WebElement cityField;

    @FindBy(xpath ="//*[@id=\"billing:postcode\"]")
    WebElement zipCodeField;

    @FindBy(xpath ="//*[@id=\"billing:region_id\"]")
    WebElement stateDropdown;

    @FindBy(xpath ="//*[@id=\"billing:country_id\"]")
    WebElement countryDropdown;

    @FindBy(xpath= "//*[@id=\"billing:telephone\"]")
    WebElement telephoneField;

    @FindBy(id="p_method_checkmo")
    WebElement checkMoneyRadioButton;

    @FindBy(xpath= "//*[@class=\"col-main\"]/p[1]/a")
    WebElement orderNumber;

    @FindBy(xpath= "//*[@id=\"checkout-review-table\"]/tfoot/tr[3]/td[2]/strong/span")
    WebElement totalPrice;

    public CheckoutPage(WebDriver webDriver, PagesFactory pagesFactory){ super(webDriver, pagesFactory);}

    @Override
    protected BooleanCondition readyCondition() {
        return Conditions.elementPresent(billingInformation);
    }

    public CheckoutPage addShippingInformation(String country, String state, String zipcode, String address, String city, String telephone) {
        selectElementFromDropdown(countryDropdown, country);
        selectElementFromDropdown(stateDropdown, state);
        clearFieldAndSendKeys(zipCodeField, zipcode);
        clearFieldAndSendKeys(addressField, address);
        clearFieldAndSendKeys(cityField, city);
        clearFieldAndSendKeys(telephoneField, telephone);
        return withPage().checkoutPage();
    }

    public void clickShippingInformationContinueButton() {
        clickElement(shippingInformationContinueButton);
    }

    public void clickShippingMethodContinueButton() {
        clickElement(shippingMethodContinueButton);
    }

    public void clickPaymentInformationContinueButton() {
        clickElement(paymentInformationContinueButton);
    }

    public void clickPlaceOrderButton() {
        clickElement(placeOrderButton);
    }

    public int getShippingPrice() {
        return parseStringToInt(shippingPrice.getText());
    }

    public void selectCheckAndMoneyRadioButton() {
        clickElement(checkMoneyRadioButton);
    }

    public String getOrderNumber(){
        return orderNumber.getText();
    }

    public int getTotal() {
        return parseStringToInt(totalPrice.getText());
    }
}
