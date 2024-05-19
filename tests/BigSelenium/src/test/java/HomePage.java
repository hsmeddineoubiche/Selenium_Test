import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class HomePage extends BasePage {
    
    private By newsLocator = By.xpath("/html/body/header/nav/div[1]/ul/li[7]/a");

    private By loginRedirectorBy = By.xpath("/html/body/header/div[1]/div[3]/ul/li[2]/a/span");
    private final By publicProfileSettingsButton = By.xpath("//*[contains(@href, '#/public-profile')]");
    private final By profileGreeting = By.xpath("/html/body/div[2]/div/main/section[1]/section/section[2]/div/div/div[2]/span");
    
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get(ConfigReader.getProperty("dashboardPage"));
    }

    public LoginPageTest clickLoginButton() {
        this.waitAndReturnElement(this.loginRedirectorBy).click();
        return new LoginPageTest(this.driver);
    }
    public StaticPage clickStaticPageToRedirect() {
        WebElement newsLocatorElement = this.waitAndReturnElement(this.newsLocator);
        newsLocatorElement.click();
        return new StaticPage(this.driver);
    }
    
}
