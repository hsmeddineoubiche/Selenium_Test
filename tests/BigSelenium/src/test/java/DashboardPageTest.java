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
import org.openqa.selenium.interactions.Actions; 

class DashboardPageTest extends BasePage {


    private final By myProfileLocator = By.xpath("/html/body/header/div[1]/div[3]/ul/li[3]/div/ul/li[2]/a");
    private final By logoLocator = By.xpath("/html/body/header/nav/div/div/div[1]/a[2]/span[2]");
    private final By logoutButton = By.xpath("//a[contains(text(),' Log Out')]");
    private final By profileDropDown = By.xpath("/html/body/header/div[1]/div[3]/ul/li[3]/button/span");
	
    public DashboardPageTest(WebDriver driver) {
        super(driver);
        this.driver.get(ConfigReader.getProperty("dashboardPage"));
    }


    public ProfilePageTest clickDashboardToRedirect() {
        //Hover test
        WebElement profileDropdownMenu = this.waitAndReturnElement(this.profileDropDown);
        Actions actions = new Actions(driver); 
        actions.moveToElement(profileDropdownMenu).perform(); 
        this.waitAndReturnElement(myProfileLocator).click();


        return new ProfilePageTest(this.driver);
    }
    public HomePage logout() {
        WebElement logoLocatorElement = this.waitAndReturnElement(this.logoLocator);
        logoLocatorElement.click();

        driver.navigate().refresh();
        //Hover test
        WebElement profileDropdownMenuElement = this.waitAndReturnElement(this.profileDropDown);
        Actions actions2 = new Actions(driver); 
        actions2.moveToElement(profileDropdownMenuElement).perform();

        WebElement logoutButtonElement = this.waitAndReturnElement(this.logoutButton);
        logoutButtonElement.click();
        return new HomePage(this.driver);
    }


}
