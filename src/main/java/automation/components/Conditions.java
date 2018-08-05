package automation.components;

import automation.components.conditions.ElementPresentImplicityWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Conditions {

    private Conditions() {
    }

    public static BooleanCondition elementPresent(final WebElement webElement) {
        return new ElementPresentImplicityWait(webElement, false);
}

    public static BooleanCondition elementPresentImplicitlyWait(final WebElement webElement) {
        return new ElementPresentImplicityWait(webElement, true);
    }

}