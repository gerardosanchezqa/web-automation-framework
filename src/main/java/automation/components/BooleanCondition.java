package automation.components;

import org.openqa.selenium.support.ui.ExpectedCondition;

public abstract class BooleanCondition implements ExpectedCondition<Boolean> {

    public abstract String describeFailure();

    public String toString() {
        return describeFailure();
    }

    public boolean shouldImplicitlyWait() {
        return true;
    }
}