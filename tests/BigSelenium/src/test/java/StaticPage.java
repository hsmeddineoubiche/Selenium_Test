import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class StaticPage extends BasePage {
    

    
    public StaticPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.allrecipes.com/food-news-trends/");
    }


 

}
