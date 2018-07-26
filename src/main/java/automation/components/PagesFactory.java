package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PagesFactory {
    private WebDriver _webDriver;

    public PagesFactory(WebDriver driver){
        _webDriver = driver;
        PageFactory.initElements(_webDriver, this);
    }

    public LoginPage loginPage(){
        return new LoginPage(getWebDriver(), this);
    }

    public ManagerHomePage managerHomePage(){
        return new ManagerHomePage(getWebDriver(), this);
    }

    public WebDriver getWebDriver() {
        return _webDriver;
    }
}
