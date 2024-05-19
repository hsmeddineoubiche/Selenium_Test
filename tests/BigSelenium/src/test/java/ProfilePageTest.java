import java.util.List;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

class ProfilePageTest extends BasePage {

    private final By publicProfileSettingsButton = By.xpath("//*[contains(@href, '#/public-profile')]");

    
    public ProfilePageTest(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.allrecipes.com/account/profile/");
    }

    public String readPageTitle(){
        return driver.getTitle();
    }

    public SettingsPageTest settings(){
        WebElement settingsButton = this.waitAndReturnElement(this.publicProfileSettingsButton);
        settingsButton.click();
        return new SettingsPageTest(this.driver);
    }


}
