import automation.components.HomePage;
import automation.components.MobilePage;
import automation.components.PagesFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTestCase extends DataProviders{

    public WebDriver webDriver;
    public PagesFactory pagesFactory;
    HomePage homePage;
    MobilePage mobilePage;

    @BeforeTest
    public void before(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        PagesFactory pagesFactory = new PagesFactory(webDriver);
    }

    public HomePage goToWebsite(String website) {
        webDriver.get(website);
        return new HomePage(webDriver, pagesFactory);
    }


    @AfterTest
    public void after() {
        webDriver.quit();
    }

}