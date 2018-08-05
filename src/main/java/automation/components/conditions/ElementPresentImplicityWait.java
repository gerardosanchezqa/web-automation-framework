package automation.components.conditions;

import automation.components.BooleanCondition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementPresentImplicityWait extends BooleanCondition {
    private boolean _shouldImplicitlyWait;
    private WebElement _webElement;

    public ElementPresentImplicityWait(WebElement webElement, boolean shouldImplicitlyWait) {
        _shouldImplicitlyWait = shouldImplicitlyWait;
        _webElement = webElement;
    }

    @Override
    public Boolean apply(WebDriver input) {
        return _webElement.isDisplayed();
    }

    @Override
    public String describeFailure() {
        return "page did not contain the desired element";
    }

    @Override
    public boolean shouldImplicitlyWait() {
        return _shouldImplicitlyWait;
    }
}
