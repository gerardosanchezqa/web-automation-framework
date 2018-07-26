package onlySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ImageLinksTest{

    public static void main(String args[]) {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Gera\\IdeaProjects\\trainingautomation\\IdeaProjects\\trainingautomation\\chromedriver.exe");

        String baseUrl = "https://www.facebook.com/login/identify?ctx=recover";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.get(baseUrl);

        driver.findElement(By.cssSelector("a[title=\"Go to Facebook Home\"]")).click();

        if(driver.getTitle().equals("Facebook - Log In or Sign Up")){
            System.out.println("We're back to Facebook's homepage");
        }else{
            System.out.println("We are NOT in Facebook's homepage");
        }

        driver.close();
    }
}