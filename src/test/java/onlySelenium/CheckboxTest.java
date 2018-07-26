package onlySelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckboxTest {

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Gera\\IdeaProjects\\trainingautomation\\IdeaProjects\\trainingautomation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/radio.html");
        WebElement checkbox1 = driver.findElement(By.id("vfb-6-0"));
        WebElement checkbox2 = driver.findElement(By.id("vfb-6-2"));

        checkbox1.click();

        if(checkbox1.isSelected()){
            System.out.println("Checkbox is selected");
        }else{
            System.out.println("Checkbox isn't selected");
        }

        checkbox1.click();

        if(!checkbox1.isSelected()){
            System.out.println("Checkbox is toggled off");
        }

        driver.close();
    }
}




