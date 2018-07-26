import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ClickAllLinksTest{

    public static void main(String args[]) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Gera\\IdeaProjects\\trainingautomation\\IdeaProjects\\trainingautomation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/newtours/");

        String underConstructionTitle = "Under Construction: Mercury Tours";
        List<WebElement> allLinksList = driver.findElements(By.cssSelector("tbody a"));

        String[] allLinksText = new String[allLinksList.size()];

        int i = 0;
        for (WebElement e : allLinksList){
            allLinksText[i] = e.getText();
            i++;
        }

        for (String t : allLinksText){
            driver.findElement(By.linkText(t)).click();
            if (driver.getTitle().equals(underConstructionTitle)){
                System.out.println("\"" + t + "\"" + " is under construction.");
            }else {
                System.out.println("\"" + t + "\"" + " is working.");
            }

            driver.navigate().back();
        }
        //driver.close();
    }
}