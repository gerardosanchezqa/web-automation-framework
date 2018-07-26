package onlySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownTest{

    public static void main(String args[]) {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Gera\\IdeaProjects\\trainingautomation\\IdeaProjects\\trainingautomation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        Select countryDropdown= new Select(driver.findElement(By.name("country")));

        countryDropdown.selectByVisibleText("ANGOLA");

        //driver.close();
    }
}




