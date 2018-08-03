package automation.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Conditions {

    private Conditions() {
    }

    public static BooleanCondition elementPresent(final WebElement webElement) {
        return elementPresentImplicitlyWait(webElement, false);
}

    public static BooleanCondition elementPresentImplicitlyWait(final WebElement webElement) {
        return elementPresentImplicitlyWait(webElement, true);
    }

    private static BooleanCondition elementPresentImplicitlyWait(final WebElement webElement, final boolean shouldImplicitlyWait) {
        return new BooleanCondition() {
            @Override
            public Boolean apply(WebDriver input) {
                return webElement.isDisplayed();
            }

            @Override
            public String describeFailure() {
                return String.format("page did not contain the desired element");
            }

            @Override
            public boolean shouldImplicitlyWait() {
                return shouldImplicitlyWait;
            }
        };
    }

}