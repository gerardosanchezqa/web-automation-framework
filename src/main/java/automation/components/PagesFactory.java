package automation.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PagesFactory {
    private WebDriver _webDriver;

    public PagesFactory(WebDriver driver){
        _webDriver = driver;
        PageFactory.initElements(_webDriver, this);
    }

    public HomePage loginPage(){
        return new HomePage(getWebDriver(), this);
    }

    public MobilePage mobilePage(){
        return new MobilePage(getWebDriver(), this);
    }

    public MobilePage mobileDetailsPage(){
        return new MobilePage(getWebDriver(), this);
    }

    public WebDriver getWebDriver() {
        return _webDriver;
    }
}
